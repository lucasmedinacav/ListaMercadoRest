package com.api.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import com.api.representations.ProdutoLista;
import com.api.resources.ListaResource;
import com.api.resources.ProdutoListaResource;
import com.api.resources.ProdutoResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class JerseyInitialization extends ResourceConfig {
	
	public JerseyInitialization(){
		this.register(new JacksonJsonProvider(ObjectMapperFactory.create()));
		this.register(ListaResource.class);
		this.register(ProdutoResource.class);
		this.register(ProdutoListaResource.class);
		this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
	}
}
