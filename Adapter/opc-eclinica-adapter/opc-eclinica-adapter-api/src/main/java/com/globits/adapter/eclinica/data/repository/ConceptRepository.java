package com.globits.adapter.eclinica.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globits.adapter.eclinica.data.domain.Concept;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Integer> {

}
