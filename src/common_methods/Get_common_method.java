package common_methods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Get_common_method {

	public static int responsestatuscode_extractor(String baseuri, String resource)
	{
		RestAssured.baseURI=baseuri;
		
		int res_status_code= given()
				.when().get(resource)
				.then().extract().statusCode();
		return res_status_code;

	}
	
	public static String responsebody_extractor(String baseuri, String resource)
	{
		RestAssured.baseURI=baseuri;
		
		String res_body= given()
				.when().get(resource)
				.then().extract().response().asString();
		return res_body;
     }
	
	
}

