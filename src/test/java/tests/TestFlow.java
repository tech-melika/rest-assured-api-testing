package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Common;
import utils.EndPoints;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class TestFlow {

  private static String token = "";

  RequestSpecification requestSpec;
  ResponseSpecification responseSpec;

  @BeforeClass
  public void setup() {
    System.out.println("Starting the application");
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    requestSpec = Common.getRequestSpec();
    responseSpec = Common.getResponseSpec();
  }

  @Test
  public void createToken() {
    Response
        response = given().spec(requestSpec)
        .body("{\"username\": \"admin\",\"password\":\"password123\"}")
        .when()
        .post(EndPoints.CREATE_TOKEN)
        .then()
        .spec(responseSpec)
        .statusCode(200)
        .extract()
        .response();

    token = response.path("token");
    System.out.println("token: " + token);
  }

  @Test
  public void getBookingsIds() {
    Response response = given().spec(requestSpec).when().get(EndPoints.BOOKING)
        .then().spec(responseSpec).statusCode(200).log().body().extract().response();
  }

  @Test
  public void createBooking() {
    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("firstname", "Jim");
    Response response = given().spec(requestSpec)
        .when()
        .post(EndPoints.BOOKING)
        .then()
        .spec(responseSpec)
        .statusCode(200)
        .log()
        .body()
        .extract()
        .response();
  }
}
