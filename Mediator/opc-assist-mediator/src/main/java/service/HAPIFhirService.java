package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Resource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;

public class HAPIFhirService {
	
	public static String encodeResourceToString(IBaseResource theResource ) {
		// Create a FHIR context
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		return parser.encodeResourceToString(theResource);
	}
	
	public static List<? extends IBaseResource> bundleToList(Bundle theBundle) {
		FhirContext ctx = FhirContext.forR4();
		 
		List<IBaseResource> list = new ArrayList<IBaseResource>();
		list.addAll(BundleUtil.toListOfResources(ctx, theBundle));
		 return list;
	}
	
	public static Bundle listToBundle(List<? extends IBaseResource> list) {
		Bundle bundle = new Bundle();

		bundle.setType(Bundle.BundleType.COLLECTION);
		
		for(int i=0;i<list.size();i++) {
			IBaseResource obj = list.get(i);
			bundle.addEntry().setFullUrl(obj.getIdElement().getValue()).setResource((Resource)obj);
		}

		return bundle;
	}
	
	public static void main(String[] args) {
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		// We'll populate this list
		List<IBaseResource> patients = new ArrayList<>();

		// We'll do a search for all Patients and extract the first page
		Bundle bundle = new Bundle();
		QuestionnaireResponse obj = new QuestionnaireResponse();
		obj.setAuthored(new Date());

		bundle.setType(Bundle.BundleType.COLLECTION);
		 
		bundle.addEntry()
		   .setFullUrl(obj.getIdElement().getValue())
		   .setResource(obj);
		bundle.addEntry()
		   .setFullUrl(obj.getIdElement().getValue())
		   .setResource(obj);
		// String json = parser.encodeResourceToString(bundle);
		// System.out.println(json);
		 List<QuestionnaireResponse>  list = (List<QuestionnaireResponse>)bundleToList(bundle);
		 
		//  Bundle bundle1 =listToBundle(list);
		//  String json1= parser.encodeResourceToString(bundle);
		//  System.out.println(json1);
	}
}
