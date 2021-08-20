package com.globits.adapter.pdma.dto;

import java.io.Serializable;

import com.globits.adapter.pdma.domain.Organization;

public class OrganizationDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer level;

	private String code;

	private String name;

	private String description;

	private Boolean active;

	private Boolean pepfarSite;

	private Boolean htsSite;

	private Boolean opcSite;

	private Boolean prepSite;

	private Boolean pnsSite;

	private Boolean confirmLab;

	private LocationDto address;

	private String phoneNumber1;

	private String phoneNumber2;

	private String faxNumber1;

	private String faxNumber2;

	private String emailAddress;

	private String websiteAddress;

	private OrganizationDto parent;

	public OrganizationDto() {
		
	}

	public OrganizationDto(Organization entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.level = entity.getLevel();
		this.code = entity.getCode();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.active = entity.getActive();
		this.pepfarSite = entity.getPepfarSite();
		this.htsSite = entity.getHtsSite();
		this.opcSite = entity.getOpcSite();
		this.prepSite = entity.getPrepSite();
		this.pnsSite = entity.getPnsSite();
		this.confirmLab = entity.getConfirmLab();

		this.phoneNumber1 = entity.getPhoneNumber1();
		this.phoneNumber2 = entity.getPhoneNumber2();
		this.faxNumber1 = entity.getFaxNumber1();
		this.faxNumber2 = entity.getFaxNumber2();
		this.emailAddress = entity.getEmailAddress();
		this.websiteAddress = entity.getWebsiteAddress();

		if (entity.getAddress() != null) {
			this.address = new LocationDto(entity.getAddress());
		}

		if (entity.getParent() != null) {
			OrganizationDto _parent = new OrganizationDto();
			_parent.setId(entity.getParent().getId());
			_parent.setLevel(entity.getLevel());

			this.parent = _parent;
		}
	}
	
	public OrganizationDto(Organization entity,boolean collapse) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.level = entity.getLevel();
		this.code = entity.getCode();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.active = entity.getActive();
		this.pepfarSite = entity.getPepfarSite();
		this.htsSite = entity.getHtsSite();
		this.opcSite = entity.getOpcSite();
		this.prepSite = entity.getPrepSite();
		this.pnsSite = entity.getPnsSite();
		this.confirmLab = entity.getConfirmLab();

		this.phoneNumber1 = entity.getPhoneNumber1();
		this.phoneNumber2 = entity.getPhoneNumber2();
		this.faxNumber1 = entity.getFaxNumber1();
		this.faxNumber2 = entity.getFaxNumber2();
		this.emailAddress = entity.getEmailAddress();
		this.websiteAddress = entity.getWebsiteAddress();
		
		if(!collapse) {
			if (entity.getAddress() != null) {
				this.address = new LocationDto(entity.getAddress());
			}

			if (entity.getParent() != null) {
				OrganizationDto _parent = new OrganizationDto();
				_parent.setId(entity.getParent().getId());
				_parent.setLevel(entity.getLevel());

				this.parent = _parent;
			}
		}		
	}

	public Organization toEntity() {
		Organization entity = new Organization();

		entity.setId(id);
		entity.setLevel(level);
		entity.setCode(code);
		entity.setName(name);
		entity.setDescription(description);
		entity.setActive(active);
		entity.setPepfarSite(pepfarSite);
		entity.setHtsSite(htsSite);
		entity.setOpcSite(opcSite);
		entity.setPrepSite(prepSite);
		entity.setPnsSite(pnsSite);
		entity.setConfirmLab(confirmLab);
		entity.setPhoneNumber1(phoneNumber1);
		entity.setPhoneNumber2(phoneNumber2);
		entity.setFaxNumber1(faxNumber1);
		entity.setFaxNumber2(faxNumber2);
		entity.setEmailAddress(emailAddress);
		entity.setWebsiteAddress(websiteAddress);

		if (address != null) {
			entity.setAddress(address.toEntity());
		}

		if (parent != null) {
			entity.setParent(parent.toEntity());
		}

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public LocationDto getAddress() {
		return address;
	}

	public void setAddress(LocationDto address) {
		this.address = address;
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

	public OrganizationDto getParent() {
		return parent;
	}

	public void setParent(OrganizationDto parent) {
		this.parent = parent;
	}
}
