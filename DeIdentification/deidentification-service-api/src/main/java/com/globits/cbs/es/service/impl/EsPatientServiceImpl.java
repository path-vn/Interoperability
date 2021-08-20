package com.globits.cbs.es.service.impl;
import static com.globits.cbs.es.util.Constant.INDEX;
import static com.globits.cbs.es.util.Constant.PATIENT_INDEX;
import static com.globits.cbs.es.util.Constant.TYPE;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globits.cbs.deidentification.ServerConst.SyncStatusResult;
import com.globits.cbs.deidentification.domain.SyncLog;
import com.globits.cbs.deidentification.dto.DeIdentificationConfigDto;
import com.globits.cbs.deidentification.dto.PatientDto;
import com.globits.cbs.deidentification.dto.SampleDto;
import com.globits.cbs.deidentification.dto.SyncResultDto;
import com.globits.cbs.deidentification.functiondto.EsSearchDto;
import com.globits.cbs.deidentification.service.DeIdentificationConfigService;
import com.globits.cbs.deidentification.service.PatientService;
import com.globits.cbs.deidentification.service.SyncLogService;
import com.globits.cbs.deidentification.service.fhir.impl.PatientFhirServiceImpl;
import com.globits.cbs.es.document.ProfileDocument;
import com.globits.cbs.es.service.EsPatientService;
import com.globits.cbs.es.util.Constant;
import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.service.impl.HAPIFhirService;
import com.google.gson.Gson;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
@Service
public class EsPatientServiceImpl implements EsPatientService{
	static FhirContext ctx = FhirContext.forR4();
	static IParser jsonParser = ctx.newJsonParser();
    private RestHighLevelClient client;
    private ObjectMapper objectMapper;
    private static Field[] allFields = PatientDto.class.getDeclaredFields();
	@Autowired
	private PatientService service;
	@Autowired
	private DeIdentificationConfigService configService;
	
	@Autowired
	private SyncLogService syncLogService;
	
