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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.api.persistence.TeamDao;
import com.api.representations.Person;
import com.api.representations.Team;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class TeamResource {
	
	private final TeamDao teamDao;
	
	@Inject
	public TeamResource(TeamDao teamDao){
		this.teamDao = teamDao;
	}
	
	@GET
	public List<Team> getAll() {
		return this.teamDao.findAll();
	}
	
	@POST
	@Path("/registerTeam")
	public String registerCampaign(Team team) {
		teamDao.save(team);
		return "ok";
	}
	
	@GET
	@Path("/countTeams")
	public String countTeams() {
		return String.valueOf(teamDao.count());
	}
	
	@GET
	@Path("/deleteRegisters")
	public String deleteRegisters() {
		this.teamDao.deleteAll();
		return "ok";
	}
	
	@GET
	@Path("/getTeamByName")
	public Team getTeamByName(@Context UriInfo uri) {
		String name = uri.getQueryParameters().getFirst("name");
		return teamDao.findTeamByName(name);
	}
}