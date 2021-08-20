package com.globits.facility.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.globits.core.domain.BaseObject;
import com.globits.facility.utilities.FacilityEnums.SystemType;

@Entity
@Table(name = "tbl_system_address_code")
@XmlRootElement
//Đơn vị hành chính theo hệ thống
public class SystemAddressCode extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "administrative_unit_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private FacilityAdministrativeUnit administrativeUnit;// Đơn vị hành chính

	private SystemType systemType;// hệ thống

	private String code;

	private String name;

	private Integer level;

	public FacilityAdministrativeUnit getAdministrativeUnit() {
		return administrativeUnit;
	}

	public void setAdministrativeUnit(FacilityAdministrativeUnit administrativeUnit) {
		this.administrativeUnit = administrativeUnit;
	}

	public SystemType getSystemType() {
		return systemType;
	}

	public void setSystemType(SystemType systemType) {
		this.systemType = systemType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
