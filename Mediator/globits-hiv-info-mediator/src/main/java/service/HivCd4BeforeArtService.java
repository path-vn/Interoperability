package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import Utils.DateTimeUtils;
import dto.Cd4BeforeArtDto;

public class HivCd4BeforeArtService {

	public List<QuestionnaireResponseItemComponent> convertDataToCd4BeforeART(List<Cd4BeforeArtDto> listData) {
		List<QuestionnaireResponseItemComponent> listResult = null;
		
		if (listData != null && listData.size() > 0) {
			listResult = new ArrayList<QuestionnaireResponseItemComponent>();
			for (Cd4BeforeArtDto data : listData) {
				boolean hasData = false;//check has data -> show
				List<QuestionnaireResponseItemAnswerComponent> listAnswer = null;
				QuestionnaireResponseItemAnswerComponent answer = null;
				QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
				/* result.setLinkId(HivConst.QRLinkIdCd4BeforeArt); */
				result.setText(HivConst.QRTextCd4BeforeArt);
				
				//dateOfSpecimenCollection
				if (data.getNgayLayMau() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent dateOfSpecimenCollection = new QuestionnaireResponseItemComponent();
					dateOfSpecimenCollection.setLinkId(HivConst.QRLinkIdDateOfSpecimenCollection);
					dateOfSpecimenCollection.setText("Date of Specimen Collection for CD4 test before ART");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					Date date = DateTimeUtils.convertDate(data.getNgayLayMau());
					answer.getValueDateType().setValue(date);
					listAnswer.add(answer);
					dateOfSpecimenCollection.setAnswer(listAnswer);
					result.addItem(dateOfSpecimenCollection);
				}
				
				//testResult
				if (data.getKetQua() != null) {
					hasData = true;
					QuestionnaireResponseItemComponent testResult = new QuestionnaireResponseItemComponent();
					testResult.setLinkId(HivConst.QRLinkIdTestResult);
					testResult.setText("CD4 test before ART - result");
					listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
					answer = new QuestionnaireResponseItemAnswerComponent();
					answer.getValueStringType().setValue(data.getKetQua());
					listAnswer.add(answer);
					testResult.setAnswer(listAnswer);
					result.addItem(testResult);
				}

				if (hasData) {
					listResult.add(result);
				}
			}
		}
		return listResult;
	}

}
