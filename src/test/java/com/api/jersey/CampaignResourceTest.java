package com.api.jersey;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.api.representations.Campaign;
import com.api.representations.Team;
import com.api.util.JsonConverter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CampaignResourceTest {
	
	private final static String HOST_PORT_REST_API_CAMPAIGN = "http://localhost:8080/campaigns/";
	private final static String URL_GET_ALL = "getAll";
	private final static String URL_GET_ALL_VALID_PERIOD = "getAllCampaignWithValidPeriod";
	private final static String URL_POST_REGISTER_CAMPAIGN = "registerCampaign";
	private final static String HOST_PORT_REST_API_TEAM_BY_NAME = "http://localhost:8080/teams/getTeamByName?name=";
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@BeforeClass
	public static void setUp() {
		Response r = given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_CAMPAIGN + "deleteRegisters");
		assertEquals("ok", r.getBody().asString());
	}
	
	@Test
	public void test1_RegisterCampaign1And2() throws ParseException, IOException {
		
		//INSERE CAMPANHAS COM TERMINO DIA 21/07 E 22/07
		insertTwoCampaigns();
		
		//VERIFICA SE OS DOIS TERMINOS PERMANECEM IGUAL CADASTRADOS
		given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_CAMPAIGN + URL_GET_ALL).then()
				.body(containsString("07-21"))
				.body(containsString("07-22"))
				.body(CoreMatchers.not(containsString(("07-23"))));
	}
	
	@Test
	public void test2_RegisterThreeCampaignsWithTwoSamePeriod() throws ParseException, IOException {
		
		//INSERE CAMPANHA COM TERMINO EM 21/07
		Campaign c3 = new Campaign("Campanha 3", sdf.parse("01/07/2017"), sdf.parse("21/07/2017"), getTeam("São Paulo"));
		Response r = given().contentType("application/json").body(JsonConverter.objectToJson(c3)).when().post(HOST_PORT_REST_API_CAMPAIGN +
				URL_POST_REGISTER_CAMPAIGN);
		assertEquals("ok", r.getBody().asString());
		
		//VERIFICA SE A REGRA DO ACRESCIMO DE DIA NA DATA FINAL PARA PERIODOS IGUAIS ESTA CORRETA
		given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_CAMPAIGN + URL_GET_ALL_VALID_PERIOD).then()
				.body(containsString("07-21"))
				.body(containsString("07-22"))
				.body(containsString(("07-23")));
	}
	
	@Test
	public void test3_RegisterCampaignWithInvalidPeriodAndGetCampaigns() throws ParseException, IOException {
		
		//INSERE CAMPANHA COM TERMINO EM VENCIDO DE 10-07
		Campaign c3 = new Campaign("Campanha Periodo Vencido", sdf.parse("01/07/2017"), sdf.parse("10/07/2017"), getTeam("São Paulo"));
		Response r = given().contentType("application/json").body(JsonConverter.objectToJson(c3)).when().post(HOST_PORT_REST_API_CAMPAIGN +
				URL_POST_REGISTER_CAMPAIGN);
		assertEquals("ok", r.getBody().asString());
		
		//VERIFICA SE CAMPANHA DE TERMINO 10-7 FOI ADICIONADA EM UMA BUSCA DE CAMPANHAS SEM FILTROS
		given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_CAMPAIGN + URL_GET_ALL).then()
				.body(containsString(("07-10")));
		
		//VERIFICA SE O SERVICO QUE BUSCA APENAS QUERYS COM PERIODOS VALIDOS FUNCIONA
		given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_CAMPAIGN + URL_GET_ALL_VALID_PERIOD).then()
				.body(CoreMatchers.not(containsString(("07-10"))));
	}
	
	@Test()
	public void test4_FindCampaignsWithHasChanges() {
		//VERIFICA SE ALGUMA DAS CAMAPANHAS ENCONTRADAS TEM SEU ATRIBUTOS HASCHANGES COMO TRUE (SIGNIFICA QUE HOUVE ALTERACAO)
		given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_CAMPAIGN + URL_GET_ALL_VALID_PERIOD).then()
				.body(containsString(("true")));
	}
	
	private Team getTeam(String name) throws JsonParseException, JsonMappingException, IOException {
		Response r = given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_TEAM_BY_NAME + name);
		return (Team) JsonConverter.jsonToObject(r.getBody().asString());
	}
	
	private void insertTwoCampaigns() throws JsonParseException, JsonMappingException, ParseException, IOException {
		//INSERE CAMPANHA COM TERMINO EM 21/07
		Campaign c1 = new Campaign("Campanha 1", sdf.parse("01/07/2017"), sdf.parse("21/07/2017"), getTeam("Palmeiras"));
		Response r = given().contentType("application/json").body(JsonConverter.objectToJson(c1))
				.when().post(HOST_PORT_REST_API_CAMPAIGN + URL_POST_REGISTER_CAMPAIGN);
		assertEquals("ok", r.getBody().asString());
		
		//INSERE CAMPANHA COM TERMINO EM 22/07
		Campaign c2 = new Campaign("Campanha 2", sdf.parse("01/07/2017"), sdf.parse("22/07/2017"), getTeam("Corinthians"));
		Response r2 = given().contentType("application/json").body(JsonConverter.objectToJson(c2))
				.when().post(HOST_PORT_REST_API_CAMPAIGN + URL_POST_REGISTER_CAMPAIGN);
		assertEquals("ok", r2.getBody().asString());
	}
}