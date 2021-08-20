package dto;

public class HivPregnancyDto {
	private String createDate;
	private String childDeliveryPlace;
	private String childDateOfBirth;
	private double birthWeight;
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
	public int getChildHivStatus() {
		return childHivStatus;
	}
	public void setChildHivStatus(int childHivStatus) {
		this.childHivStatus = childHivStatus;
	}
	
}
