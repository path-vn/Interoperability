package com.globits.hiv.receive.dto;

import java.util.Date;

import com.globits.hiv.receive.valueset.WhoStageValueSet;

public class WHOStateDto {
    
    private SystemCodeDto whoClinicalStage;
    private Date dateWhoStage;

    public SystemCodeDto getWhoClinicalStage() {
        return whoClinicalStage;
    }
    public void setWhoClinicalStage(SystemCodeDto whoClinicalStage) {
        this.whoClinicalStage = whoClinicalStage;
    }
    public Date getDateWhoStage() {
        return dateWhoStage;
    }
    public void setDateWhoStage(Date dateWhoStage) {
        this.dateWhoStage = dateWhoStage;
    }

    
}
