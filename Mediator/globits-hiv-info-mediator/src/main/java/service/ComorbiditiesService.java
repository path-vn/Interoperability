package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import Utils.DateTimeUtils;
import dto.HIVInfoDto;
import dto.HbvHcvDto;
import dto.HivDPLaoDto;
import dto.HivDTLaoDto;
import dto.TuberculosisDto;

public class ComorbiditiesService {
	ConvertService convertService = new ConvertService();

	public QuestionnaireResponseItemComponent convertDataToComorbidities(HIVInfoDto data) {
		QuestionnaireResponseItemComponent result = null;
		if ((data.getListHbv() != null && data.getListHbv().size() > 0)
				|| (data.getListHcv() != null && data.getListHcv().size() > 0) || (data.getTuberculosis() != null)) {

			result = new QuestionnaireResponseItemComponent();
			result.setLinkId(HivConst.QRLinkIdComorbidities);
			result.setText(HivConst.QRTextComorbidities);
			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

			// tuberculosis
			if (data.getTuberculosis() != null) {
				List<QuestionnaireResponseItemComponent> tuberculosis = convertDataToTuberculosis(
						data.getTuberculosis());
				if (tuberculosis != null && tuberculosis.size() > 0) {
					listItem.addAll(tuberculosis);
				}
			}

			// hbv
			if (data.getListHbv() != null && data.getListHbv().size() > 0) {
				List<QuestionnaireResponseItemComponent> hbv = convertDataToHBV(data.getListHbv());
				if (hbv != null && hbv.size() > 0) {
					listItem.addAll(hbv);
				}
			}

			// hcv
			if (data.getListHcv() != null && data.getListHcv().size() > 0) {
				List<QuestionnaireResponseItemComponent> hcv = convertDataToHCV(data.getListHcv());
				if (hcv != null && hcv.size() > 0) {
					listItem.addAll(hcv);
				}
			}
		}
		return result;
	}

	private List<QuestionnaireResponseItemComponent> convertDataToHCV(List<HbvHcvDto> listData) {
		List<QuestionnaireResponseItemComponent> results = null;

		if (listData != null && listData.size() > 0) {
			results = new ArrayList<QuestionnaireResponseItemComponent>();
			for (HbvHcvDto data : listData) {
				QuestionnaireResponseItemComponent item = new QuestionnaireResponseItemComponent();
				item.setLinkId(HivConst.QRLinkIdHcv);
				item.setText(HivConst.QRTextHcv);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

				// diagnosisDate
				if (data.getNgayXetNghiem() != null) {
					QuestionnaireResponseItemComponent diagnosisDate = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdDiagnosisDate, HivConst.QRTextHcvDiagnosisDate, data.getNgayXetNghiem());
					if (diagnosisDate != null) {
						listItem.add(diagnosisDate);
					}
				}

				// diagnosis_result
				if (data.getKetQua() != null) {
					QuestionnaireResponseItemComponent diagnosis_result = convertService.newQRItemComponentTypeChoice(
							HivConst.QRLinkIdDiagnosisResult, HivConst.QRTextHcvDiagnosisResult, data.getKetQua());
					if (diagnosis_result != null) {
						listItem.add(diagnosis_result);
					}
				}

				// treatmentStartDate
				if (data.getNgayBatDau() != null) {
					QuestionnaireResponseItemComponent treatmentStartDate = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdDateOfTreatmentStart, HivConst.QRTextHcvDateOfTreatmentStart,
							data.getNgayBatDau());
					if (treatmentStartDate != null) {
						listItem.add(treatmentStartDate);
					}
				}

				// dateOfTreatmentStop
				if (data.getNgayKetThuc() != null) {
					QuestionnaireResponseItemComponent dateOfTreatmentStop = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdDateOfTreatmentStop, HivConst.QRTextHcvDateOfTreatmentStop,
							data.getNgayKetThuc());
					if (dateOfTreatmentStop != null) {
						listItem.add(dateOfTreatmentStop);
					}
				}

