package com.globits.adapter.opcassist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_regimen")
public class Regimen {
	@Transient
	private static final long serialVersionUID = -468218118776197363L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "regimen_short_name", unique = true, length = 100, nullable = true)
	private String shortName;

	@Column(name = "regimen_name", length = 255, nullable = false)
	private String name;

	@Column(name = "regimen_line", nullable = false)
	private Integer line;

	@ManyToOne
	@JoinColumn(name = "disease_id", nullable = false)
	private Dictionary disease;

	@Column(name = "description", nullable = true)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public Dictionary getDisease() {
		return disease;
	}

	public void setDisease(Dictionary disease) {
		this.disease = disease;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
