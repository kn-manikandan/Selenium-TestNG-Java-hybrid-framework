package helpers;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.accessibility.AccessibilityScanner;
import com.accessibility.Result;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


/**
 * Contains Generic helper methods
 * 
 * @author Ankit
 * @since 2016-09-01
 *
 */
public class GenericHelper{
	
	DataFormatter formatter = new DataFormatter();
	Properties log4jProperties = new Properties();
	XSSFSheet excelWSheet;
    XSSFWorkbook excelWBook;
    XSSFCell cell;
    long currentTime;
	long newTime;
	PDFParser parser;
	String parsedText = null;
	PDFTextStripper pdfStripper;
	PDDocument pdDoc;
	COSDocument cosDoc;
	PDDocumentInformation pdDocInfo;
	
	/**
	 * Will parse the json file and return the jsonObject
	 * @param filePath The file path of the json file to be parsed 
	 * @return jsonObject
	 * @throws FileNotFoundException If the json file does not exist
	 * @throws IOException If the file cannot be read or is closed
	 * @throws ParseException If the json file cannot be parsed
	 */
	public JSONObject jsonParser(String filePath) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject)obj;
		return jsonObject;
	}
	
	public List<String> parseJsonArray(String filePath, String[] keys)
	{
		
		return null;
		
	}
	
	/**
	 * This method will return the method name of the method
	 * @param addExcelExtensionToTheReturnValue This will add .xlsx to the method name(string)
	 * @param depth The stack trace element you want to retrieve from the array(index)
	 * @return Will return the method name
	 */
	public String getMethodName(boolean addExcelExtensionToTheReturnValue,int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[depth].getMethodName();
		if(addExcelExtensionToTheReturnValue)
		{
			methodName = methodName + ".xlsx";
		}
		return methodName;
	}
	
	public String getMethodName(int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[depth].getMethodName();
		return methodName;
	}
	
	/**
	 * Will set the excel file path and will also get the required sheet
	 * @param path Path to the excel file
	 * @param sheetName Sheet name from the excel file
	 */
	public void setExcelFile(String path, String sheetName)
	{
		try {
			FileInputStream excelFile = new FileInputStream(path);
			try {
				excelWBook = new XSSFWorkbook(excelFile);
			} catch (IOException e) {
				Assert.fail(e.getMessage());
			}
            excelWSheet = excelWBook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Will get the row number for the cell data inputed
	 * @param webElement Cell data for which you need the row number
	 * @return Will return the row number
	 */
	public int getRowNumber(String webElement)
	{
		int rowNumber = 0;
		for (int RowNum = 0; RowNum<=excelWSheet.getLastRowNum();RowNum++){
	        if(excelWSheet.getRow(RowNum).getCell(0).toString().equalsIgnoreCase(webElement)){
	        	rowNumber =  excelWSheet.getRow(RowNum).getRowNum();
	        	break;
	        }
		}
		return rowNumber;
	}
	
	/**
	 * For the row number retrieved using the data, we want to get the corresponding test data from the next column
	 * @param webElement The data that you want the row number for
	 * @return Will return the corresponding test data for the data we passed
	 */
	public String getTestData(String webElement)
	{
		int rowNum = this.getRowNumber(webElement);
  	    cell = excelWSheet.getRow(rowNum).getCell(1);
  	    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        String cellData = cell.getStringCellValue();
        return cellData;
  	}
	
	/**
	 * Will open the required excel file and the sheet as well
	 * @param dataFileLocation The path to the excel file
	 */
	public void setTestData(String dataFileLocation)
	{
		this.setExcelFile(dataFileLocation + this.getMethodName(true,3), "sheet1");
	}	
	
	/**
	 * This function will help in switching tabs
	 * @param driver driver
	 * @param tabNumber The tab you want to switch to (current tab will be 0, the next will be 1 and so on)
	 */
	public void switchTabs(WebDriver driver, int tabNumber)
	{
		newTime = System.currentTimeMillis() + 15000;
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		if(tabNumber!=0){
			while(System.currentTimeMillis() < newTime && tabs.size()<1){
				tabs = new ArrayList<String> (driver.getWindowHandles());
			}
		}
		Reporter.log("Switching to tab: " + tabNumber, true);
		driver.switchTo().window(tabs.get(tabNumber));
		Reporter.log("SUCCESS", true);
	}
	
	/**
	 * This function will help in closing the tab
	 * @param driver driver
	 * @param tabNumber The tab you want to switch to and close
	 */
	public void closeTab(WebDriver driver, int tabNumber)
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		Reporter.log("Switching to tab: " + tabNumber, true);
		driver.switchTo().window(tabs.get(tabNumber));
		Reporter.log("Closing tab: " + tabNumber, true);
		driver.close();
		Reporter.log("SUCCESS", true);
	}
	
	/**
	 * This will get the url
	 * @param driver WebDriver driver
	 * @return String url value
	 */
	public String getUrl(WebDriver driver)
	{
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 30000;
		String url = null;
		while (currentTime < newTime){
			try{
				url = driver.getCurrentUrl();
				if(url != null || url != ""){
					Reporter.log("url retrieved: " + url, true);
					return url;
				}
			}
			catch(Exception e){}
			currentTime = System.currentTimeMillis();
			if(currentTime > newTime){
				Assert.fail("Could not get the url");
			}
		}
		return url;
	}
	
	/**
	 * Will run the accessibility test for the current page
	 * @param driver 'WebDriver driver'
	 * @param log log 'Logger log'
	 */
	public void runAccessibilityScan(WebDriver driver, Logger log)
	{
		AccessibilityScanner scanner = null;
		try {
			scanner = new AccessibilityScanner(driver);
		} catch (IOException e) {
			Assert.fail(e.toString());
		}
		Map<String, Object> audit_report = null;
		try {
			audit_report = scanner.runAccessibilityAudit();
		} catch (IOException e) {
			Assert.fail(e.toString());
		}
		if (audit_report.containsKey("error")) {
			 List<?> errors = (List<?>) audit_report.get("error");
			 Assert.assertEquals(errors.size(), 0, "Expected errors to be 0 errors but got " + errors.size() + " errors\n");
			 for (Object error : errors) {
				 log.info(((Result) error).getRule());
				 log.info(((Result) error).getUrl());
			  for (String element : ((Result) error).getElements())
				  log.info(element);
			}	
		}
	}
	
	/**
	 * Helps in going back to the previous page
	 * @param driver 'WebDriver driver'
	 */
	public void goBackTothePreviousPage(WebDriver driver)
	{
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 15000;
		String url= driver.getCurrentUrl();
		Reporter.log("The current url:\n" + url + "\n", true);
		String url1 = url;
		while(url==url1 && System.currentTimeMillis() < newTime){
			driver.navigate().back();
			url1 = driver.getCurrentUrl();
			Reporter.log("The url after navigating to the previous page:\n" + url1 + "\n", true);
		}
		currentTime = System.currentTimeMillis();
		if(currentTime > newTime){
			Assert.fail("Could not navigate to the previous page");
		}
	}
	
	/**
	 * The method will help us in parsing the PDF document
	 * @param fileName The PDF file
	 * @return The text after parsing the PDF
	 */
	public String parsePDF(String fileName)
	{
		newTime = System.currentTimeMillis() + 15000;
		Reporter.log("Parsing text from PDF file " + fileName + "....", true);
		File file = null;
		while(parsedText==null && System.currentTimeMillis() < newTime){
			file = new File(fileName);
			try{
				parser = new PDFParser(new FileInputStream(file));
		        parser.parse();
	            cosDoc = parser.getDocument();
	            pdfStripper = new PDFTextStripper();
	            pdDoc = new PDDocument(cosDoc);
	            parsedText = pdfStripper.getText(pdDoc);
			}
			catch(Exception e){
		        if(System.currentTimeMillis() >= newTime){
					Reporter.log("File " + fileName + " does not exist.\n" + e.getMessage(), true);
		        }
			}
		}
		Reporter.log("Parsed PDF Text: \n" + parsedText, true);
        return parsedText;
	}
	
	/**
	 * Will help us in creating a new folder
	 * @param path The path of the folder to be created	
	 * @return boolean true or false, whether we could create the folder or not
	 */
	public boolean createFolder(String path)
	{
		try{
			File file = new File(path);
			if(!file.exists()){
			new File(path).mkdirs();
			return true;
			}
		}
		catch(Exception e)
		{
			Reporter.log(e.getMessage(),true);
		}
		return false;
	}
}
