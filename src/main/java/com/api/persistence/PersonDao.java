package com.api.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.representations.Campaign;
import com.api.representations.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, Long> {
	
	@Query("SELECT p FROM Person p where p.email = :email")
	Person findPersonByEmail(@Param("email") String email);
	
	@Query(nativeQuery = true)
	List<Campaign> findCampaingsNotRegisteredAndPeriodIsValid(@Param("idPerson") Long idPerson, @Param("idTeam") Long idTeam);
	
	@Query("SELECT c FROM Campaign c INNER JOIN c.people p where p.email = ?1 and p.team = c.team")
	List<Campaign> findCampaingsRegistered(String email);
}