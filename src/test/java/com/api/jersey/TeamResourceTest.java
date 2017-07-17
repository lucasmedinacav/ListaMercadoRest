package com.api.jersey;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.api.representations.Team;
import com.api.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamResourceTest {
	
	private final static String HOST_PORT_REST_API_TEAM = "http://localhost:8080/teams/";
	
	@BeforeClass
	public static void setUp() {
		Response r = given().contentType("application/json")
				.when().get(HOST_PORT_REST_API_TEAM + "deleteRegisters");
		assertEquals("ok", r.getBody().asString());
	}
	
	@Test
	public void testRegisterPalmeiras() throws ParseException, JsonProcessingException {
		Team t1 = new Team("Palmeiras");
		Response r = given().contentType("application/json").body(JsonConverter.objectToJson(t1))
				.when().post(HOST_PORT_REST_API_TEAM + "registerTeam");
		String body = r.getBody().asString();
		assertEquals("ok", body);
	}
	
	@Test
	public void testRegisterSaoPaulo() throws ParseException, JsonProcessingException {
		Team t1 = new Team("São Paulo");
		Response r = given().contentType("application/json").body(JsonConverter.objectToJson(t1))
				.when().post(HOST_PORT_REST_API_TEAM + "registerTeam");
		assertEquals("ok", r.getBody().asString());
	}
	
	@Test
	public void testRegisterCorinthians() throws ParseException, JsonProcessingException {
		Team t1 = new Team("Corinthians");
		Response r = given().contentType("application/json").body(JsonConverter.objectToJson(t1))
				.when().post(HOST_PORT_REST_API_TEAM + "registerTeam");
		assertEquals("ok", r.getBody().asString());
	}
	
	@Test
	public void testTotalTeams() {
		Response r = given().contentType("application/json").when().get(HOST_PORT_REST_API_TEAM + "countTeams");
		assertEquals("3", r.getBody().asString());
	}
}