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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.api.bindings.ProdutoListaRequest;
import com.api.bindings.ProdutoListaResponse;
import com.api.persistence.ProdutoListaDao;
import com.api.services.ProdutoListaService;

@Path("/produtosLista")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class ProdutoListaResource {
	
	@Autowired
	private ProdutoListaService service;
	
	@POST
	@Path("/deletaProdutoDaLista")
	public void deletaProdutoDaLista(@Context UriInfo uri) {
		String idLista = uri.getQueryParameters().getFirst("idLista");
		String idProduto = uri.getQueryParameters().getFirst("idProduto");
		this.service.excluirProdutoDaLista(Long.valueOf(idProduto), Long.valueOf(idLista));
	}
	
	@POST
	@Path("/cadastraProdutoNaLista")
	public void cadastraProdutoNaLista(ProdutoListaRequest request) {
		if (request != null) {
			this.service.adicionaProdutoNaLista(request);
		} else {
			Response.serverError();
		}
	}
	
	@GET
	@Path("/buscaProdutosDaLista")
	public List<ProdutoListaResponse> buscaProdutosDaLista(@Context UriInfo uri) {
		String idLista = uri.getQueryParameters().getFirst("idLista");
		if (StringUtils.isNotBlank(idLista))
			return service.buscaProdutosDaLista(Long.valueOf(idLista));
		return null;
	}
	
	@GET
	@Path("/buscaProdutosNaoCadastradosNaLista")
	public List<ProdutoListaResponse> buscaProdutosNaoCadastradosNaLista(@Context UriInfo uri) {
		String idLista = uri.getQueryParameters().getFirst("idLista");
		if (StringUtils.isNotBlank(idLista))
			return service.buscaProdutosNaoCadastradosNaLista(Long.valueOf(idLista));
		return null;
	}
}