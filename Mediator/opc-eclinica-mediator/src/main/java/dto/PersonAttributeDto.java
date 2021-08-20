package dto;

import java.util.UUID;

public class PersonAttributeDto {
	private Integer personAttributeId;
//	private Person person;
	private UUID uuid;
	private String value;
	private PersonAttributeTypeDto personAttributeType;
	private Integer personId;

	private boolean voided;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public boolean isVoided() {
		return voided;
	}

	public void setVoided(boolean voided) {
		this.voided = voided;
	}

	public Integer getPersonAttributeId() {
		return personAttributeId;
	}

	public void setPersonAttributeId(Integer personAttributeId) {
		this.personAttributeId = personAttributeId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PersonAttributeTypeDto getPersonAttributeType() {
		return personAttributeType;
	}

	public void setPersonAttributeType(PersonAttributeTypeDto personAttributeType) {
		this.personAttributeType = personAttributeType;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public PersonAttributeDto() {
	}

}
