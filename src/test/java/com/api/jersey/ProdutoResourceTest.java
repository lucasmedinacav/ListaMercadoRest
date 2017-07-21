package com.api.jersey;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.api.representations.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoResourceTest {
	
	private final static String HOST_API = "http://localhost:8080/produtos/";
	private final static String BUSCA_TODOS = "buscaTodos";
	private final static String DELETA_TODOS = "deletaTodos";
	private static final String CADASTRA_PRODUTO = "cadastraProduto";
	private static final String DELETA_PRODUTO = "deletaProdutoPeloId?id=";
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	//	@BeforeClass
	//	public static void setUp() {
	//		Response r = given().contentType("application/json")
	//				.when().get(HOST_PORT_REST_API_CAMPAIGN + "deleteRegisters");
	//		assertEquals("ok", r.getBody().asString());
	//	}
	
	@Test
	public void teste01_verificaSeNaoExisteNenhumProdutoCadastrado() throws Exception {
		deletaProdutos();
		assertTrue(getProdutos().size() == 0);
	}
	
	@Test
	public void teste02_verificaSeExisteProdutoCadastradoAposCadastroDeUm() throws Exception {
		cadastraProduto("Produto01", new Double("10.00"), "");
		assertTrue(getProdutos().size() > 0);
	}
	
	@Test
	public void teste03_verificaSeNumeroDePordutosSubtraiUmAposExcluirUm() throws Exception {
		Produto produto;
		List<Produto> produtos = getProdutos();
		int qtd = produtos.size();
		if (qtd > 0) {
			produto = produtos.get(0);
			deletaProdutoPeloId(produto.getId().toString());
		}
		assertTrue(produtos.size() > getProdutos().size());
	}
	
	private void deletaProdutoPeloId(String id) {
		given().contentType("application/json").when().post(HOST_API + DELETA_PRODUTO + id);
	}
	
	private void cadastraProduto(String nome, Double valor, String img) {
		given().contentType("application/json").body(gson.toJson(new Produto(nome, valor, img))).when().post(HOST_API + CADASTRA_PRODUTO);
	}
	
	private void deletaProdutos() {
		given().contentType("application/json").when().post(HOST_API + DELETA_TODOS);
	}
	
	private List<Produto> getProdutos() throws Exception {
		Response r = given().contentType("application/json").when().get(HOST_API + BUSCA_TODOS);
		ObjectMapper mapper = new ObjectMapper();
		List<Produto> list = mapper.readValue(r.getBody().asString(),
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						Produto.class));
		return list;
	}
}
