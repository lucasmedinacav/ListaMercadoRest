package com.api.representations;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lista")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lista_id")
	private Long id;
	
	@NotNull
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "dt_cadastro")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dtCadastro;
	
	@OneToMany(mappedBy = "lista", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	private List<ProdutoLista> produtosLista;
	
	@Transient
	private Double total;
	
	@Transient
	private Double estimado;
	
	public Lista(){
		this.dtCadastro = new Date();
	}
	
	public Lista(String nome, List<ProdutoLista> produtosLista){
		this.nome = nome;
		this.produtosLista = produtosLista;
		this.dtCadastro = new Date();
	}
	
	public Lista(String nome){
		this.nome = nome;
		this.dtCadastro = new Date();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Date getDtCadastro() {
		return dtCadastro;
	}
	
	public List<ProdutoLista> getProdutosLista() {
		return produtosLista;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
	public void setProdutosLista(List<ProdutoLista> produtosLista) {
		this.produtosLista = produtosLista;
	}
}