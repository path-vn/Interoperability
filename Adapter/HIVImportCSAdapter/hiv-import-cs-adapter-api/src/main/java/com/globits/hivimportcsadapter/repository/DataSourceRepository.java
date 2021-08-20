package com.globits.hivimportcsadapter.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.hivimportcsadapter.domain.DataSource;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, UUID>{
	@Query("select count(entity.id) from DataSource entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
	@Query("select count(entity.id) from DataSource entity where entity.channelUrl =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkUrl(String channelUrl, UUID id);
}
