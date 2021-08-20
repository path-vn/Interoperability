package dto;

import java.util.Date;

public class RegimenPatientDto {
	private Date startDate;
	private Date endDate;
	private String place;
	private String regimenName;
	private int arvRegimenLine;

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

	public String getRegimenName() {
		return regimenName;
	}

	public void setRegimenName(String regimenName) {
		this.regimenName = regimenName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getArvRegimenLine() {
		return arvRegimenLine;
	}

	public void setArvRegimenLine(int arvRegimenLine) {
		this.arvRegimenLine = arvRegimenLine;
	}

	public RegimenPatientDto() {

	}

}
