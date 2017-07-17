package com.api.resources;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.api.persistence.PersonDao;
import com.api.representations.Campaign;
import com.api.representations.Person;
import com.api.services.PersonService;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class PersonResource {
	
	@Autowired
	PersonService personService;
	
	private final PersonDao personDao;
	
	@Inject
	public PersonResource(PersonDao personDao){
		this.personDao = personDao;
	}
	
	@GET
	public List<Person> getAll() {
		return this.personDao.findAll();
	}
	
	@GET
	@Path("/getPersonByEmail")
	public Person getPersonByEmail(@Context UriInfo uri) {
		String email = uri.getQueryParameters().getFirst("email");
		return personDao.findPersonByEmail(email);
	}
	
	@POST
	@Path("/registerPerson")
	public String registerNewPerson(Person person) {
		personDao.saveAndFlush(person);
		return person.getId().toString();
	}
	
	@GET
	@Path("/deleteRegisters")
	public String deleteRegisters() {
		this.personDao.deleteAll();
		return "ok";
	}
	
	@GET
	@Path("/getCampaingsNotRegisteredWithThisPerson")
	public List<Campaign> getCampaingsNotRegisteredWithThisPerson(@Context UriInfo uri) {
		String idTeam = uri.getQueryParameters().getFirst("idTeam");
		String idPerson = uri.getQueryParameters().getFirst("idPerson");
		return this.personService.getCampaingsNotRegisteredWithThisPerson(Long.valueOf(idPerson), Long.valueOf(idTeam));
	}
}