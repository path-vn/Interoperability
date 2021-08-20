package com.globits.adapter.eclinica.data.domain;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class Person {
	public static final long serialVersionUID = 2L;
	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "person_id")
	protected Integer personId;
	@Column(name = "uuid")
	protected UUID uuid;
	@Column(name = "gender")
	protected String gender;
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "birthdate")
	protected Date birthdate;
	@Column(name = "dead")
	protected boolean dead;
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "death_date")
	protected Date deathDate;
	@Column(name = "cause_of_death")
	protected String causeOfDeath;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	protected Set<PersonName> names;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	protected Set<PersonAttribute> attributes;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	protected Set<PersonAddress> addresses;
	@Column(name = "voided")
	protected boolean voided;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	protected Set<Obs> obs;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	protected Set<ActiveList> activeLists;
	@Column(name = "date_changed")
	protected Date dateChanged;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public Set<PersonName> getNames() {
		return names;
	}

	public void setNames(Set<PersonName> names) {
		this.names = names;
	}

	public Set<PersonAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<PersonAttribute> attributes) {
		this.attributes = attributes;
	}

	public Set<PersonAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<PersonAddress> addresses) {
		this.addresses = addresses;
	}

	public boolean isVoided() {
		return voided;
	}

	public void setVoided(boolean voided) {
		this.voided = voided;
	}

	public Set<Obs> getObs() {
		return obs;
	}

	public void setObs(Set<Obs> obs) {
		this.obs = obs;
	}

	public Set<ActiveList> getActiveLists() {
		return activeLists;
	}

	public void setActiveLists(Set<ActiveList> activeLists) {
		this.activeLists = activeLists;
	}

	public Date getDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	public Person() {

	}

	public Person(Person person) {
		this.personId = person.getPersonId();
		this.uuid = person.getUuid();
		this.gender = person.getGender();
		this.birthdate = person.getBirthdate();
//		if (person.getAddress() != null) {
//			Set<PersonAddress> address = new HashSet<PersonAddress>();
//			for (PersonAddress tt : person.getAddress() ) {
//				PersonAddress ttDto = new PersonAddress();
//				ttDto=tt;
//				address.add(ttDto);
//			}
//			this.address=address;
//		}		
	}
}
