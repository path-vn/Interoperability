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
@Table(name = "tbl_system_health_organization_code")
@XmlRootElement
//Đơn vị Y tế theo mã hệ thống
public class SystemHealthOrganizationCode extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "health_organization_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private HealthOrganization healthOrganization;// Đơn vị Y tế

	private SystemType systemType;// hệ thống

	private String code;

	private String name;

	private Integer level;

	public HealthOrganization getHealthOrganization() {
		return healthOrganization;
	}

	public void setHealthOrganization(HealthOrganization healthOrganization) {
		this.healthOrganization = healthOrganization;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
