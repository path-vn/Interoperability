package com.globits.adapter.opcassist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.types.DictionaryType;

@Entity
@Table(name = "tbl_dictionary")
public class Dictionary {
	@Transient
	private static final long serialVersionUID = -1198390339253668630L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "dic_type", nullable = false)
	private DictionaryType type;

	@Column(name = "code", unique = true, nullable = true)
	private String code;

	@Column(name = "entry_value", length = 255, nullable = false)
	private String value;

	@Column(name = "entry_value_en", length = 255, nullable = false)
	private String valueEn;

	@Column(name = "description", length = 1024, nullable = true)
	private String description;

	@Column(name = "entry_order", nullable = false)
	private Integer order;

	@Column(name = "is_active", nullable = false)
	private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DictionaryType getType() {
		return type;
	}

	public void setType(DictionaryType type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueEn() {
		return valueEn;
	}

	public void setValueEn(String valueEn) {
		this.valueEn = valueEn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).append(type).append(code).append(value).append(valueEn)
				.append(description).append(order).append(active).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Dictionary)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		Dictionary that = (Dictionary) obj;

		return new EqualsBuilder().append(id, that.id).append(type, that.type).append(code, that.code)
				.append(value, that.value).append(valueEn, that.valueEn).append(description, that.description)
				.append(order, that.order).append(active, that.active).isEquals();
	}
}
