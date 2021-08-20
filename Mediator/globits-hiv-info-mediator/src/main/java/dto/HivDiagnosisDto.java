package dto;

import java.util.Date;

public class HivDiagnosisDto {
    public Date dateOfConfirmation; //Date of Confirmation
    public String confirmingLab; //Confirming Lab	
    public Date dateOfSpecimenCollection; //Date of Specimen Collection	
    public String placeOfSpecimenCollection; //Place of Specimen Collection	
    
	public Date getDateOfConfirmation() {
		return dateOfConfirmation;
	}
	public void setDateOfConfirmation(Date dateOfConfirmation) {
		this.dateOfConfirmation = dateOfConfirmation;
	}
	public String getConfirmingLab() {
		return confirmingLab;
	}
	public void setConfirmingLab(String confirmingLab) {
		this.confirmingLab = confirmingLab;
	}
	public Date getDateOfSpecimenCollection() {
		return dateOfSpecimenCollection;
	}
	public void setDateOfSpecimenCollection(Date dateOfSpecimenCollection) {
		this.dateOfSpecimenCollection = dateOfSpecimenCollection;
	}
	public String getPlaceOfSpecimenCollection() {
		return placeOfSpecimenCollection;
	}
	public void setPlaceOfSpecimenCollection(String placeOfSpecimenCollection) {
		this.placeOfSpecimenCollection = placeOfSpecimenCollection;
	}

}
