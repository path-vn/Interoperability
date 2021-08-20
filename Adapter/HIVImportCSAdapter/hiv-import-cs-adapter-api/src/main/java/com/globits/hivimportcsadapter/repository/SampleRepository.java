package com.globits.hivimportcsadapter.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.hivimportcsadapter.domain.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample, UUID> {
	@Query("select entity FROM Sample entity where entity.code =?1 ")
	List<Sample> getByCode(String code);

}
