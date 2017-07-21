package com.api.bindings;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoListaResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long idLista;
	
	private boolean japPegou;
	
	private Integer quantidade;
	
	private String imagem;
	
	private Double valor;
	
	private String nome;
	
	public ProdutoListaResponse(){}
	
	public ProdutoListaResponse(Long id, String imagem, String nome, Double valor,
			Long idLista, boolean japPegou, Integer quantidade){
		this.id = id;
		this.idLista = idLista;
		this.japPegou = japPegou;
		this.quantidade = quantidade;
		this.imagem = imagem;
		this.valor = valor;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getIdLista() {
		return idLista;
	}
	
	public boolean isJapPegou() {
		return japPegou;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	
	public void setJapPegou(boolean japPegou) {
		this.japPegou = japPegou;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}