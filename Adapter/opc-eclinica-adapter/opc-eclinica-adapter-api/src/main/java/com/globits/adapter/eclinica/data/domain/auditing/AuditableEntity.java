package com.globits.adapter.eclinica.data.domain.auditing;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.globits.adapter.eclinica.utils.LocalDateTimeAttributeConverter;

@MappedSuperclass
@EntityListeners(value = { EntityAuditListener.class })
public class AuditableEntity implements Serializable
{

	@Transient
	private static final long serialVersionUID = 4559994432567537044L;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "created_by", length = 100, nullable = false)
	private String createdBy;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "modify_date", nullable = true)
	private LocalDateTime modifyDate;

	@Column(name = "modified_by", length = 100, nullable = true)
	private String modifiedBy;

	// -----------------------------
	// GETTERS/SETTERS
	// -----------------------------

	/**
	 * @return the createDate
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifyDate
	 */
	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
