package com.api.jersey;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.api.representations.Lista;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListaResourceTest {
	
	private final static String HOST_API = "http://localhost:8080/listas/";
	private final static String BUSCA_TODAS = "buscaTodas";
	private final static String DELETA_TODAS = "deletaTodas";
	private static final String CADASTRA_LISTA = "cadastraLista?nome=";
	private static final String DELETA_LISTA = "deletaListaPeloId?id=";
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	//	@BeforeClass
	//	public static void setUp() {
	//		Response r = given().contentType("application/json")
	//				.when().get(HOST_PORT_REST_API_CAMPAIGN + "deleteRegisters");
	//		assertEquals("ok", r.getBody().asString());
	//	}
	
	@Test
	public void teste01_verificaSeNaoExisteNenhumaListaCadastrada() throws Exception {
		deletaListas();
		assertTrue(getListas().size() == 0);
	}
	
	@Test
	public void teste02_verificaSeExisteListaCadastradaAposCadastroDeUma() throws Exception {
		cadastraLista("Lista01");
		assertTrue(getListas().size() > 0);
	}
	
	@Test
	public void teste03_verificaSeNumeroDeListasSubtraiUmAposExcluirUma() throws Exception {
		Lista lista;
		List<Lista> listas = getListas();
		int qtd = listas.size();
		if (qtd > 0) {
			lista = listas.get(0);
			deletaListaPeloId(lista.getId().toString());
		}
		assertTrue(listas.size() > getListas().size());
	}
	
	
	private void deletaListaPeloId(String id) {
		given().contentType("application/json").when().post(HOST_API + DELETA_LISTA + id);
	}
	
	private void cadastraLista(String nome) {
		given().contentType("application/json").when().post(HOST_API + CADASTRA_LISTA + nome);
	}
	
	private void deletaListas() {
		given().contentType("application/json").when().post(HOST_API + DELETA_TODAS);
	}
	
	private List<Lista> getListas() throws Exception {
		Response r = given().contentType("application/json").when().get(HOST_API + BUSCA_TODAS);
		ObjectMapper mapper = new ObjectMapper();
		List<Lista> list = mapper.readValue(r.getBody().asString(),
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						Lista.class));
		return list;
	}
}