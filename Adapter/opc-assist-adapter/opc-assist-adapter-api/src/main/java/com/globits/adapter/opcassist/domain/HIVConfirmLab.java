package com.globits.adapter.opcassist.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_hivconfirm_lab")
public class HIVConfirmLab {
	@Transient
	private static final long serialVersionUID = -339574627475895840L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	private Long id;

	@Column(name = "code", length = 255, nullable = true)
	private String code;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = true)
	private Location address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).append(code).append(name).append(address).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof HIVConfirmLab)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		HIVConfirmLab that = (HIVConfirmLab) obj;

		return new EqualsBuilder().append(id, that.id).append(code, that.code).append(name, that.name)
				.append(address, that.address).isEquals();
	}
}
