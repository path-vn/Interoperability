package dto;

public class HivCd4Dto {
	private String testReason;
	private String sampleDate;
	private String samplePlace;
	private String resultDate;
	private String org;
	private String resultText;
	private Long resultNumber;
	private String labName;

	
	public String getTestReason() {
		return testReason;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getResultText() {
		return resultText;
	}
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	public Long getResultNumber() {
		return resultNumber;
	}
	public void setResultNumber(Long resultNumber) {
		this.resultNumber = resultNumber;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	public String getSamplePlace() {
		return samplePlace;
	}
	public void setSamplePlace(String samplePlace) {
		this.samplePlace = samplePlace;
	}
	public String getSampleDate() {
		return sampleDate;
	}
	public void setSampleDate(String sampleDate) {
		this.sampleDate = sampleDate;
	}
	public void setTestReason(String testReason) {
		this.testReason = testReason;
	}


	
}
