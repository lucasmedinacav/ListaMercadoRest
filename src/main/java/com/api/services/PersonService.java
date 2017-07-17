package com.api.services;

import java.util.List;
import org.springframework.stereotype.Component;
import com.api.representations.Campaign;

@Component("personService")
public interface PersonService {
	
	public List<Campaign> getCampaingsNotRegisteredWithThisPerson(Long idPerson, Long idTeam);
}
