package com.globits.adapter.eclinica.data.domain.auditing;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityAuditListener {

	@PrePersist
	public void beforePersit(AuditableEntity auditableEntity) {


	}

	@PreUpdate
	public void beforeMerge(AuditableEntity auditableEntity) {


	}
}
