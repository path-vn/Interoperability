package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import Utils.DateTimeUtils;
import dto.HIVInfoDto;

public class DeathService {

	public QuestionnaireResponseItemComponent convertDataToDeath(HIVInfoDto data) {
		QuestionnaireResponseItemComponent result = null;
		List<QuestionnaireResponseItemAnswerComponent> listAnswer = null;
		QuestionnaireResponseItemAnswerComponent answer = null;
		if (data.getDateOfDeath() != null || data.getCauseOfDeath() != null) {

			result = new QuestionnaireResponseItemComponent();
			result.setLinkId(HivConst.QRLinkIdDeath);
			result.setText("Patient death");

			//dateOfDeath
			if (data.getDateOfDeath() != null) {
				QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
				itemComponent.setLinkId(HivConst.QRLinkIdDateOfDeath);
				itemComponent.setText("Date of death");
				listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
				answer = new QuestionnaireResponseItemAnswerComponent();
				Date date = DateTimeUtils.convertDate(data.getDateOfDeath());
				answer.getValueDateTimeType().setValue(date);
				listAnswer.add(answer);
				itemComponent.setAnswer(listAnswer);
				result.addItem(itemComponent);
			}
			
			//causeOfDeath
			if (data.getCauseOfDeath() != null) {
				QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
				itemComponent.setLinkId(HivConst.QRLinkIdCauseOfDeath);
				itemComponent.setText("Cause of death");
				listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
				answer = new QuestionnaireResponseItemAnswerComponent();
				answer.getValueCoding().setDisplay(data.getCauseOfDeath());
				listAnswer.add(answer);
				itemComponent.setAnswer(listAnswer);
				result.addItem(itemComponent);
			}
		}
		return result;
	}

}
