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
import com.api.persistence.ProdutoDao;
import com.api.representations.Produto;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class ProdutoResource {
	
	private final ProdutoDao produtoDao;
	
	@Inject
	public ProdutoResource(ProdutoDao produtoDao){
		this.produtoDao = produtoDao;
	}
	
	@GET
	@Path("/buscaTodos")
	public List<Produto> buscaTodos() {
		return produtoDao.findAll();
	}
	
	@POST
	@Path("/deletaTodos")
	public void deletaTodas() {
		this.produtoDao.deleteAll();
	}
	
	@POST
	@Path("/cadastraProduto")
	public void cadastraLista(Produto produto) {
		if (produto != null) {
			this.produtoDao.save(produto);
		} else {
			Response.serverError();
		}
	}
	
	@POST
	@Path("/deletaProdutoPeloId")
	public void deletaListaPeloId(@Context UriInfo uri) {
		try {
			String id = uri.getQueryParameters().getFirst("id");
			this.produtoDao.delete(Long.valueOf(id));
		} catch (Exception e) {
			Response.serverError();
		}
	}
}