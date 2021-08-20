package com.globits.hiv.extraction.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseStatus;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.utils.CoreUtils;
import com.globits.fhir.hiv.utils.HivConst;
import com.globits.hiv.extraction.dto.PatientInfoDto;
import com.globits.hiv.extraction.service.impl.HAPIFhirService;
import com.globits.hiv.extraction.service.impl.QRExtractionServiceImpl;
import com.globits.hiv.extraction.utils.QRConvertUtil;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
@RestController
@RequestMapping("/public/extraction")
public class QuestionnaireExtractionController {
	//private  Boolean IsUsingEncodeData=HivConst.IsUsingEncodeData;
	@RequestMapping(path ="/encode",method = RequestMethod.POST, produces = { "text/plain; charset=utf-8" })
	public String postEncode(@RequestBody String body) throws UnsupportedEncodingException{
		//return Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
		String result = Base64.getMimeEncoder().encodeToString(body.getBytes(StandardCharsets.UTF_8));
		System.out.println(result.length());
		return result;
	}
	
	@RequestMapping(path ="/decode",method = RequestMethod.POST, produces = { "text/plain; charset=utf-8" })
	public ResponseEntity<String> postDecode(@RequestBody String body) throws UnsupportedEncodingException{
		//String result = new String (Base64.getMimeDecoder().decode(body),StandardCharsets.UTF_8);
		System.out.println(body.length());
		String result = new String (Base64.getMimeDecoder().decode(body),StandardCharsets.UTF_8);
		System.out.println(result);
		return new ResponseEntity<String>(result, HttpStatus.OK);
		//return new String ("Bùi Văn Thành");
	}
	
	@RequestMapping(path ="/receive",method = RequestMethod.POST, produces = { "text/plain; charset=utf-8" })
	//@RequestMapping(path ="/receive",method = RequestMethod.POST)
	public String postListQuestionnaireResponse(@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		
		if(HivConst.IsUsingEncodeData) {
			body = CoreUtils.decode(body);
		}

//		System.out.println("Body:"+body);
//		System.out.println("BodyLength:"+body.length());
		Bundle bundle = (Bundle)HAPIFhirService.parseResource(body);
		
		////Convert Bundle to QR and POST to Fhir
		List<QuestionnaireResponse> listQuestionnaireResponse= (List<QuestionnaireResponse>)HAPIFhirService.bundleToList(bundle);
		String result = new String();
		for (QuestionnaireResponse questionnaireResponse : listQuestionnaireResponse) {
			PatientInfoDto patientInfoDto = QRConvertUtil.convertToBundle(questionnaireResponse);
			Bundle bundlee = QRExtractionServiceImpl.savePatientTransaction(patientInfoDto.getPatient(), patientInfoDto.getChildren(),patientInfoDto.getListCoMorbidity(),patientInfoDto.getPregnancies(),patientInfoDto.getArvTreatmentDto());
			parser.setPrettyPrint(true);
			result = result + " " + bundlee.getEntry().get(0).getResponse().getLocation();
		  }
		return "Post patient successful " + result ;
	}
}
