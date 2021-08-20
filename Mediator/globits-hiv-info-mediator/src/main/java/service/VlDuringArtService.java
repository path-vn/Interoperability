package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import Utils.HivInfoMediatorConstants;
import dto.VlDuringArtDto;

public class VlDuringArtService {
	ConvertService convertService = new ConvertService();

	public List<QuestionnaireResponseItemComponent> convertDataToVlDuringART(List<VlDuringArtDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;
		
		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (VlDuringArtDto data : listData) {
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdVlTest);
				result.setText(HivConst.QRTextVlTest);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
				
				//date_specimen_collected
				if (data.getNgayLayMau() != null) {
					QuestionnaireResponseItemComponent dateOfSpecimenCollection = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdLastestDateSpecimen, HivConst.QRTextVlDateSpecimenCollected, data.getNgayLayMau());
					if (dateOfSpecimenCollection != null) {
						listItem.add(dateOfSpecimenCollection);
					}
				}
				
				//date_test_performed
				if (data.getNgay() != null) {
					QuestionnaireResponseItemComponent date_test_performed = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdLastestDateTestPerformed, HivConst.QRTextVlTestDateTestPerformed, data.getNgay());
					if (date_test_performed != null) {
						listItem.add(date_test_performed);
					}
				}
				
				//testResult OR test_result_other
				if (data.getKetQua() != null) {
					QuestionnaireResponseItemComponent testResult = null;
					try {
						testResult = convertService.newQRItemComponentTypeInteger(HivConst.QRLinkIdTestResult, HivConst.QRTextVlTestResult, data.getKetQua());
					} catch (Exception e) {
						testResult = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdTestResultOther, HivConst.QRTextVlTestResultOther, HivInfoMediatorConstants.getUndetectable());
					}
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
