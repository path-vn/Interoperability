package dto;

import java.util.Date;

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
