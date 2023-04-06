package test_class;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_methods.commonMethodUtilities;
import common_methods.Patch_common_method;
import io.restassured.path.json.JsonPath;
import req_repository.Patch_request_repository;

public class Patch_tc1 

	{
     @Test
		public static void orchestrator() throws IOException
		{
			String responsebody= "";
			int res_statuscode= 0;
			String baseuri =Patch_request_repository.baseuri();
			String resource=Patch_request_repository.resource();
			String requestbody=Patch_request_repository.Patch_request_tc1();

		   for(int i=0 ; i<5 ;i++)
		   {
			     res_statuscode = Patch_common_method.responsestatuscode_extractor(baseuri,resource,requestbody);
		         if (res_statuscode == 200) 
		     {
		    	responsebody=Patch_common_method.responsebody_extractor(baseuri, resource,requestbody);
		    	responsebodyValidator(responsebody);
		    	break;
		    }
		    else
		    {
		    	System.out.println("correct status code is not found in the iteration" + i);
		    }
		}
		commonMethodUtilities.evidenceFilecreator("Patch_TC1", requestbody, responsebody);	
		AssertJUnit.assertEquals(res_statuscode,200);
	}

	 private static void responsebodyValidator(String responsebody)
	 {
		//create json path object to extract responsebody 
				JsonPath jsp = new JsonPath(responsebody);
				
				//extract responsebody parameters
				String res_name =jsp.getString("name");
				System.out.println(res_name);
				
				String res_job =jsp.getString("job");
				System.out.println(res_job);
				
				String res_updatedAt=jsp.getString("updatedAt");
				System.out.println(res_updatedAt);  
				
				//validate responsebody parameter
				
				AssertJUnit.assertEquals(res_name, "swati");
				AssertJUnit.assertEquals(res_job, "leader");
				
				
				//extract date
				String actual_date=res_updatedAt.substring(0,10);
				String current_date=LocalDate.now().toString();
				System.out.println(actual_date);
				//validate a date
				AssertJUnit.assertEquals(actual_date,current_date);
			

		}

}
