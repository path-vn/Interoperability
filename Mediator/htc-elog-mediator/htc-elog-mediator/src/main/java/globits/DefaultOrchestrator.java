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
import dto.HtcElogDto;
import dto.OpcAssistDto;
import service.HtcElogService;
import types.AdapterObjectType;

public class DefaultOrchestrator extends UntypedActor {
	private Boolean IsUsingEncodeData=HivConst.IsUsingEncodeData;
	private Boolean IsClientEncodeData=false;

	HtcElogService htcElogService = new HtcElogService();
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

		//// Add Basic Authentication to Header
		ActorSelection httpConnector = getContext().actorSelection(config.userPathFor("http-connector"));
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "text/plain");
		//headers.put("content-type", "text/plain");
		headers.put("authorization", "Basic aa=");
		headers.put("Content-Type", "text/plain;charset=UTF-8");
//		String extractionPath = "/fhir/public" + request.getPath().replace("/fhir", "");
		request.getHeaders().put("Content-Type", "text/plain;charset=UTF-8");
		String body = request.getBody();
//		System.out.println("BodyLength:"+body.length());
//		System.out.println("Body:"+body);
//		String decodedString = new String(Base64.getMimeDecoder().decode(body.getBytes(StandardCharsets.UTF_8)));
//		System.out.println("BodyLengthDecode:"+decodedString.length());

		if(IsClientEncodeData) {
			body = CoreUtils.decode(body);
		}
			
		Gson gson = new Gson();
		Type type = new TypeToken<AdapterObjectDto>() {
		}.getType();

		List<HtcElogDto> listHtcElogDto = new ArrayList<HtcElogDto>();
		AdapterObjectDto object = gson.fromJson(body, type);
		if(object!=null&&object.getListData()!=null&&object.getListData().size()>0) {
			listHtcElogDto = object.getListData();
		}
		//// Check if ListData's size more than 10 QR, then split into parts of 10 QR
		if (listHtcElogDto != null && listHtcElogDto.size() > 0) {
			if (listHtcElogDto.size() > 10) {
				if ((listHtcElogDto.size() % 10) == 0) {
					int part = ((int) listHtcElogDto.size() / 10);
					for (int i = 0; i < part; i++) {
						List<HtcElogDto> subListHtcElogDto = listHtcElogDto.subList(10 * i, 10 * (i + 1));
						Bundle questionnaireResponseBundle = htcElogService
								.convertListHtcElogObjectToBundle(subListHtcElogDto);
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
					int part = ((int) listHtcElogDto.size() / 10) + 1;
					for (int i = 0; i < part; i++) {
						List<HtcElogDto> subListHtcElogDto = new ArrayList<HtcElogDto>();
						if (i < (part - 1)) {
							subListHtcElogDto = listHtcElogDto.subList(10 * i, 10 * (i + 1));
						} else {
							subListHtcElogDto = listHtcElogDto.subList(10 * i, listHtcElogDto.size());
						}
						Bundle questionnaireResponseBundle = htcElogService
								.convertListHtcElogObjectToBundle(subListHtcElogDto);
						String questionnaireResponseBody = parser.encodeResourceToString(questionnaireResponseBundle);
						
						if(IsUsingEncodeData) {
							//questionnaireResponseBody = Base64.getMimeEncoder().encodeToString(questionnaireResponseBody.getBytes(StandardCharsets.UTF_8));
							questionnaireResponseBody = CoreUtils.encode(questionnaireResponseBody);
						}
						
						MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(request.getRequestHandler(),
								getSelf(), "GLOBITS Health Record Service", request.getMethod(), "http", this.extractionHost,
								this.extractionPort, this.extractionFunction, questionnaireResponseBody, headers, null);
						httpConnector.tell(serviceRequest, getSelf());
						
//				        MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(
//				                request.getRequestHandler(),
//				                getSelf(),
//				                "GLOBITS Health Record Service",
//				                request.getMethod(),
//				                "http",
//				                "localhost",
//				                3443,
//				                "/fhir/public/test/encounters",
//				                questionnaireResponseBody,
//				                headers,
//				                null
//				        );
					}
				}
			} else {
//				System.out.println("fullname:"+listOpcAssistDto.get(0).getFullname());
				Bundle questionnaireResponseBundle = htcElogService.convertListHtcElogObjectToBundle(listHtcElogDto);
				String questionnaireResponseBody = parser.encodeResourceToString(questionnaireResponseBundle);
//				System.out.println("Questionnaire Response: " + questionnaireResponseBody);
//				MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(request.getRequestHandler(), getSelf(),
//						"GLOBITS Health Record Service", request.getMethod(), "http", this.extractionPath, this.extractionPort, extractionPath,
//						questionnaireResponseBody, headers, null);
				
//				System.out.println("questionaire:"+questionnaireResponseBody.length());
				
				if(IsUsingEncodeData) {
					//questionnaireResponseBody = Base64.getMimeEncoder().encodeToString(questionnaireResponseBody.getBytes(StandardCharsets.UTF_8));
					questionnaireResponseBody = CoreUtils.encode(questionnaireResponseBody);
				}

				MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(request.getRequestHandler(), getSelf(),
				"GLOBITS Health Record Service", request.getMethod(), "http", this.extractionHost, this.extractionPort, this.extractionFunction,
				questionnaireResponseBody, headers, null);
				httpConnector.tell(serviceRequest, getSelf());
			}
		}
	}

	private void processHealthRecordServiceResponse(MediatorHTTPResponse response) {
		log.info("Received response from health record service:"+originalRequest.getHost()+":"+originalRequest.getPath()+":"+originalRequest.getPort());
		originalRequest.getRespondTo().tell(response.toFinishRequest(), getSelf());
		//originalRequest.getRespondTo().tell(finishRequest, getSelf());
		
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
