package com.globits.cbs.deidentification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.cbs.deidentification.domain.LastSyncInfo;
import com.globits.cbs.deidentification.domain.Sample;

@Repository
public interface LastSyncInfoRepository extends JpaRepository<LastSyncInfo, UUID> {

}
