package com.globits.adapter.pdma.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_organization_unit")
public class Organization {
	@Transient
	private static final long serialVersionUID = 7003094500428536371L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "level", nullable = false)
	private Integer level;

	// recently added -- nullable
	@Column(name = "code", length = 100, unique = true, nullable = true)
	private String code;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "description", length = 1024, nullable = true)
	private String description;

	@Column(name = "is_active", nullable = false)
	private Boolean active;

	@Column(name = "is_pepfar_site", nullable = false)
	private Boolean pepfarSite;

	@Column(name = "is_hts_site", nullable = true)
	private Boolean htsSite;

	@Column(name = "is_opc_site", nullable = true)
	private Boolean opcSite;

	@Column(name = "is_prep_site", nullable = true)
	private Boolean prepSite;

	@Column(name = "is_pns_site", nullable = true)
	private Boolean pnsSite;

	@Column(name = "is_confirm_lab", nullable = true)
	private Boolean confirmLab;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = true)
	private Location address;

	@Column(name = "phone_number_1", length = 20, nullable = true)
	private String phoneNumber1;

	@Column(name = "phone_number_2", length = 20, nullable = true)
	private String phoneNumber2;

	@Column(name = "fax_number_1", length = 20, nullable = true)
	private String faxNumber1;

	@Column(name = "fax_number_2", length = 20, nullable = true)
	private String faxNumber2;

	@Column(name = "email_address", length = 150, nullable = true)
	private String emailAddress;

	@Column(name = "website_address", length = 150, nullable = true)
	private String websiteAddress;

	@ManyToOne()
	@JoinColumn(name = "parent_id", nullable = true)
	private Organization parent;

	@OneToMany(mappedBy = "parent")
	private Set<Organization> children = new LinkedHashSet<>();

	@OneToMany(mappedBy = "theCase", cascade = CascadeType.ALL)
	@OrderBy("id DESC")
	private Set<CaseOrg> caseOrgs = new LinkedHashSet<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Location getAddress() {
		return address;
	}

	public Boolean getPepfarSite() {
		return pepfarSite;
	}

	public void setPepfarSite(Boolean pepfarSite) {
		this.pepfarSite = pepfarSite;
	}

	public Boolean getHtsSite() {
		return htsSite;
	}

	public void setHtsSite(Boolean htsSite) {
		this.htsSite = htsSite;
	}

	public Boolean getOpcSite() {
		return opcSite;
	}

	public void setOpcSite(Boolean opcSite) {
		this.opcSite = opcSite;
	}

	public Boolean getPrepSite() {
		return prepSite;
	}

	public void setPrepSite(Boolean prepSite) {
		this.prepSite = prepSite;
	}

	public Boolean getPnsSite() {
		return pnsSite;
	}

	public void setPnsSite(Boolean pnsSite) {
		this.pnsSite = pnsSite;
	}

	public Boolean getConfirmLab() {
		return confirmLab;
	}

	public void setConfirmLab(Boolean confirmLab) {
		this.confirmLab = confirmLab;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getFaxNumber1() {
		return faxNumber1;
	}

	public void setFaxNumber1(String faxNumber1) {
		this.faxNumber1 = faxNumber1;
	}

	public String getFaxNumber2() {
		return faxNumber2;
	}

	public void setFaxNumber2(String faxNumber2) {
		this.faxNumber2 = faxNumber2;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getWebsiteAddress() {
		return websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public Set<Organization> getChildren() {

		if (children == null) {
			children = new LinkedHashSet<>();
		}

		return children;
	}

	public void setChildren(Set<Organization> children) {
		this.children = children;
	}
	
}
