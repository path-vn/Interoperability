package dto;

public class HivHepatitisDto {
	private String diagnosisDate;
	private String treatmentStartDate;
	private String treatmentEndDate;
	private String placeProvided;
	public String getDiagnosisDate() {
		return diagnosisDate;
	}
	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	public String getTreatmentStartDate() {
		return treatmentStartDate;
	}
	public void setTreatmentStartDate(String treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}
	public String getTreatmentEndDate() {
		return treatmentEndDate;
	}
	public void setTreatmentEndDate(String treatmentEndDate) {
		this.treatmentEndDate = treatmentEndDate;
	}
	public String getPlaceProvided() {
		return placeProvided;
	}
	public void setPlaceProvided(String placeProvided) {
		this.placeProvided = placeProvided;
	}
}
