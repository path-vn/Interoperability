package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.springframework.util.StringUtils;

import com.globits.fhir.hiv.utils.HivConst;
import com.globits.fhir.hiv.utils.Valueset;

import dto.HIVInfoDto;

public class PatientIdentificationService {
	ConvertService convertService = new ConvertService();

	public QuestionnaireResponseItemComponent convertDataToPatientInformation(HIVInfoDto data) {
		QuestionnaireResponseItemComponent patientInformation = new QuestionnaireResponseItemComponent();
		patientInformation.setLinkId(HivConst.QRLinkIdPatientIdentification);
		patientInformation.setText(HivConst.QRTextPatientIdentification);
		List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

		//synOrg
		QuestionnaireResponseItemComponent synOrg = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdOrganizationUnitName, HivConst.QRTextOrganizationUnitName, null);
		if(synOrg != null)
			listItem.add(synOrg);
		
		//	ARV patient number
		QuestionnaireResponseItemComponent patient_arv_nr = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdPatientArvNumber, HivConst.QRTextPatientArvNumber, data.getMaSo());
		if(patient_arv_nr != null) 
			listItem.add(patient_arv_nr);

		//Case UID
		QuestionnaireResponseItemComponent case_uid = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdCaseUid, HivConst.QRTextCaseUid, "");
		if(case_uid != null) 
			listItem.add(case_uid);

		//Index testing
		QuestionnaireResponseItemComponent index_testing = new QuestionnaireResponseItemComponent();
		index_testing.setLinkId(HivConst.QRLinkIdIndexTesting);
		index_testing.setText(HivConst.QRTextIndexTesting);
		List<QuestionnaireResponseItemComponent> listIndexTestingItem = new ArrayList<QuestionnaireResponseItemComponent>();
		////Link to index national id 12 digits
		QuestionnaireResponseItemComponent index_case_national_id12 = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdIndexCaseNationalId12, HivConst.QRTextIndexCaseNationalId12, "");
		if(index_case_national_id12 != null) 
			listIndexTestingItem.add(index_case_national_id12);
		////Link to index national id 9 digits
		QuestionnaireResponseItemComponent index_case_national_id9 = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdIndexCaseNationalId9, HivConst.QRTextIndexCaseNationalId9, "");
		if(index_case_national_id9 != null) 
			listIndexTestingItem.add(index_case_national_id9);
		////Link to index hivinfo case id
		QuestionnaireResponseItemComponent index_case_uid = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdIndexCaseUID, HivConst.QRTextIndexCaseUID, "");
		if(index_case_uid != null) 
			listIndexTestingItem.add(index_case_uid);
		
		index_testing.setItem(listIndexTestingItem);
		if(index_testing.getItem() != null && index_testing.getItem().size() > 0) 
			listItem.add(index_testing);

		//Personal information
		QuestionnaireResponseItemComponent personal_information = new QuestionnaireResponseItemComponent();
		personal_information.setLinkId(HivConst.QRLinkIdPersonalInformation);
		personal_information.setText(HivConst.QRTextPersonalInformation);
		List<QuestionnaireResponseItemComponent> listPersonalInformationItem = new ArrayList<QuestionnaireResponseItemComponent>();

		////fullname
		QuestionnaireResponseItemComponent fullname = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdPatientFullName, HivConst.QRTextPatientFullName, data.getHoTen());
		if(fullname != null)
			listPersonalInformationItem.add(fullname);

		////name
		QuestionnaireResponseItemComponent name = newNameQuestionnaireResponseItemComponent(data);
		if(name != null)
			listPersonalInformationItem.add(name);
		
		////ethnicity
		QuestionnaireResponseItemComponent ethnicity = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdEthnicity, HivConst.QRTextEthnicity, data.getGioiTinh());
		if(ethnicity != null)
			listPersonalInformationItem.add(ethnicity);

		////gender
		QuestionnaireResponseItemComponent gender = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdGender, HivConst.QRTextGender, data.getGioiTinh());
		if(gender != null)
			listPersonalInformationItem.add(gender);

		////birth_date
		QuestionnaireResponseItemComponent birth_date = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdYearOfBirth, HivConst.QRTextYearOfBirth, data.getNamSinh());
		if(birth_date != null)
			listPersonalInformationItem.add(birth_date);
		
		////identification
		QuestionnaireResponseItemComponent identification = newIdentificationQuestionnaireResponseItemComponent(data);
		if(identification != null)
			listPersonalInformationItem.add(identification);
		
		////residence
		QuestionnaireResponseItemComponent residence = newResidenceQuestionnaireResponseItemComponent(data);
		if(residence != null)
			listPersonalInformationItem.add(residence);
		
		////occupation
		QuestionnaireResponseItemComponent occupation = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdOccupation, HivConst.QRTextOccupation, (String)data.getNgheNghiep());
		if(occupation != null)
			listPersonalInformationItem.add(occupation);
		
		personal_information.setItem(listPersonalInformationItem);
		if(personal_information.getItem() != null && personal_information.getItem().size() > 0)
			listItem.add(personal_information);
		
		// synOrg
		/*
		 * QuestionnaireResponseItemComponent synchronizedOrganization = new
		 * QuestionnaireResponseItemComponent();
		 * synchronizedOrganization.setLinkId(HivConst.QRLinkIdSynchronizedOrganization)
		 * ; synchronizedOrganization.setText("Synchronized Organization");
		 * List<QuestionnaireResponseItemAnswerComponent>
		 * listAnswerSynchronizedOrganization = new
		 * ArrayList<QuestionnaireResponseItemAnswerComponent>();
		 * QuestionnaireResponseItemAnswerComponent answerSynchronizedOrganization = new
		 * QuestionnaireResponseItemAnswerComponent();
		 * answerSynchronizedOrganization.getValueCoding().setCode(HivConst.synOrg.
		 * HivInfo.getValue().toString());
		 * answerSynchronizedOrganization.getValueCoding().setDisplay("HIV Info");
		 * listAnswerSynchronizedOrganization.add(answerSynchronizedOrganization);
		 * synchronizedOrganization.setAnswer(listAnswerSynchronizedOrganization);
		 * patientInformation.addItem(synchronizedOrganization);
		 */
		
		patientInformation.setItem(listItem);
		
		if (patientInformation.getItem() != null && patientInformation.getItem().size() > 0) {
			return patientInformation;
		}
		return null;
	}

	private QuestionnaireResponseItemComponent newNameQuestionnaireResponseItemComponent(HIVInfoDto data) {
		if (data.getHoTen() != null && StringUtils.hasText(data.getHoTen())) {
			String fullNamePart = data.getHoTen();
			String[] nameParts = fullNamePart.split(" ", 2);
			String familyNamePart = nameParts[0];
			String givenNamePart = nameParts[1];
			
			QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
			result.setLinkId(HivConst.QRLinkIdPatientName);
			result.setText("Name");
			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
			//Family name
			QuestionnaireResponseItemComponent familyName = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdPatientFamilyName, "Family name", familyNamePart);
			//Given name
			QuestionnaireResponseItemComponent givenName = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdPatientGivenName, "Given name", givenNamePart);
			//Full name
			QuestionnaireResponseItemComponent fullName = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdPatientFullName, "Full name", fullNamePart);
			
			listItem.add(familyName);
			listItem.add(givenName);
			listItem.add(fullName);
			result.setItem(listItem);
			return result;
		}
		return null;
	}

	private QuestionnaireResponseItemComponent newResidenceQuestionnaireResponseItemComponent(HIVInfoDto data) {
		QuestionnaireResponseItemComponent residence = new QuestionnaireResponseItemComponent();
		residence.setLinkId(HivConst.QRLinkIdResidence);
		residence.setText(HivConst.QRTextResidence);
		List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
		
		//current_residence
		QuestionnaireResponseItemComponent current_residence = new QuestionnaireResponseItemComponent();
		current_residence.setLinkId(HivConst.QRLinkIdCurrentResidence);
		current_residence.setText(HivConst.QRTextCurrentResidence);
		List<QuestionnaireResponseItemComponent> listCurrentResidenceItem = new ArrayList<QuestionnaireResponseItemComponent>();

		//country
		QuestionnaireResponseItemComponent country = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdCountry, HivConst.QRTextCountry, new Valueset(HivConst.VN_COUNTRY_CODE, HivConst.VN_COUNTRY_DISPLAY, ""));
		listCurrentResidenceItem.add(country);

		////province
		QuestionnaireResponseItemComponent province = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdProvince, HivConst.QRTextProvince, (Valueset)data.getTinhTT());
		listCurrentResidenceItem.add(province);

		////district
		QuestionnaireResponseItemComponent district = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, (Valueset)data.getHuyenTT());
		listCurrentResidenceItem.add(district);

		////commune
		QuestionnaireResponseItemComponent commune = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdCommune, HivConst.QRTextCommune, (Valueset)data.getXaTT());
		listCurrentResidenceItem.add(commune);

		////address
		QuestionnaireResponseItemComponent address = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdAddress, HivConst.QRTextAddress, data.getDuongPhoTT());
		listCurrentResidenceItem.add(address);
		
		current_residence.setItem(listCurrentResidenceItem);
		listItem.add(current_residence);
		
		//permanent_residence
		QuestionnaireResponseItemComponent permanent_residence = new QuestionnaireResponseItemComponent();
		permanent_residence.setLinkId(HivConst.QRLinkIdPermanentResidence);
		permanent_residence.setText(HivConst.QRTextPermanentResidence);
		List<QuestionnaireResponseItemComponent> listPermanentResidenceItem = new ArrayList<QuestionnaireResponseItemComponent>();

		//country
		country = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdCountry, HivConst.QRTextCountry, new Valueset(HivConst.VN_COUNTRY_CODE, HivConst.VN_COUNTRY_DISPLAY, ""));
		listPermanentResidenceItem.add(country);

		////province
		province = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdProvince, HivConst.QRTextProvince, (Valueset)data.getTinhHK());
		listPermanentResidenceItem.add(province);

		////district
		district = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, (Valueset)data.getHuyenHK());
		listPermanentResidenceItem.add(district);

		////commune
		commune = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdCommune, HivConst.QRTextCommune, (Valueset)data.getXaHK());
		listPermanentResidenceItem.add(commune);

		////address
		address = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdAddress, HivConst.QRTextAddress, data.getDuongPhoHK());
		listPermanentResidenceItem.add(address);

		permanent_residence.setItem(listPermanentResidenceItem);
		listItem.add(permanent_residence);
		
		residence.setItem(listItem);
		if (residence.getItem() != null && residence.getItem().size() > 0) {
			return residence;
		}
		return residence;
	}

	private QuestionnaireResponseItemComponent newIdentificationQuestionnaireResponseItemComponent(HIVInfoDto data) {
		QuestionnaireResponseItemComponent identification = new QuestionnaireResponseItemComponent();
		identification.setLinkId(HivConst.QRLinkIdIdentification);
		identification.setText(HivConst.QRTextIdentification);
		List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
		
		//national_id12
		QuestionnaireResponseItemComponent national_id12 = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdNationalId12, HivConst.QRTextNationalId12, data.getSoCMND().length() == 12 ? data.getSoCMND() : null);
		if (national_id12 != null) {
			listItem.add(national_id12);
		}
		
		//national_id9
		QuestionnaireResponseItemComponent national_id9 = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdNationalId9, HivConst.QRTextNationalId9, data.getSoCMND().length() == 9 ? data.getSoCMND() : null);
		if (national_id9 != null) {
			listItem.add(national_id9);
		}
		
		//insurance_nr
		QuestionnaireResponseItemComponent insurance_nr = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdHealthInsuranceNumber, HivConst.QRTextHealthInsuranceNumber, data.getMaBHYT());
		if (insurance_nr != null) {
			listItem.add(insurance_nr);
		}
		
		//insurance_exp_date
		QuestionnaireResponseItemComponent insurance_exp_date = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdInsuranceExpDate, HivConst.QRTextInsuranceExpDate, data.getNgayHetHanBHYT());
		if (insurance_exp_date != null) {
			listItem.add(insurance_exp_date);
		}
		
		//passport_nr
		QuestionnaireResponseItemComponent passport_nr = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdPassportNumber, HivConst.QRTextPassportNumber, null);
		if (passport_nr != null) {
			listItem.add(passport_nr);
		}
		
		//driver_license
		QuestionnaireResponseItemComponent driver_license = convertService.newQRItemComponentTypeString(HivConst.QRLinkIdDriverLicense, HivConst.QRTextDriverLicense, null);
		if (driver_license != null) {
			listItem.add(driver_license);
		}
		
		identification.setItem(listItem);
		if (identification.getItem() != null && identification.getItem().size() > 0) {
			return identification;
		}
		return null;
	}

}
