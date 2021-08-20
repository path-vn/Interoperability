package com.globits.adapter.pdma.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.pdma.utils.UUIDAttributeConverter;
@Entity
@Table(name = "tbl_case")
public class Case {

	@Transient
	private static final long serialVersionUID = 4328797164530953140L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;


	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;
	
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "modify_date", nullable = true)
	private LocalDateTime modifyDate;
	
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "hiv_screen_date", nullable = true)
	private LocalDateTime hivScreenDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "hiv_confirmation_date", nullable = true)
	private LocalDateTime hivConfirmDate;

	@Column(name = "confirm_lab_name", nullable = true)
	private String confirmLabName; // for conversion of data from OPC-Assist

	@OneToOne
	@JoinColumn(name = "confirm_lab_id", nullable = true)
	private HIVConfirmLab confirmLab;

	@Column(name = "hiv_confirmation_id", nullable = true)
	private String hivConfirmId;

	@Column(name = "national_health_id", nullable = true)
	private String nationalHealthId;

	@Column(name = "shi_number", nullable = true)
	private String shiNumber;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "last_sync_date", nullable = true)
	private LocalDateTime lastSyncDate;

	@Column(name = "hivinfo_id", nullable = true)
	private String hivInfoID;

	@Column(name = "hivinfo_id_locked", nullable = true)
	private Boolean hivInfoIdLocked;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "arv_start_date", nullable = true)
	private LocalDateTime arvStartDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "second_line_start_date", nullable = true)
	private LocalDateTime secondLineStartDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "third_line_start_date", nullable = true)
	private LocalDateTime thirdLineStartDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "fourth_line_start_date", nullable = true)
	private LocalDateTime fourthLineStartDate;

	@ManyToOne
	@JoinColumn(name = "arv_regimen_id", nullable = true)
	private Regimen currentArvRegimen;

	@Column(name = "arv_regimen_name", nullable = true)
	private String currentArvRegimenName; // For conversion of data from legacy systems

	@Column(name = "arv_regimen_line", nullable = true)
	private Integer currentArvRegimenLine; // for reporting purpose

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "arv_regimen_start_date", nullable = true)
	private LocalDateTime currentArvRegimenStartDate;

	// Latest WHO stage
	@Column(name = "who_stage", nullable = true)
	private Integer whoStage;

	// Latest WHO stage evaluation date
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "who_stage_eval_date", nullable = true)
	private LocalDateTime whoStageEvalDate;

	@Column(name = "deleted", nullable = false)
	private boolean deleted;

	// Extracted from weekly report
	// 0 = undetermined, 1 = new, 2 = old chua DT, 3 = Ca cu bo tri, 4 = Ngoai tinh