    private SearchRequest buildSearchRequest(String index, String type) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        return searchRequest;
    }
    private List<PatientDto> getSearchResult(SearchResponse response) {
        SearchHit[] searchHit = response.getHits().getHits();

        List<PatientDto> patients = new ArrayList<>();

        for (SearchHit hit : searchHit){
        	patients.add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), PatientDto.class));
        }

        return patients;
    } 	
    
    private Page<PatientDto> getSearchResultByPage(SearchResponse response, int pageIndex, int pageSize) {
    	long count = response.getHits().getTotalHits().value;
        SearchHit[] searchHit = response.getHits().getHits();

        List<PatientDto> patients = new ArrayList<>();

        for (SearchHit hit : searchHit){
        	patients.add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), PatientDto.class));
        }
		Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
		Page<PatientDto> result = new PageImpl<PatientDto>(patients, pageable, count);
		
        return result;
    } 

    private PatientDto convertMapToPatientDto(Map<String, Object> map){
        return objectMapper.convertValue(map,PatientDto.class);
    }
    private Map<String, Object> convertPatientDtoToMap(PatientDto patient) {
        return objectMapper.convertValue(patient, Map.class);
    }
    @Autowired
    public EsPatientServiceImpl(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }
	@Override
	public Bundle getPatientEverythingById(String theId) {
		String theUrl = "Patient/"+theId+"/$everything";
		return HAPIFhirHivPatientService.getPatientEverythingById(theUrl);
	}
	@Override
	public Bundle getPatientByLastUpdate(int pageIndex, int pageSize, Date fromDate, Date toDate) {
		//HAPIFhirHivPatientService.serverBaseUrl="http://fhir.globits.net:8082/fhir";
		return HAPIFhirHivPatientService.getPatientByLastUpdate(pageIndex, pageSize, fromDate, toDate);
	}
	@Override
	public String savePatientBundleToES(Bundle patientEverything) {
	        UUID uuid = UUID.randomUUID();
	        String body =jsonParser.encodeResourceToString(patientEverything);
	        IndexRequest indexRequest = new IndexRequest(PATIENT_INDEX, TYPE, patientEverything.getIdElement().getIdPart()).source(body, XContentType.JSON); 
	        Gson gson = new Gson();
	        try {
				IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "Success";
	}
	private static Boolean isFieldInClass(String fieldName) {
		for(Field field:allFields) {
			if(field.getName().equals(fieldName)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public PatientDto deIdentification(PatientDto patient) {
		List<DeIdentificationConfigDto> configs= configService.getAll();
		if(configs!=null) {
			try {
				for(int i=0;i<configs.size();i++) {
						DeIdentificationConfigDto config = configs.get(i);
						if(config!=null && config.getCode()!=null) {
							if(isFieldInClass(config.getCode())) {
								Field  fieldByCode = patient.getClass().getDeclaredField(config.getCode());
								fieldByCode.setAccessible(true);
							    fieldByCode.set(patient, null);
							}
						}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return patient;
	}	

	@Override
	public String savePatientDtoToESById(String id) {
			PatientDto result = service.getPatient(id);
			result = deIdentification(result);
	        Gson gson = new Gson();
	        IndexRequest indexRequest = new IndexRequest(Constant.PATIENT_INDEX);
	        indexRequest.id(id);
	        indexRequest.source(convertPatientDtoToMap(result), XContentType.JSON);
	        try {
				IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			} catch (IOException e) {
		        SyncLog log = new SyncLog();
		        log.setPatientId(id);
				log.setSyncStatus(SyncStatusResult.Fail.getValue());
				syncLogService.save(log);
				e.printStackTrace();
			}
		return id;
	}


	@Override
	public PatientDto searchPatientFromESById(String theId) throws IOException {
        SearchRequest searchRequest = buildSearchRequest(Constant.PATIENT_INDEX,Constant.TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder  matchQueryBuilder = new MatchQueryBuilder("id", theId);
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.size(2);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        if(searchHits!=null && searchHits.length>0) {
            SearchHit hit = searchHits[0];
            PatientDto dto = objectMapper.convertValue(hit.getSourceAsMap(), PatientDto.class);
        	return dto;
        }
        return null;
	}
	@Override
	public PatientDto getPatientFromESById(String theId) throws IOException {
        GetRequest getRequest = new GetRequest(Constant.PATIENT_INDEX, theId);
    	//GetRequest getRequest = new GetRequest(INDEX);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        PatientDto dto = objectMapper.convertValue(getResponse.getSource(), PatientDto.class);
        return dto;
	}
	
	@Override
	public String getPatientFromESById(String index, String theId) throws IOException {
        GetRequest getRequest = new GetRequest(index, theId);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();
        return resultMap.toString();
	}
	@Override
	public String savePatientToESById(String theId) {
		Bundle bundle = this.getPatientEverythingById(theId);
		return savePatientBundleToES(bundle);
	}
	@Override
	public SyncResultDto synPatientDto(int pageIndex, int pageSize, Date fromDate, Date toDate) {
		SyncResultDto result =new SyncResultDto();
		
		Integer syncNumberPatient =0;
		Bundle bundle = this.getPatientByLastUpdate(pageIndex, pageSize,fromDate,toDate);
		result.setTotal(bundle.getTotal());
		List<HivPatient> patients = (List<HivPatient> )HAPIFhirService.bundleToList(bundle);
		for(HivPatient p : patients) {
			savePatientDtoToESById(p.getIdElement().getIdPart());
			syncNumberPatient++;
		}
		result.setSyncNumberPatient(syncNumberPatient);
		return result;
	}
	
	@Override
	public Integer synPatientDtoByDate(int pageIndex, int pageSize, String strFromDate, String strToDate) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate;
		int result=0;
		try {
			fromDate = sdf.parse(strFromDate);
			Date toDate = sdf.parse(strToDate);
			Bundle bundle = this.getPatientByLastUpdate(pageIndex, pageSize,fromDate,toDate);
			List<HivPatient> patients = (List<HivPatient> )HAPIFhirService.bundleToList(bundle);
			for(HivPatient p : patients) {
				savePatientDtoToESById(p.getIdElement().getIdPart());
				result++;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	@Override
	public String synPatientByDate(int pageIndex, int pageSize, String strFromDate, String strToDate) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate;
		String result ="[";
		try {
			fromDate = sdf.parse(strFromDate);
			Date toDate = sdf.parse(strToDate);
			Bundle bundle = this.getPatientByLastUpdate(pageIndex, pageSize,fromDate,toDate);
			List<HivPatient> patients = (List<HivPatient> )HAPIFhirService.bundleToList(bundle);
			for(HivPatient p : patients) {
		        Gson gson = new Gson();
		        String body = gson.toJson(p);
		        String id = p.getIdElement().getIdPart();
		        IndexRequest indexRequest = new IndexRequest(Constant.RESOURCE_INDEX, TYPE, id).source(body, XContentType.JSON);
		        result+=id+",";
		        try {
					IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			result+="]";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
		@Override
	   public List<PatientDto> findAllPatientDto() throws Exception {
        SearchRequest searchRequest = buildSearchRequest(PATIENT_INDEX,TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(20);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);

	        return getSearchResult(searchResponse);
	    }
		@Override
		public Page<PatientDto> findByPage(int pageIndex, int pageSize) throws Exception {
		int from = (pageIndex-1)*pageSize;
        SearchRequest searchRequest = buildSearchRequest(PATIENT_INDEX,TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.from(from);
        searchSourceBuilder.sort("lastUpdateDate",SortOrder.DESC);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);

	        return getSearchResultByPage(searchResponse,pageIndex,pageSize);
	    }		
		@Override
		public String deletePatientDto(String theId) throws Exception {
	        DeleteRequest deleteRequest = new DeleteRequest(PATIENT_INDEX, theId);
	        DeleteResponse response = client.delete(deleteRequest,RequestOptions.DEFAULT);
	        return response.getResult().name();
		}
		@Override
		public Boolean deleteIndex(String indexName) throws Exception {
			DeleteIndexRequest request = new DeleteIndexRequest(indexName);
			//DeleteResponse response = client.e(request,RequestOptions.DEFAULT);
			request.timeout(TimeValue.timeValueMinutes(2));
			AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);
			return deleteIndexResponse.isAcknowledged();
		}
		@Override
		public Page<PatientDto> searchByPage(EsSearchDto dto) throws Exception {
			if(dto != null) {
			int from =   (dto.getPageIndex()-1)*dto.getPageSize();
	        SearchRequest searchRequest = buildSearchRequest(PATIENT_INDEX,TYPE);
	        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	        searchSourceBuilder.size(dto.getPageSize());
	        searchSourceBuilder.from(from);
	        searchSourceBuilder.sort("lastUpdateDate",SortOrder.DESC);
	        searchSourceBuilder.trackTotalHits(true);
	        //searchSourceBuilder.sort(SortBuilders.fieldSort("id.keyword").order(SortOrder.DESC));
	        if(dto.getSyncOrg()==null) {
	        	searchSourceBuilder.query(QueryBuilders.matchAllQuery());
	        }
	        if(dto.getSyncOrg()!=null) {
	        	searchSourceBuilder.query(QueryBuilders.matchQuery( "lastUpdated.display",dto.getSyncOrg()));
	        }
	        searchRequest.source(searchSourceBuilder);

	        SearchResponse searchResponse =
	                client.search(searchRequest, RequestOptions.DEFAULT);

		        return getSearchResultByPage(searchResponse,dto.getPageIndex(),dto.getPageSize());
		    }
			return null;
		}
		@Override
		public String savePatientDtoToES(PatientDto dto) {
			PatientDto result = dto;
			result = deIdentification(result);
	        Gson gson = new Gson();
	        IndexRequest indexRequest = new IndexRequest(Constant.PATIENT_INDEX);
	        indexRequest.id(dto.getId());
	        indexRequest.source(convertPatientDtoToMap(result), XContentType.JSON);
	        try {
				IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	        return result.getId();
		}
}
