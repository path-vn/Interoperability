package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import dto.Cd4DuringArtDto;

public class Cd4DuringArtService {
	ConvertService convertService = new ConvertService();

	public List<QuestionnaireResponseItemComponent> convertDataToCd4DuringArt(
			List<Cd4DuringArtDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = new ArrayList<QuestionnaireResponseItemComponent>();
		
		if (listData != null && listData.size() > 0) {
			for (Cd4DuringArtDto data : listData) {
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				result.setLinkId(HivConst.QRLinkIdCd4Test); 
				result.setText(HivConst.QRTextCd4Test);
				List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
				
				//dateOfSpecimenCollection
				if (data.getNgayLayMau() != null) {
					QuestionnaireResponseItemComponent dateOfSpecimenCollection = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdLastestDateSpecimen, HivConst.QRTextLastestDateSpecimen, data.getNgayLayMau());
					if (dateOfSpecimenCollection != null) {
						listItem.add(dateOfSpecimenCollection);
					}
				}
				
				//testResult
				if (data.getKetQua() != null) {
					QuestionnaireResponseItemComponent testResult = convertService.newQRItemComponentTypeInteger(HivConst.QRLinkIdTestResult, HivConst.QRTextCd4TestResult, data.getKetQua());
					if (testResult != null) {
						listItem.add(testResult);
					}
				}
				
				//date_test_performed
				if (data.getNgay() != null) {
					QuestionnaireResponseItemComponent date_test_performed = convertService.newQRItemComponentTypeDate(HivConst.QRLinkIdLastestDateTestPerformed, HivConst.QRTextCd4TestDateTestPerformed, data.getNgay());
					if (date_test_performed != null) {
						listItem.add(date_test_performed);
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
