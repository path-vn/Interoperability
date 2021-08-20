package dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HivPregnancyDto {

    private String createDate;
	private String childDeliveryPlace;
	private String childDateOfBirth;
	private double birthWeight;
	private String pregResult;
	private String childDiagnosedDate;
	private int childHivStatus;
    
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getChildDeliveryPlace() {
        return childDeliveryPlace;
    }
    public void setChildDeliveryPlace(String childDeliveryPlace) {
        this.childDeliveryPlace = childDeliveryPlace;
    }
    public String getChildDateOfBirth() {
        return childDateOfBirth;
    }
    public void setChildDateOfBirth(String childDateOfBirth) {
        this.childDateOfBirth = childDateOfBirth;
    }
    public double getBirthWeight() {
        return birthWeight;
    }
    public void setBirthWeight(double birthWeight) {
        this.birthWeight = birthWeight;
    }
    public String getPregResult() {
        return pregResult;
    }
    public void setPregResult(String pregResult) {
        this.pregResult = pregResult;
    }
    public String getChildDiagnosedDate() {
        return childDiagnosedDate;
    }
    public void setChildDiagnosedDate(String childDiagnosedDate) {
        this.childDiagnosedDate = childDiagnosedDate;
    }
    public int getChildHivStatus() {
        return childHivStatus;
    }
    public void setChildHivStatus(int childHivStatus) {
        this.childHivStatus = childHivStatus;
    }

   
    

}
