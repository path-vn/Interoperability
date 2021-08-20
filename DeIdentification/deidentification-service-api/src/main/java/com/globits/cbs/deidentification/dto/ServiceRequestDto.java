package com.globits.cbs.deidentification.dto;

import java.util.Date;

import com.globits.cbs.deidentification.utilities.Enums.ReferralStatusEnum;

public class ServiceRequestDto {
	private ReferralStatusEnum status;
	private Date fulfillmentTime;
	private OrganizationDto requester;
	private OrganizationDto recipient;
	//private EncounterDto context;
	public ReferralStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ReferralStatusEnum status) {
		this.status = status;
	}
	public Date getFulfillmentTime() {
		return fulfillmentTime;
	}
	public void setFulfillmentTime(Date fulfillmentTime) {
		this.fulfillmentTime = fulfillmentTime;
	}
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
//	public EncounterDto getContext() {
//		return context;
//	}
//	public void setContext(EncounterDto context) {
//		this.context = context;
//	}

}
