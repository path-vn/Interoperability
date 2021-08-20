package com.globits.hivimportcsadapter.dto;

public class HivRecencyTestDto {
	private LabTestDto rapidTest;
    private LabTestDto vlTest;
    private SystemCodeDto recentInfectionConclusion;
    
    public LabTestDto getRapidTest() {
        return rapidTest;
    }
    public void setRapidTest(LabTestDto rapidTest) {
        this.rapidTest = rapidTest;
    }
    public LabTestDto getVlTest() {
        return vlTest;
    }
    public void setVlTest(LabTestDto vlTest) {
        this.vlTest = vlTest;
    }
    public SystemCodeDto getRecentInfectionConclusion() {
        return recentInfectionConclusion;
    }
    public void setRecentInfectionConclusion(SystemCodeDto recentInfectionConclusion) {
        this.recentInfectionConclusion = recentInfectionConclusion;
    }
}
