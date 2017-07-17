package com.api.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.representations.Team;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {
	
	@Query("SELECT t FROM Team t where t.name = :name")
	Team findTeamByName(@Param("name") String name);
}