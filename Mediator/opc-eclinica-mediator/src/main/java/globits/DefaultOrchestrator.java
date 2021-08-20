package globits;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.r4.model.Bundle;
import org.openhim.mediator.engine.MediatorConfig;
import org.openhim.mediator.engine.messages.MediatorHTTPRequest;
import org.openhim.mediator.engine.messages.MediatorHTTPResponse;

import com.globits.fhir.hiv.utils.CoreUtils;
import com.globits.fhir.hiv.utils.HivConst;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import dto.AdapterObjectDto;
import dto.PatientDto;
import service.OpcEclinicaService;
import types.AdapterObjectType;

public class DefaultOrchestrator extends UntypedActor {
	private Boolean IsUsingEncodeData=HivConst.IsUsingEncodeData;
	private Boolean IsClientEncodeData=false;
	OpcEclinicaService opcEclinicaService = new OpcEclinicaService();
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private String extractionHost="localhost";
	private String extractionFunction="/fhir/public/extraction/receive";
	private int extractionPort=3443;
	private final MediatorConfig config;

	private MediatorHTTPRequest originalRequest;

	public DefaultOrchestrator(MediatorConfig config) {
		this.config = config;
		if(config.getProperty("extraction.host")!=null) {
			this.extractionHost=config.getProperty("extraction.host");
		}
		if(config.getProperty("extraction.port")!=null) {
			this.extractionPort=Integer.parseInt(config.getProperty("extraction.port"));	
		}
		if(config.getProperty("extraction.function")!=null) {
			this.extractionFunction=config.getProperty("extraction.function");	
		}
		if(config.getProperty("server.usingencode")!=null) {
			this.IsUsingEncodeData=Boolean.valueOf(config.getProperty("server.usingencode"));	
		}
		if(config.getProperty("client.usingencode")!=null) {
			this.IsClientEncodeData=Boolean.valueOf(config.getProperty("client.usingencode"));	
		}	
	}

	private void queryHealthRecordService(MediatorHTTPRequest request) {
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		log.info("Querying the health record service");
		originalRequest = request;

		ActorSelection httpConnector = getContext().actorSelection(config.userPathFor("http-connector"));
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "text/plain");
		headers.put("authorization", "Basic aa=");
		headers.put("Content-Type", "text/plain;charset=UTF-8");
		request.getHeaders().put("Content-Type", "text/plain;charset=UTF-8");
		String body = request.getBody();
		System.out.println("Body:"+body);
		if(IsClientEncodeData) {
			body = CoreUtils.decode(body);
		}
		
