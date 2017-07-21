package com.api.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.representations.Lista;

@Repository
public interface ListaDao extends JpaRepository<Lista, Long> {
	
}