package test_class;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_methods.Get_common_method;
import common_methods.commonMethodUtilities;
import io.restassured.path.json.JsonPath;
import req_repository.Get_request_repository;

public class Get_tc1 

	{
      @Test
		public static void orchestrator() throws IOException
		{
			String responsebody= "";
			int res_statuscode= 0;
			String baseuri =Get_request_repository.baseuri();
			String resource=Get_request_repository.resource();
			
		 for(int i=0 ; i<5 ;i++)
		   {
			     res_statuscode = Get_common_method.responsestatuscode_extractor(baseuri,resource);
		         if (res_statuscode == 200) 
		     {
		    	responsebody=Get_common_method.responsebody_extractor(baseuri, resource);
		    	responsebodyValidator(responsebody);
		    	break;
		    }
		    else
		    {
		    	System.out.println("correct status code is not found in the iteration" + i);
		    }
		}
		//evidence file
		 commonMethodUtilities.evidenceFilecreator("GetTC1", null, responsebody);	
		AssertJUnit.assertEquals(res_statuscode,200);
}
		
		private static void responsebodyValidator(String responsebody)
		{
			JsonPath jsp= new JsonPath(responsebody);
			int array_length= jsp.getInt("data.size()");
			System.out.println("the data array length is " + array_length);
			
			int exp_id[]= {7,8,9,10,11,12};
			String exp_email[]= {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in", 
					              "george.edwards@reqres.in","rachel.howell@reqres.in"};
			String exp_fname[]= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
			String exp_lname[]= {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
			String exp_avatar[]= {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg",
					               "https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg",
					               "https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
			for(int i = 0; i<array_length; i++)
			{
				int res_id =jsp.getInt("data["+i+"].id");
				String res_fname=jsp.getString("data["+i+"].first_name");
				String res_lname=jsp.getString("data["+i+"].last_name");
				String res_email=jsp.getString("data["+i+"].email");
				String res_avatar=jsp.getString("data["+i+"].avatar");
				System.out.println(res_id);
				System.out.println(res_fname);
				System.out.println(res_lname);
				System.out.println(res_email);
				System.out.println(res_avatar);
				
				//validation
				AssertJUnit.assertEquals(res_id, exp_id[i]);
				AssertJUnit.assertEquals(res_fname, exp_fname[i]);
				AssertJUnit.assertEquals(res_lname, exp_lname[i]);
				AssertJUnit.assertEquals(res_email, exp_email[i]);
				AssertJUnit.assertEquals(res_avatar, exp_avatar[i]);
			}
	  }
}