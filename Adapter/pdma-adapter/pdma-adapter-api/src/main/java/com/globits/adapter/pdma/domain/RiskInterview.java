package com.globits.adapter.pdma.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.pdma.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_risk_interview")
public class RiskInterview {

	@Transient
	private static final long serialVersionUID = -3199956455915747783L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = false)
	private Organization organization;

	@ManyToOne
	@JoinColumn(name = "case_id", nullable = false)
	private Case theCase;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "interview_date", nullable = false)
	private LocalDateTime interviewDate;

	@Column(name = "risk_identified", nullable = false)
	private Boolean riskIdentified;

	@Column(name = "other_risk", length = 255, nullable = true)
	private String otherRiskGroupText;

	@ManyToMany
	@JoinTable(
			name = "tbl_riskinterview_risks",
			joinColumns = @JoinColumn(name = "interview_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "risk_id", referencedColumnName = "id"))
	private Set<Dictionary> risks = new HashSet<>();

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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Case getTheCase() {
		return theCase;
	}

	public void setTheCase(Case theCase) {
		this.theCase = theCase;
	}

	public LocalDateTime getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDateTime interviewDate) {
		this.interviewDate = interviewDate;
	}

	public Boolean getRiskIdentified() {
		return riskIdentified;
	}

	public void setRiskIdentified(Boolean riskIdentified) {
		this.riskIdentified = riskIdentified;
	}

	public String getOtherRiskGroupText() {
		return otherRiskGroupText;
	}

	public void setOtherRiskGroupText(String otherRiskGroupText) {
		this.otherRiskGroupText = otherRiskGroupText;
	}

	public Set<Dictionary> getRisks() {

		if (risks == null) {
			risks = new HashSet<>();
		}

		return risks;
	}

	public void setRisks(Set<Dictionary> risks) {
		this.risks = risks;
	}
}
