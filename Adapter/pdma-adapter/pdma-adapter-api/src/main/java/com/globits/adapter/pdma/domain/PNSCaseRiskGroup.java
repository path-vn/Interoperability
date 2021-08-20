package com.globits.adapter.pdma.domain;

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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.types.HTSRiskGroupEnum;
@Entity
@Table(name = "tbl_pns_case_risk_group")
public class PNSCaseRiskGroup {
	@Transient
	private static final long serialVersionUID = -300384104809700661L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pns_case_id", nullable = false)
	private PNSCase pnsCase;	
	
	@Column(name = "name", nullable = false)	
	private String name;

	@Column(name = "val", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private HTSRiskGroupEnum val;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PNSCase getPnsCase() {
		return pnsCase;
	}

	public void setPnsCase(PNSCase pnsCase) {
		this.pnsCase = pnsCase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HTSRiskGroupEnum getVal() {
		return val;
	}
	
	public void setVal(HTSRiskGroupEnum val) {
		this.val = val;
	}
}
