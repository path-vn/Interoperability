package service;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;

import com.globits.fhir.hiv.utils.HivConst;

import dto.HIVInfoDto;

public class RiskFactorService {
	ConvertService convertService = new ConvertService();

	public QuestionnaireResponseItemComponent convertDataToRiskFactor(HIVInfoDto data) {
		QuestionnaireResponseItemComponent result = new QuestionnaireResponseItemComponent();
		result.setLinkId(HivConst.QRLinkIdRiskFactor);
		result.setText(HivConst.QRTextRiskFactor);
		List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
		
		//population_group
		QuestionnaireResponseItemComponent population_group = convertService.newQRItemComponentTypeOpenChoice(HivConst.QRLinkIdPopulationGroup, HivConst.QRTextPopulationGroup, (String)data.getDoiTuong());
		if(population_group != null)
			listItem.add(population_group);
		
		//risk_behavior
		QuestionnaireResponseItemComponent risk_behavior = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdRiskBehavior, HivConst.QRTextRiskBehavior, data.getNguyCo());
		if(risk_behavior != null)
			listItem.add(risk_behavior);
		
		//transmission_route
		QuestionnaireResponseItemComponent transmission_route = convertService.newQRItemComponentTypeChoice(HivConst.QRLinkIdTransmissionRoute, HivConst.QRTextTransmissionRoute, data.getDuongLay());
		if(transmission_route != null)
			listItem.add(transmission_route);
		
		result.setItem(listItem);
		if (result.getItem() != null && result.getItem().size() > 0) {
			return result;
		}
		return null;
	}

}
