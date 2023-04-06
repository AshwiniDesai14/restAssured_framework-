package test_class;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import common_methods.common_method_post_api;
import common_methods.commonMethodUtilities;
import io.restassured.path.json.JsonPath;
import req_repository.Post_request_repository;

public class Post_tc1
{
	@Test
	public static void orchestrator() throws IOException
	{    
		String responseBody = "" ;
		int responseStatuscode = 0;
		String baseUri = Post_request_repository.baseuri();
		String resource = Post_request_repository.resource();
		String requestBody = Post_request_repository.Post_request_tc1();
		for(int i=0 ; i<5 ; i++) 
        {
		 responseStatuscode = common_method_post_api.responseStatuscode_Extractor(baseUri, resource, requestBody);	
          if (responseStatuscode == 201)
		  {
			responseBody = common_method_post_api.responseBodyExtractor(baseUri, resource, requestBody);
			responseBodyValidator(responseBody);
			
			break;
	      }
          else
          {
        	  System.out.println("correct status code is not found in the iteration " + i);
          }
        } 
		commonMethodUtilities.evidenceFilecreator("PostTc1",requestBody,responseBody);
		AssertJUnit.assertEquals(responseStatuscode, 201);
		
     }

    public static void responseBodyValidator(String responseBody)
	{
		// create jsonPath object to extract responsebody parameters
		JsonPath jsp = new JsonPath(responseBody);

		// extract responsebody parameters
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");

		//System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt);

		// validate responsebody parameter
		AssertJUnit.assertEquals(res_name, "morpheus");
		AssertJUnit.assertEquals(res_job, "leader");
		AssertJUnit.assertNotNull(res_id, "assertion error , id parameter is null");

		// extract date from createdAt parameter
		String actual_date = res_createdAt.substring(0, 10);
		String current_date = LocalDate.now().toString(); // Create a date object
		AssertJUnit.assertEquals(actual_date, current_date);
		//System.out.println("Actual DATE : " + actual_date + "\nCURRENT DATE : " + current_date);

	}

	

}
