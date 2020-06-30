package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestFlow {

  String token = "";

  @BeforeClass
  public void setup() {
    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
  }

  @Test
  public void createToken() {
    Response
        response = given().log()
        .ifValidationFails()
        .contentType(ContentType.JSON)
        .body("{\"username\": \"admin\",\"password\":\"password123\"}")
        .when()
        .post("/auth")
        .then()
        .log()
        .ifError()
        .statusCode(200)
        .log()
        .body()
        .extract()
        .response();

    token = response.path("token");
    System.out.println("token: " + token);
  }

  @Test
  public void getBookingsIds() {
    Response response = given().log().ifValidationFails().contentType(ContentType.JSON).when().get("/booking")
        .then().log().ifError().statusCode(200).log().body().extract().response();

    System.out.println(response.toString());

  }
}
