package dto;


import java.util.Date;

public class CoMorbidityTreatmentDto {

	private String treatmentId;
	private Date startDate;
	private Date endDate;
	private OrganizationDto placeProvided;
	private Boolean isCompleted;

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}
	public OrganizationDto getPlaceProvided() {
		return placeProvided;
	}
	public void setPlaceProvided(OrganizationDto placeProvided) {
		this.placeProvided = placeProvided;
	}
	public Boolean getIsComplete() {
		return isCompleted;
	}
	public void setIsComplete(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	
}
