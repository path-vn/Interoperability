package com.globits.hiv.fhir.core.test;

import java.util.ArrayList;
import java.util.Date;

import org.hl7.fhir.hiv.vietnam.r4.model.ARVCarePlan;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
// import org.hl7.fhir.hiv.vietnam.r4.model.TestCarePlanEx;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Address.AddressType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;
import org.junit.Test;

import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.utils.HivConst;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.IDelete;
import ca.uhn.fhir.rest.gclient.IDeleteTyped;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;

public class ServiceTest {
	//@Test
	public void deletePatientTest() {
		//HivPatient patient = HAPIFhirHivPatientService.getPatientById("Patient/1068");
		MethodOutcome outcome = HAPIFhirHivPatientService.deletePatientById("P00000002");
		System.out.println(outcome.getId());	
	}
	@Test
	public void createPatientTest() {
		HivPatient patient = new HivPatient();
//		try {
//			patient = HAPIFhirHivPatientService.getPatientById("Patient/1066");
//		}catch (Exception ex) {
//			
//		}
//		if(patient==null) {
//			patient = new HivPatient();
//		}
		HumanName name = new HumanName();
		name.addGiven("Thanh Lâm-2");
		name.setFamily("Nguyễn");
		patient.setName(new ArrayList<HumanName>());
		patient.addName(name);
		patient.setActive(true);
		patient.setBirthDate(DateTimeType.now().getValue());
		patient.setGenderIdentity(new CodeableConcept());
		Identifier artIden = new Identifier();
		artIden.setSystem("https://basespecs.vn/NamingSystem/ARTIdentifiers");
		artIden.setValue("ARV001");
		patient.addIdentifier(artIden);
		
		Identifier passportNumbers = new Identifier();
		passportNumbers.setSystem("https://basespecs.vn/NamingSystem/PassportNumbers");
		passportNumbers.setValue("P0000012311");
		patient.addIdentifier(passportNumbers);
		
		Identifier nationalID9 = new Identifier();
		nationalID9.setSystem("https://basespecs.vn/NamingSystem/NationalID9");
		nationalID9.setValue("011100101");
		patient.addIdentifier(nationalID9);
		
		
		Identifier nationalID = new Identifier();
		nationalID.setSystem("https://basespecs.vn/NamingSystem/NationalID");
		nationalID.setValue("01112303122");
		patient.addIdentifier(nationalID);
		
		Identifier insuranceNumbers = new Identifier();
		insuranceNumbers.setSystem("https://basespecs.vn/NamingSystem/InsuranceNumbers");
		insuranceNumbers.setValue("NN-1232717432");
		patient.addIdentifier(insuranceNumbers);
		
		
		Coding gender = patient.getGenderIdentity().addCoding();
		gender.setCode("Male");
		gender.setSystem(HivConst.VsPatientGenderIdentity);
		gender.setDisplay("Male");
		patient.setEthnicity(new CodeableConcept());
		patient.getEthnicity().addCoding(new Coding(HivConst.VsEthnicity, "Kinh", "Kinh"));
		Address tempAddress = new Address();
		tempAddress.addLine("31 Tôn Thất Thiệp, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh 70000");
		tempAddress.setCity("TP Hồ Chí Minh");
		tempAddress.setCountry("Việt nam");
		tempAddress.setDistrict("Quận 1");
		tempAddress.addLine("Số nhà 31");
		tempAddress.setType(AddressType.PHYSICAL);
		Extension adminAddressTemp = new Extension("Temporary");
		Extension province  = new Extension("province", new Coding("vietnam_administrative", "TP.HCM", "Thành Phố Hồ Chí Minh"));
		Extension district  = new Extension("district", new Coding("vietnam_administrative", "Quan1", "Quận 1"));
		Extension commune  = new Extension("commune", new Coding("vietnam_administrative", "BenNghe", "Bến Nghé"));

		adminAddressTemp.addExtension(province);
		adminAddressTemp.addExtension(district);
		adminAddressTemp.addExtension(commune);
		tempAddress.addExtension(adminAddressTemp);
		
		Address address = new Address();
		
		address.addLine("3c Ly Thuong Kiet Street, Phu Thanh Block, Tan Phu Ward");
		
		Extension adminAddress = new Extension("Permanent");
		province  = new Extension("province", new Coding("vietnam_administrative",  "TP.HCM", "Thành Phố Hồ Chí Minh"));
		district  = new Extension("district", new Coding("vietnam_administrative", "TanPhu", "Tân Phú"));
		commune  = new Extension("commune", new Coding("vietnam_administrative", "TanPhu", "Tân Phú"));

		adminAddress.addExtension(province);
		adminAddress.addExtension(district);
		adminAddress.addExtension(commune);

		address.addExtension(adminAddress);
		
		patient.setAddress(new ArrayList<Address>());
		
		patient.addAddress(tempAddress);
		patient.addAddress(address);

		Coding coding = new Coding();
		coding.setCode("occupation");
		patient.setOccupation(coding);
		FhirContext ctx = FhirContext.forR4();
		IParser jsonParser = ctx.newJsonParser();
		jsonParser.setPrettyPrint(true);
		String resourceText = jsonParser.encodeResourceToString(patient);
		System.out.println(resourceText);
		
		//MethodOutcome outcome = HAPIFhirHivPatientService.createPatient(patient);
		//System.out.println(outcome.getId());	
	}
	//@Test
	// public void test() {
	// 	System.out.println("Test");
	// 	TestCarePlanEx carePlan = new TestCarePlanEx();
	// 	carePlan.setSubject(new Reference("Patient/2"));
	// 	carePlan.getSubject().setDisplay("Peter James Chalmers");
	// 	carePlan.setPlaceOfTransferOut(new Reference("Organization/example"));
	// 	FhirContext ctx = FhirContext.forR4();
	// 	IParser jsonParser = ctx.newJsonParser();
	// 	jsonParser.setPrettyPrint(true);
	// 	String resourceText = jsonParser.encodeResourceToString(carePlan);
	// 	System.out.println(resourceText);
	// }
	//@Test
	public void testValidator() {
		HivPatient patient = HAPIFhirHivPatientService.getPatientById("Patient/2");
		FhirContext ctx = FhirContext.forR4();
		IParser jsonParser = ctx.newJsonParser();
		String resourceText = jsonParser.encodeResourceToString(patient);
		// Ask the context for a validator
		FhirValidator validator = ctx.newValidator();
		ValidationResult result = validator.validateWithResult(patient);
		ValidationResult result2 = validator.validateWithResult(resourceText);
		// The result object now contains the validation results
		for (SingleValidationMessage next : result2.getMessages()) {
		   System.out.println(next.getLocationString() + " " + next.getMessage());
		}
	}
	public void main(String args[]) {
		//HivPatient patient = HAPIFhirHivPatientService.getPatientById("3");
		//Date date  = patient.getMeta().getLastUpdated();
		ARVCarePlan carePlan = new ARVCarePlan();
		Reference subject = new Reference();
		subject.setResource(new HivPatient());
		carePlan.setSubject(subject);
		carePlan.getSubject().getResource().setId("Patient/example");
		carePlan.getSubject().setDisplay("Peter James Chalmers");

		FhirContext ctx = FhirContext.forR4();
		IParser jsonParser = ctx.newJsonParser();
		jsonParser.setPrettyPrint(true);
		String resourceText = jsonParser.encodeResourceToString(carePlan);
		System.out.println(resourceText);
	}
}
