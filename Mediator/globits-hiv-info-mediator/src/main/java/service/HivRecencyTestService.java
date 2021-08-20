package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import Utils.HivInfoMediatorConstants;
import dto.HivRecencyTest;

public class HivRecencyTestService {
	ConvertService convertService = new ConvertService();

	public QuestionnaireResponseItemComponent convertDataToHivRecencyTest(HivRecencyTest data) {
		if (data != null) {
			QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
			result.setLinkId(HivConst.QRLinkIdHivRecencyTest);
			result.setText(HivConst.QRTextHivRecencyTest);
			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

			//rapid_test
			QuestionnaireResponseItemComponent rapid_test = new QuestionnaireResponseItemComponent();
			rapid_test.setLinkId(HivConst.QRLinnkIdRecencyRapidTest);
			rapid_test.setText(HivConst.QRTextRecencyRapidTest);
			List<QuestionnaireResponseItemComponent> listRapidTestItem = new ArrayList<QuestionnaireResponseItemComponent>();
			////date_test_performed
			if (data.getNgayXetNghiemSangLoc() != null) {
				QuestionnaireResponseItemComponent date_test_performed = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdLastestDateTestPerformed, HivConst.QRTextLastestDateTestPerformed, data.getNgayXetNghiemSangLoc());
				if (date_test_performed != null) {
					listRapidTestItem.add(date_test_performed);
				}
			}
			////test_result
			if (data.getKetQuaSangLoc() != null) {
				QuestionnaireResponseItemComponent test_result = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdLastestTestResult, HivConst.QRTextRapidTestResult, data.getKetQuaSangLoc());
				if (test_result != null) {
					listRapidTestItem.add(test_result);
				}
			}
			rapid_test.setItem(listRapidTestItem);
			listItem.add(rapid_test);
			
			//vl_test
			QuestionnaireResponseItemComponent vl_test = new QuestionnaireResponseItemComponent();
			vl_test.setLinkId(HivConst.QRLinnkIdRecencyRapidTest);
			vl_test.setText(HivConst.QRTextRecencyRapidTest);
			List<QuestionnaireResponseItemComponent> listVlTestItem = new ArrayList<QuestionnaireResponseItemComponent>();
			////date_test_performed
			if (data.getNgayXetNghiemTaiLuong() != null) {
				QuestionnaireResponseItemComponent date_test_performed = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdLastestDateTestPerformed, HivConst.QRTextHivRecencyTestVlTestDateTestPerformed, data.getNgayXetNghiemTaiLuong());
				if (date_test_performed != null) {
					listVlTestItem.add(date_test_performed);
				}
			}
			////test_result - test_result_other
			QuestionnaireResponseItemComponent test_result = null;
			try {
				test_result = convertService.newQRItemComponentTypeInteger(HivConst.QRLinkIdTestResult, HivConst.QRTextHivRecencyTestResult, data.getKetQuaTaiLuong());
			} catch (Exception e) {
				test_result = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdTestResultOther, HivConst.QRTextHivRecencyTestResultOther, HivInfoMediatorConstants.getUndetectable());
			}
			if (test_result != null) {
				listVlTestItem.add(test_result);
			}
			vl_test.setItem(listVlTestItem);
			listItem.add(vl_test);

			//recent_infection_conclusion
			QuestionnaireResponseItemComponent recent_infection_conclusion = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdRecentInfectionConclusion, HivConst.QRTextRecentInfectionConclusion, data.getKetQua());
			listItem.add(recent_infection_conclusion);
			
			if (listItem != null && listItem.size() > 0) {
				result.setItem(listVlTestItem);
			}
			return result;
		}
		return null;
	}

}
