package dto;

import java.util.Date;

public class ObjectDto {
	private Date sampleDate;
	private Date resultDate;
	private String place;
	private Double result;
	private String valueText;

	
	public Date getSampleDate() {
		return sampleDate;
	}


	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}


	public Date getResultDate() {
		return resultDate;
	}


	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
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


	public ObjectDto() {

	}

}
