package com.globits.facility.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globits.facility.domain.HealthOrganization;

@Repository
public interface HealthOrganizationRepository extends JpaRepository<HealthOrganization, UUID> {

}
