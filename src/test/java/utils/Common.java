package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Common {

  public static RequestSpecBuilder REQUEST_BUILDER;
  public static RequestSpecification REQUEST_SPEC;
  public static ResponseSpecBuilder RESPONSE_BUILDER;
  public static ResponseSpecification RESPONSE_SPEC;

  public static RequestSpecification getRequestSpec() {
    REQUEST_BUILDER = new RequestSpecBuilder();
    REQUEST_BUILDER.setBaseUri(Path.BASE_URL);
    REQUEST_BUILDER.setContentType(ContentType.JSON);
    return REQUEST_SPEC = REQUEST_BUILDER.build();
  }

  public static ResponseSpecification getResponseSpec(){
    RESPONSE_BUILDER = new ResponseSpecBuilder();
    RESPONSE_BUILDER.expectContentType(ContentType.JSON);
    return RESPONSE_SPEC = RESPONSE_BUILDER.build();
  }

}
