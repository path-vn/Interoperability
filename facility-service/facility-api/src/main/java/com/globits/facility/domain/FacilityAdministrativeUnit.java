package com.globits.facility.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_facility_administrative_unit")
@XmlRootElement
//Đơn vị hành chính
public class FacilityAdministrativeUnit extends BaseObject {
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
	@Column(name = "level")
	private Integer level;
	@Column(name = "map_code")
	private String mapCode;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private FacilityAdministrativeUnit parent;// Đơn vị cha

	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private Set<FacilityAdministrativeUnit> subAdministrativeUnits;// Đơn vị con

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMapCode() {
		return mapCode;
	}

	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
	}

	public FacilityAdministrativeUnit getParent() {
		return parent;
	}

	public void setParent(FacilityAdministrativeUnit parent) {
		this.parent = parent;
	}

	public Set<FacilityAdministrativeUnit> getSubAdministrativeUnits() {
		return subAdministrativeUnits;
	}

	public void setSubAdministrativeUnits(Set<FacilityAdministrativeUnit> subAdministrativeUnits) {
		this.subAdministrativeUnits = subAdministrativeUnits;
	}

}
