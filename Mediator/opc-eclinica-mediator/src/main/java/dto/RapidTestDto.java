package dto;

import java.util.Date;

public class RapidTestDto {
	private Date dateOfSpecimenCollection;
	private Date dateOfTestPerformance;
	private String placeOfSpecimenCollection;
	private Double result;
	private String valueText;

	public Date getDateOfSpecimenCollection() {
		return dateOfSpecimenCollection;
	}

	public void setDateOfSpecimenCollection(Date dateOfSpecimenCollection) {
		this.dateOfSpecimenCollection = dateOfSpecimenCollection;
	}

	public Date getDateOfTestPerformance() {
		return dateOfTestPerformance;
	}

	public void setDateOfTestPerformance(Date dateOfTestPerformance) {
		this.dateOfTestPerformance = dateOfTestPerformance;
	}

	public String getPlaceOfSpecimenCollection() {
		return placeOfSpecimenCollection;
	}

	public void setPlaceOfSpecimenCollection(String placeOfSpecimenCollection) {
		this.placeOfSpecimenCollection = placeOfSpecimenCollection;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public String getValueText() {
		return valueText;
	}

	public void setValueText(String valueText) {
		this.valueText = valueText;
	}

	public RapidTestDto() {

	}

}
