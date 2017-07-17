package com.api.jersey;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.api.representations.Campaign;
import com.api.representations.Person;
import com.api.representations.Team;
import com.api.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonResourceTest {
	
	private final static String HOST_PORT_REST_API_PERSON = "http://localhost:8080/people/";
	private final static String HOST_PORT_REST_API_TEAM_BY_NAME = "http://localhost:8080/teams/getTeamByName?name=";
	private final static String URL_GET_PERSON_BY_EMAIL = "getPersonByEmail?email=";
	private final static String URL_GET_CAMPAIGN_BY_PERSON_ID = "getCampaingsNotRegisteredWithThisPerson?idPerson=";
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	@BeforeClass
	public static void setUp() {
		Response r = given().contentType("application/json").when().get(HOST_PORT_REST_API_PERSON + "deleteRegisters");
		String body = r.getBody().asString();
		assertFalse(body.isEmpty());
	}
	
	@Test
	public void test1_RegisterJoaoPalmeirense() throws Exception {
		Person p1 = new Person("Joao", sdf.parse("11/10/1990"), "joao@email.com");
		p1.setTeam(getTeam("Palmeiras"));
		assertFalse(registerPerson(p1).isEmpty());
	}
	
	@Test
	public void test2_InsertLaisSaoPaulina() throws Exception {
		Person p1 = new Person("Lais", sdf.parse("30/10/1995"), "lais@email.com");
		p1.setTeam(getTeam("São Paulo"));
		assertFalse(registerPerson(p1).isEmpty());
	}
	
	@Test
	public void test3_InsertPedroCorintiano() throws Exception {
		Person p1 = new Person("Pedro", sdf.parse("30/07/1997"), "pedro@email.com");
		p1.setTeam(getTeam("Corinthians"));
		assertFalse(registerPerson(p1).isEmpty());
	}
	
	@Test
	public void test4_ValidationOfGetCampaignsValidsAndNotRegisterWithPerson() throws Exception {
		//ADICIONANDO UMA NOVA PESSOA
		Person p1 = new Person("Rebeca", sdf.parse("30/07/1997"), "rebeca@email.com");
		p1.setTeam(getTeam("São Paulo"));
		String idAdd = registerPerson(p1);
		
		//RECUPERANDO TODAS CAMPANHAS DO MESMO TIME QUE A PESSOA E QUE ESTEJAM COM O PERIODO DE VIGENCIA VALIDO E QUE ELA NAO ESTEJA CADASTRADA 
		List<Campaign> campaignsNotRegisterForThisPerson = getCampaigns(idAdd, p1.getTeam().getId().toString());
		
		int size = campaignsNotRegisterForThisPerson.size();
		
		assertFalse(size <= 0);
		
		if (size > 0) {
			p1.setId(Long.valueOf(idAdd));
			Campaign c1 = campaignsNotRegisterForThisPerson.get(0);
			c1.getPeople().add(p1);
			
			//CADASTRA O USUARIO NA CAMPANHA
			Response r = given().contentType("application/json").body(JsonConverter.objectToJson(c1)).when().post(
					"http://localhost:8080/campaigns/registerCampaign");
			assertEquals("ok", r.getBody().asString());
			
			//RECUPERA TODAS CAMPANHAS DO MESMO TIME QUE A PESSOA E QUE ESTEJAM COM O PERIODO DE VIGENCIA VALIDO 
			//E QUE ELA NAO ESTEJA CADASTRADA (ENTAO AGORA DEVE RETORNAR UMA A MENOS QUE A PRIMEIRA VEZ)
			campaignsNotRegisterForThisPerson = getCampaigns(p1.getId().toString(), p1.getTeam().getId().toString());
			assertEquals((size - 1), campaignsNotRegisterForThisPerson.size());
		}
	}
	
	@Test
	public void test5_FoundPersonWithNameJoao() {
		given().when().get(HOST_PORT_REST_API_PERSON).then().body(containsString("Joao"));
	}
	
	@Test
	public void test6_FoundPersonEmptyWithEmailWrong() {
		String emailDontExists = "emailNotRegister@mail.com";
		assertEquals("", get(HOST_PORT_REST_API_PERSON + URL_GET_PERSON_BY_EMAIL + emailDontExists).getBody().asString());
	}
	
	@Test
	public void test7_FoundPersoWithNameLucasnWithEmailRight() {
		String emailJoao = "joao@email.com";
		given().when().get(HOST_PORT_REST_API_PERSON + URL_GET_PERSON_BY_EMAIL + emailJoao).then().body(containsString("Joao"));
	}
	
	private Team getTeam(String name) throws Exception {
		Response r = given().contentType("application/json").when().get(HOST_PORT_REST_API_TEAM_BY_NAME + name);
		return (Team) gson.fromJson(r.getBody().asString(), Team.class);
	}
	
	private List<Campaign> getCampaigns(String idPerson, String idTeam) throws Exception {
		Response r = given().contentType("application/json").when().get(HOST_PORT_REST_API_PERSON + URL_GET_CAMPAIGN_BY_PERSON_ID + idPerson + "&idTeam=" +
				idTeam);
		ObjectMapper mapper = new ObjectMapper();
		List<Campaign> list = mapper.readValue(r.getBody().asString(),
				TypeFactory.defaultInstance().constructCollectionType(List.class,
						Campaign.class));
		return list;
	}
	
	private String registerPerson(Person p1) throws JsonProcessingException {
		Response r = given().contentType("application/json").body(gson.toJson(p1))
				.when().post(HOST_PORT_REST_API_PERSON + "registerPerson");
		return r.getBody().asString();
	}
}
