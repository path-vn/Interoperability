package dto;

import java.util.Date;

public class HcvDto extends CoMorbidityDto {

    private Date antiHcvDate;
    private SystemCodeDto antiHcvResult;
    private Date vlDiagnosisDate;
    private Integer vlDiagnosisResult;

    public Date getAntiHcvDate() {
        return antiHcvDate;
    }

    public void setAntiHcvDate(Date antiHcvDate) {
        this.antiHcvDate = antiHcvDate;
    }

    public SystemCodeDto getAntiHcvResult() {
        return antiHcvResult;
    }

    public void setAntiHcvResult(SystemCodeDto antiHcvResult) {
        this.antiHcvResult = antiHcvResult;
    }

    public Date getVlDiagnosisDate() {
        return vlDiagnosisDate;
    }

    public void setVlDiagnosisDate(Date vlDiagnosisDate) {
        this.vlDiagnosisDate = vlDiagnosisDate;
    }

    public Integer getVlDiagnosisResult() {
        return vlDiagnosisResult;
    }

    public void setVlDiagnosisResult(Integer vlDiagnosisResult) {
        this.vlDiagnosisResult = vlDiagnosisResult;
    }

}
