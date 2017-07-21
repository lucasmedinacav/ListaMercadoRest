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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.api.persistence.ListaDao;
import com.api.representations.Lista;

@Path("/listas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class ListaResource {
	
	private final ListaDao listaDao;
	
	@Inject
	public ListaResource(ListaDao listaDao){
		this.listaDao = listaDao;
	}
	
	@GET
	@Path("/buscaTodas")
	public List<Lista> buscaTodas() {
		return listaDao.findAll();
	}
	
	@POST
	@Path("/deletaTodas")
	public void deletaTodas() {
		this.listaDao.deleteAll();
	}
	
	@POST
	@Path("/cadastraProdutoNaLista")
	public void cadastraLista(Lista lista) {
		if (lista != null) {
			this.listaDao.save(lista);
		} else {
			Response.serverError();
		}
	}
	
	@POST
	@Path("/cadastraLista")
	public void cadastraLista(@Context UriInfo uri) {
		String nome = uri.getQueryParameters().getFirst("nome");
		if (!nome.isEmpty()) {
			this.listaDao.save(new Lista(nome));
		} else {
			Response.serverError();
		}
	}
	
	@POST
	@Path("/deletaListaPeloId")
	public void deletaListaPeloId(@Context UriInfo uri) {
		try {
			String id = uri.getQueryParameters().getFirst("id");
			this.listaDao.delete(Long.valueOf(id));
		} catch (Exception e) {
			Response.serverError();
		}
	}
}