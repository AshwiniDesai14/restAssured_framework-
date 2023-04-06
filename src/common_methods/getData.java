package common_methods;

import java.io.FileInputStream;
import java.lang.Exception;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getData {
	
	public static ArrayList<String> getdataexcel(String testsheetName, String testcaseName) throws IOException
	
	{
		ArrayList<String>  Arraydata = new ArrayList<String>();
		
	    //by creating the object of fileinputstream 
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Shivaji\\eclipse-workspace\\restAssured_framework\\testdata.xlsx");
		
	   //create the object of XSSFWorkbook to open the file	
		
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
	   //access the desired sheet
	   //step 3.1 fetch the count of sheets available in the excel file
		 
		int countofsheet=workbook.getNumberOfSheets();
		
		//fetch the name of sheet and compare against desired sheet name
		for(int i=0; i<countofsheet; i++)
		{
		String sheetname=workbook.getSheetName(i);	
	 	
		if (sheetname.equalsIgnoreCase(testsheetName))
		{
			//System.out.println(testsheetName);
			//access the sheet iterate through rows to fetch the column in which testcaseName column found
			XSSFSheet sheet=workbook.getSheetAt(i);
			
			//Step 4.1 create iterator for rows
			Iterator<Row> rows = sheet.iterator();
			Row firstrow=rows.next();
			
			//Step 4.2 Create iterator for cells
			Iterator<Cell> cells = firstrow.cellIterator();
			int j=0;
			int tc_column=0;
			
			//step 4.3 read the cell values of row no 1 to compare against the test case name
			while(cells.hasNext())
			{
				Cell cellvalue = cells.next();
				//System.out.println(cellvalue);
				if (cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
				{
					tc_column = j;
				}
				j++;
			}
			//step 5 fetch the data for designated test case
			while(rows.hasNext())
			{
				Row datarow=rows.next();
				if (datarow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testcaseName))
					{
						Iterator<Cell> datacellValue = datarow.cellIterator();
						
						while(datacellValue.hasNext())
						{ 
							
							Cell dataOfCell = datacellValue.next();
							//method 1 extract cell value by using try and catch
							try 
							{
							String testdata =dataOfCell.getStringCellValue();
							Arraydata.add(testdata);
							}
							catch (IllegalStateException e)
							{
							  int inttestdata= (int) dataOfCell.getNumericCellValue();
							  String Stringtestdata=Integer.toString(inttestdata);
								Arraydata.add(Stringtestdata);
							}
							//method 2 extract cell value by if and else
							/*CellType datatype = dataOfCell.getCellType();
                            if (datatype.toString() == "NUMERIC")
                            {
                            	int inttestdata=  (int) dataOfCell.getNumericCellValue();
                            	String Stringtestdata=Integer.toString(inttestdata);
 								Arraydata.add(Stringtestdata);
								//System.out.println(Stringtestdata);
                            }
                            else if(datatype.toString() == "STRING")
                            {
                            	String testdata =  dataOfCell.getStringCellValue();
								Arraydata.add(testdata);
                            }*/
							//method 3 --extract the data by converting into string 
							//String testdata =dataOfCell.toString().replaceAll("\\.\\d+$", "");
							//Arraydata.add(testdata);
							
							//method 4 --Extract the data by using Dataformatter
							/*DataFormatter format =new DataFormatter();
							String testData=format.formatCellValue(dataOfCell);
							Arraydata.add(testData);
							System.out.println(testData);*/
				

						}
					}
			}
		}
		}
		return Arraydata;
	}

	

}
