package com.globits.adapter.opcassist.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_admin_unit")
public class AdminUnit {
	@Transient
	private static final long serialVersionUID = -300384104809700661L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code_gso", unique = true, nullable = false)
	private String codeGso;

	@Column(name = "code", length = 100, unique = true, nullable = false)
	private String code;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "level", nullable = false)
	private Integer level;

	@Column(name = "is_voided", nullable = false)
	private Boolean voided;

	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private AdminUnit parent;

	@OneToMany(mappedBy = "parent")
	private Set<AdminUnit> children = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeGso() {
		return codeGso;
	}

	public void setCodeGso(String codeGso) {
		this.codeGso = codeGso;
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

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public AdminUnit getParent() {
		return parent;
	}

	public void setParent(AdminUnit parent) {
		this.parent = parent;
	}

	public Set<AdminUnit> getChildren() {

		if (children == null) {
			children = new HashSet<>();
		}

		return children;
	}

	public void setChildren(Set<AdminUnit> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).append(codeGso).append(code).append(name).append(level)
				.append(voided).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof AdminUnit)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		AdminUnit that = (AdminUnit) obj;

		return new EqualsBuilder().append(id, that.id).append(codeGso, that.codeGso).append(code, that.code)
				.append(name, that.name).append(level, that.level).append(voided, that.voided).isEquals();
	}
}