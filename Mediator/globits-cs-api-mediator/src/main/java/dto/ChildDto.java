package dto;

import java.math.BigDecimal;
import java.util.Date;

public class ChildDto {

    private BigDecimal birthWeight;
    private Boolean birthDefects;
    private SystemCodeDto hivStatus;
    private Date hivDiagnosisDate;
    private Date childArvStartDate;

    public BigDecimal getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(BigDecimal birthWeight) {
        this.birthWeight = birthWeight;
    }

    public Boolean getBirthDefects() {
        return birthDefects;
    }

    public void setBirthDefects(Boolean birthDefects) {
        this.birthDefects = birthDefects;
    }

    public SystemCodeDto getHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(SystemCodeDto hivStatus) {
        this.hivStatus = hivStatus;
    }

    public Date getHivDiagnosisDate() {
        return hivDiagnosisDate;
    }

    public void setHivDiagnosisDate(Date hivDiagnosisDate) {
        this.hivDiagnosisDate = hivDiagnosisDate;
    }

    public Date getChildArvStartDate() {
        return childArvStartDate;
    }

    public void setChildArvStartDate(Date childArvStartDate) {
        this.childArvStartDate = childArvStartDate;
    }

}