		Gson gson = new Gson();
		Type type = new TypeToken<AdapterObjectDto>() {
		}.getType();
		List<PatientDto> list = new ArrayList<PatientDto>();
		AdapterObjectDto object = gson.fromJson(body, type);
		if (object.getObjectType() == AdapterObjectType.SINGLE_DATA) {
			list.add(object.getSingleData());
		}
		if (object.getObjectType() == AdapterObjectType.LIST_DATA) {
			list = object.getListData();
		}
		if (list != null && list.size() > 0) {
			if (list.size() > 10) {
				if ((list.size() % 10) == 0) {
					int part = ((int) list.size() / 10);
					for (int i = 0; i < part; i++) {
						List<PatientDto> subListtOpcAssistDto = list.subList(10 * i, 10 * (i+1));
						Bundle questionnaireResponseBundle = opcEclinicaService
								.convertListOpcObjectToBundle(subListtOpcAssistDto);
						String questionnaireResponseBody = parser.encodeResourceToString(questionnaireResponseBundle);
						if(IsUsingEncodeData) {
							//questionnaireResponseBody = Base64.getMimeEncoder().encodeToString(questionnaireResponseBody.getBytes(StandardCharsets.UTF_8));
							questionnaireResponseBody = CoreUtils.encode(questionnaireResponseBody);
						}
						MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(request.getRequestHandler(),
								getSelf(), "GLOBITS Health Record Service", request.getMethod(), "http", this.extractionHost,
								this.extractionPort, this.extractionFunction, questionnaireResponseBody, headers, null);
						httpConnector.tell(serviceRequest, getSelf());

					}

				} else {
					int part = ((int) list.size() / 10) + 1;
					for (int i = 0; i < part; i++) {
						List<PatientDto> subListtOpcAssistDto = new ArrayList<PatientDto>();
						if (i < (part-1)) {
							subListtOpcAssistDto = list.subList(10 * i, 10 * (i+1));
						} else {
							subListtOpcAssistDto = list.subList(10 *i, list.size());
						}
						Bundle questionnaireResponseBundle = opcEclinicaService
								.convertListOpcObjectToBundle(subListtOpcAssistDto);
						String questionnaireResponseBody = parser.encodeResourceToString(questionnaireResponseBundle);
						if(IsUsingEncodeData) {
							//questionnaireResponseBody = Base64.getMimeEncoder().encodeToString(questionnaireResponseBody.getBytes(StandardCharsets.UTF_8));
							questionnaireResponseBody = CoreUtils.encode(questionnaireResponseBody);
						}
						MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(request.getRequestHandler(),
								getSelf(), "GLOBITS Health Record Service", request.getMethod(), "http", this.extractionHost,
								this.extractionPort, this.extractionFunction, questionnaireResponseBody, headers, null);
						httpConnector.tell(serviceRequest, getSelf());
					}
				}

			} else {
				Bundle questionnaireResponseBundle = opcEclinicaService.convertListOpcObjectToBundle(list);
				String questionnaireResponseBody = parser.encodeResourceToString(questionnaireResponseBundle);
				if(IsUsingEncodeData) {
					//questionnaireResponseBody = Base64.getMimeEncoder().encodeToString(questionnaireResponseBody.getBytes(StandardCharsets.UTF_8));
					questionnaireResponseBody = CoreUtils.encode(questionnaireResponseBody);
				}
				MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(request.getRequestHandler(),
						getSelf(), "GLOBITS Health Record Service", request.getMethod(), "http", this.extractionHost,
						this.extractionPort, this.extractionFunction, questionnaireResponseBody, headers, null);
				httpConnector.tell(serviceRequest, getSelf());
			}
		}

//		Bundle questionnaireResponseBundle = opcEclinicaService.convertListOpcObjectToBundle(list);
//
//		String questionnaireResponseBody = parser.encodeResourceToString(questionnaireResponseBundle);
//		// System.out.println(questionnaireResponseBody);
//		System.out.println("Questionnaire Response: " + questionnaireResponseBody);
//
//		// String questionnaireResponseBody = new
//		// Gson().toJson(questionnaireResponseBundle);
//
////        extractionPath ="/fhir/public/ocpassist/list";
//		extractionPath = "/fhir/public/extraction/receive";
//		headers.put("Content-Type", "application/json; charset=utf-8");
//		MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(request.getRequestHandler(), getSelf(),
//				"GLOBITS Health Record Service", request.getMethod(), "http", "localhost", 3443, extractionPath,
//				questionnaireResponseBody, headers, null);
//
//		// System.out.println(questionnairesBody);
//
//		httpConnector.tell(serviceRequest, getSelf());

	}

	private void processHealthRecordServiceResponse(MediatorHTTPResponse response) {
		log.info("Received response from health record service");
		originalRequest.getRespondTo().tell(response.toFinishRequest(), getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
//        if (msg instanceof MediatorHTTPRequest) {
//            FinishRequest finishRequest = new FinishRequest("A message from my new mediator!", "text/plain", HttpStatus.SC_OK);
//            ((MediatorHTTPRequest) msg).getRequestHandler().tell(finishRequest, getSelf());
//        } else {
//            unhandled(msg);
//        }

		if (msg instanceof MediatorHTTPRequest) {
			queryHealthRecordService((MediatorHTTPRequest) msg);
		} else if (msg instanceof MediatorHTTPResponse) {
			processHealthRecordServiceResponse((MediatorHTTPResponse) msg);
		} else {
			unhandled(msg);
		}
	}
}
