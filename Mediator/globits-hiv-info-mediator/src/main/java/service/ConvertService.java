package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.springframework.util.StringUtils;

import com.globits.fhir.hiv.utils.HivConst;
import com.globits.fhir.hiv.utils.Valueset;

import Utils.DateTimeUtils;
import Utils.HivInfoMediatorConstants;

public class ConvertService {

	public QuestionnaireResponseItemComponent newQRItemComponentTypeDate(String qrLinkId, String qrText, Object value) {
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(qrLinkId);
		result.setText(qrText);
		Date date = null;
		if (value != null) {
			//birth_date
			if (qrLinkId.equals(HivConst.QRLinkIdYearOfBirth)) {
				date = DateTimeUtils.convertDate(DateTimeUtils.newDateWithYear(Integer.parseInt((String)value)));
			}
			else {
				date = DateTimeUtils.convertDate((Date)value);
			}
		}
		if (date != null) {
			List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
			answer.getValueDateType().setValue(DateTimeUtils.convertDate(date));
			listAnswer.add(answer);
			result.setAnswer(listAnswer);
			return result;
		}
		
		return null;
	}

	public QuestionnaireResponseItemComponent newQRItemComponentTypeDisplay(String qrLinkId, String qrText,
			String value) { 
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(qrLinkId);
		result.setText(qrText);
		return result;
	}

	public QuestionnaireResponseItemComponent newQRItemComponentTypeString(String qrLinkId, String qrText,
			String value) { 
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(qrLinkId);
		result.setText(qrText);

		if (value != null && StringUtils.hasText(value)) {
			List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
			answer.getValueStringType().setValue(value);
			listAnswer.add(answer);
			result.setAnswer(listAnswer);
			return result;
		}
		return null;
	}

	public QuestionnaireResponseItemComponent newQRItemComponentTypeChoice(String qrLinkId, String qrText,
			Object obj) {
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(qrLinkId);
		result.setText(qrText);

		List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
		Valueset data = null;
		if (qrLinkId.equals(HivConst.QRLinkIdOrganizationUnitName)) {
			data = new Valueset(HivConst.synOrg.HivInfo.getValue().toString(), "HIV Info", (String)obj);
		}
		//giới tính
		else if (qrLinkId.equals(HivConst.QRLinkIdGender)) {
			data = HivInfoMediatorConstants.getGender((String)obj);
		}
		//Hành vi rủi ro - nguy cơ
		else if (qrLinkId.equals(HivConst.QRLinkIdRiskBehavior)) {
			data = HivInfoMediatorConstants.getRiskBehavior((String)obj);
		}
		//Đường lây
		else if (qrLinkId.equals(HivConst.QRLinkIdTransmissionRoute)) {
			data = HivInfoMediatorConstants.getTransmissionRoute((String)obj);
		}
		//Kết quả của kết quả khác vl_test
		else if (qrLinkId.equals(HivConst.QRLinkIdTestResultOther)) {
			data = (Valueset)obj;
		}
		//Rapid test result	
		else if(qrLinkId.equals(HivConst.QRLinkIdLastestTestResult)) {
			data = HivInfoMediatorConstants.getRapidTestResult((String)obj);
		}
		//Recent infection conclusion	
		else if(qrLinkId.equals(HivConst.QRLinkIdRecentInfectionConclusion)) {
			data = HivInfoMediatorConstants.getRecentInfectionConclusion((String)obj);
		}
		//Pregnancy outcome code
		else if(qrLinkId.equals(HivConst.QRLinkIdPregnancyOutcomeCode)) {
			data = HivInfoMediatorConstants.getPregnancyOutcomeCode((String)obj);
		}
		//WHO clinical stage	
		else if(qrLinkId.equals(HivConst.QRLinkIdWhoClinicalStage)) {
			data = HivInfoMediatorConstants.getWhoClinicalStage((String)obj);
		}
		if (data != null) {
			answer.getValueCoding().setCode(data.getCode());
			answer.getValueCoding().setDisplay(data.getDisplay());
			listAnswer.add(answer);
			result.setAnswer(listAnswer);
			return result;
		}
		return null;
	}

	public QuestionnaireResponseItemComponent newQRItemComponentTypeOpenChoice(String qrLinkId, String qrText, Object obj) { 
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(qrLinkId);
		result.setText(qrText);

		if (obj != null) {
			List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
			Valueset data = null;
			//Nghề nghiệp
			if (qrLinkId.equals(HivConst.QRLinkIdOccupation)) {
				data = HivInfoMediatorConstants.getOccupationByText((String)obj);
			}
			//Đối tượng
			else if (qrLinkId.equals(HivConst.QRLinkIdPopulationGroup)) {
				data = HivInfoMediatorConstants.getPopulationGroupByText((String)obj);
			}
			//address
			else if (qrLinkId.equals(HivConst.QRLinkIdCountry)
					|| qrLinkId.equals(HivConst.QRLinkIdProvince)
					|| qrLinkId.equals(HivConst.QRLinkIdDistrict)
					|| qrLinkId.equals(HivConst.QRLinkIdCommune)) {
				data = (Valueset)obj;
				answer.getValueCoding().setCode(data.getCode());
				answer.getValueCoding().setDisplay(data.getDisplay());
				listAnswer.add(answer);
			}
			//ARV treatment regimen name
			else if(qrLinkId.equals(HivConst.QRLinkIdArvRegimenName)) {
				
			}
			else {
				answer.getValueStringType().setValue((String)obj);
				listAnswer.add(answer);
			}
			result.setAnswer(listAnswer);
			return result;
		}
		return null;
	}

	public QuestionnaireResponseItemComponent newQRItemComponentTypeInteger(String qrLinkId, String qrText, Object obj) { 
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(qrLinkId);
		result.setText(qrText);
		Integer value = null;
		if (obj != null) {
			//Result of CD4 test
			if (qrLinkId.equals(HivConst.QRLinkIdTestResult) && qrText.equals(HivConst.QRTextCd4TestResult)) {
				value = Integer.valueOf((String)obj);
			}
			//hiv_recency_test --> VL test result (copies/mL)	
			else if (qrLinkId.equals(HivConst.QRLinkIdTestResult)) {
				value = Integer.valueOf((String)obj);
			}
		}
		if (value != null) {
			List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
			answer.getValueIntegerType().setValue(value);
			listAnswer.add(answer);
			result.setAnswer(listAnswer);
			return result;
		}
		return null;
	}

	public QuestionnaireResponseItemComponent newQRItemComponentTypeBoolean(String qrLinkId, String qrText, Object obj) {
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(qrLinkId);
		result.setText(qrText);
		Boolean value = null;

		if (obj != null) {
			//TPT completed	
			if (qrLinkId.equals(HivConst.QRLinkIdTptCompleted)) {
				value = HivInfoMediatorConstants.getTPTCompleted((String)obj);
			}
		}

		if (value != null) {
			List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
			answer.getValueBooleanType().setValue(value);
			listAnswer.add(answer);
			result.setAnswer(listAnswer);
			return result;
		}
		return null;
	}

}
