package com.globits.cbs.deidentification.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globits.cbs.deidentification.domain.SyncLog;

@Repository
public interface SyncLogRepository extends JpaRepository<SyncLog, UUID> {

}
