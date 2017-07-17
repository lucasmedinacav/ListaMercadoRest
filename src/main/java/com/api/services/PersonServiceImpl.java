package com.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.persistence.PersonDao;
import com.api.representations.Campaign;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonDao dao;
	
	@Override
	public List<Campaign> getCampaingsNotRegisteredWithThisPerson(Long idPerson, Long idTeam) {
		return dao.findCampaingsNotRegisteredAndPeriodIsValid(idPerson, idTeam);
	}
}
