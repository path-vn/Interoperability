package com.globits.hivimportcsadapter.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globits.hivimportcsadapter.domain.DataFile;
@Repository
public interface DataFileRepository extends JpaRepository<DataFile, UUID>{

}
