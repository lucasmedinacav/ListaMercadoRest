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
import com.api.persistence.CampaignDao;
import com.api.representations.Campaign;
import com.api.services.CampaignService;

@Path("/campaigns")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class CampaignResource {
	
	@Autowired
	CampaignService service;
	
	private final CampaignDao campaignDao;
	
	@Inject
	public CampaignResource(CampaignDao campaignDao){
		this.campaignDao = campaignDao;
	}
	
	@POST
	@Path("/registerCampaign")
	public String registerCampaign(Campaign campaign) {
		service.insertCampaign(campaign);
		return "ok";
	}
	
	@GET
	@Path("/getCampaignById")
	public Campaign getPersonByEmail(@Context UriInfo uri) {
		String id = uri.getQueryParameters().getFirst("id");
		return service.getCampaignById(Long.valueOf(id));
	}
	
	@GET
	@Path("/getAll")
	public List<Campaign> getAll() {
		return this.campaignDao.findAll();
	}
	
	@GET
	@Path("/getAllCampaignWithValidPeriod")
	public List<Campaign> getAllCampaignWithValidPeriod() {
		return this.service.findAllCampaignsWithValidPeriod();
	}
	
	@GET
	@Path("/getAllCampaignsWithPeriodChanged")
	public List<Campaign> getAllCampaignsWithPeriodChanged() {
		return this.service.updateOtherSystemsAboutChangesAtCampaignPeriod();
	}
	
	@GET
	@Path("/clearHistoryPeriodChanges")
	public void clearHistoryPeriodChanges() {
		this.service.updateCampaignStatusChanged();
	}
	
	@GET
	@Path("/deleteCampaign")
	public void clearHistoryPeriodChanges(@Context UriInfo uri) {
		String id = uri.getQueryParameters().getFirst("id");
		this.service.deleteCampaign(Long.valueOf(id));
	}
	
	@GET
	@Path("/deleteRegisters")
	public String deleteRegisters() {
		this.campaignDao.deleteAll();
		return "ok";
	}
}