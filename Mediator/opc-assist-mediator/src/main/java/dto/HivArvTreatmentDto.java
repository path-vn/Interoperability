package dto;

import java.util.List;

public class HivArvTreatmentDto {
	private String arvTreatmentDateStart;
	private String arvTreatmentDateEnd;
	private String arvTreatmentPlaceInitiation;
	
	private String dateOfLossToFollowUp;
	private String dateOfTransferredOut;
	private String placeTransferredOut;
	
	private int arvRegimenLine;
	private String arvRegimenName;
	
	private String treatmentStopReason;
	
	private List<ClinicalStageDto> whoStage;
	
	public String getArvTreatmentDateStart() {
		return arvTreatmentDateStart;
	}
	public void setArvTreatmentDateStart(String arvTreatmentDateStart) {
		this.arvTreatmentDateStart = arvTreatmentDateStart;
	}
	public String getArvTreatmentDateEnd() {
		return arvTreatmentDateEnd;
	}
	public void setArvTreatmentDateEnd(String arvTreatmentDateEnd) {
		this.arvTreatmentDateEnd = arvTreatmentDateEnd;
	}
	public String getArvTreatmentPlaceInitiation() {
		return arvTreatmentPlaceInitiation;
	}
	public void setArvTreatmentPlaceInitiation(String arvTreatmentPlaceInitiation) {
		this.arvTreatmentPlaceInitiation = arvTreatmentPlaceInitiation;
	}
	public int getArvRegimenLine() {
		return arvRegimenLine;
	}
	public void setArvRegimenLine(int arvRegimenLine) {
		this.arvRegimenLine = arvRegimenLine;
	}
	public String getArvRegimenName() {
		return arvRegimenName;
	}
	public void setArvRegimenName(String arvRegimenName) {
		this.arvRegimenName = arvRegimenName;
	}
	public String getDateOfLossToFollowUp() {
		return dateOfLossToFollowUp;
	}
	public void setDateOfLossToFollowUp(String dateOfLossToFollowUp) {
		this.dateOfLossToFollowUp = dateOfLossToFollowUp;
	}
	public String getDateOfTransferredOut() {
		return dateOfTransferredOut;
	}
	public void setDateOfTransferredOut(String dateOfTransferredOut) {
		this.dateOfTransferredOut = dateOfTransferredOut;
	}
	public String getPlaceTransferredOut() {
		return placeTransferredOut;
	}
	public void setPlaceTransferredOut(String placeTransferredOut) {
		this.placeTransferredOut = placeTransferredOut;
	}
	public String getTreatmentStopReason() {
		return treatmentStopReason;
	}
	public void setTreatmentStopReason(String treatmentStopReason) {
		this.treatmentStopReason = treatmentStopReason;
	}
	public List<ClinicalStageDto> getWhoStage() {
		return whoStage;
	}
	public void setWhoStage(List<ClinicalStageDto> whoStage) {
		this.whoStage = whoStage;
	}
}
