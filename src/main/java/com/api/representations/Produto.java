package com.api.representations;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.api.bindings.ProdutoListaResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto")
@JsonIgnoreProperties(ignoreUnknown = true)
@SqlResultSetMapping(name = "response",
		classes = {@ConstructorResult(
				targetClass = ProdutoListaResponse.class,
				columns = {@ColumnResult(name = "produto_id", type = Long.class), @ColumnResult(name = "imagem", type = String.class), @ColumnResult(
						name = "nome", type = String.class), @ColumnResult(name = "valor", type = Double.class), @ColumnResult(name = "lista_id",
								type = Long.class), @ColumnResult(name = "produto_pego", type = boolean.class), @ColumnResult(name = "produto_quantidade",
										type = Integer.class)
				})
		})
@NamedNativeQueries({@NamedNativeQuery(name = "Produto.buscaProdutosDaLista", query = "select * from Produto p join produto_lista pl " +
		"on (p.produto_id = pl.produto_id) where pl.lista_id = :idLista", resultSetMapping = "response"), @NamedNativeQuery(
				name = "Produto.buscaProdutosNaoCadastradosNaLista", query = "select distinct(p.*), 0 as lista_id, " +
						"false as produto_pego, 0 as produto_quantidade from produto p, lista l where NOT EXISTS(select * from produto_lista pl " +
						"where pl.produto_id = p.produto_id and pl.lista_id = :idLista)", resultSetMapping = "response")
})
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "produto_id")
	private Long id;
	
	@NotNull
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "imagem")
	private String imagem;
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	private List<ProdutoLista> produtosLista;
	
	public Produto(){}
	
	public Produto(String nome, Double valor, String imagem){
		super();
		this.nome = nome;
		this.valor = valor;
		this.imagem = imagem;
	}
	
	public List<ProdutoLista> getProdutosLista() {
		return produtosLista;
	}
	
	public void setProdutosLista(List<ProdutoLista> produtosLista) {
		this.produtosLista = produtosLista;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String name) {
		this.nome = name;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