				item.setItem(listItem);
				results.add(item);
			}
		}
		return results;
	}

	private List<QuestionnaireResponseItemComponent> convertDataToHBV(List<HbvHcvDto> listData) {
		List<QuestionnaireResponseItemComponent> results = null;

		if (listData != null && listData.size() > 0) {
			results = new ArrayList<QuestionnaireResponseItemComponent>();
			for (HbvHcvDto data : listData) {
				QuestionnaireResponseItemComponent item = new QuestionnaireResponseItemComponent();
				item.setLinkId(HivConst.QRLinkIdHbv);
				item.setText(HivConst.QRTextHbv);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

				// diagnosisDate
				if (data.getNgayXetNghiem() != null) {
					QuestionnaireResponseItemComponent diagnosisDate = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdDiagnosisDate, HivConst.QRTextHbvDiagnosisDate, data.getNgayXetNghiem());
					if (diagnosisDate != null) {
						listItem.add(diagnosisDate);
					}
				}

				// diagnosis_result
				if (data.getKetQua() != null) {
					QuestionnaireResponseItemComponent diagnosis_result = convertService.newQRItemComponentTypeChoice(
							HivConst.QRLinkIdDiagnosisResult, HivConst.QRTextHbvDiagnosisResult, data.getKetQua());
					if (diagnosis_result != null) {
						listItem.add(diagnosis_result);
					}
				}

				// treatmentStartDate
				if (data.getNgayBatDau() != null) {
					QuestionnaireResponseItemComponent treatmentStartDate = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdDateOfTreatmentStart, HivConst.QRTextHbvDateOfTreatmentStart,
							data.getNgayBatDau());
					if (treatmentStartDate != null) {
						listItem.add(treatmentStartDate);
					}
				}

				// dateOfTreatmentStop
				if (data.getNgayKetThuc() != null) {
					QuestionnaireResponseItemComponent dateOfTreatmentStop = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdDateOfTreatmentStop, HivConst.QRTextHbvDateOfTreatmentStop,
							data.getNgayKetThuc());
					if (dateOfTreatmentStop != null) {
						listItem.add(dateOfTreatmentStop);
					}
				}

				item.setItem(listItem);
				results.add(item);
			}
		}
		return results;
	}

	private List<QuestionnaireResponseItemComponent> convertDataToTuberculosis(TuberculosisDto data) {
		List<QuestionnaireResponseItemComponent> results = null;
		if (data != null) {
			results = new ArrayList<QuestionnaireResponseItemComponent>();

			// tb_treatment
			if (data.getListTbTreatment() != null) {
				for (HivDTLaoDto item : data.getListTbTreatment()) {
					QuestionnaireResponseItemComponent tuberculosis = newTuberculosisByType(null, item);
					results.add(tuberculosis);
				}
			}
			// TPT
			if (data.getListTPT() != null && data.getListTPT().size() > 0) {
				for (HivDPLaoDto item : data.getListTPT()) {
					QuestionnaireResponseItemComponent tuberculosis = newTuberculosisByType(item, null);
					results.add(tuberculosis);
				}
			}

		}
		return results;
	}

	private QuestionnaireResponseItemComponent newTuberculosisByType(HivDPLaoDto dpLao, HivDTLaoDto dtLao) {
		QuestionnaireResponseItemComponent tuberculosis = new QuestionnaireResponseItemComponent();
		tuberculosis.setLinkId(HivConst.QRLinkIdTuberculosis);
		tuberculosis.setText(HivConst.QRTextTuberculosis);
		List<QuestionnaireResponseItemComponent> listTuberculosis = new ArrayList<QuestionnaireResponseItemComponent>();

		// TPT
		if (dpLao != null) {
			QuestionnaireResponseItemComponent item = new QuestionnaireResponseItemComponent();
			item.setLinkId(HivConst.QRLinkIdTpt);
			item.setText(HivConst.QRTextTPT);
			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
			//// date_started
			QuestionnaireResponseItemComponent date_started = convertService.newQRItemComponentTypeDate(
					HivConst.QRLinkIdDateStarted, HivConst.QRTextDateTPTStarted, dpLao.getNgayBatDau());
			if (date_started != null) {
				listItem.add(date_started);
			}
			//// date_ended
			QuestionnaireResponseItemComponent date_ended = convertService.newQRItemComponentTypeDate(
					HivConst.QRLinkIdDateEnded, HivConst.QRTextDateTPTEnded, dpLao.getNgayKetThuc());
			if (date_ended != null) {
				listItem.add(date_ended);
			}
			//// Place TPT provided
			QuestionnaireResponseItemComponent location = convertService.newQRItemComponentTypeString(
					HivConst.QRLinkIdLocation, HivConst.QRLinkIdPlaceTPTProvided, dpLao.getCoSoDieuTri());
			if (location != null) {
				listItem.add(location);
			}
			//// TPT completed
			QuestionnaireResponseItemComponent tpt_completed = convertService.newQRItemComponentTypeBoolean(
					HivConst.QRLinkIdTptCompleted, HivConst.QRTextTptCompleted, dpLao.getKetQua());
			if (tpt_completed != null) {
				listItem.add(tpt_completed);
			}

			if (listItem != null && listItem.size() > 0) {
				item.setItem(listItem);
				listTuberculosis.add(item);
			}
		}
		// TB treatment
		else if (dtLao != null) {
			// TB diagnosis date
			if (dtLao.getNgayChanDoan() != null) {
				QuestionnaireResponseItemComponent date_ended = convertService.newQRItemComponentTypeDate(
						HivConst.QRLinkIdTbDiagnosisDate, HivConst.QRTextTbDiagnosisDate, dtLao.getNgayChanDoan());
				if (date_ended != null) {
					listTuberculosis.add(date_ended);
				}
			}

			// TB treatment
			QuestionnaireResponseItemComponent item = new QuestionnaireResponseItemComponent();
			item.setLinkId(HivConst.QRLinkIdTbTreatment);
			item.setText(HivConst.QRTextTbTreatment);
			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
			//// date_started
			QuestionnaireResponseItemComponent date_started = convertService.newQRItemComponentTypeDate(
					HivConst.QRLinkIdDateStarted, HivConst.QRTextTbTreatmentDateStarted, dtLao.getNgayBatDau());
			if (date_started != null) {
				listItem.add(date_started);
			}
			//// date_ended
			QuestionnaireResponseItemComponent date_ended = convertService.newQRItemComponentTypeDate(
					HivConst.QRLinkIdDateEnded, HivConst.QRTextTbTreatmentDateEnded, dtLao.getNgayKetThuc());
			if (date_ended != null) {
				listItem.add(date_ended);
			}
			//// Place TB treatment provided
			QuestionnaireResponseItemComponent location = convertService.newQRItemComponentTypeString(
					HivConst.QRLinkIdLocation, HivConst.QRLinkIdPlaceTbTreatmentProvided, dtLao.getCoSoDieuTri());
			if (location != null) {
				listItem.add(location);
			}

			if (listItem != null && listItem.size() > 0) {
				item.setItem(listItem);
				listTuberculosis.add(item);
			}
		}

		if (listTuberculosis != null && listTuberculosis.size() > 0) {
			tuberculosis.setItem(listTuberculosis);
			return tuberculosis;
		}
		return null;
	}

	private QuestionnaireResponseItemComponent convertDataToTbTreatment(QuestionnaireResponseItemComponent tuberculosis,
			List<HivDTLaoDto> listData) {
		if (listData != null && listData.size() > 0) {
			for (HivDTLaoDto data : listData) {
				boolean hasData = false;// check has data -> show
				List<QuestionnaireResponseItemAnswerComponent> listAnswer = null;
				QuestionnaireResponseItemAnswerComponent answer = null;
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdTbTreatment);
				result.setText("TB Treatment");

				// diagnosisDate
				if (data.getNgayChanDoan() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdDiagnosisDate);
					itemComponent.setText("TB Diagnosis Date");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getNgayChanDoan());
					answer.getValueDateType().setValue(date);
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}

				// dateStarted
				if (data.getNgayBatDau() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdDateStarted);
					itemComponent.setText("Date TB Treatment started");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getNgayBatDau());
					answer.getValueDateType().setValue(date);
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}

				// dateCompleted
				if (data.getNgayKetThuc() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdDateCompleted);
					itemComponent.setText("Date TB Treatment completed");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getNgayKetThuc());
					answer.getValueDateType().setValue(date);
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}

				// placeProvided
				if (data.getCoSoDieuTri() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdPlaceProvided);
					itemComponent.setText("Place TB Treatment provided");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					answer.getValueStringType().setValue(data.getCoSoDieuTri());
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}

				if (hasData) {
					tuberculosis.addItem(result);
				}
			}
		}
		return tuberculosis;
	}

	private QuestionnaireResponseItemComponent convertDataToTPT(QuestionnaireResponseItemComponent tuberculosis,
			List<HivDPLaoDto> listData) {
		if (listData != null && listData.size() > 0) {
			for (HivDPLaoDto data : listData) {
				boolean hasData = false;// check has data -> show
				List<QuestionnaireResponseItemAnswerComponent> listAnswer = null;
				QuestionnaireResponseItemAnswerComponent answer = null;
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdTpt);
				result.setText("TPT");

				// dateStarted
				if (data.getNgayBatDau() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdDateStarted);
					itemComponent.setText("Date TPT started");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getNgayBatDau());
					answer.getValueDateType().setValue(date);
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}

				// dateCompleted
				if (data.getNgayKetThuc() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdDateCompleted);
					itemComponent.setText("Date TPT completed");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getNgayKetThuc());
					answer.getValueDateType().setValue(date);
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}

				// placeProvided
				if (data.getCoSoDieuTri() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
					itemComponent.setLinkId(HivConst.QRLinkIdPlaceProvided);
					itemComponent.setText("Place TPT provided");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					answer.getValueStringType().setValue(data.getCoSoDieuTri());
					listAnswer.add(answer);
					itemComponent.setAnswer(listAnswer);
					result.addItem(itemComponent);
				}

				if (hasData) {
					tuberculosis.addItem(result);
				}
			}
		}
		return tuberculosis;
	}

}
