package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import dto.ArvTreatmentDto;
import dto.RegimenDto;
import dto.WhoStageDto;

public class ArvTreatmentService {
	ConvertService convertService = new ConvertService();

	public List<QuestionnaireResponseItemComponent> convertDataToArvTreatment(List<ArvTreatmentDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;

		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (ArvTreatmentDto data : listData) {
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdArvTreatment);
				result.setText(HivConst.QRTextArvTreatment);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

				// initiation_date
				if (data.getNgayBatDau() != null) {
					QuestionnaireResponseItemComponent initiation_date = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdInitiationDate, HivConst.QRTextInitiationDate, data.getNgayBatDau());
					listItem.add(initiation_date);
				}

				// treatment_stop_date
				if (data.getNgayKetThuc() != null) {
					QuestionnaireResponseItemComponent treatment_stop_date = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdArvTreatmentStopDate, HivConst.QRTextArvTreatmentStopDate,
							data.getNgayKetThuc());
					listItem.add(treatment_stop_date);
				}

				// initiation_facility
				if (data.getTenCoSoDieuTri() != null) {
					QuestionnaireResponseItemComponent initiation_facility = convertService
							.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdArvTreatmentInitiationFacility,
									HivConst.QRTextArvTreatmentInitiationFacility, data.getTenCoSoDieuTri());
					listItem.add(initiation_facility);
				}

				// arv_regimen
				List<QuestionnaireResponseItemComponent> arv_regimen = convertDataToArvRegimen(
						data.getListArvRegimen());
				if (arv_regimen != null && arv_regimen.size() > 0) {
					listItem.addAll(arv_regimen);
				}

				// who_stage
				List<QuestionnaireResponseItemComponent> who_stage = convertDataToWhoStage(data.getListWhoStage());
				if (who_stage != null && who_stage.size() > 0) {
					listItem.addAll(who_stage);
				}

				if (listResult != null && listResult.size() > 0) {
					result.setItem(listResult);
					listResult.add(result);
				}
			}
		}
		return listResult;
	}

	private List<QuestionnaireResponseItemComponent> convertDataToArvRegimen(List<RegimenDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;
		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (RegimenDto data : listData) {
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdArvRegimen);
				result.setText(HivConst.QRTextArvRegimen);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

				// Date regimen started
				QuestionnaireResponseItemComponent date_regimen_started = convertService.newQRItemComponentTypeDate(
						HivConst.QRLinkIdArvRegimenDateStarted, HivConst.QRTextArvRegimenDateStarted, data.getNgayBatDau());
				if (date_regimen_started != null) {
					listItem.add(date_regimen_started);
				}

				// Date regimen stopped
				QuestionnaireResponseItemComponent date_regimen_stopped = convertService.newQRItemComponentTypeDate(
						HivConst.QRLinkIdArvRegimenDateStopped, HivConst.QRTextArvRegimenDateStopped, data.getNgayKetThuc());
				if (date_regimen_stopped != null) {
					listItem.add(date_regimen_stopped);
				}

				//Regimen name	
				QuestionnaireResponseItemComponent regimen_name = convertService.newQRItemComponentTypeOpenChoice(
						HivConst.QRLinkIdArvRegimenName, HivConst.QRTextArvRegimenName,
						data.getTenPhacDo());
				if (regimen_name != null) {
					listItem.add(regimen_name);
				}

				if (listItem != null && listItem.size() > 0) {
					result.setItem(listItem);
					listResult.add(result);
				}
			}
		}
		return listResult;
	}

	private List<QuestionnaireResponseItemComponent> convertDataToWhoStage(List<WhoStageDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;
		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (WhoStageDto data : listData) {
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdWhoStage);
				result.setText(HivConst.QRTextWhoStage);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

				// WHO clinical stage
				QuestionnaireResponseItemComponent who_clinical = convertService.newQRItemComponentTypeChoice(
						HivConst.QRLinkIdWhoClinicalStage, HivConst.QRTextWhoClinicalStage,
						data.getTenGiaiDoanLamSang());
				if (who_clinical != null) {
					listItem.add(who_clinical);
				}

				// Date of WHO clinical stage
				QuestionnaireResponseItemComponent date_who_stage = convertService.newQRItemComponentTypeDate(
						HivConst.QRLinkIdDateWhoStage, HivConst.QRTextDateWhoStage, data.getNgayBatDau());
				if (date_who_stage != null) {
					listItem.add(date_who_stage);
				}

				if (listItem != null && listItem.size() > 0) {
					result.setItem(listItem);
					listResult.add(result);
				}
			}
		}
		return listResult;
	}

}
