package service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseStatus;
import org.springframework.util.StringUtils;

import com.globits.fhir.hiv.utils.HivConst;
import com.globits.fhir.hiv.utils.HivEnums;
import com.globits.fhir.hiv.utils.Valueset;

import Utils.OpcAssisitMediatorConstants;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import dto.HivDrugResistanceDto;
import dto.HivHepatitisDto;
import dto.HivPregnancyDto;
import dto.HivRecencyDto;
import dto.ClinicalStageDto;
import dto.HivArvTreatmentDto;
import dto.HivCd4Dto;
import dto.HivViralLoadDto;
import dto.OpcAssistDto;
import dto.HivRiskFactorDto;
import dto.HivTBProphylaxisDto;
import dto.HivTBTreatmentDto;

public class OpcAssistService {
	public QuestionnaireResponseItemComponent createEmptyItemCompenent(String linkId, String text) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);
		return itemComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueString(
			String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueStringType().setValue(valueString);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueDecimal(
			String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		BigDecimal decimaValue = null;
		decimaValue = new BigDecimal(valueString);

		itemAnswerComponent.getValueDecimalType().setValue(decimaValue);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueInteger(Integer value) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueIntegerType().setValue(value);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueBoolean(Boolean value) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueBooleanType().setValue(value);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueDate(String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		Date dateType = null;
		try {
			dateType = new SimpleDateFormat("yyyy-MM-dd").parse(valueString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		itemAnswerComponent.getValueDateType().setValue(dateType);

		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueCoding(String valueCode,
			String valueDisplay) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueCoding().setCode(valueCode);
		itemAnswerComponent.getValueCoding().setDisplay(valueDisplay);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueString(String linkId, String text,
			String valueString) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueString(
				valueString);
		itemComponent.setAnswer(listItemAnswerComponent);

		return itemComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueDecimal(String linkId, String text,
			String valueString) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueDecimal(
				valueString);
		itemComponent.setAnswer(listItemAnswerComponent);

		return itemComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueInteger(String linkId, String text,
			Integer value) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueInteger(
				value);
		itemComponent.setAnswer(listItemAnswerComponent);

		return itemComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueBoolean(String linkId, String text,
			Boolean value) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueBoolean(
				value);
		itemComponent.setAnswer(listItemAnswerComponent);

		return itemComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueDate(String linkId, String text,
			String valueString) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueDate(
				valueString);
		itemComponent.setAnswer(listItemAnswerComponent);
		return itemComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueCoding(String linkId, String text,
			String valueCode, String valueDisplay) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueCoding(
				valueCode, valueDisplay);
		itemComponent.setAnswer(listItemAnswerComponent);
		return itemComponent;
	}

	public QuestionnaireResponseItemComponent generatePatientIdentifying(OpcAssistDto data) {
		// patient_identification
		QuestionnaireResponseItemComponent patientIdentification = new QuestionnaireResponseItemComponent();
		patientIdentification.setLinkId(HivConst.QRLinkIdPatientIdentification);
		patientIdentification.setText("Part I: Identifying Information");

		// arv number
		if (data.getPersonId() != null) {
			QuestionnaireResponseItemComponent arvIdNr = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientArvNumber, "ARV Patient number", data.getPersonId().toString());
			patientIdentification.addItem(arvIdNr);
		}
		// caseid
		if (data.getCaseId() != null) {
			QuestionnaireResponseItemComponent casrUid = createQRItemCompenentValueString(HivConst.QRLinkIdCaseUid,
					"Case UID", data.getPersonId().toString());
			patientIdentification.addItem(casrUid);
		}

		QuestionnaireResponseItemComponent patientInformation = createEmptyItemCompenent(
				HivConst.QRLinkIdPersonalInformation, "Patient Information");

		QuestionnaireResponseItemComponent name = createEmptyItemCompenent(HivConst.QRLinkIdPatientName, "Name");
		if (data.getFullname() != null) {
			String fullNamePart = data.getFullname();
			String[] nameParts = fullNamePart.split(" ", 2);
			if (nameParts != null && nameParts.length > 1) {
				String familyNamePart = nameParts[0];
				String givenNamePart = nameParts[1];

				// familyName
				QuestionnaireResponseItemComponent familyName = createQRItemCompenentValueString(
						HivConst.QRLinkIdPatientFamilyName, "Family name", familyNamePart);
				name.addItem(familyName);
				// GivenName
				QuestionnaireResponseItemComponent givenName = createQRItemCompenentValueString(
						HivConst.QRLinkIdPatientGivenName, "Given name", givenNamePart);
				name.addItem(givenName);
			}

			// FullName
			QuestionnaireResponseItemComponent fullName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientFullName, "Full name", fullNamePart);
			name.addItem(fullName);
			patientInformation.addItem(name);

			if (data.getEthnic() != null) {
				Valueset coding = OpcAssisitMediatorConstants.getEthnicity(data.getEthnic());
				QuestionnaireResponseItemComponent ethnicity = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdEthnicity, "Ethnicity", coding.getCode(), coding.getDisplay());
				patientInformation.addItem(ethnicity);
			}
		}

		if (data.getGender() != null) {
			String genderText = null;
			if (data.getGender() == 0) {
				genderText = "Male";
			} else if (data.getGender() == 1) {
				genderText = "Female";
			} else if (data.getGender() == 2) {
				genderText = "Other";
			} else if (data.getGender() == 3) {
				genderText = "Does not wish to disclose";
			}
			QuestionnaireResponseItemComponent gender = createQRItemCompenentValueCoding(HivConst.QRLinkIdGender,
					"Gender", genderText.toLowerCase(), genderText);
			patientInformation.addItem(gender);
		}

		// date of birth
		if (data.getDob() != null) {
			QuestionnaireResponseItemComponent birthDate = createQRItemCompenentValueDate(HivConst.QRLinkIdYearOfBirth,
					"Date of Birth", data.getDob());

			patientInformation.addItem(birthDate);
		}
		QuestionnaireResponseItemComponent identification = createEmptyItemCompenent(HivConst.QRLinkIdIdentification,
				"Identification");

		if (data.getPassportNumber() != null) {
			// Passport
			QuestionnaireResponseItemComponent passport_nr = createQRItemCompenentValueString(
					HivConst.QRLinkIdPassportNumber, "Passport Number", data.getPassportNumber());
			identification.addItem(passport_nr);
		}
		if (data.getNationalIdNumber() != null) {
			// NationalId
			QuestionnaireResponseItemComponent nationalIdRr = createQRItemCompenentValueString(
					HivConst.QRLinkIdNationalId9, "National ID 9 digits", data.getNationalIdNumber());
			identification.addItem(nationalIdRr);
		}

		patientInformation.addItem(identification);
		//// Residence
		QuestionnaireResponseItemComponent residence = new QuestionnaireResponseItemComponent();
		residence.setLinkId(HivConst.QRLinkIdResidence);
		residence.setText("Residence");
		// Current Residence
		QuestionnaireResponseItemComponent current = new QuestionnaireResponseItemComponent();
		current.setLinkId(HivConst.QRLinkIdCurrentResidence);
		current.setText("Current Residence");

		if (data.getCurrentStressAddress() != null) {
			// currentStressAddress
			QuestionnaireResponseItemComponent currentStressAddress = createQRItemCompenentValueString(
					HivConst.QRLinkIdAddress, HivConst.QRTextAddress, data.getCurrentStressAddress());
			current.addItem(currentStressAddress);
		}
		///
		if (data.getCurrentCommune() != null && data.getCurrentCommuneCode() != null) {
			// currentCommune
			QuestionnaireResponseItemComponent currentCommune = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getCurrentCommuneCode(),
					data.getCurrentCommune());
			current.addItem(currentCommune);
		}
		if (data.getCurrentCommune() == null && data.getCurrentCommuneCode() != null) {
			// currentCommune
			QuestionnaireResponseItemComponent currentCommune = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getCurrentCommuneCode(),
					"");
			current.addItem(currentCommune);
		}
		if (data.getCurrentCommune() != null && data.getCurrentCommuneCode() == null) {
			// currentCommune
			QuestionnaireResponseItemComponent currentCommune = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCommune, HivConst.QRTextCommune, "",
					data.getCurrentCommune());
			current.addItem(currentCommune);
		}
		///
		if (data.getCurrentDistrict() != null && data.getCurrentDistrictCode() != null) {
			// currentDistrict
			QuestionnaireResponseItemComponent currentDistrict = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, data.getCurrentDistrictCode(),
					data.getCurrentDistrict());
			current.addItem(currentDistrict);
		}
		if (data.getCurrentDistrict() == null && data.getCurrentDistrictCode() != null) {
			// currentDistrict
			QuestionnaireResponseItemComponent currentDistrict = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, data.getCurrentDistrictCode(),
					"");
			current.addItem(currentDistrict);
		}
		if (data.getCurrentDistrict() != null && data.getCurrentDistrictCode() == null) {
			// currentDistrict
			QuestionnaireResponseItemComponent currentDistrict = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, "",
					data.getCurrentDistrict());
			current.addItem(currentDistrict);
		}
		///
		if (data.getCurrentProvince() != null && data.getCurrentProvinceCode() != null) {
			// currentProvince
			QuestionnaireResponseItemComponent currentProvince = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdProvince, HivConst.QRTextProvince, data.getCurrentProvinceCode(),
					data.getCurrentProvince());
			current.addItem(currentProvince);
		}
		if (data.getCurrentProvince() == null && data.getCurrentProvinceCode() != null) {
			// currentProvince
			QuestionnaireResponseItemComponent currentProvince = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdProvince, HivConst.QRTextProvince, data.getCurrentProvinceCode(),
					"");
			current.addItem(currentProvince);
		}
		if (data.getCurrentProvince() != null && data.getCurrentProvinceCode() == null) {
			// currentProvince
			QuestionnaireResponseItemComponent currentProvince = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdProvince, HivConst.QRTextProvince, "",
					data.getCurrentProvince());
			current.addItem(currentProvince);
		}
		///
		if (data.getCurrentCountry() != null && data.getCurrentCountryCode() != null) {
			// currentCountry
			QuestionnaireResponseItemComponent currentCountry = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getCurrentCountryCode(),
					data.getCurrentCountry());
			current.addItem(currentCountry);
		}
		if (data.getCurrentCountry() == null && data.getCurrentCountryCode() != null) {
			// currentCountry
			QuestionnaireResponseItemComponent currentCountry = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getCurrentCountryCode(),
					"");
			current.addItem(currentCountry);
		}
		if (data.getCurrentCountry() != null && data.getCurrentCountryCode() == null) {
			// currentCountry
			QuestionnaireResponseItemComponent currentCountry = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCountry, HivConst.QRTextCountry, "",
					data.getCurrentCountry());
			current.addItem(currentCountry);
		}
		///
		// // currentText
		// String currentTextString = "";
		// currentTextString = (StringUtils.hasText(data.getCurrentStressAddress()))
		// ? (currentTextString + data.getCurrentStressAddress())
		// : "";
		// if (StringUtils.hasText(data.getCurrentCommune())) {
		// if (StringUtils.hasText(currentTextString))
		// currentTextString += ", ";
		// currentTextString += data.getCurrentCommune();
		// }
		// if (StringUtils.hasText(data.getCurrentDistrict())) {
		// if (StringUtils.hasText(currentTextString))
		// currentTextString += ", ";
		// currentTextString += data.getCurrentDistrict();
		// }
		// if (StringUtils.hasText(data.getCurrentProvince())) {
		// if (StringUtils.hasText(currentTextString))
		// currentTextString += ", ";
		// currentTextString += data.getCurrentProvince();
		// }
		// if (StringUtils.hasText(data.getCurrentCountry())) {
		// if (StringUtils.hasText(currentTextString))
		// currentTextString += ", ";
		// currentTextString += data.getCurrentCountry();
		// }
		// QuestionnaireResponseItemComponent currentText =
		// createQRItemCompenentValueString(
		// HivConst.QRLinkIdCurrentResidenceText, "Current Text", currentTextString);
		// current.addItem(currentText);

		residence.addItem(current);

		QuestionnaireResponseItemComponent permanent = new QuestionnaireResponseItemComponent();
		permanent.setLinkId(HivConst.QRLinkIdPermanentResidence);
		permanent.setText("Permanent residence");
		if (data.getPermanentStressAddress() != null) {
			// permanentStressAddress
			QuestionnaireResponseItemComponent permanentStressAddress = createQRItemCompenentValueString(
					HivConst.QRLinkIdAddress, HivConst.QRTextAddress, data.getPermanentStressAddress());
			permanent.addItem(permanentStressAddress);
		}
		if (data.getPermanentCommune() != null && data.getPermanentCommuneCode() != null) {
			// permanentCommune
			QuestionnaireResponseItemComponent permanentCommune = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getPermanentCommuneCode(),
					data.getPermanentCommune());
			permanent.addItem(permanentCommune);
		}
		if (data.getPermanentCommune() == null && data.getPermanentCommuneCode() != null) {
			// permanentCommune
			QuestionnaireResponseItemComponent permanentCommune = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getPermanentCommuneCode(),
					"");
			permanent.addItem(permanentCommune);
		}
		if (data.getPermanentCommune() != null && data.getPermanentCommuneCode() == null) {
			// permanentCommune
			QuestionnaireResponseItemComponent permanentCommune = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCommune, HivConst.QRTextCommune, "",
					data.getPermanentCommune());
			permanent.addItem(permanentCommune);
		}
		////
		if (data.getPermanentDistrict() != null && data.getPermanentDistrictCode() != null) {
			// PermanentDistrict
			QuestionnaireResponseItemComponent permanentDistrict = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, data.getPermanentDistrictCode(),
					data.getPermanentDistrict());
			permanent.addItem(permanentDistrict);
		}
		if (data.getPermanentDistrict() == null && data.getPermanentDistrictCode() != null) {
			// PermanentDistrict
			QuestionnaireResponseItemComponent permanentDistrict = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, data.getPermanentDistrictCode(),
					"");
			permanent.addItem(permanentDistrict);
		}
		if (data.getPermanentDistrict() != null && data.getPermanentDistrictCode() == null) {
			// PermanentDistrict
			QuestionnaireResponseItemComponent permanentDistrict = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, "",
					data.getPermanentDistrict());
			permanent.addItem(permanentDistrict);
		}
		////
		if (data.getPermanentProvince() != null && data.getPermanentProvinceCode() != null) {
			// PermanentProvince
			QuestionnaireResponseItemComponent permanentProvince = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdProvince, HivConst.QRTextProvince, data.getPermanentProvinceCode(),
					data.getPermanentProvince());
			permanent.addItem(permanentProvince);
		}
		if (data.getPermanentProvince() == null && data.getPermanentProvinceCode() != null) {
			// PermanentProvince
			QuestionnaireResponseItemComponent permanentProvince = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdProvince, HivConst.QRTextProvince, data.getPermanentProvinceCode(),
					"");
			permanent.addItem(permanentProvince);
		}
		if (data.getPermanentProvince() != null && data.getPermanentProvinceCode() == null) {
			// PermanentProvince
			QuestionnaireResponseItemComponent permanentProvince = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdProvince, HivConst.QRTextProvince, "",
					data.getPermanentProvince());
			permanent.addItem(permanentProvince);
		}
		///
		if (data.getPermanentCountry() != null && data.getPermanentCountryCode() != null) {
			// PermanentCountry
			QuestionnaireResponseItemComponent permanentCountry = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getPermanentCountryCode(),
					data.getPermanentCountry());
			permanent.addItem(permanentCountry);
		}
		if (data.getPermanentCountry() == null && data.getPermanentCountryCode() != null) {
			// PermanentCountry
			QuestionnaireResponseItemComponent permanentCountry = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getPermanentCountryCode(),
					"");
			permanent.addItem(permanentCountry);
		}
		if (data.getPermanentCountry() != null && data.getPermanentCountryCode() == null) {
			// PermanentCountry
			QuestionnaireResponseItemComponent permanentCountry = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdCountry, HivConst.QRTextCountry, "",
					data.getPermanentCountry());
			permanent.addItem(permanentCountry);
		}
		// // PermanentText
		// String permanentTextString = "";
		// permanentTextString = (StringUtils.hasText(data.getPermanentStressAddress()))
		// ? (permanentTextString + data.getPermanentStressAddress())
		// : "";
		// if (StringUtils.hasText(data.getPermanentCommune())) {
		// if (StringUtils.hasText(permanentTextString))
		// permanentTextString += ", ";
		// permanentTextString += data.getPermanentCommune();
		// }
		// if (StringUtils.hasText(data.getPermanentDistrict())) {
		// if (StringUtils.hasText(permanentTextString))
		// permanentTextString += ", ";
		// permanentTextString += data.getPermanentDistrict();
		// }
		// if (StringUtils.hasText(data.getPermanentProvince())) {
		// if (StringUtils.hasText(permanentTextString))
		// permanentTextString += ", ";
		// permanentTextString += data.getPermanentProvince();
		// }
		// if (StringUtils.hasText(data.getPermanentCountry())) {
		// if (StringUtils.hasText(permanentTextString))
		// permanentTextString += ", ";
		// permanentTextString += data.getPermanentCountry();
		// }
		// QuestionnaireResponseItemComponent permanentText =
		// createQRItemCompenentValueString(
		// HivConst.QRLinkIdPermanentResidenceText, "Permanent Text",
		// permanentTextString);
		// permanent.addItem(permanentText);

		residence.addItem(permanent);
		patientInformation.addItem(residence);

		// death
		if (data.getDateOfDeath() != null) {
			QuestionnaireResponseItemComponent death = createEmptyItemCompenent(HivConst.QRLinkIdDeath,
					"Patient death");

			QuestionnaireResponseItemComponent dateOfDeath = new QuestionnaireResponseItemComponent();
			dateOfDeath.setLinkId(HivConst.QRLinkIdDateOfDeath);
			dateOfDeath.setText("Date of death");
			List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
			Date dateTimeType = null;
			try {
				dateTimeType = new SimpleDateFormat("yyyy-MM-dd").parse(data.getDateOfDeath());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answer.getValueDateTimeType().setValue(dateTimeType);
			listAnswer.add(answer);
			dateOfDeath.setAnswer(listAnswer);
			death.addItem(dateOfDeath);

			if (data.getCauseOfDeath() != null) {
				QuestionnaireResponseItemComponent causeOfDeath = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCauseOfDeath, "Cause of death", data.getCauseOfDeath().toLowerCase(),
						data.getCauseOfDeath());
				death.addItem(causeOfDeath);
			}
			patientInformation.addItem(death);
			// listItem.add(death);
		}
		patientIdentification.addItem(patientInformation);
		return patientIdentification;

	}

	public QuestionnaireResponseItemComponent generateRiskFactors(OpcAssistDto data) {
		QuestionnaireResponseItemComponent riskFactors = createEmptyItemCompenent(HivConst.QRLinkIdRiskFactor,
				"Risk Factors");

		if (data.getRiskPopulation() != null) {
			Valueset coding = OpcAssisitMediatorConstants.getPopulationGroupByText(data.getRiskPopulation().getCode());
			if (coding != null) {
				QuestionnaireResponseItemComponent riskPopulation = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdPopulationGroup, "Risk Population", coding.getCode(), coding.getDisplay());
				riskFactors.addItem(riskPopulation);
			}

			// List<QuestionnaireResponseItemAnswerComponent> listAnswerRiskPopulation = new
			// ArrayList<QuestionnaireResponseItemAnswerComponent>();

			// for (HivRiskFactorDto item : data.getListRiskPopulation()) {
			// Valueset coding =
			// OpcAssisitMediatorConstants.getPopulationGroupByText(item.getCode());
			// QuestionnaireResponseItemAnswerComponent answerRiskPopulation = new
			// QuestionnaireResponseItemAnswerComponent();
			// answerRiskPopulation.getValueCoding().setCode(coding.getCode());
			// answerRiskPopulation.getValueCoding().setDisplay(coding.getDisplay());
			// listAnswerRiskPopulation.add(answerRiskPopulation);
			// }
			// riskPopulation.setAnswer(listAnswerRiskPopulation);

		}
		return riskFactors;
	}

	public QuestionnaireResponseItemComponent generateHivDiagnosis(OpcAssistDto data) {
		QuestionnaireResponseItemComponent hivDiagnosis = createEmptyItemCompenent(HivConst.QRLinkIdHivDiagnosis,
				"HIV Diagnosis");

		// Date Of Confirmation
		if (data.getHivConfirmationDate() != null) {
			QuestionnaireResponseItemComponent dateOfConfirmation = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfConfirmation, "Date of Confirmation",
					data.getHivConfirmationDate().toString());

			hivDiagnosis.addItem(dateOfConfirmation);
		}
		// Confirming Lab
		if (data.getConfirmLab() != null) {
			QuestionnaireResponseItemComponent confirmingLab = createQRItemCompenentValueString(
					HivConst.QRLinkIdConfirmingLab, "Confirming Lab", data.getConfirmLab());
			hivDiagnosis.addItem(confirmingLab);

		}
		return hivDiagnosis;
	}

	public QuestionnaireResponseItemComponent generateHivRecencyTest(OpcAssistDto data) {
		QuestionnaireResponseItemComponent hivRecencyTest = createEmptyItemCompenent(HivConst.QRLinkIdHivRecencyTest,
				"HIV Recency Test");
		QuestionnaireResponseItemComponent vlRecency = null;
		if (data.getRecency() != null) {

			QuestionnaireResponseItemComponent vlTest = createEmptyItemCompenent(HivConst.QRLinkIdRecencyVlTest,
					"Rapid VL test");
			if (data.getRecency().getDateOfTestPerformance() != null) {
				QuestionnaireResponseItemComponent vlTestDateOfTestPerformance = createQRItemCompenentValueDate(
						HivConst.QRLinkIdDateOfTestPerformance, "Date of rapid test performance",
						data.getRecency().getDateOfTestPerformance());
				vlTest.addItem(vlTestDateOfTestPerformance);
			}
			if (data.getRecency().getTestResult() != null) {
				QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
						HivConst.QRLinkIdVlRecencyTestResult, "VL recency test result",
						data.getRecency().getTestResult());

				vlTest.addItem(vlRecencyTestResult);
			}
			if (data.getRecency().getTestResultOther() != null) {
				QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
						HivConst.QRLinkIdVlRecencyTestResultOther, "Other of viral load test (undetectable)",
						data.getRecency().getTestResultOther());

				vlTest.addItem(vlRecencyTestResult);
			}
			// if (data.getRecency().getRecentInfectionConclusion() != null) {
			// QuestionnaireResponseItemComponent vlRecencyTestResult =
			// createQRItemCompenentValueString(
			// HivConst.QRLinkIdRecencyRecentInfectionConclusion, "Recent infection
			// conclusion",
			// data.getRecency().getRecentInfectionConclusion());
			// vlTest.addItem(vlRecencyTestResult);
			// }
			if (data.getRecency().getRecentInfectionConclusion() != null) {

				vlRecency = createQRItemCompenentValueCoding(HivConst.QRLinkIdRecencyRecentInfectionConclusion,
						"Recent infection conclusion", data.getRecency().getRecentInfectionConclusion().toLowerCase(),
						data.getRecency().getRecentInfectionConclusion());

			}
			hivRecencyTest.addItem(vlTest);
			if (vlRecency != null) {
				// hivRecencyTest = vlRecency;
				hivRecencyTest.addItem(vlRecency);
			}

		}

		return hivRecencyTest;

	}

	// public QuestionnaireResponseItemComponent generateCd4BeforeArt(HivCd4Dto
	// item) {
	// QuestionnaireResponseItemComponent cd4BeforeART =
	// createEmptyItemCompenent(HivConst.QRLinkIdCd4BeforeArt,
	// "CD4 test before ART");
	// if (item.getCd4SampleDate() != null) {
	// QuestionnaireResponseItemComponent cd4BeforeARTDateOfSpecimenCollection =
	// createQRItemCompenentValueDate(
	// HivConst.QRLinkIdDateOfSpecimenCollection, "Date of Specimen Collection for
	// CD4 test before ART",
	// item.getCd4SampleDate());

	// cd4BeforeART.addItem(cd4BeforeARTDateOfSpecimenCollection);
	// }
	// if (item.getCd4ResultDate() != null) {
	// QuestionnaireResponseItemComponent cd4BeforeARTDateOfTestPerformance =
	// createQRItemCompenentValueDate(
	// HivConst.QRLinkIdDateOfTestPerformance, "Date of CD4 test before ART
	// performance",
	// item.getCd4ResultDate());
	// cd4BeforeART.addItem(cd4BeforeARTDateOfTestPerformance);
	// }
	// if (item.getCd4Org() != null) {
	// QuestionnaireResponseItemComponent cd4BeforeARTPlaceOfSpecimenCollection =
	// createQRItemCompenentValueString(
	// HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of Specimen Collection for
	// CD4 test before ART",
	// item.getCd4Org());
	// cd4BeforeART.addItem(cd4BeforeARTPlaceOfSpecimenCollection);
	// }
	// if (item.getCd4Result() != null) {
	// QuestionnaireResponseItemComponent cd4BeforeARTTestResult =
	// createQRItemCompenentValueString(
	// HivConst.QRLinkIdTestResult, "CD4 test before ART - result",
	// item.getCd4Result());
	// cd4BeforeART.addItem(cd4BeforeARTTestResult);
	// }
	// return cd4BeforeART;
	// }

	// public QuestionnaireResponseItemComponent generateCd4DuringArt(HivCd4Dto
	// item) {
	// QuestionnaireResponseItemComponent cd4DuringART =
	// createEmptyItemCompenent(HivConst.QRLinkIdCd4DuringArt,
	// "CD4 test during ART");
	// if (item.getCd4SampleDate() != null) {
	// QuestionnaireResponseItemComponent cd4DuringARTDateOfSpecimenCollection =
	// createQRItemCompenentValueDate(
	// HivConst.QRLinkIdDateOfSpecimenCollection, "Date of Specimen Collection for
	// CD4 test during ART",
	// item.getCd4SampleDate());
	// cd4DuringART.addItem(cd4DuringARTDateOfSpecimenCollection);
	// }
	// if (item.getCd4ResultDate() != null) {
	// QuestionnaireResponseItemComponent cd4DuringARTDateOfTestPerformance =
	// createQRItemCompenentValueDate(
	// HivConst.QRLinkIdDateOfTestPerformance, "Date of CD4 test during ART
	// performance",
	// item.getCd4ResultDate());
	// cd4DuringART.addItem(cd4DuringARTDateOfTestPerformance);
	// }
	// if (item.getCd4Org() != null) {
	// QuestionnaireResponseItemComponent cd4DuringARTPlaceOfSpecimenCollection =
	// createQRItemCompenentValueString(
	// HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of Specimen Collection for
	// CD4 test during ART",
	// item.getCd4Org());
	// cd4DuringART.addItem(cd4DuringARTPlaceOfSpecimenCollection);
	// }
	// if (item.getCd4Result() != null) {
	// QuestionnaireResponseItemComponent cd4DuringARTTestResult =
	// createQRItemCompenentValueString(
	// HivConst.QRLinkIdTestResult, "CD4 test during ART - result",
	// item.getCd4Result());

	// cd4DuringART.addItem(cd4DuringARTTestResult);
	// }
	// return cd4DuringART;
	// }

	public QuestionnaireResponseItemComponent generateLastestCd4(HivCd4Dto item) {
		QuestionnaireResponseItemComponent cd4Lastest = createEmptyItemCompenent(HivConst.QRLinkIdCd4Test,
				"CD4 test results");

		// reason test
		Valueset coding = OpcAssisitMediatorConstants.getCD4Valueset(item.getTestReason());
		if (coding != null) {
			QuestionnaireResponseItemComponent testReason = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdLastestTestReason, "Reason for present CD4 test", coding.getCode(),
					coding.getDisplay());
			cd4Lastest.addItem(testReason);
		}
		// sample date
		if (item.getSampleDate() != null) {
			QuestionnaireResponseItemComponent sampleDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for present CD4 test",
					item.getSampleDate());
			cd4Lastest.addItem(sampleDate);
		}

		// sample place
		if (item.getSamplePlace() != null) {
			QuestionnaireResponseItemComponent samplePlace = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for present CD4 test",
					item.getSamplePlace());
			cd4Lastest.addItem(samplePlace);
		}

		// test date
		if (item.getResultDate() != null) {
			QuestionnaireResponseItemComponent sampleDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of present CD4 test", item.getResultDate());
			cd4Lastest.addItem(sampleDate);
		}

		// test result
		if (item.getResultText() != null) {
			QuestionnaireResponseItemComponent testResult = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTestResultOther, "Result of present CD4 test", item.getResultText().toLowerCase(),
					item.getResultText().toLowerCase());
			cd4Lastest.addItem(testResult);
		}

		if (item.getResultNumber() != null) {
			QuestionnaireResponseItemComponent testResult = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdTestResult, "Result of present CD4 test",
					Integer.parseInt(item.getResultNumber() + ""));
			cd4Lastest.addItem(testResult);
		}

		return cd4Lastest;
	}

	public QuestionnaireResponseItemComponent generateCd4History(HivCd4Dto item) {
		QuestionnaireResponseItemComponent cd4routine = createEmptyItemCompenent(HivConst.QRLinkIdCd4Test,
				"CD4 test results");

		// reason test getCD4Valueset

		QuestionnaireResponseItemComponent testReason = createQRItemCompenentValueCoding(HivConst.QRLinkIdTestReason,
				"Reason for CD4 test", item.getTestReason().toLowerCase(), item.getTestReason());
		cd4routine.addItem(testReason);
		// sample date
		if (item.getSampleDate() != null) {
			QuestionnaireResponseItemComponent sampleDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfSpecimenCollection, "Date of Specimen Collection for CD4 test",
					item.getSampleDate());
			cd4routine.addItem(sampleDate);
		}
		// sample place
		if (item.getSamplePlace() != null) {
			QuestionnaireResponseItemComponent samplePlace = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for CD4 test",
					item.getSamplePlace());
			cd4routine.addItem(samplePlace);
		}
		return cd4routine;
	}

	public QuestionnaireResponseItemComponent generateVlDuringArt(HivViralLoadDto item) {
		QuestionnaireResponseItemComponent vl4DuringART = createEmptyItemCompenent(HivConst.QRLinkIdVlTest,
				"VL test during ART");
		if (item.getTestReason() != null) {

			Valueset coding = OpcAssisitMediatorConstants.getVLValueset(item.getTestReason());
			if (coding != null) {
				QuestionnaireResponseItemComponent vl4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdTestReason, "Reason for VL test", coding.getCode(), coding.getDisplay());
				vl4DuringART.addItem(vl4DuringARTDateOfSpecimenCollection);
			}
		}
		if (item.getVlSampleDate() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for VL test during ART",
					item.getVlSampleDate());
			vl4DuringART.addItem(vl4DuringARTDateOfSpecimenCollection);
		}
		if (item.getVlResultDate() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTestPerformance, "Date of VL test during ART performance",
					item.getVlResultDate());
			vl4DuringART.addItem(vl4DuringARTDateOfTestPerformance);
		}
		if (item.getVlOrg() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of Specimen Collection for VL test during ART",
					item.getVlOrg());
			vl4DuringART.addItem(vl4DuringARTPlaceOfSpecimenCollection);
		}
		if (item.getResultNumber() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTTestResult = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdTestResult, "VL test during ART - result",
					Integer.parseInt(item.getResultNumber() + ""));

			vl4DuringART.addItem(vl4DuringARTTestResult);
		}
		if (item.getResultText() != null) {
			Valueset value = OpcAssisitMediatorConstants.getResultOther(item.getResultText());
			if (value != null) {
				QuestionnaireResponseItemComponent vl4DuringARTTestResult = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdTestResultOther, "Result of other outcome", value.getCode(),
						value.getDisplay());
				vl4DuringART.addItem(vl4DuringARTTestResult);
			}
		}
		return vl4DuringART;
	}

	public QuestionnaireResponseItemComponent generateDrugResistance(HivDrugResistanceDto item) {
		QuestionnaireResponseItemComponent drugResistanceTest = createEmptyItemCompenent(
				HivConst.QRLinkIdDrugResistanceTest, "Drug Resistance test");
		if (item.getDrugResistanceSampleDate() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for Drug Resistance test",
					item.getDrugResistanceSampleDate());

			drugResistanceTest.addItem(drugResistanceTestDateOfSpecimenCollection);
		}
		if (item.getDrugResistanceResultDate() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTestPerformance, "Date of Drug Resistance test",
					item.getDrugResistanceResultDate());
			drugResistanceTest.addItem(drugResistanceTestDateOfTestPerformance);
		}
		if (item.getDrugResistanceOrg() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for Drug Resistance test",
					item.getDrugResistanceOrg());

			drugResistanceTest.addItem(drugResistanceTestPlaceOfSpecimenCollection);
		}
		// if (item.getDrugResistanceResult() != null) {
		// QuestionnaireResponseItemComponent drugResistanceTestTestResult =
		// createQRItemCompenentValueString(
		// HivConst.QRLinkIdTestResult, "Drug Resistance test result",
		// item.getDrugResistanceResult());
		//
		// drugResistanceTest.addItem(drugResistanceTestTestResult);
		// }
		// if (item.getResult() != null) {
		// QuestionnaireResponseItemComponent drugResistanceTestTestResult =
		// createQRItemCompenentValueInteger(
		// HivConst.QRLinkIdTestResult, "Drug Resistance test result",
		// Integer.parseInt(item.getResult() + ""));
		//
		// drugResistanceTest.addItem(drugResistanceTestTestResult);
		// }
		if (item.getDrugResistanceResult() != null) {
			QuestionnaireResponseItemComponent testResult = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTestResultOther, "Drug resistance test result", item.getDrugResistanceResult(),
					item.getDrugResistanceResult());
			drugResistanceTest.addItem(testResult);
		}
		return drugResistanceTest;
	}

	public QuestionnaireResponseItemComponent generateArvTreatment(HivArvTreatmentDto item) {
		QuestionnaireResponseItemComponent arvTreatment = createEmptyItemCompenent(HivConst.QRLinkIdArvTreatment,
				"ARV Treatment");
		if (item.getArvTreatmentDateStart() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStart = createQRItemCompenentValueDate(
					HivConst.QRLinkIdInitiationDate, HivConst.QRTextInitiationDate, item.getArvTreatmentDateStart());

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStart);
		}
		if (item.getArvTreatmentDateEnd() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStop = createQRItemCompenentValueDate(
					HivConst.QRLinkIdArvTreatmentStopDate, HivConst.QRTextArvTreatmentStopDate,
					item.getArvTreatmentDateEnd());

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStop);
		}
		if (item.getArvTreatmentPlaceInitiation() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = createQRItemCompenentValueString(
					HivConst.QRLinkIdArvTreatmentInitiationFacility, HivConst.QRTextArvTreatmentInitiationFacility,
					item.getArvTreatmentPlaceInitiation());
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		}
		if (item.getArvTreatmentPlaceInitiation() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = createQRItemCompenentValueString(
					HivConst.QRLinkIdArvTreatmentEnrollmentFacility, HivConst.QRTextArvTreatmentEnrollmentFacility,
					item.getArvTreatmentPlaceInitiation());
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		}
		///
		if (item.getDateOfLossToFollowUp() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfLossToFollowUp = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfLossToFollowUp, "Date of loss to follow up", item.getDateOfLossToFollowUp());

			arvTreatment.addItem(arvTreatmentDateOfLossToFollowUp);
		}
		if (item.getTreatmentStopReason() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfLossToFollowUp = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdArvTreatmentStopReason, HivConst.QRTextArvTreatmentStopReason,
					item.getTreatmentStopReason(), item.getTreatmentStopReason());

			arvTreatment.addItem(arvTreatmentDateOfLossToFollowUp);
		}

		if (item.getDateOfTransferredOut() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTransferredOut = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTransferredOut, "Date ARV treatment transferred out",
					item.getDateOfTransferredOut());

			arvTreatment.addItem(arvTreatmentDateOfTransferredOut);
		}

		if (item.getPlaceTransferredOut() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceTransferredOut = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfTransferredOut, "Place ARV treatment transferred out",
					item.getPlaceTransferredOut());
			arvTreatment.addItem(arvTreatmentPlaceTransferredOut);
		}
		///
		QuestionnaireResponseItemComponent regimen = createEmptyItemCompenent(HivConst.QRLinkIdRegimen,
				"ARV treatment regimen");
		if (item.getArvRegimenLine() == 1) {
			QuestionnaireResponseItemComponent regimen1stLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate1stLineStarted, "Date 1st ARV regimen started",
					item.getArvTreatmentDateStart());

			regimen.addItem(regimen1stLine);
		}
		if (item.getArvRegimenLine() == 2) {
			QuestionnaireResponseItemComponent regimen2ndLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate2ndLineStarted, "Date 2nd ARV regimen started",
					item.getArvTreatmentDateStart());

			regimen.addItem(regimen2ndLine);
		}
		if (item.getArvRegimenLine() == 3) {
			QuestionnaireResponseItemComponent regimen3rdLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate3rdLineStarted, "Date 3rd ARV regimen started",
					item.getArvTreatmentDateStart());

			regimen.addItem(regimen3rdLine);
		}
		if (item.getWhoStage() != null && item.getWhoStage().size() > 0) {

			for (ClinicalStageDto element : item.getWhoStage()) {
				if (element.getStage() == 1) {
					QuestionnaireResponseItemComponent whoStage = new QuestionnaireResponseItemComponent();
					whoStage.setLinkId(HivConst.QRLinkIdWhoStage);
					whoStage.setText(HivConst.QRTextWhoStage);
					QuestionnaireResponseItemComponent stage = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdWhoClinicalStage, HivConst.QRTextWhoClinicalStage,
							HivEnums.WhoStageValueset.STAGE1.getData().getCode(),
							HivEnums.WhoStageValueset.STAGE1.getData().getDisplay());

					// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					// String dateFormat = formatter.format(element.getEvalDate());

					QuestionnaireResponseItemComponent dateStage = createQRItemCompenentValueDate(
							HivConst.QRLinkIdDateWhoStage, HivConst.QRTextDateWhoStage,
							element.getEvalDate().toString());

					whoStage.addItem(stage);
					whoStage.addItem(dateStage);
					arvTreatment.addItem(whoStage);

				}
				if (element.getStage() == 2) {
					QuestionnaireResponseItemComponent whoStage = new QuestionnaireResponseItemComponent();
					whoStage.setLinkId(HivConst.QRLinkIdWhoStage);
					whoStage.setText(HivConst.QRTextWhoStage);
					QuestionnaireResponseItemComponent stage = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdWhoClinicalStage, HivConst.QRTextWhoClinicalStage,
							HivEnums.WhoStageValueset.STAGE2.getData().getCode(),
							HivEnums.WhoStageValueset.STAGE2.getData().getDisplay());

					// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					// String dateFormat = formatter.format(element.getEvalDate());

					QuestionnaireResponseItemComponent dateStage = createQRItemCompenentValueDate(
							HivConst.QRLinkIdDateWhoStage, HivConst.QRTextDateWhoStage,
							element.getEvalDate().toString());

					whoStage.addItem(stage);
					whoStage.addItem(dateStage);
					arvTreatment.addItem(whoStage);
				}
				if (element.getStage() == 3) {
					QuestionnaireResponseItemComponent whoStage = new QuestionnaireResponseItemComponent();
					whoStage.setLinkId(HivConst.QRLinkIdWhoStage);
					whoStage.setText(HivConst.QRTextWhoStage);
					QuestionnaireResponseItemComponent stage = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdWhoClinicalStage, HivConst.QRTextWhoClinicalStage,
							HivEnums.WhoStageValueset.STAGE3.getData().getCode(),
							HivEnums.WhoStageValueset.STAGE3.getData().getDisplay());

					// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					// String dateFormat = formatter.format(element.getEvalDate());

					QuestionnaireResponseItemComponent dateStage = createQRItemCompenentValueDate(
							HivConst.QRLinkIdDateWhoStage, HivConst.QRTextDateWhoStage,
							element.getEvalDate().toString());

					whoStage.addItem(stage);
					whoStage.addItem(dateStage);
					arvTreatment.addItem(whoStage);
				}
				if (element.getStage() == 4) {
					QuestionnaireResponseItemComponent whoStage = new QuestionnaireResponseItemComponent();
					whoStage.setLinkId(HivConst.QRLinkIdWhoStage);
					whoStage.setText(HivConst.QRTextWhoStage);
					QuestionnaireResponseItemComponent stage = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdWhoClinicalStage, HivConst.QRTextWhoClinicalStage,
							HivEnums.WhoStageValueset.STAGE4.getData().getCode(),
							HivEnums.WhoStageValueset.STAGE4.getData().getDisplay());

					// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					// String dateFormat = formatter.format(element.getEvalDate());

					QuestionnaireResponseItemComponent dateStage = createQRItemCompenentValueDate(
							HivConst.QRLinkIdDateWhoStage, HivConst.QRTextDateWhoStage,
							element.getEvalDate().toString());

					whoStage.addItem(stage);
					whoStage.addItem(dateStage);
					arvTreatment.addItem(whoStage);
				}

			}
		}
		arvTreatment.addItem(regimen);
		return arvTreatment;
	}

	public QuestionnaireResponseItemComponent generateComorbidities(OpcAssistDto data) {
		QuestionnaireResponseItemComponent comorbidities = createEmptyItemCompenent(HivConst.QRLinkIdComorbidities,
				"Comorbidities");
		// Tuberculosis
		QuestionnaireResponseItemComponent tuberculosis = createEmptyItemCompenent(HivConst.QRLinkIdTuberculosis,
				"Tuberculosis");
		// Tuberculosis.TPT

		if (data.getListTBProphylaxis() != null && data.getListTBProphylaxis().size() > 0) {
			for (HivTBProphylaxisDto item : data.getListTBProphylaxis()) {
				QuestionnaireResponseItemComponent tpt = generateTPT(item);
				tuberculosis.addItem(tpt);
			}
		}

		// Comorbidities.Tuberculosis.TB Treatment
		if (data.getListTBTreatment() != null) {
			if (data.getListTBTreatment().get(data.getListTBTreatment().size() - 1) != null) {
				QuestionnaireResponseItemComponent tbDiagnosisDate = createQRItemCompenentValueDate(
						HivConst.QRLinkIdTbDiagnosisDate, "TB Diagnosis Date",
						data.getListTBTreatment().get(data.getListTBTreatment().size() - 1).getTbDiagnoseDate());
				tuberculosis.addItem(tbDiagnosisDate);
			}
		}
		if (data.getListTBTreatment() != null && data.getListTBTreatment().size() > 0) {

			for (HivTBTreatmentDto item : data.getListTBTreatment()) {
				QuestionnaireResponseItemComponent tbTreatment = generateTbTreatment(item);
				tuberculosis.addItem(tbTreatment);
			}
		}
		comorbidities.addItem(tuberculosis);

		// HBV-HCV
		QuestionnaireResponseItemComponent hbvHcv = createEmptyItemCompenent(HivConst.QRLinkIdHbvHcv, "HBV and HCV");
		// HBV
		if (data.getListHbv() != null && data.getListHbv().size() > 0) {
			for (HivHepatitisDto item : data.getListHbv()) {
				QuestionnaireResponseItemComponent hbvTreatment = generateHbvTreatment(item);
				hbvHcv.addItem(hbvTreatment);
			}
		}
		// HCV
		if (data.getListHcv() != null && data.getListHcv().size() > 0) {
			for (HivHepatitisDto item : data.getListHcv()) {
				QuestionnaireResponseItemComponent hcvTreatment = generateHcvTreatment(item);
				hbvHcv.addItem(hcvTreatment);

			}
		}
		comorbidities.addItem(hbvHcv);
		return comorbidities;
	}

	public QuestionnaireResponseItemComponent generateTPT(HivTBProphylaxisDto item) {
		QuestionnaireResponseItemComponent tpt = createEmptyItemCompenent(HivConst.QRLinkIdTpt, "TPT");
		if (item.getTpTreamentDateStart() != null) {
			QuestionnaireResponseItemComponent tptDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TPT started", item.getTpTreamentDateStart());
			tpt.addItem(tptDateStarted);
		}
		if (item.getTpTreamentDateEnd() != null) {
			QuestionnaireResponseItemComponent tptDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TPT completed", item.getTpTreamentDateEnd());
			tpt.addItem(tptDateCompleted);
		}
		if (item.getTpTreamentOrg() != null) {
			QuestionnaireResponseItemComponent tptPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TPT provided", item.getTpTreamentOrg());

			tpt.addItem(tptPlaceProvided);
		}
		return tpt;
	}

	public QuestionnaireResponseItemComponent generateTbTreatment(HivTBTreatmentDto item) {
		QuestionnaireResponseItemComponent tbTreatment = createEmptyItemCompenent(HivConst.QRLinkIdTbTreatment,
				"TB Treatment");
		if (item.getTbDiagnoseDate() != null) {
			QuestionnaireResponseItemComponent tbDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdTbDiagnosisDate, "TB Diagnosis Date", item.getTbDiagnoseDate());
			tbTreatment.addItem(tbDiagnosisDate);
		}
		if (item.getTbTreamentDateStart() != null) {
			QuestionnaireResponseItemComponent tbTreatmentDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TB Treatment started", item.getTbTreamentDateStart());

			tbTreatment.addItem(tbTreatmentDateStarted);
		}
		if (item.getTbTreamentDateEnd() != null) {
			QuestionnaireResponseItemComponent tbTreatmentDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TB Treatment completed", item.getTbTreamentDateEnd());
			tbTreatment.addItem(tbTreatmentDateCompleted);
		}
		if (item.getTbTreamentFacility() != null) {
			QuestionnaireResponseItemComponent tbTreamentplaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TB Treatment provided", item.getTbTreamentFacility());
			tbTreatment.addItem(tbTreamentplaceProvided);
		}
		return tbTreatment;
	}

	public QuestionnaireResponseItemComponent generateHbvTreatment(HivHepatitisDto item) {
		QuestionnaireResponseItemComponent hbvTreatment = createEmptyItemCompenent(HivConst.QRLinkIdHbv, "HBV");
		if (item.getDiagnosisDate() != null) {
			QuestionnaireResponseItemComponent hbvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HBV diagnosis", item.getDiagnosisDate());
			hbvTreatment.addItem(hbvDiagnosisDate);
		}
		if (item.getTreatmentStartDate() != null) {
			QuestionnaireResponseItemComponent hbvStartDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStart, "Date of HBV treatment start", item.getTreatmentStartDate());
			hbvTreatment.addItem(hbvStartDate);
		}
		if (item.getTreatmentEndDate() != null) {
			QuestionnaireResponseItemComponent hbvEndDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of HBV treatment end", item.getTreatmentEndDate());

			hbvTreatment.addItem(hbvEndDate);
		}
		if (item.getPlaceProvided() != null) {
			QuestionnaireResponseItemComponent hbvPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place HBV treatment provided", item.getPlaceProvided());
			hbvTreatment.addItem(hbvPlaceProvided);
		}
		return hbvTreatment;
	}

	public QuestionnaireResponseItemComponent generateHcvTreatment(HivHepatitisDto item) {
		QuestionnaireResponseItemComponent hcvTreatment = createEmptyItemCompenent(HivConst.QRLinkIdHcv, "HCV");
		if (item.getDiagnosisDate() != null) {
			QuestionnaireResponseItemComponent hcvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HCV diagnosis", item.getDiagnosisDate());
			hcvTreatment.addItem(hcvDiagnosisDate);
		}
		if (item.getTreatmentStartDate() != null) {
			QuestionnaireResponseItemComponent hcvStartDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStart, "Date of HCV treatment start", item.getTreatmentStartDate());
			hcvTreatment.addItem(hcvStartDate);
		}
		if (item.getTreatmentEndDate() != null) {
			QuestionnaireResponseItemComponent hcvEndDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of HCV treatment end", item.getTreatmentEndDate());
			hcvTreatment.addItem(hcvEndDate);
		}
		if (item.getPlaceProvided() != null) {
			QuestionnaireResponseItemComponent hcvPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place HCV treatment provided", item.getPlaceProvided());
			hcvTreatment.addItem(hcvPlaceProvided);
		}
		return hcvTreatment;
	}

	public QuestionnaireResponseItemComponent generatePregnancy(HivPregnancyDto item) {
		QuestionnaireResponseItemComponent pregnancy = createEmptyItemCompenent(HivConst.QRLinkIdPregnancies,
				"Pregnancies");
		if (item.getCreateDate() != null) {
			QuestionnaireResponseItemComponent datePregnancyReported = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDatePregnancyReported, "Date pregnancy reported", item.getCreateDate());
			pregnancy.addItem(datePregnancyReported);
		}
		if (item.getChildDeliveryPlace() != null) {
			QuestionnaireResponseItemComponent datePregnancyReported = createQRItemCompenentValueString(
					HivConst.QRLinkIdDeliveryPlace, "Delivery place", item.getChildDeliveryPlace());
			pregnancy.addItem(datePregnancyReported);
		}
		// if (item.getPlaceReported() != null) {
		// QuestionnaireResponseItemComponent datePregnancyReported =
		// createQRItemCompenentValueString(
		// HivConst.QRLinkIdPlaceReported, "Place pregnancy reported",
		// item.getPlaceReported());
		// pregnancy.addItem(datePregnancyReported);
		// }
		QuestionnaireResponseItemComponent outcome = createEmptyItemCompenent(HivConst.QRLinkIdPregnancyOutcomes,
				HivConst.QRTextPregnancyOutcomes);

		if (item.getPregResult() != null) {

			Valueset coding = OpcAssisitMediatorConstants.getOutComeCode(item.getPregResult());
			if (coding != null) {
				QuestionnaireResponseItemComponent outcomeCode = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdPregnancyOutcomeCode, HivConst.QRTextPregnancyOutcomeCode, coding.getCode(),
						coding.getDisplay());
				outcome.addItem(outcomeCode);
			}
		}
		QuestionnaireResponseItemComponent child = createEmptyItemCompenent(HivConst.QRLinkIdPregnancyOutcomesChild,
				HivConst.QRTextPregnancyOutcomesChild);
		QuestionnaireResponseItemComponent childBirthWeight = createQRItemCompenentValueString(
				HivConst.QRLinkIdChildBirthWeight, HivConst.QRTextPregnancyBirthWeight, item.getBirthWeight() + "");
		child.addItem(childBirthWeight);
		// QuestionnaireResponseItemComponent childBirthWeight =
		// createQRItemCompenentValueDecimal(HivConst.QRLinkIdChildBirthWeight,
		// HivConst.QRTextPregnancyBirthWeight, item.getBirthWeight())

		if (item.getChildHivStatus() > 0) {
			QuestionnaireResponseItemComponent birthDefects = createQRItemCompenentValueBoolean(
					HivConst.QRLinkIdPregnancyBirthDefects, HivConst.QRTextPregnancyBirthDefects, true);
			child.addItem(birthDefects);

//			Valueset coding = OpcAssisitMediatorConstants.getBirthDefact(item.getChildHivStatus());
//			if (coding != null) {
//				QuestionnaireResponseItemComponent hivStatus = createQRItemCompenentValueCoding(
//						HivConst.QRLinkIdPregnancyHivStatus, HivConst.QRTextPregnancyHivStatus, coding.getCode(),
//						coding.getDisplay());
//				child.addItem(hivStatus);
//			}
		} else {
			QuestionnaireResponseItemComponent birthDefects = createQRItemCompenentValueBoolean(
					HivConst.QRLinkIdPregnancyBirthDefects, HivConst.QRTextPregnancyBirthDefects, false);
			child.addItem(birthDefects);
		}

		if (item.getChildDiagnosedDate() != null) {
			QuestionnaireResponseItemComponent diagnosedDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdHivDiagnosisDate, HivConst.QRLinkIdChildHivDiagnosisDate,
					item.getChildDiagnosedDate());
			child.addItem(diagnosedDate);
		}
		outcome.addItem(child);
		pregnancy.addItem(outcome);
		return pregnancy;
	}

	public Bundle convertListOpcObjectToBundle(List<OpcAssistDto> listOpcAssistDto) {
		Bundle bundle = new Bundle();
		for (OpcAssistDto data : listOpcAssistDto) {
			FhirContext ctx = FhirContext.forR4();
			IParser parser = ctx.newJsonParser();

			QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
			// Meta
			Meta meta = new Meta();
			CanonicalType canonicalType = new CanonicalType();
			canonicalType.setValue("http://hl7.org/fhir/uv/sdc/StructureDefinition/sdc-questionnaireresponse|2.7");
			List<CanonicalType> listCanonicalType = new ArrayList<CanonicalType>();
			listCanonicalType.add(canonicalType);
			// Meta--Profile
			meta.setProfile(listCanonicalType);
			List<Coding> listCoding = new ArrayList<Coding>();
			Coding coding = new Coding();
			coding.setCode("lformsVersion: 28.1.1");
			listCoding.add(coding);
			// Meta--Tag
			meta.setTag(listCoding);
			questionnaireResponse.setMeta(meta);
			// Status
			questionnaireResponse.setStatus(QuestionnaireResponseStatus.COMPLETED);
			// Authored
			questionnaireResponse.setAuthored(new Date());

			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

			// Title
			QuestionnaireResponseItemComponent title = new QuestionnaireResponseItemComponent();
			title.setLinkId(HivConst.QRLinkIdTitle);
			title.setText("HIV Case Report");
			listItem.add(title);

			// instructions
			QuestionnaireResponseItemComponent instructions = new QuestionnaireResponseItemComponent();
			instructions.setLinkId(HivConst.QRLinkIdInstructions);
			instructions.setText(
					"Reporting instructions: Monthly - applicable to each patient during the reporting period");
			listItem.add(instructions);

			QuestionnaireResponseItemComponent orgUnitName = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdOrganizationUnitName, "Facility ID",
					HivConst.synOrg.OpcAssist.getValue().toString(), HivConst.synOrg.OpcAssist.name());
			listItem.add(orgUnitName);

			// Date of Report
			QuestionnaireResponseItemComponent reportDate = new QuestionnaireResponseItemComponent();
			reportDate.setLinkId(HivConst.QRLinkIdReportDate);
			reportDate.setText("Date of Report");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerReportDate = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerReportDate = new QuestionnaireResponseItemAnswerComponent();
			answerReportDate.getValueDateType().setValue(new Date());
			listAnswerReportDate.add(answerReportDate);
			reportDate.setAnswer(listAnswerReportDate);
			listItem.add(reportDate);
			// Questions
			QuestionnaireResponseItemComponent questions = new QuestionnaireResponseItemComponent();
			questions.setLinkId(HivConst.QRLinkIdQuestions);
			questions.setText("Monthly report");

			// // Patient Identification
			// QuestionnaireResponseItemComponent patientIdentification = new
			// QuestionnaireResponseItemComponent();
			// patientIdentification.setLinkId(HivConst.QRLinkIdPatientIdentification);
			// patientIdentification.setText("Patient Identification");

			// Patient Information
			QuestionnaireResponseItemComponent patientIdentification = generatePatientIdentifying(data);
			questions.addItem(patientIdentification);
			// Risk factors
			QuestionnaireResponseItemComponent riskFactors = generateRiskFactors(data);
			questions.addItem(riskFactors);
			// HIV Diagnosis
			QuestionnaireResponseItemComponent hivDiagnosis = generateHivDiagnosis(data);
			questions.addItem(hivDiagnosis);
			// Recency Test
			if (data.getRecency() != null) {
				QuestionnaireResponseItemComponent hivRecencyTest = generateHivRecencyTest(data);
				questions.addItem(hivRecencyTest);
			}
			// if (data.getListRecency() != null && data.getListRecency().size() > 0) {
			// // for (HivRecencyDto item : data.getListRecency()) {
			// QuestionnaireResponseItemComponent hivRecencyTest =
			// generateHivRecencyTest(data);
			// questions.addItem(hivRecencyTest);
			// // }

			// }

			// // CD4 Before ART
			// if (data.getListHivCd4BeforeArt() != null &&
			// data.getListHivCd4BeforeArt().size() > 0) {
			// for (HivCd4Dto item : data.getListHivCd4BeforeArt()) {
			// QuestionnaireResponseItemComponent cd4BeforeART = generateCd4BeforeArt(item);
			// listItem.add(cd4BeforeART);
			// }
			// }
			// // CD4 During ART
			// if (data.getListCd4DuringArt() != null && data.getListCd4DuringArt().size() >
			// 0) {
			// for (HivCd4Dto item : data.getListCd4DuringArt()) {
			// QuestionnaireResponseItemComponent cd4DuringART = generateCd4DuringArt(item);
			// listItem.add(cd4DuringART);
			// }
			// }

			// CD4
			if (data.getListHivCd4() != null && data.getListHivCd4().size() > 0) {
				for (HivCd4Dto item : data.getListHivCd4()) {
					QuestionnaireResponseItemComponent lastestCd4 = generateLastestCd4(item);
					questions.addItem(lastestCd4);
				}
				// HivCd4Dto item = data.getListHivCd4().get(0);
				// QuestionnaireResponseItemComponent lastestCd4 = generateLastestCd4(item);
				// questions.addItem(lastestCd4);
				// history
				// QuestionnaireResponseItemComponent history =
				// createEmptyItemCompenent(HivConst.QRLinkIdHistory,
				// "Other CD4 test results");
				// for (int i = 1; 1 < data.getListHivCd4().size(); i++) {
				// HivCd4Dto itemHistory = data.getListHivCd4().get(i);
				// QuestionnaireResponseItemComponent cd4Routine =
				// generateCd4History(itemHistory);
				// history.addItem(cd4Routine);
				// }

				// listItem.add(history);
			}

			// VL test During ART
			if (data.getListHivViralLoad() != null && data.getListHivViralLoad().size() > 0) {
				for (HivViralLoadDto item : data.getListHivViralLoad()) {
					QuestionnaireResponseItemComponent vl = generateVlDuringArt(item);
					questions.addItem(vl);
				}
			}
			// Drug Resistance Test
			if (data.getListDrugResistance() != null && data.getListDrugResistance().size() > 0) {
				for (HivDrugResistanceDto item : data.getListDrugResistance()) {
					QuestionnaireResponseItemComponent drugResistanceTest = generateDrugResistance(item);
					questions.addItem(drugResistanceTest);
				}
			}
			// ARV Treatment
			if (data.getListArvTreatment() != null && data.getListArvTreatment().size() > 0) {
				for (HivArvTreatmentDto item : data.getListArvTreatment()) {
					QuestionnaireResponseItemComponent arvTreatment = generateArvTreatment(item);
					questions.addItem(arvTreatment);
				}
			}
			// Comorbidities
			QuestionnaireResponseItemComponent comorbidities = generateComorbidities(data);
			questions.addItem(comorbidities);
			// Pregnancy
			if (data.getListPregnancy() != null && data.getListPregnancy().size() > 0) {
				for (HivPregnancyDto item : data.getListPregnancy()) {
					QuestionnaireResponseItemComponent pregnancy = generatePregnancy(item);
					questions.addItem(pregnancy);
				}
			}
			listItem.add(questions);
			questionnaireResponse.setItem(listItem);

			bundle.setType(Bundle.BundleType.COLLECTION);

			bundle.addEntry().setFullUrl(questionnaireResponse.getIdElement().getValue())
					.setResource(questionnaireResponse);

			String body = parser.encodeResourceToString(bundle);
			System.out.println(body);
		}
		return bundle;

	}
}
