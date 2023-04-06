package common_methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class commonMethodUtilities {
	
	public static void evidenceFilecreator(String filename, String request, String response) throws IOException
	{
		File newTextFile = new File("D:\\restAssuredEvidence\\" + filename + ".txt");
		if (newTextFile.createNewFile())
		{
	
		FileWriter dataWriter = new FileWriter(newTextFile);
		dataWriter.write("Request body is : \n" + request + "\n\n");
		dataWriter.write("Request body is : \n" + response );
		dataWriter.close();
		System.out.println("Request and response body data successfully saved in :" + newTextFile.getName());
		}
		else
		{
			System.out.println(newTextFile.getName()+ "This file already exists, take a backup of it");
		}
		
	}
}
