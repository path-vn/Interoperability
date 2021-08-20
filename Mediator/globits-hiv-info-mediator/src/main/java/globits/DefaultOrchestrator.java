package globits;

import java.io.UnsupportedEncodingException;
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
import dto.HIVInfoDto;
import service.HivInfoService;

public class DefaultOrchestrator extends UntypedActor {
	private Boolean IsUsingEncodeData=HivConst.IsUsingEncodeData;
	private Boolean IsClientEncodeData=false;
	HivInfoService hivInfoService = new HivInfoService();
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	FhirContext ctx = FhirContext.forR4();
	IParser parser = ctx.newJsonParser();

    private final MediatorConfig config;
    
    private MediatorHTTPRequest originalRequest;

	String hostExtractionService = "localhost";
	Integer portExtractionService = 3443;

    public DefaultOrchestrator(MediatorConfig config) {
        this.config = config;
        if (config.getProperty("extraction-service.host") != null) {
            hostExtractionService = config.getProperty("extraction-service.host");
		}
        if (config.getProperty("extraction-service.port") != null) {
            portExtractionService = Integer.parseInt(config.getProperty("extraction-service.port"));
		}
		if(config.getProperty("server.usingencode")!=null) {
			this.IsUsingEncodeData=Boolean.valueOf(config.getProperty("server.usingencode"));	
		}
		if(config.getProperty("client.usingencode")!=null) {
			this.IsClientEncodeData=Boolean.valueOf(config.getProperty("client.usingencode"));	
		}	
    }
    
    private void queryHealthRecordService(MediatorHTTPRequest request) {
        log.info("Querying the health record service");
        originalRequest = request;

        String body = request.getBody();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<HIVInfoDto>>(){}.getType();  
        
        List<HIVInfoDto> listHIVInfoDto = gson.fromJson(body, listType);
        //this.postListToDataExtraction(request, listHIVInfoDto);
        this.postAllToDataExtraction(request, listHIVInfoDto);
    }

    private void postAllToDataExtraction(MediatorHTTPRequest request, List<HIVInfoDto> listHIVInfoDto) {
        ActorSelection httpConnector = getContext().actorSelection(config.userPathFor("http-connector"));
        String extractionPath = "/fhir/public"+request.getPath().replace("/fhir", "");
        Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "text/plain");
		headers.put("authorization", "Basic aa=");
		headers.put("Content-Type", "text/plain;charset=UTF-8");

    	Bundle questionnaireResponseBundle = hivInfoService.convertListObjectToBundle(listHIVInfoDto);
        
        String questionnaireResponseBody= parser.encodeResourceToString(questionnaireResponseBundle);
		if(IsUsingEncodeData) {
			questionnaireResponseBody = CoreUtils.encode(questionnaireResponseBody);
		}
        
		extractionPath ="/fhir/public/extraction/receive";
        MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(
                request.getRequestHandler(),
                getSelf(),
                "GLOBITS Health Record Service",
                request.getMethod(),
                "http",
                hostExtractionService,
                portExtractionService,
                extractionPath,
                questionnaireResponseBody,
                headers,
                null
        );

        httpConnector.tell(serviceRequest, getSelf());
	}

	private void postListToDataExtraction(MediatorHTTPRequest request, List<HIVInfoDto> listHIVInfoDto) {
        ActorSelection httpConnector = getContext().actorSelection(config.userPathFor("http-connector"));
        String extractionPath = "/fhir/public"+request.getPath().replace("/fhir", "");
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Basic aa=");
        
    	int pageNumber = 1;
        final int RESULTS_PER_PAGE = 10;
        int totalPage = listHIVInfoDto.size() / RESULTS_PER_PAGE;
        if((listHIVInfoDto.size() % RESULTS_PER_PAGE) > 0){
        	totalPage++;
        }
        for (int i = pageNumber; i <= totalPage; i++) {
        	// algorithm
        	int to = pageNumber * RESULTS_PER_PAGE;
        	int from = to - RESULTS_PER_PAGE;
            if(to > listHIVInfoDto.size()){
            	to = listHIVInfoDto.size();
            }
        	List<HIVInfoDto> listNew = listHIVInfoDto.subList(from, to);

        	Bundle questionnaireResponseBundle = hivInfoService.convertListObjectToBundle(listNew);
            
            String questionnaireResponseBody= parser.encodeResourceToString(questionnaireResponseBundle);
    		if(IsUsingEncodeData) {
    			questionnaireResponseBody = CoreUtils.encode(questionnaireResponseBody);
    		}
            
    		extractionPath ="/fhir/public/extraction/receive";
            MediatorHTTPRequest serviceRequest = new MediatorHTTPRequest(
                    request.getRequestHandler(),
                    getSelf(),
                    "GLOBITS Health Record Service",
                    request.getMethod(),
                    "http",
                    hostExtractionService,
                    portExtractionService,
                    extractionPath,
                    questionnaireResponseBody,
                    headers,
                    null
            );

            httpConnector.tell(serviceRequest, getSelf());
            pageNumber++;
        }
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
