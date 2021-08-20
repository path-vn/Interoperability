package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import Utils.DateTimeUtils;
import dto.RegimenDto;

public class RegimenService {

	public List<QuestionnaireResponseItemComponent> convertDataToRegimen(List<RegimenDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;
		
		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (RegimenDto data : listData) {
				boolean hasData = false;//check has data -> show
				List<QuestionnaireResponseItemAnswerComponent> listAnswer = null;
				QuestionnaireResponseItemAnswerComponent answer = null;
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdArvTreatment);
				result.setText(HivConst.QRTextArvTreatment);
				
				//dateOfTreatmentStart
				if (data.getNgayBatDau() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdDateOfTreatmentStart);
					itemComponent.setText("Date of treatment start");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					Date date = null;
					date = DateTimeUtils.convertDate(data.getNgayBatDau());
					answer.getValueDateType().setValue(date);
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}
				
				if (hasData) {
					listResult.add(result);
				}
			}
		}
		return listResult;
	}

}
