package com.globits.adapter.eclinica.data.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.adapter.eclinica.data.domain.auditing.BaseOpenmrsData;

@Entity
@Table(name = "active_list")
public class ActiveList  extends BaseOpenmrsData{
	@Id
	@Column(name = "active_list_id")
	private Integer activeListId;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne
	@JoinColumn(name = "active_list_type_id")
	private ActiveListType activeListType;


	@ManyToOne
	@JoinColumn(name = "concept_id")
	private Concept concept;
	
	@Column(name = "start_date", nullable = false, length = 19)
	private Date startDate;
	
	@Column(name = "end_date", nullable = false, length = 19)
	private Date endDate;


	@Column(name = "comments")
	private String comments;

	@Column(name = "uuid")
	private UUID uuid;

	public Integer getActiveListId() {
		return activeListId;
	}

	public void setActiveListId(Integer activeListId) {
		this.activeListId = activeListId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public ActiveListType getActiveListType() {
		return activeListType;
	}

	public void setActiveListType(ActiveListType activeListType) {
		this.activeListType = activeListType;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
