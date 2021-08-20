package com.globits.adapter.pdma.dto;

import java.util.UUID;

public class CaseSearchDto extends SearchDto {
	private UUID caseId;

	public UUID getCaseId() {
		return caseId;
	}

	public void setCaseId(UUID caseId) {
		this.caseId = caseId;
	}
	
}
