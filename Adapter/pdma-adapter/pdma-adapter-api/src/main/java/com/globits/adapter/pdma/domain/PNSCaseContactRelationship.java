package com.globits.adapter.pdma.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.types.PNSCaseContactRelationshipType;
@Entity
@Table(name = "tbl_pns_case_contact_relationship")
public class PNSCaseContactRelationship {
	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "pns_case_contact_id", nullable = true)
	private PNSCaseContact pnsCaseContact;
	
	@Column(name = "val", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private PNSCaseContactRelationshipType val;
	
	@Column(name = "name", nullable = false)	
	private String name;
	
	public PNSCaseContactRelationship() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PNSCaseContact getPnsCaseContact() {
		return pnsCaseContact;
	}

	public void setPnsCaseContact(PNSCaseContact pnsCaseContact) {
		this.pnsCaseContact = pnsCaseContact;
	}

	public PNSCaseContactRelationshipType getVal() {
		return val;
	}

	public void setVal(PNSCaseContactRelationshipType val) {
		this.val = val;
	}

	public String getName() {
		if(this.val!=null) {
			this.name = val.getDescription();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
