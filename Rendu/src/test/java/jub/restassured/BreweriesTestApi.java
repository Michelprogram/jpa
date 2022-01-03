package jub.restassured;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


import io.restassured.response.Response;


import jub.service.BreweriesService;
import org.junit.jupiter.api.*;


import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BreweriesTestApi {
    final static String host = "http://localhost:8080";
    final static String version = "/api/v1";
    final static String baseUrl = host+version+"/brasserie";

    @BeforeAll
    static void  setup(){
        Response res = given().when().get(host+version+"/util/brasserie/reset");
        System.out.println(host+version+"/util/reset");
    }

    @Test
    @Order(1)
    public void get_brasserie_status_responseOK() {

        Response res = given().when().get(baseUrl);
        assertEquals(200,res.getStatusCode());
        String s =  "[{\"id\":1,\"name\":\"brewery1\",\"address\":\"address1\",\"city\":\"city1\"," +
                "\"country\":\"fr\",\"description\":\"desc1\"}," +
                "{\"id\":2,\"name\":\"brewery2\",\"address\":\"address2\",\"city\":\"city2\"," +
                "\"country\":\"fr\",\"description\":\"desc2\"}]";
        assertEquals(s,res.getBody().asString());
        }
    @Test
    @Order(2)
    public void get_brasserie_1_status_responseOK() {

        Response res = given().when().get(baseUrl+"/1");
        assertEquals(200,res.getStatusCode());
        String s =  "{\"id\":1,\"name\":\"brewery1\",\"address\":\"address1\",\"city\":\"city1\",\"country\":\"fr\"," +
                "\"description\":\"desc1\"}";
        assertEquals(s,res.getBody().asString());
    }
    @Test
    @Order(3)
    public void get_brasserie_1_status_responseKO() {

        Response res = given()
                .when()
                .get(baseUrl+"/3");
        assertEquals(404,res.getStatusCode());
        String s =  "{\"error\":\"not found\"}";
        assertEquals(s,res.getBody().asString());
    }

    @Test
    @Order(4)
    public void post_brasserie_3_status_responseKO() {
        Map<String, String> request = new HashMap<>();
        request.put("id", "3");


        Response res = given()
                .contentType("application/json")
                .body(request)
                .when().post(baseUrl);
       assertEquals(203,res.getStatusCode());
        String s =  "{\"error\":\"request error\"}";
        assertEquals(s,res.getBody().asString());
    }
    @Test
    @Order(5)
    public void post_brasserie_1_status_responseKO() {
        Map<String, String> request = new HashMap<>();
        request.put("id", "1");
        request.put("name", "brewery3");
        request.put("address", "address3");
        request.put("city", "city3");
        request.put("country", "fr");
        request.put("description", "desc3");


        Response res = given()
                .contentType("application/json")
                .body(request)
                .when().post(baseUrl);
        assertEquals(203,res.getStatusCode());
        String s =  "{\"error\":\"already exists\"}";
        assertEquals(s,res.getBody().asString());
    }
    @Test
    @Order(6)
    public void post_brasserie_1_status_responseOK() {
        Map<String, String> request = new HashMap<>();
        request.put("id", "3");
        request.put("name", "brewery3");
        request.put("address", "address3");
        request.put("city", "city3");
        request.put("country", "fr");
        request.put("description", "desc3");


        Response res = given()
                .contentType("application/json")
                .body(request)
                .when().post(baseUrl);
        assertEquals(201,res.getStatusCode());
        String s =  "{\"id\":3,\"name\":\"brewery3\",\"address\":\"address3\",\"city\":\"city3\",\"country\":\"fr\"" +
                ",\"description\":\"desc3\"}";
        assertEquals(s,res.getBody().asString());
    }
}
