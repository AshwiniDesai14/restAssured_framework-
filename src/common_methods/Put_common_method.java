package common_methods;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Put_common_method {
	public static int responsestatuscode_extractor(String baseuri, String resource, String reqbody)
	{
		RestAssured.baseURI=baseuri;
		
		int res_status_code= given().header("Content-Type","application/json").body(reqbody)
				.when().put(resource)
				.then().extract().statusCode();
		return res_status_code;

	}
	
	public static String responsebody_extractor(String baseuri, String resource, String reqbody)
	{
		RestAssured.baseURI=baseuri;
		
		String res_body= given().header("Content-Type","application/json").body(reqbody)
				.when().put(resource)
				.then().extract().response().asString();
		return res_body;
     }
	
	
}

