package com.globits.adapter.eclinica.data.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Patient")
@PrimaryKeyJoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "patient_person", value = ConstraintMode.NO_CONSTRAINT))
@DiscriminatorValue("2")
public class Patient extends Person {
//	@Id
//	@Column(name="patient_id")
//	private Integer id;
	@Column(name = "tribe")
	private Integer tribe;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<PatientIdentifier> identifiers;
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public Integer getTribe() {
		return tribe;
	}

	public void setTribe(Integer tribe) {
		this.tribe = tribe;
	}

	public Set<PatientIdentifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<PatientIdentifier> identifiers) {
		this.identifiers = identifiers;
	}

	public Patient() {

	}

	public Patient(Patient p) {
		super(p);
	}

}
