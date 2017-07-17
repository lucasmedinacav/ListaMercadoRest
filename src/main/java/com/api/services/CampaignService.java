package com.api.services;

import java.util.List;
import org.springframework.stereotype.Component;
import com.api.representations.Campaign;

@Component("campaignService")
public interface CampaignService {
	
	public Campaign getCampaignById(Long id);
	
	public void insertCampaign(Campaign campaign);
	
	public List<Campaign> findAllCampaignsWithValidPeriod();
	
	public List<Campaign> updateOtherSystemsAboutChangesAtCampaignPeriod();
	
	public void updateCampaignStatusChanged();
	
	public void deleteCampaign(Long id);
	
}
