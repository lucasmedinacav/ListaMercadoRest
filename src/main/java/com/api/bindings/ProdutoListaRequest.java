package com.api.bindings;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoListaRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idProduto;
	
	private Long idLista;
	
	private boolean foiPego;
	
	private Integer quantidade;
	
	public ProdutoListaRequest(){}
	
	public ProdutoListaRequest(Long idProduto, Long idLista, boolean foiPego, Integer quantidade){
		super();
		this.idProduto = idProduto;
		this.idLista = idLista;
		this.foiPego = foiPego;
		this.quantidade = quantidade;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public Long getIdLista() {
		return idLista;
	}
	
	public boolean isFoiPego() {
		return foiPego;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	
	public void setFoiPego(boolean foiPego) {
		this.foiPego = foiPego;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
