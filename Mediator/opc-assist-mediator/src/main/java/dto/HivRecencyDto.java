package dto;

public class HivRecencyDto {
	private String dateOfTestPerformance;
	private String testResult;
	private String testResultOther;
	private String recentInfectionConclusion;
	
	public String getDateOfTestPerformance() {
		return dateOfTestPerformance;
	}
	public void setDateOfTestPerformance(String dateOfTestPerformance) {
		this.dateOfTestPerformance = dateOfTestPerformance;
	}
	
	public String getTestResultOther() {
		return testResultOther;
	}
	public void setTestResultOther(String testResultOther) {
		this.testResultOther = testResultOther;
	}
	public String getRecentInfectionConclusion() {
		return recentInfectionConclusion;
	}
	public void setRecentInfectionConclusion(String recentInfectionConclusion) {
		this.recentInfectionConclusion = recentInfectionConclusion;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
}
