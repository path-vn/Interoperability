package com.globits.hiv.receive.dto;

import java.util.Date;
import java.util.UUID;

import com.globits.hiv.receive.domain.UserSystemCode;
import com.globits.security.dto.UserDto;

public class UserSystemCodeDto {
	
	private UUID id;
	
	private Date createDate;

	private String createdBy;

	private Date modifyDate;

	private String modifiedBy;
	
	private Boolean voided;
	
	private UserDto user;
	
	private String systemCode;

	public UserSystemCodeDto() {
		
	}
	
	public UserSystemCodeDto(UserSystemCode entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.createDate = entity.getCreateDate();
			this.createdBy = entity.getCreatedBy();
			this.modifyDate = entity.getModifyDate();
			this.modifiedBy = entity.getModifiedBy();
			this.voided = entity.getVoided();
			if(entity.getUser()!=null) {
				this.user = new UserDto(entity.getUser());
			}
			this.systemCode = entity.getSystemCode();
		}
		
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}	
}
