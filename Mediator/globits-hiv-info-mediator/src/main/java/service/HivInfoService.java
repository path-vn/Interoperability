package service;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.globits.fhir.hiv.utils.HivConst;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import dto.HIVInfoDto;

public class HivInfoService {

	@Autowired
	HivCd4BeforeArtService hivCd4BeforeArtService = new HivCd4BeforeArtService();
	HIVDiagnosisService hivDiagnosisService = new HIVDiagnosisService();
	Cd4DuringArtService cd4DuringArtService = new Cd4DuringArtService();
	VlDuringArtService vlDuringArtService = new VlDuringArtService();
	DrugResistanceTestService drugResistanceTestService = new DrugResistanceTestService();
	ArvTreatmentService arvTreatmentService = new ArvTreatmentService();
	RegimenService regimenService = new RegimenService();
	ComorbiditiesService comorbiditiesService = new ComorbiditiesService();
	PregnanciesService pregnanciesService = new PregnanciesService();
	DeathService deathService = new DeathService();
	PatientIdentificationService patientIdentificationService = new PatientIdentificationService();
	ConvertService convertService = new ConvertService();
	RiskFactorService riskFactorService = new RiskFactorService();
	HivRecencyTestService hivRecencyTestService = new HivRecencyTestService();
	
	
	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueCoding(String valueCode,
			String valueDisplay) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueCoding().setCode(valueCode);
		itemAnswerComponent.getValueCoding().setDisplay(valueDisplay);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
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
	public Bundle convertListObjectToBundle(List<HIVInfoDto> listHIVInfoDto) {
		Bundle bundle = new Bundle();
		for (HIVInfoDto data : listHIVInfoDto) {
			FhirContext ctx = FhirContext.forR4();
			IParser parser = ctx.newJsonParser();

			QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();

			if (data.getIdDoiTuong() != null) {
				if (data.getIdDoiTuong() == 1 || data.getIdDoiTuong() == 28 || data.getIdDoiTuong() == 54) {
					data.setDoiTuong("Injection drug users");
				} else if (data.getIdDoiTuong() == 2 || data.getIdDoiTuong() == 18 || data.getIdDoiTuong() == 19
						|| data.getIdDoiTuong() == 29) {
					data.setDoiTuong("FSW");
				} else if (data.getIdDoiTuong() == 10 || data.getIdDoiTuong() == 20 || data.getIdDoiTuong() == 30) {
					data.setDoiTuong("Pregnant women");
				} else if (data.getIdDoiTuong() == 5 || data.getIdDoiTuong() == 55 || data.getIdDoiTuong() == 56
						|| data.getIdDoiTuong() == 57) {
					data.setDoiTuong("Blood donors");
				} else if (data.getIdDoiTuong() == 6 || data.getIdDoiTuong() == 22) {
					data.setDoiTuong("TB patients");
				} else if (data.getIdDoiTuong() == 3 || data.getIdDoiTuong() == 8 || data.getIdDoiTuong() == 23
						|| data.getIdDoiTuong() == 52) {
					data.setDoiTuong("People with STDs");
				} else if (data.getIdDoiTuong() == 16 || data.getIdDoiTuong() == 24) {
					data.setDoiTuong("Young recruits");
				} else if (data.getIdDoiTuong() == 11 || data.getIdDoiTuong() == 25) {
					data.setDoiTuong("MSM");
				} else {
					data.setDoiTuong("Others");
				}
			} else {
				data.setDoiTuong("Others");
			}

			if (data.getIdNguyCo() != null) {
				if (data.getIdNguyCo() == 1) {
					data.setNguyCo("Drug injection");
				} else if (data.getIdNguyCo() == 2) {
					data.setNguyCo("Sex workers");
				} else if (data.getIdNguyCo() == 3) {
					data.setNguyCo("MSM");
				} else if (data.getIdNguyCo() == 4) {
					data.setNguyCo("Multiple Sex Partners");
				} else {
					data.setNguyCo("Others");
				}
			} else {
				data.setNguyCo("Others");
			}

			if (data.getIdDuongLay() != null) {
				if (data.getIdDuongLay() == 3 || data.getIdDuongLay() == 4 || data.getIdDuongLay() == 5) {
					data.setDuongLay("Blood born");
				} else if (data.getIdDuongLay() == 2 || data.getIdDuongLay() == 7 || data.getIdDuongLay() == 8) {
					data.setDuongLay("Sexual Relationship");
				} else if (data.getIdDuongLay() == 1) {
					data.setDuongLay("Mother to child");
				} else {
					data.setDuongLay("Unidentified");
				}
			} else {
				data.setDuongLay("Unidentified");
			}

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
			listItem.add(
					convertService.newQRItemComponentTypeDisplay(HivConst.QRLinkIdTitle, HivConst.QRTextTitle, ""));

			// instructions
			listItem.add(convertService.newQRItemComponentTypeDisplay(HivConst.QRLinkIdInstructions,
					HivConst.QRTextInstructions, ""));

			// Date of Report
			/*
			 * listItem.add(convertService.newQRItemComponentTypeDate(HivConst.
			 * QRLinkIdReportDate, HivConst.QRTextReportDate, null));
			 */
			//organization_unit_name
			QuestionnaireResponseItemComponent synOrg = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdOrganizationUnitName, "Organization Unit Name",
					HivConst.synOrg.HivInfo.getValue().toString(), HivConst.synOrg.HivInfo.name());
			listItem.add(synOrg);
			// questions
			QuestionnaireResponseItemComponent questions = new QuestionnaireResponseItemComponent();
			questions.setLinkId(HivConst.QRLinkIdQuestions);
			questions.setText(HivConst.QRTextQuestions);
			List<QuestionnaireResponseItemComponent> listQuestionsItem = new ArrayList<QuestionnaireResponseItemComponent>();

			// Patient Information
			QuestionnaireResponseItemComponent patientInformation = patientIdentificationService
					.convertDataToPatientInformation(data);
			listQuestionsItem.add(patientInformation);

			// risk_factors
			QuestionnaireResponseItemComponent risk_factors = riskFactorService.convertDataToRiskFactor(data);
			if (risk_factors != null)
				listQuestionsItem.add(risk_factors);

			// hivDiagnosis
			List<QuestionnaireResponseItemComponent> hivDiagnosis = hivDiagnosisService
					.convertDataToHivDiagnosis(data.getListHivDiagnosis());
			if (hivDiagnosis != null && hivDiagnosis.size() > 0) {
				listQuestionsItem.addAll(hivDiagnosis);
			}

			// hiv_recency_test
			QuestionnaireResponseItemComponent hiv_recency_test = hivRecencyTestService
					.convertDataToHivRecencyTest(data.getHivRecencyTest());
			listQuestionsItem.add(hiv_recency_test);

			// cd4
			List<QuestionnaireResponseItemComponent> cd4 = cd4DuringArtService
					.convertDataToCd4DuringArt(data.getListCd4DuringArt());
			if (cd4 != null && cd4.size() > 0) {
				listQuestionsItem.addAll(cd4);
			}

			// vlDuringART
			List<QuestionnaireResponseItemComponent> vl = vlDuringArtService
					.convertDataToVlDuringART(data.getListVlDuringArt());
			if (vl != null && vl.size() > 0) {
				listQuestionsItem.addAll(vl);
			}

			// drugResistanceTest
			List<QuestionnaireResponseItemComponent> drugResistanceTest = drugResistanceTestService
					.convertDataToDrugResistanceTest(data.getListDrugResistanceTest());
			if (drugResistanceTest != null && drugResistanceTest.size() > 0) {
				listQuestionsItem.addAll(drugResistanceTest);
			}

			// arvTreatment
			List<QuestionnaireResponseItemComponent> arvTreatment = arvTreatmentService
					.convertDataToArvTreatment(data.getListArvTreatment());
			if (arvTreatment != null && arvTreatment.size() > 0) {
				listQuestionsItem.addAll(arvTreatment);
			}

			// comorbidities
			QuestionnaireResponseItemComponent comorbidities = comorbiditiesService.convertDataToComorbidities(data);
			if (comorbidities != null && comorbidities.getItem() != null && comorbidities.getItem().size() > 0) {
				listQuestionsItem.add(comorbidities);
			}

			// pregnancies
			List<QuestionnaireResponseItemComponent> pregnancies = pregnanciesService
					.convertDataToPregnancies(data.getListPregnancies());
			if (pregnancies != null && pregnancies.size() > 0) {
				listQuestionsItem.addAll(pregnancies);
			}

			// death
			QuestionnaireResponseItemComponent death = deathService.convertDataToDeath(data);
			if (death != null && death.getItem() != null && death.getItem().size() > 0) {
				listQuestionsItem.add(death);
			}

			questions.setItem(listQuestionsItem);
			listItem.add(questions);

			questionnaireResponse.setItem(listItem);
			bundle.setType(Bundle.BundleType.COLLECTION);
			bundle.addEntry().setFullUrl(questionnaireResponse.getIdElement().getValue())
					.setResource(questionnaireResponse);
		}
		return bundle;
	}

}
