package dto;

import java.util.Date;
import java.util.List;

public class ArvTreatmentDto {

	private Date initiationDate;
	private OrganizationDto initiationFacility;
	private Date enrollmentDate;
	private OrganizationDto enrollmentFacility;
	private SystemCodeDto enrollmentType;
	private OrganizationDto facilityTransferIn;
	private Date treatmentStopDate;
	private SystemCodeDto treatmentStopReason;
	private OrganizationDto placeTransferOut;
	private Date dateNextAppointment;
	private Date dateLastExamination;
	private List<PatientRegimenDto> arvRegimens;
	private List<WHOStateDto> whoStage;
	
	public Date getInitiationDate() {
		return initiationDate;
	}
	public void setInitiationDate(Date initiationDate) {
		this.initiationDate = initiationDate;
	}
	public OrganizationDto getInitiationFacility() {
		return initiationFacility;
	}
	public void setInitiationFacility(OrganizationDto initiationFacility) {
		this.initiationFacility = initiationFacility;
	}
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public OrganizationDto getEnrollmentFacility() {
		return enrollmentFacility;
	}
	public void setEnrollmentFacility(OrganizationDto enrollmentFacility) {
		this.enrollmentFacility = enrollmentFacility;
	}
	public SystemCodeDto getEnrollmentType() {
		return enrollmentType;
	}
	public void setEnrollmentType(SystemCodeDto enrollmentType) {
		this.enrollmentType = enrollmentType;
	}
	public OrganizationDto getFacilityTransferIn() {
		return facilityTransferIn;
	}
	public void setFacilityTransferIn(OrganizationDto facilityTransferIn) {
		this.facilityTransferIn = facilityTransferIn;
	}
	public Date getTreatmentStopDate() {
		return treatmentStopDate;
	}
	public void setTreatmentStopDate(Date treatmentStopDate) {
		this.treatmentStopDate = treatmentStopDate;
	}
	public SystemCodeDto getTreatmentStopReason() {
		return treatmentStopReason;
	}
	public void setTreatmentStopReason(SystemCodeDto treatmentStopReason) {
		this.treatmentStopReason = treatmentStopReason;
	}
	public OrganizationDto getPlaceTransferOut() {
		return placeTransferOut;
	}
	public void setPlaceTransferOut(OrganizationDto placeTransferOut) {
		this.placeTransferOut = placeTransferOut;
	}
	public Date getDateNextAppointment() {
		return dateNextAppointment;
	}
	public void setDateNextAppointment(Date dateNextAppointment) {
		this.dateNextAppointment = dateNextAppointment;
	}
	public Date getDateLastExamination() {
		return dateLastExamination;
	}
	public void setDateLastExamination(Date dateLastExamination) {
		this.dateLastExamination = dateLastExamination;
	}
	public List<PatientRegimenDto> getArvRegimens() {
		return arvRegimens;
	}
	public void setArvRegimens(List<PatientRegimenDto> arvRegimens) {
		this.arvRegimens = arvRegimens;
	}
	public List<WHOStateDto> getWhoStage() {
		return whoStage;
	}
	public void setWhoStage(List<WHOStateDto> whoStage) {
		this.whoStage = whoStage;
	}

	

}
