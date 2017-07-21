package com.api.representations;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto_lista")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoLista implements Serializable {
	
	@Embeddable
	public static class ProdutoListaId implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Column(name = "produto_id")
		protected Long produtoId;
		
		@Column(name = "lista_id")
		protected Long listaId;
		
		public ProdutoListaId(){}
		
		public ProdutoListaId(Long produtoId, Long listaId){
			this.produtoId = produtoId;
			this.listaId = listaId;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((listaId == null) ? 0 : listaId.hashCode());
			result = prime * result + ((produtoId == null) ? 0 : produtoId.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ProdutoListaId other = (ProdutoListaId) obj;
			if (listaId == null) {
				if (other.listaId != null)
					return false;
			} else if (!listaId.equals(other.listaId))
				return false;
			if (produtoId == null) {
				if (other.produtoId != null)
					return false;
			} else if (!produtoId.equals(other.produtoId))
				return false;
			return true;
		}
		
	}
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProdutoListaId id;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", insertable = false, updatable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "lista_id", insertable = false, updatable = false)
	private Lista lista;
	
	@Column(name = "produto_pego")
	private boolean produtoPego;
	
	@Column(name = "produto_quantidade")
	private Integer produtoQuantidade;
	
	public ProdutoLista(){}
	
	public ProdutoLista(Produto produto, Lista lista, boolean produtoPego, Integer produtoQuantidade){
		this.id = new ProdutoListaId(produto.getId(), lista.getId());
		this.produto = produto;
		this.lista = lista;
		this.produtoPego = produtoPego;
		this.produtoQuantidade = produtoQuantidade;
		if (this.produtoQuantidade == null || this.produtoQuantidade == 0)
			this.produtoQuantidade = 1;
		if (lista.getProdutosLista() == null)
			lista.setProdutosLista(new ArrayList<>());
		if (produto.getProdutosLista() == null)
			produto.setProdutosLista(new ArrayList<>());
		produto.getProdutosLista().add(this);
		lista.getProdutosLista().add(this);
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public Lista getLista() {
		return lista;
	}
	
	public boolean isProdutoPego() {
		return produtoPego;
	}
	
	public Integer getProdutoQuantidade() {
		return produtoQuantidade;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void setLista(Lista lista) {
		this.lista = lista;
	}
	
	public void setProdutoPego(boolean produtoPego) {
		this.produtoPego = produtoPego;
	}
	
	public void setProdutoQuantidade(Integer produtoQuantidade) {
		this.produtoQuantidade = produtoQuantidade;
	}
}
