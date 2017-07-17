package com.api.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.persistence.CampaignDao;
import com.api.representations.Campaign;
import com.api.util.DateUtil;

@Service
public class CampaignServiceImpl implements CampaignService {
	
	@Autowired
	CampaignDao dao;
	
	@Override
	public Campaign getCampaignById(Long id) {
		return dao.findOne(id);
	}
	
	@Override
	public void insertCampaign(Campaign campaign) {
		Campaign campaignTemp = campaign;
		Map<Long, Date> campaignsUpdateMap = new HashMap<Long, Date>();
		Date dtFinish = campaign.getDtFinish();
		while (campaignTemp != null) {
			campaignTemp = dao.findCampaignWithSamePeriod(campaign.getDtStart(), dtFinish);
			if (campaignTemp != null) {
				dtFinish = DateUtil.datePlusOneDay(campaignTemp.getDtFinish());
				campaignsUpdateMap.put(campaignTemp.getId(), dtFinish);
			}
		}
		
		//ATUALIZA TODAS OS PERIODOS ENCONTRADOS COM MESMA DATA DE INICIO E FIM SOMANDO UM DIA NA DATA FIM
		for (Entry<Long, Date> camp : campaignsUpdateMap.entrySet()) {
			dao.updateDtFinish(camp.getValue(), camp.getKey());
		}
		
		dao.save(campaign);
	}
	
	@Override
	public List<Campaign> findAllCampaignsWithValidPeriod() {
		return dao.findAllCampaignsWithValidPeriod(); //ENCONTRA TODAS CAMPANHAS QUE NÃO ESTAO COM PERIODO VENCIDO
	}
	
	@Override
	public List<Campaign> updateOtherSystemsAboutChangesAtCampaignPeriod() {
		return dao.findAllCampaignsWithPeriodHasModified(); //INFORMA A OUTROS SISTEMAS QUAIS CAMPANHAS TIVERAM SEUS PERIODOS MODIFICADOS
	}
	
	@Override
	public void updateCampaignStatusChanged() {
		dao.updateCampaingStatusChanged();
	}

	@Override
	public void deleteCampaign(Long id) {
		dao.delete(id);
	}
	
	
}
