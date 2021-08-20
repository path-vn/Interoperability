package com.globits.hiv.receive.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.globits.security.domain.User;

@Entity
@Table(name = "tbl_user_system_code")
public class UserSystemCode {
	@Id
	@Type(type = "uuid-char")
	@Column(name = "id", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private UUID id;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "created_by", length = 100, nullable = false)
	private String createdBy;

	@Column(name = "modify_date", nullable = true)
	private Date modifyDate;

	@Column(name = "modified_by", length = 100, nullable = true)
	private String modifiedBy;
	
	@Column(name = "voided",  nullable = true)
	private Boolean voided;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "system_code",  nullable = true)
	private String systemCode;

	public UserSystemCode() {
		this.id = UUID.randomUUID();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}	
}
