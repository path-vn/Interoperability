package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import dto.PregnanciesDto;

public class PregnanciesService {
	ConvertService convertService = new ConvertService();

	public List<QuestionnaireResponseItemComponent> convertDataToPregnancies(List<PregnanciesDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;
		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (PregnanciesDto data : listData) {
				boolean hasData = false;// check has data -> show
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdPregnancies);
				result.setText("Pregnancies");

				// datePregnancyReported
				// placePregnancyReported
				// childDeliveryDate
				// childDeliveryPlace

				// pregnancyOutcomes
				QuestionnaireResponseItemComponent pregnancyOutcomes = this.convertDataToPregnancyOutcomes(data);

				if (pregnancyOutcomes != null && pregnancyOutcomes.getItem() != null
						&& pregnancyOutcomes.getItem().size() > 0) {
					result.addItem(pregnancyOutcomes);
					listResult.add(result);
				}
			}
		}
		return listResult;
	}

	private QuestionnaireResponseItemComponent convertDataToPregnancyOutcomes(PregnanciesDto data) {
		QuestionnaireResponseItemComponent pregnancyOutcomes = null;
		if (data != null) {
			pregnancyOutcomes = new QuestionnaireResponseItemComponent();
			pregnancyOutcomes.setLinkId(HivConst.QRLinkIdPregnancyOutcomes);
			pregnancyOutcomes.setText(HivConst.QRTextPregnancyOutcomes);
			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

			// pregnancyOutcomeCode
			if (data.getKetQuaMangThai() != null) {
				QuestionnaireResponseItemComponent itemComponent = convertService.newQRItemComponentTypeChoice(
						HivConst.QRLinkIdPregnancyOutcomeCode, HivConst.QRTextPregnancyOutcomeCode,
						data.getKetQuaMangThai());
				if (itemComponent != null) {
					listItem.add(itemComponent);
				}
			}

			// child
			QuestionnaireResponseItemComponent child = convertDataToChild(data);
			if (child != null) {
				listItem.add(child);
			}

			if (listItem != null && listItem.size() > 0) {
				pregnancyOutcomes.setItem(listItem);
			}
		}
		return pregnancyOutcomes;
	}

	private QuestionnaireResponseItemComponent convertDataToChild(PregnanciesDto data) {
		QuestionnaireResponseItemComponent result = null;
		if (data != null) {
			result = new QuestionnaireResponseItemComponent();
			result.setLinkId(HivConst.QRLinkIdPregnancyOutcomesChild);
			result.setText(HivConst.QRTextPregnancyOutcomesChild);
			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

			// hiv_diagnosis_date
			QuestionnaireResponseItemComponent itemComponent = convertService.newQRItemComponentTypeDate(
					HivConst.QRLinkIdHivDiagnosisDate, HivConst.QRLinkIdChildHivDiagnosisDate, data.getNgayChanDoan());
			if (itemComponent != null) {
				listItem.add(itemComponent);
			}

			if (listItem != null && listItem.size() > 0) {
				result.setItem(listItem);
			}
		}
		return result;
	}

}
