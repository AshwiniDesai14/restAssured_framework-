package req_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_methods.getData;

public class Post_request_repository
{
	
	public static String baseuri()
	{
		String baseuri = "https://reqres.in/";
		return baseuri;
	}
	public static String resource ()
	{
		String resource = "api/users";
		return resource;
	}
    public static String Post_request_tc1 () throws IOException
    {
    	ArrayList<String>data=
    	getData.getdataexcel("post_data", "tc1");
    	System.out.println(data);
    	String Name = data.get(1);
    	String job = data.get(2);
    	String reqbody = "{\r\n"
    			+ "    \"name\": \""+Name+"\",\r\n"
    			+ "    \"job\": \""+job+"\"\r\n"
    			+ "}";
    	return reqbody;
    }
}
