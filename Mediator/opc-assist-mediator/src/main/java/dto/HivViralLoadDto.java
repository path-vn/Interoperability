package dto;

public class HivViralLoadDto {
	private String testReason;
	private String vlSampleDate;
	private String vlResultDate;
	private String vlOrg;
	private String resultText;
	private Long resultNumber;
	private String vlLabName;
	public String getVlSampleDate() {
		return vlSampleDate;
	}
	public void setVlSampleDate(String vlSampleDate) {
		this.vlSampleDate = vlSampleDate;
	}
	public String getVlResultDate() {
		return vlResultDate;
	}
	public void setVlResultDate(String vlResultDate) {
		this.vlResultDate = vlResultDate;
	}
	public String getVlOrg() {
		return vlOrg;
	}
	public void setVlOrg(String vlOrg) {
		this.vlOrg = vlOrg;
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
	public String getVlLabName() {
		return vlLabName;
	}
	public void setVlLabName(String vlLabName) {
		this.vlLabName = vlLabName;
	}
	public String getTestReason() {
		return testReason;
	}
	public void setTestReason(String testReason) {
		this.testReason = testReason;
	}
	
}
