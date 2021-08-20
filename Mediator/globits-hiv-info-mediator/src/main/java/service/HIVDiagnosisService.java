package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import Utils.DateTimeUtils;
import dto.HivDiagnosisDto;

public class HIVDiagnosisService {

	public List<QuestionnaireResponseItemComponent> convertDataToHivDiagnosis(List<HivDiagnosisDto> listData) {

		List<QuestionnaireResponseItemComponent> listResult = null;
		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			
			for (HivDiagnosisDto data : listData) {

				boolean hasData = false;//check has data -> show
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdHivDiagnosis);
				result.setText("HIV Diagnosis");

				//Date Of Confirmation
				if (data.getDateOfConfirmation() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent dateOfConfirmation = new QuestionnaireResponseItemComponent();
					dateOfConfirmation.setLinkId(HivConst.QRLinkIdDateOfConfirmation);
					dateOfConfirmation.setText("Date of Confirmation");
					List<QuestionnaireResponseItemAnswerComponent> listAnswerDateOfConfirmation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					QuestionnaireResponseItemAnswerComponent answerDateOfConfirmation = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getDateOfConfirmation());
					answerDateOfConfirmation.getValueDateType().setValue(date);
					
					listAnswerDateOfConfirmation.add(answerDateOfConfirmation);
					dateOfConfirmation.setAnswer(listAnswerDateOfConfirmation);
					result.addItem(dateOfConfirmation);
				}
				//Confirming Lab
				if (data.getConfirmingLab() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent confirmingLab = new QuestionnaireResponseItemComponent();
					confirmingLab.setLinkId(HivConst.QRLinkIdConfirmingLab);
					confirmingLab.setText("Confirming Lab");
					List<QuestionnaireResponseItemAnswerComponent> listAnswerConfirmingLab = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					QuestionnaireResponseItemAnswerComponent answerConfirmingLab = new QuestionnaireResponseItemAnswerComponent();
					answerConfirmingLab.getValueStringType().setValue(data.getConfirmingLab());
					listAnswerConfirmingLab.add(answerConfirmingLab);
					confirmingLab.setAnswer(listAnswerConfirmingLab);
					result.addItem(confirmingLab);
				}

				//Date of Specimen Collection	
				if (data.getDateOfSpecimenCollection() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent dateOfSpecimenCollection = new QuestionnaireResponseItemComponent();
					dateOfSpecimenCollection.setLinkId(HivConst.QRLinkIdDateOfSpecimenCollection);
					dateOfSpecimenCollection.setText("Date of Specimen Collection");
					List<QuestionnaireResponseItemAnswerComponent> listAnswerDateOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					QuestionnaireResponseItemAnswerComponent answerDateOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getDateOfSpecimenCollection());
					answerDateOfSpecimenCollection.getValueDateType().setValue(date);
					
					listAnswerDateOfSpecimenCollection.add(answerDateOfSpecimenCollection);
					dateOfSpecimenCollection.setAnswer(listAnswerDateOfSpecimenCollection);
					result.addItem(dateOfSpecimenCollection);
				}
				//Place of Specimen Collection
				if (data.getPlaceOfSpecimenCollection() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent placeOfSpecimenCollection = new QuestionnaireResponseItemComponent();
					placeOfSpecimenCollection.setLinkId(HivConst.QRLinkIdPlaceOfSpecimenCollection);
					placeOfSpecimenCollection.setText("Place of Specimen Collection");
					List<QuestionnaireResponseItemAnswerComponent> listAnswerPlaceOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					QuestionnaireResponseItemAnswerComponent answerPlaceOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
					answerPlaceOfSpecimenCollection.getValueStringType().setValue(data.getPlaceOfSpecimenCollection());
					listAnswerPlaceOfSpecimenCollection.add(answerPlaceOfSpecimenCollection);
					placeOfSpecimenCollection.setAnswer(listAnswerPlaceOfSpecimenCollection);
					result.addItem(placeOfSpecimenCollection);
				}
				
				if (hasData) {
					listResult.add(result);
				}
			}
		}
		return listResult;
	}

}