//	@Column(name = "case_status", nullable = false)
//	private int caseStatus;

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("evalDate DESC")
	private Set<ClinicalStage> whoStages = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL) // { CascadeType.PERSIST, CascadeType.REFRESH,
																// CascadeType.REMOVE }
	@OrderBy("startDate DESC, id DESC")
	private Set<CaseOrg> caseOrgs = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("sampleDate DESC")
	private Set<LabTest> labTests = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("startDate DESC")
	private Set<Treatment> treatments = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("interviewDate DESC")
	private Set<RiskInterview> riskInterviews = new LinkedHashSet<>();


	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("createDate DESC")
	private Set<Pregnancy> pregnancies = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("testDate DESC")
	private Set<Hepatitis> hepatitises = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("screenSampleDate DESC")
	private Set<Recency> recencies = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("startDate DESC")
	private Set<TBProphylaxis> tbpros = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("diagnoseDate DESC")
	private Set<TBTreatment> tbtxs = new LinkedHashSet<>();


	@Lob
	@Column(name = "note", nullable = true)
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public LocalDateTime getHivScreenDate() {
		return hivScreenDate;
	}

	public void setHivScreenDate(LocalDateTime hivScreenDate) {
		this.hivScreenDate = hivScreenDate;
	}

	public LocalDateTime getHivConfirmDate() {
		return hivConfirmDate;
	}

	public void setHivConfirmDate(LocalDateTime hivConfirmDate) {
		this.hivConfirmDate = hivConfirmDate;
	}

	public HIVConfirmLab getConfirmLab() {
		return confirmLab;
	}

	public void setConfirmLab(HIVConfirmLab confirmLab) {
		this.confirmLab = confirmLab;
	}

	public String getHivConfirmId() {
		return hivConfirmId;
	}

	public void setHivConfirmId(String hivConfirmId) {
		this.hivConfirmId = hivConfirmId;
	}

	public String getHivInfoID() {
		return hivInfoID;
	}

	public void setHivInfoID(String hivInfoID) {
		this.hivInfoID = hivInfoID;
	}

	public Boolean getHivInfoIdLocked() {
		return hivInfoIdLocked;
	}

	public void setHivInfoIdLocked(Boolean hivInfoIdLocked) {
		this.hivInfoIdLocked = hivInfoIdLocked;
	}

	public LocalDateTime getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(LocalDateTime lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	public String getNationalHealthId() {
		return nationalHealthId;
	}

	public void setNationalHealthId(String nationalHealthId) {
		this.nationalHealthId = nationalHealthId;
	}

	public String getShiNumber() {
		return shiNumber;
	}

	public void setShiNumber(String shiNumber) {
		this.shiNumber = shiNumber;
	}

	public String getCurrentArvRegimenName() {
		return currentArvRegimenName;
	}

	public void setCurrentArvRegimenName(String currentArvRegimenName) {
		this.currentArvRegimenName = currentArvRegimenName;
	}

	public Integer getCurrentArvRegimenLine() {
		return currentArvRegimenLine;
	}

	public void setCurrentArvRegimenLine(Integer currentArvRegimenLine) {
		this.currentArvRegimenLine = currentArvRegimenLine;
	}

	public LocalDateTime getCurrentArvRegimenStartDate() {
		return currentArvRegimenStartDate;
	}

	public void setCurrentArvRegimenStartDate(LocalDateTime currentArvRegimenStartDate) {
		this.currentArvRegimenStartDate = currentArvRegimenStartDate;
	}

	public LocalDateTime getArvStartDate() {
		return arvStartDate;
	}

	public void setArvStartDate(LocalDateTime arvStartDate) {
		this.arvStartDate = arvStartDate;
	}

	public LocalDateTime getSecondLineStartDate() {
		return secondLineStartDate;
	}

	public void setSecondLineStartDate(LocalDateTime secondLineStartDate) {
		this.secondLineStartDate = secondLineStartDate;
	}

	public LocalDateTime getThirdLineStartDate() {
		return thirdLineStartDate;
	}

	public void setThirdLineStartDate(LocalDateTime thirdLineStartDate) {
		this.thirdLineStartDate = thirdLineStartDate;
	}

	public LocalDateTime getFourthLineStartDate() {
		return fourthLineStartDate;
	}

	public void setFourthLineStartDate(LocalDateTime fourthLineStartDate) {
		this.fourthLineStartDate = fourthLineStartDate;
	}

	public Regimen getCurrentArvRegimen() {
		return currentArvRegimen;
	}

	public void setCurrentArvRegimen(Regimen currentArvRegimen) {
		this.currentArvRegimen = currentArvRegimen;
	}

	public String getConfirmLabName() {
		return confirmLabName;
	}

	public void setConfirmLabName(String confirmLabName) {
		this.confirmLabName = confirmLabName;
	}

	public Integer getWhoStage() {
		return whoStage;
	}

	public void setWhoStage(Integer whoStage) {
		this.whoStage = whoStage;
	}

	public LocalDateTime getWhoStageEvalDate() {
		return whoStageEvalDate;
	}

	public void setWhoStageEvalDate(LocalDateTime whoStageEvalDate) {
		this.whoStageEvalDate = whoStageEvalDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

//	public int getCaseStatus() {
//		return caseStatus;
//	}
//
//	public void setCaseStatus(int caseStatus) {
//		this.caseStatus = caseStatus;
//	}

	public Set<ClinicalStage> getWhoStages() {
		return whoStages;
	}

	public void setWhoStages(Set<ClinicalStage> whoStages) {
		this.whoStages = whoStages;
	}

	public Set<CaseOrg> getCaseOrgs() {
		if (caseOrgs == null) {
			caseOrgs = new LinkedHashSet<>();
		}

		return caseOrgs;
	}

	public void setCaseOrgs(Set<CaseOrg> caseOrgs) {
		this.caseOrgs = caseOrgs;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<LabTest> getLabTests() {
		if (labTests == null) {
			labTests = new LinkedHashSet<>();
		}

		return labTests;
	}

	public void setLabTests(Set<LabTest> labTests) {
		this.labTests = labTests;
	}

	public Set<Treatment> getTreatments() {
		if (treatments == null) {
			treatments = new LinkedHashSet<>();
		}
		return treatments;
	}

	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}

	public Set<RiskInterview> getRiskInterviews() {

		if (riskInterviews == null) {
			riskInterviews = new LinkedHashSet<>();
		}

		return riskInterviews;
	}

	public void setRiskInterviews(Set<RiskInterview> riskInterviews) {
		this.riskInterviews = riskInterviews;
	}


	public Set<Hepatitis> getHepatitises() {
		if (hepatitises == null) {
			hepatitises = new LinkedHashSet<>();
		}

		return hepatitises;
	}

	public void setHepatitises(Set<Hepatitis> hepatitises) {
		this.hepatitises = hepatitises;
	}

	public Set<Pregnancy> getPregnancies() {
		if (pregnancies == null) {
			pregnancies = new LinkedHashSet<>();
		}

		return pregnancies;
	}

	public void setPregnancies(Set<Pregnancy> pregnancies) {
		this.pregnancies = pregnancies;
	}

	

	public Set<Recency> getRecencies() {
		if (recencies == null) {
			recencies = new LinkedHashSet<>();
		}

		return recencies;
	}

	public void setRecencies(Set<Recency> recencies) {
		this.recencies = recencies;
	}

	public Set<TBProphylaxis> getTbpros() {
		if (tbpros == null) {
			tbpros = new LinkedHashSet<>();
		}

		return tbpros;
	}

	public void setTbpros(Set<TBProphylaxis> tbpros) {
		this.tbpros = tbpros;
	}

	public Set<TBTreatment> getTbtxs() {
		if (tbtxs == null) {
			tbtxs = new LinkedHashSet<>();
		}

		return tbtxs;
	}

	public void setTbtxs(Set<TBTreatment> tbtxs) {
		this.tbtxs = tbtxs;
	}



	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).append(uid).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Case)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		Case that = (Case) obj;

		return new EqualsBuilder().append(id, that.id).append(uid, that.uid).isEquals();
	}
}
