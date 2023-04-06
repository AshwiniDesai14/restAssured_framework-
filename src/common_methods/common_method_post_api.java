package common_methods;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class common_method_post_api
{
	
public static int responseStatuscode_Extractor(String baseuri,String resource, String req_body)
	
	{
		// Declare base URI through arguments
			RestAssured.baseURI=baseuri;
				
		// Configure and return the status code
			int responseStatuscode =given().header("Content-Type","application/json").body(req_body).when().post(resource)
					.then().extract().statusCode();
			return responseStatuscode;
	}

public static String responseBodyExtractor(String baseuri,String resource, String req_body)

{
	// Declare base URI through arguments
		RestAssured.baseURI=baseuri;
			
	// Configure and return the response body
		String responseBody =given().header("Content-Type","application/json").body(req_body).when().post(resource)
				.then().extract().response().asString();
		return responseBody;
}

}
