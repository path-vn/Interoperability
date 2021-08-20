package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.joda.time.DateTimeUtils;

import com.globits.fhir.hiv.utils.HivConst;

import dto.PatientDto;

public class DeathService {

	public QuestionnaireResponseItemComponent convertDataToDeath(PatientDto data) {
		QuestionnaireResponseItemComponent result = null;
		List<QuestionnaireResponseItemAnswerComponent> listAnswer = null;
		QuestionnaireResponseItemAnswerComponent answer = null;
		if (data.getDeathDate() != null || data.getCauseOfDeath() != null) {

			result = new QuestionnaireResponseItemComponent();
			result.setLinkId(HivConst.QRLinkIdDeath);
			result.setText("Patient death");

			// dateOfDeath
			if (data.getDeathDate() != null) {
				QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
				itemComponent.setLinkId(HivConst.QRLinkIdDateOfDeath);
				itemComponent.setText("Date of death");
				listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
				answer = new QuestionnaireResponseItemAnswerComponent();			
				Date date = data.getDeathDate();
				answer.getValueDateTimeType().setValue(date);
				listAnswer.add(answer);
				itemComponent.setAnswer(listAnswer);
				result.addItem(itemComponent);
			}

			// causeOfDeath
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
