package com.api.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import com.api.resources.CampaignResource;
import com.api.resources.PersonResource;
import com.api.resources.TeamResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class JerseyInitialization extends ResourceConfig {
	
	public JerseyInitialization(){
		this.register(new JacksonJsonProvider(ObjectMapperFactory.create()));
		this.register(CampaignResource.class);
		this.register(PersonResource.class);
		this.register(TeamResource.class);
		this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
	}
}
