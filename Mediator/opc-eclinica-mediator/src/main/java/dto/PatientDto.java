package dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class PatientDto extends PersonDto {
	private Integer patientId;
	protected Set<PatientIdentifierDto> identifiers;
	private String identifier;// mã thẻ
	private String locationDefault;//opc hien thoi
	private List<RapidTestDto> listRapidTest;// kiểm tra nhanh
	private List<ObjectDto> listCD4BeforeARV;// cd4 trước arv
	private List<ObjectDto> listCD4DuringARV;// cd4 trong arv

	private List<ObjectDto> listVlResultDuringARV;// tải lượng hiv

	private List<ObjectDto> listDrugResistance;// khả năng kháng thuốc
	private List<RegimenPatientDto> listRegimenHistory;// danh sách thay đổi phác đồ
	private Date arvTreatmentDateStart;// ngày bắt đầu điều trị ARV

	private Date firstARVRegimenStartDate;
	private Date secondARVRegimenStartDate;
	private Date thirdARVRegimenStartDate;

	private Date arvTreatmentDateOfLossToFollowUp;// ngày bỏ trị
	private Date arvTreatmentDateOfTransferredOut;// ngày chuyển đi
	private String arvTreatmentPlaceTransferredOut;// nơi chuyển
	private List<TPTDto> listTPT;// danh sách TPT- dự phòng lao
	private List<Date> tbDiagnosisDate;// ds ngày chuẩn đoán lao
	private List<TBTreatmentDto> listTBTreatment;// danh sách điều trị lao
	private List<HbvDto> listHBV;// ds HBV
	private List<HcvDto> listHCV;// danh sách HCV
	private List<PregnancyDto> listPregnancy;// mang thai

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Set<PatientIdentifierDto> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<PatientIdentifierDto> identifiers) {
		this.identifiers = identifiers;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLocationDefault() {
		return locationDefault;
	}

	public void setLocationDefault(String locationDefault) {
		this.locationDefault = locationDefault;
	}

	public List<RapidTestDto> getListRapidTest() {
		return listRapidTest;
	}

	public void setListRapidTest(List<RapidTestDto> listRapidTest) {
		this.listRapidTest = listRapidTest;
	}

	public List<ObjectDto> getListCD4BeforeARV() {
		return listCD4BeforeARV;
	}

	public void setListCD4BeforeARV(List<ObjectDto> listCD4BeforeARV) {
		this.listCD4BeforeARV = listCD4BeforeARV;
	}

	public List<ObjectDto> getListCD4DuringARV() {
		return listCD4DuringARV;
	}

	public void setListCD4DuringARV(List<ObjectDto> listCD4DuringARV) {
		this.listCD4DuringARV = listCD4DuringARV;
	}

	public List<ObjectDto> getListVlResultDuringARV() {
		return listVlResultDuringARV;
	}

	public void setListVlResultDuringARV(List<ObjectDto> listVlResultDuringARV) {
		this.listVlResultDuringARV = listVlResultDuringARV;
	}

	public List<ObjectDto> getListDrugResistance() {
		return listDrugResistance;
	}

	public void setListDrugResistance(List<ObjectDto> listDrugResistance) {
		this.listDrugResistance = listDrugResistance;
	}

	public Date getArvTreatmentDateStart() {
		return arvTreatmentDateStart;
	}

	public void setArvTreatmentDateStart(Date arvTreatmentDateStart) {
		this.arvTreatmentDateStart = arvTreatmentDateStart;
	}

	public List<RegimenPatientDto> getListRegimenHistory() {
		return listRegimenHistory;
	}

	public void setListRegimenHistory(List<RegimenPatientDto> listRegimenHistory) {
		this.listRegimenHistory = listRegimenHistory;
	}

	public Date getArvTreatmentDateOfLossToFollowUp() {
		return arvTreatmentDateOfLossToFollowUp;
	}

	public void setArvTreatmentDateOfLossToFollowUp(Date arvTreatmentDateOfLossToFollowUp) {
		this.arvTreatmentDateOfLossToFollowUp = arvTreatmentDateOfLossToFollowUp;
	}

	public Date getArvTreatmentDateOfTransferredOut() {
		return arvTreatmentDateOfTransferredOut;
	}

	public void setArvTreatmentDateOfTransferredOut(Date arvTreatmentDateOfTransferredOut) {
		this.arvTreatmentDateOfTransferredOut = arvTreatmentDateOfTransferredOut;
	}

	public String getArvTreatmentPlaceTransferredOut() {
		return arvTreatmentPlaceTransferredOut;
	}

	public void setArvTreatmentPlaceTransferredOut(String arvTreatmentPlaceTransferredOut) {
		this.arvTreatmentPlaceTransferredOut = arvTreatmentPlaceTransferredOut;
	}

	public Date getFirstARVRegimenStartDate() {
		return firstARVRegimenStartDate;
	}

	public void setFirstARVRegimenStartDate(Date firstARVRegimenStartDate) {
		this.firstARVRegimenStartDate = firstARVRegimenStartDate;
	}

	public Date getSecondARVRegimenStartDate() {
		return secondARVRegimenStartDate;
	}

	public void setSecondARVRegimenStartDate(Date secondARVRegimenStartDate) {
		this.secondARVRegimenStartDate = secondARVRegimenStartDate;
	}

	public Date getThirdARVRegimenStartDate() {
		return thirdARVRegimenStartDate;
	}

	public void setThirdARVRegimenStartDate(Date thirdARVRegimenStartDate) {
		this.thirdARVRegimenStartDate = thirdARVRegimenStartDate;
	}

	public List<TPTDto> getListTPT() {
		return listTPT;
	}

	public void setListTPT(List<TPTDto> listTPT) {
		this.listTPT = listTPT;
	}

	public List<Date> getTbDiagnosisDate() {
		return tbDiagnosisDate;
	}

	public void setTbDiagnosisDate(List<Date> tbDiagnosisDate) {
		this.tbDiagnosisDate = tbDiagnosisDate;
	}

	public List<TBTreatmentDto> getListTBTreatment() {
		return listTBTreatment;
	}

	public void setListTBTreatment(List<TBTreatmentDto> listTBTreatment) {
		this.listTBTreatment = listTBTreatment;
	}

	public List<HbvDto> getListHBV() {
		return listHBV;
	}

	public void setListHBV(List<HbvDto> listHBV) {
		this.listHBV = listHBV;
	}

	public List<HcvDto> getListHCV() {
		return listHCV;
	}

	public void setListHCV(List<HcvDto> listHCV) {
		this.listHCV = listHCV;
	}

	public List<PregnancyDto> getListPregnancy() {
		return listPregnancy;
	}

	public void setListPregnancy(List<PregnancyDto> listPregnancy) {
		this.listPregnancy = listPregnancy;
	}
}
