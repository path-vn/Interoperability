package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import dto.DrugResistanceTestDto;

public class DrugResistanceTestService {
	ConvertService convertService = new ConvertService();

	public List<QuestionnaireResponseItemComponent> convertDataToDrugResistanceTest(
			List<DrugResistanceTestDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;

		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (DrugResistanceTestDto data : listData) {
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdDrugResistanceTest);
				result.setText(HivConst.QRTextDrugResistanceTest);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

				// dateOfSpecimenCollection
				if (data.getNgayLayMau() != null) {
					QuestionnaireResponseItemComponent dateOfSpecimenCollection = convertService
							.newQRItemComponentTypeDate(HivConst.QRLinkIdLastestDateSpecimen,
									HivConst.QRTextDrugResistanceTestDateSpecimenCollected, data.getNgayLayMau());
					if (dateOfSpecimenCollection != null) {
						listItem.add(dateOfSpecimenCollection);
					}
				}

				// date_test_performed
				if (data.getNgay() != null) {
					QuestionnaireResponseItemComponent date_test_performed = convertService.newQRItemComponentTypeDate(
							HivConst.QRLinkIdLastestDateTestPerformed,
							HivConst.QRTextDrugResistanceTestDateTestPerformed, data.getNgay());
					if (date_test_performed != null) {
						listItem.add(date_test_performed);
					}
				}

				// testResult
				if (data.getKetQua() != null) {
					QuestionnaireResponseItemComponent testResult = convertService.newQRItemComponentTypeChoice(
							HivConst.QRLinkIdTestResult, HivConst.QRTextDrugResistanceTestResult, null);
					if (testResult != null) {
						listItem.add(testResult);
					}
				}

				if (listItem != null && listItem.size() > 0) {
					result.setItem(listItem);
					listResult.add(result);
				}
			}
			return listResult;
		}
		return null;
	}

}
