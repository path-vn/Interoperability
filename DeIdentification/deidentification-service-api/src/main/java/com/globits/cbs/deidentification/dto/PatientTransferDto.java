package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class PatientTransferDto {
	private OrganizationDto requester;//Nơi gửi
	private OrganizationDto recipient;//Nơi nhận
	private EncounterDto encounter;//Lần thăm khám nào
	private Date dateSent;//Ngày gửi
	private Date fulfillmentTime;//Ngày hoàn thành
	public OrganizationDto getRequester() {
		return requester;
	}
	public void setRequester(OrganizationDto requester) {
		this.requester = requester;
	}
	public OrganizationDto getRecipient() {
		return recipient;
	}
	public void setRecipient(OrganizationDto recipient) {
		this.recipient = recipient;
	}
	public EncounterDto getEncounter() {
		return encounter;
	}
	public void setEncounter(EncounterDto encounter) {
		this.encounter = encounter;
	}
	public Date getDateSent() {
		return dateSent;
	}
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	public Date getFulfillmentTime() {
		return fulfillmentTime;
	}
	public void setFulfillmentTime(Date fulfillmentTime) {
		this.fulfillmentTime = fulfillmentTime;
	}
	
	
}
