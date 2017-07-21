package com.api.jersey;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.api.bindings.ProdutoListaRequest;
import com.api.bindings.ProdutoListaResponse;
import com.api.representations.Lista;
import com.api.representations.Produto;
import com.api.representations.ProdutoLista;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoListaResourceTest {
	
	private final static String HOST_API = "http://localhost:8080/produtosLista/";
	private final static String HOST_BUSCA_LISTAS = "http://localhost:8080/listas/buscaTodas";
	private final static String HOST_BUSCA_PRODUTOS = "http://localhost:8080/produtos/buscaTodos";
	private static final String DELETA_PRODUTO_DA_LISTA = "deletaProdutoDaLista";
	private static final String CADASTRA_PRODUTOS_NA_LISTA = "cadastraProdutoNaLista";
	private static final String BUSCA_PRODUTOS_DA_LISTA = "buscaProdutosDaLista?idLista=";
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	//@Test
	public void test01_cadastraProdutoNaLista() throws Exception {
		Lista lista = getListas().get(0);
		Produto produto = getProdutos().get(0);
		ProdutoListaRequest req = new ProdutoListaRequest(produto.getId(), lista.getId(), true, 5);
		cadastraProdutoLista(req);
	}
	
	@Test
	public void teste02_buscaProdutosDaLista() throws Exception {
		assertTrue(getProdutosLista(2L).size() == 2);
	}
	
	private void cadastraProdutoLista(ProdutoListaRequest request) {
		given().contentType("application/json").body(gson.toJson(request)).when().post(HOST_API + CADASTRA_PRODUTOS_NA_LISTA);
	}
	
	private List<Lista> getListas() throws Exception {
		Response r = given().contentType("application/json").when().get(HOST_BUSCA_LISTAS);
		ObjectMapper mapper = new ObjectMapper();
		List<Lista> list = mapper.readValue(r.getBody().asString(),
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						Lista.class));
		return list;
	}
	
	private List<Produto> getProdutos() throws Exception {
		Response r = given().contentType("application/json").when().get(HOST_BUSCA_PRODUTOS);
		ObjectMapper mapper = new ObjectMapper();
		List<Produto> list = mapper.readValue(r.getBody().asString(),
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						Produto.class));
		return list;
	}
	
	private List<ProdutoListaResponse> getProdutosLista(Long idLista) throws Exception {
		Response r = given().contentType("application/json").when().get(HOST_API + BUSCA_PRODUTOS_DA_LISTA + idLista.toString());
		ObjectMapper mapper = new ObjectMapper();
		List<ProdutoListaResponse> list = mapper.readValue(r.getBody().asString(),
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						ProdutoListaResponse.class));
		return list;
	}
}
