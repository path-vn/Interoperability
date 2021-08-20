package com.globits.adapter.opcassist.dto;

import java.time.LocalDateTime;

public class OPCAssistSearchDto extends SearchDto{
	private Long personId;
	private Long caseId;
	private LocalDateTime lastSynDate;
	
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public LocalDateTime getLastSynDate() {
		return lastSynDate;
	}
	public void setLastSynDate(LocalDateTime lastSynDate) {
		this.lastSynDate = lastSynDate;
	}
}