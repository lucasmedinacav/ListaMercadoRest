package com.api.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.api.representations.Campaign;

@Repository
public interface CampaignDao extends JpaRepository<Campaign, Long> {
	
	@Modifying
	@Query("update Campaign c set c.dtFinish = ?1, c.hasChanges = true where c.id = ?2")
	void updateDtFinish(Date dtFinish, Long id);
	
	@Query("select c from Campaign c where c.dtStart = ?1 and c.dtFinish = ?2")
	Campaign findCampaignWithSamePeriod(Date dtStart, Date dtFinish);
	
	@Query("select c from Campaign c where c.dtFinish >= CURRENT_DATE")
	List<Campaign> findAllCampaignsWithValidPeriod();
	
	@Query("select c from Campaign c where c.hasChanges = true")
	List<Campaign> findAllCampaignsWithPeriodHasModified();
	
	@Modifying
	@Query("update Campaign c set c.hasChanges = false where c.hasChanges = true")
	void updateCampaingStatusChanged();
	
}