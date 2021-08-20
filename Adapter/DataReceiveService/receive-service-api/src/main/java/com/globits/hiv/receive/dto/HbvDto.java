package com.globits.hiv.receive.dto;

import java.util.Date;

import com.globits.hiv.receive.valueset.DiagnosisValueSet;

public class HbvDto extends CoMorbidityDto {

    private Date hbsagDate;
    private SystemCodeDto hbsagResult;

    public Date getHbsagDate() {
        return hbsagDate;
    }

    public void setHbsagDate(Date hbsagDate) {
        this.hbsagDate = hbsagDate;
    }

    public SystemCodeDto getHbsagResult() {
        return hbsagResult;
    }

    public void setHbsagResult(SystemCodeDto hbsagResult) {
        this.hbsagResult = hbsagResult;
    }

}
