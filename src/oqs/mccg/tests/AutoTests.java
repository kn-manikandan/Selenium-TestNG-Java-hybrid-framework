package oqs.mccg.tests;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import helpers.BaseClass;
import helpers.GenericHelper;
import helpers.OQSHelper;
import helpers.UserActions;
import oqs.mccg.pages.BrokerPageWebElements;
import oqs.mccg.pages.QuotePageWebElements;
import oqs.mccg.pages.SummaryPageWebElements;

/**
 * Auto Tests
 * @author Ankit
 * @since 10/07/2016
 */
public class AutoTests extends BaseClass {
	QuotePageWebElements quotePage = new QuotePageWebElements();
	BrokerPageWebElements brokerPage = new BrokerPageWebElements();
	SummaryPageWebElements summaryPage = new SummaryPageWebElements();
	UserActions actions = new UserActions();
	GenericHelper helper = new GenericHelper();
	OQSHelper oqsHelp = new OQSHelper();
	String dataFileLocation = System.getProperty("user.dir") + "/data/oqs/mccg/";
	LocalDateTime now = LocalDateTime.now();
	
	/**
	 * View quote details button is enabled
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test
	public void auto_001() throws InterruptedException
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(quotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(quotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(quotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(quotePage.classOfLicenseGButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDown(),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDown(),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDown(),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
		
		oqsHelp.moveSlider(quotePage.howManyYearsContinuosAutoInsuranceSlider(), helper.getTestData("howManyYearsContinuosAutoInsuranceNumberInput"), quotePage.howManyYearsContinuosAutoInsuranceNumberInput(), true, false);	
		
		actions.click(quotePage.autoConvictionsNoButton());
		actions.click(quotePage.areYouRetiredNoButton());
		oqsHelp.moveSlider(quotePage.modelYearSlider(), helper.getTestData("modelYearNumberInput"), quotePage.modelYearNumberInput(), true, false);	
		actions.select(quotePage.manufacturerDropDown(), helper.getTestData("manufacturerDropDown"));
		actions.select(quotePage.modelDropDown(), helper.getTestData("modelDropDown"));
		actions.select(quotePage.whoWillBeMainDriverDropDown(), helper.getTestData("whoWillBeMainDriverDropDown"));
		actions.click(quotePage.willAnyOtherDriverBeUnder25NoButton());
		
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSlider"), quotePage.whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearLabel(), false, true);
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverPerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverPerYearSlider"), quotePage.whatIsTheTotalDistanceDriverPerYearLabel(), false, true);
		oqsHelp.moveSlider(quotePage.whatIsDistanceDrivenToWorkOneWaySlider(), helper.getTestData("whatIsDistanceDrivenToWorkOneWaySlider"), quotePage.whatIsDistanceDrivenToWorkOneWayLabel(), false, true);
		
		actions.click(quotePage.doesThisVehicleHaveWinterTiresNoButton());
		actions.click(quotePage.doYouHaveAnyPropertyFarmCommercialPoliciesWithUsNoButton());
		actions.click(quotePage.doYouHaveOtherPrivatePassengerVehiclesNoButton());
		actions.click(quotePage.increaseMyDeductibleAmount500Button());
		actions.click(quotePage.decreaseMyLiabilityInsurance2MillionButton());
		Thread.sleep(1000);
		oqsHelp.waitForTheSpinnerToDisappear();
		
		//validates the button is enabled
		Assert.assertEquals(quotePage.viewQuoteDetailsButton().getAttribute("class").contains("btn-primary"), true);
	}
	
	/**
	 * Clicking on the logo should take you to the company site
	 */
	@Test
	public void auto_002()
	{
		actions.click(quotePage.logo());
		helper.switchTabs(driver, 1);
		Assert.assertEquals(helper.getUrl(driver), "https://www.mccg.net/");
		helper.closeTab(driver, 1);
		helper.switchTabs(driver, 0);
	}
	
	/**
	 * Validate only 5 drivers can be added
	 */
	@Test
	public void auto_003()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		for(int i=0;i<4;i++){
			actions.click(quotePage.addAnotherDriverTab());
		}
		List<WebElement> tabs = quotePage.driversTabs();
		Assert.assertEquals(actions.getText(tabs.get(0)), helper.getTestData("nameOfTheDriverTextInput"));
		for(int i=1;i < tabs.size();i++){
			Assert.assertEquals(actions.getText(tabs.get(i)), "Driver "+String.valueOf(i+1));
		}
	}
	
	/**
	 * Validate only 4 vehicles can be added
	 */
	@Test
	public void auto_004()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(quotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(quotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(quotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(quotePage.classOfLicenseGButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDown(),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDown(),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDown(),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
		
		oqsHelp.moveSlider(quotePage.howManyYearsContinuosAutoInsuranceSlider(), helper.getTestData("howManyYearsContinuosAutoInsuranceNumberInput"), quotePage.howManyYearsContinuosAutoInsuranceNumberInput(), true, false);	
		
		actions.click(quotePage.autoConvictionsNoButton());
		actions.click(quotePage.areYouRetiredNoButton());
		for(int i=0;i<3;i++){
			actions.click(quotePage.addAnotherVehicleTab());
		}
		List<WebElement> tabs = quotePage.vehiclesTabs();
		for(int i=0;i < tabs.size();i++){
			Assert.assertEquals(actions.getText(tabs.get(i)), "Vehicle "+String.valueOf(i+1));
		}	
	}
	
	/**
	 *  Validate error message displayed when D.O.B < License Obtained Date
	 */
	@Test
	public void auto_005()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(quotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(quotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(quotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(quotePage.classOfLicenseGButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDown(),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDown(),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDown(),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
		
		oqsHelp.moveSlider(quotePage.howManyYearsContinuosAutoInsuranceSlider(), helper.getTestData("howManyYearsContinuosAutoInsuranceNumberInput"), quotePage.howManyYearsContinuosAutoInsuranceNumberInput(), true, false);	
		
		actions.click(quotePage.autoConvictionsNoButton());
		actions.click(quotePage.areYouRetiredNoButton());
		oqsHelp.moveSlider(quotePage.modelYearSlider(), helper.getTestData("modelYearNumberInput"), quotePage.modelYearNumberInput(), true, false);	
		actions.select(quotePage.manufacturerDropDown(), helper.getTestData("manufacturerDropDown"));
		actions.select(quotePage.modelDropDown(), helper.getTestData("modelDropDown"));
		actions.select(quotePage.whoWillBeMainDriverDropDown(), helper.getTestData("whoWillBeMainDriverDropDown"));
		actions.click(quotePage.willAnyOtherDriverBeUnder25NoButton());
		String errorMessage = actions.getText(quotePage.quoteErrorResponse());
		Assert.assertEquals(errorMessage, "Continuous auto insurance exceeds the time that Jessy Brown has had a license. Please review your answers.");
	}
	
	/**
	 * Current license class drop down fields should be automatically populated to D.O.B + 17 years
	 */
	@Test
	public void auto_006()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(quotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(quotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(quotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(quotePage.classOfLicenseGButton());
		
		Assert.assertEquals(actions.getTextOfTheDropDownValue(quotePage.dayCurrentLicenseObtainedDropDown()),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		Assert.assertEquals(actions.getTextOfTheDropDownValue(quotePage.monthCurrentLicenseObtainedDropDown()),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		Assert.assertEquals(actions.getTextOfTheDropDownValue(quotePage.yearCurrentLicenseObtainedDropDown()),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
	}
	
	/**
	 * Validate the list of main drivers are generated automatically
	 */
	@Test
	public void auto_007()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(quotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(quotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(quotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(quotePage.classOfLicenseGButton());
		
		actions.click(quotePage.addAnotherDriverTab());
		
		actions.set(quotePage.nameOfTheDriverTextInputDriver2(),helper.getTestData("nameOfTheDriverTextInputDriver2"));
		actions.select(quotePage.dayDOBDropDownDriver2(),helper.getTestData("dayDOBDropDownDriver2"));
		actions.select(quotePage.monthDOBDropDownDriver2(),helper.getTestData("monthDOBDropDownDriver2"));
		actions.select(quotePage.yearDOBDropDownDriver2(),helper.getTestData("yearDOBDropDownDriver2"));
		
		actions.click(quotePage.classOfLicenseDriver2GButton());
		
		oqsHelp.moveSlider(quotePage.modelYearSlider(), helper.getTestData("modelYearNumberInput"), quotePage.modelYearNumberInput(), true, false);	
		actions.select(quotePage.manufacturerDropDown(), helper.getTestData("manufacturerDropDown"));
		actions.select(quotePage.modelDropDown(), helper.getTestData("modelDropDown"));
		
		List<String> dropDownOptions = actions.getDropDownOptions(quotePage.whoWillBeMainDriverDropDown());
		Assert.assertEquals(dropDownOptions.get(0), helper.getTestData("nameOfTheDriverTextInput"));
		Assert.assertEquals(dropDownOptions.get(1), helper.getTestData("nameOfTheDriverTextInputDriver2"));
	}
	
	/**
	 * Validate broker information matches with the information on the pin in the map
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test
	(dependsOnMethods = { "auto_001" })
	public void auto_008() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		List<String> brokerBDetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("B",true,true,true);
		String brokerBName = brokerBDetails.get(0);
		String brokerBAddress = brokerBDetails.get(1);
		Assert.assertEquals(oqsHelp.validateBrokerInformationOnTheMap(brokerBName),true,"The broker selected and the name of the broker on the pin in the map do not match");
		Assert.assertEquals(oqsHelp.validateBrokerInformationOnTheMap(brokerBAddress),true,"The address of the broker selected and the address of the broker on the pin in the map do not match");
		List<String>brokerADetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		String brokerAName = brokerADetails.get(0);
		String brokerAAddress = brokerADetails.get(0);
		Assert.assertEquals(oqsHelp.validateBrokerInformationOnTheMap(brokerAName),true,"The broker selected and the name of the broker on the pin in the map do not match");
		Assert.assertEquals(oqsHelp.validateBrokerInformationOnTheMap(brokerAAddress),true,"The address of the broker selected and the address of the broker on the pin in the map do not match");
	}
	
	/**
	 * Validate postal code on the broker page matches with the postal code entered on the quote page
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test(dependsOnMethods = { "auto_001" })
	public void auto_009() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton());
		Assert.assertEquals(actions.getValue(brokerPage.postalCodeTextInput()), helper.getTestData("postalCodeTextInput"));
	}
	
	/**
	 * Validate clicking on the logo from the broker page works correctly
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test(dependsOnMethods = { "auto_001","auto_002" })
	public void auto_010() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton());
		this.auto_002();
	}
	
	/**
	 * Clicking on the broker's web site should take you to their web site
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test
	(dependsOnMethods = { "auto_001" })
	public void auto_011() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton());
		oqsHelp.clickOnBrokerAndThenClickOnTheirWebSite("B");
		helper.switchTabs(driver,1);
		Assert.assertEquals(helper.getUrl(driver), "https://pearsonins.ca/");
		helper.closeTab(driver,1);
		helper.switchTabs(driver,0);
	}
	
	/**
	 * Validate Broker logo is added to the Quote page
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test(dependsOnMethods = { "auto_001" })
	public void auto_012() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton());
		List<String> brokerDetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("B",true,true,true);
		String logoSrc = brokerDetails.get(2);
		helper.goBackTothePreviousPage(driver);
		Assert.assertEquals((quotePage.brokerLogo().getAttribute("style")).contains(logoSrc), true);
	}
	
	/**
	 *  Validate that you cannot navigate to the broker page without entering the valid information on the quote page
	 */
	@Test
	public void auto_013()
	{	
		String urlToTheBrokerPage = url.replace("quote", "broker");
		while(driver.getCurrentUrl() != urlToTheBrokerPage){
			driver.get(urlToTheBrokerPage);
			break;
		}
		Assert.assertEquals(driver.getCurrentUrl(), url);
	}
	
	/**
	 *  Validate that the three sections on the OQS page collapse after clicking the header of the section
	 */
	@Test
	public void auto_014()
	{	
		helper.setTestData(dataFileLocation);
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		
		// Validates that the section collapses after clicking 
		int i=1;
		while(quotePage.tellUsAboutWhoWillBeDrivingOQSSection().getAttribute("class").contains("hide") == false && i<=5){
			actions.click(quotePage.tellUsAboutWhoWillBeDrivingHeader());	
			i++;
		}
		i=1;
		
		// Validates that the section collapses after clicking 
		while(quotePage.tellUsAboutYourVehiclesOQSSection().getAttribute("class").contains("hide") == false && i<=5){
			actions.click(quotePage.tellUsAboutYourVehiclesHeader());	
			i++;
		}
		i=1;
		
		// Validates that the section collapses after clicking 
		while(quotePage.howCanISaveYourMoneyOQSSection().getAttribute("class").contains("hide") == false && i<=5){
			actions.click(quotePage.howCanISaveYourMoneyHeader());	
			i++;
		}
		
		Assert.assertEquals(quotePage.tellUsAboutWhoWillBeDrivingOQSSection().getAttribute("class").contains("hide"), true);
		Assert.assertEquals(quotePage.tellUsAboutYourVehiclesOQSSection().getAttribute("class").contains("hide"), true);
		Assert.assertEquals(quotePage.howCanISaveYourMoneyOQSSection().getAttribute("class").contains("hide"), true);
	}
	
	/**
	 * Validate navigation to summary page from the broker page is not possible without selecting a broker
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test
//	(dependsOnMethods = { "auto_001" })
	public void auto_015() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton());
		Assert.assertEquals(actions.getValue(brokerPage.postalCodeTextInput()), helper.getTestData("postalCodeTextInput"));
		String brokerUrl = driver.getCurrentUrl();
		driver.get(brokerUrl.replace("broker", "summary"));
		Assert.assertEquals(driver.getCurrentUrl(),url.replace("quote/", "broker"));
	}
	
	/**
	 * Validate information on the summary page matches with the data provided to get the quote
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test(dependsOnMethods = { "auto_001" })
	public void auto_016() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		helper.goBackTothePreviousPage(driver);
		oqsHelp.waitForTheSpinnerToDisappear();
		String quoteValueMonthly = actions.getText(quotePage.quoteValueMonthly());
		String quoteValueYearly = actions.getText(quotePage.quoteValueYearly());
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		List<String> brokerADetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		String brokerName = brokerADetails.get(0);
		String brokerAddress = brokerADetails.get(1);
		
		Assert.assertEquals(actions.getValue(brokerPage.postalCodeTextInput()), helper.getTestData("postalCodeTextInput"));
		
		oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		actions.click(brokerPage.continueWithThisBrokerButton(),true);
		Assert.assertEquals(actions.getText(summaryPage.summaryPageTitle()).contains("Auto"),true);		
		Assert.assertEquals(actions.getText(summaryPage.companyInformation()).contains("Mutual Concept Computer Group Inc."), true);
		Assert.assertEquals(actions.getText(summaryPage.companyInformation()).contains("785 Wonderland Road South London, ON N6K 1M6"), true);
		Assert.assertEquals(actions.getText(summaryPage.brokerName()), brokerName);
		Assert.assertEquals(actions.getText(summaryPage.brokerAddress()), brokerAddress);
		
		String quoteOnTheSummaryPage = actions.getText(summaryPage.autoQuote());
		int index = quoteValueMonthly.indexOf("/");
		quoteValueMonthly = quoteValueMonthly.substring(0, index) + " " + quoteValueMonthly.substring(index);
		index = quoteValueYearly.indexOf("/");
		quoteValueYearly = quoteValueYearly.substring(0, index) + " " + quoteValueYearly.substring(index);
		
		Assert.assertEquals(quoteOnTheSummaryPage.contains(quoteValueMonthly),true);
		Assert.assertEquals(quoteOnTheSummaryPage.contains(quoteValueYearly),true);
		String itemOnTheSummaryPage = actions.getText(summaryPage.item());
		Assert.assertEquals(itemOnTheSummaryPage.contains(helper.getTestData("modelYearNumberInput")), true);
		Assert.assertEquals(itemOnTheSummaryPage.contains(helper.getTestData("manufacturerDropDown")), true);
		Assert.assertEquals(itemOnTheSummaryPage.contains(helper.getTestData("modelDropDown")), true);
		Assert.assertEquals(actions.getText(summaryPage.driverName().get(0)), helper.getTestData("nameOfTheDriverTextInput"));
	}
	
	/**
	 * PDF Validation: Positive test case for auto insurance quote: 1 Driver & 1 Vehicle
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 * @throws ParseException Signals that an error has been reached unexpectedly while parsing
	 */
	@Test(dependsOnMethods = { "auto_001" },groups = {"PDF Validations"})
	public void auto_017() throws InterruptedException, ParseException
	{
		this.auto_001();
		List<String> questionsAndAnswersOnThePage = oqsHelp.getAllQuestionsAndAnswers(oqsHelp.getAllQuestions(quotePage.questions()), oqsHelp.getAllAnswers(quotePage.answers()));
		
		actions.click(quotePage.viewQuoteDetailsButton());
		oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		actions.click(brokerPage.continueWithThisBrokerButton());
		actions.click(summaryPage.downloadQuoteButton());
		String pdfText = helper.parsePDF(fileWhereOQSQuotesWillBeSaved + "//Auto Quote.pdf").replace("\n", "").replace("\r", " ");	
		String companyName = actions.getText(summaryPage.companyName());
		Reporter.log("\nCompany Name: " + companyName + "\n",true);
		String companyAddress = actions.getText(summaryPage.companyInformation()).replace(companyName, "").replace("\n", "");
		Reporter.log("\nCompany Address: " + companyAddress + "\n",true);
		String brokerName = actions.getText(summaryPage.brokerName());
		Reporter.log("\nBroker name: " + brokerName + "\n",true);
		String brokerAddress = actions.getText(summaryPage.brokerAddress());
		Reporter.log("\nBroker Address: " + brokerAddress + "\n",true);
		
		List<String> summaryDateAndTime = oqsHelp.getSummaryPageDateAndTime((summaryPage.summaryDateAndTime()));
		String summaryDate = summaryDateAndTime.get(0);
		String summaryTime = summaryDateAndTime.get(1);
	    
		String quoteValueMonthly = actions.getText(summaryPage.quoteValueMonthly());
		String quoteValueYearly = actions.getText(summaryPage.quoteValueYearly());
		int index = quoteValueMonthly.indexOf("/");
		quoteValueMonthly = quoteValueMonthly.substring(0, index);
		Reporter.log("\nMonthly quote: " + quoteValueMonthly + "\n",true);
		index = quoteValueYearly.indexOf("/");
		quoteValueYearly = quoteValueYearly.substring(0, index);
		Reporter.log("\nYearly quote: " + quoteValueYearly + "\n",true);
		
		Assert.assertEquals(pdfText.contains(companyName),true);
		Assert.assertEquals(pdfText.contains(companyAddress),true);
		Assert.assertEquals(pdfText.contains(brokerName),true);
		Assert.assertEquals(pdfText.contains(brokerAddress),true);
		Assert.assertEquals(pdfText.contains(summaryDate),true);
		Assert.assertEquals(pdfText.contains(summaryTime),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("nameTextInput")),true);
		Assert.assertEquals(pdfText.contains("(519) 432-8553"),true);
		Assert.assertEquals(pdfText.contains(quoteValueMonthly),true);
		Assert.assertEquals(pdfText.contains(quoteValueYearly),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("modelYearNumberInput")),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("manufacturerDropDown")),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("modelDropDown")),true);
		
		//validate coverages 
		List<WebElement> coverages = summaryPage.coverages();
		for (int i = 0;i < coverages.size();i++){
			String coverage = coverages.get(0).getText();
			String coverageDeductibleNewValue;
			String coverageLimitNewValue;
			int indexOfCoverageDeductible = coverage.indexOf("$");
			int index2OfCoverageDeductible = coverage.indexOf("$",coverage.indexOf("$")+1);
			String coverageDeductibleActualValue = coverage.substring(indexOfCoverageDeductible+1, index2OfCoverageDeductible).trim();
			
			//ensure locale is English, have to use this because we cannot parse the value to double as the value has commas in it sometimes
			NumberFormat nf = NumberFormat.getInstance();
			double coverageDeductibleDoubleValue = nf.parse(coverageDeductibleActualValue).doubleValue();

			if (coverageDeductibleDoubleValue % 1 == 0){
				coverageDeductibleNewValue = String.valueOf((int)coverageDeductibleDoubleValue);
			}
			else{
				coverageDeductibleNewValue = String.valueOf(coverageDeductibleDoubleValue);
			}
			String coverageLimitActualValue = coverage.substring(index2OfCoverageDeductible+1).trim();
			double coverageLimitDoubleValue = nf.parse(coverageLimitActualValue).doubleValue();
			if (coverageLimitDoubleValue % 1 == 0){
				coverageLimitNewValue = String.valueOf((int)coverageLimitDoubleValue);
			}
			else{
				coverageLimitNewValue = String.valueOf(coverageLimitDoubleValue);
			}
			coverage = coverage.replace(coverageDeductibleActualValue, coverageDeductibleNewValue).replace(coverageLimitActualValue, coverageLimitNewValue);
			Assert.assertEquals(pdfText.contains(coverage),true);
		}
		Reporter.log("\nQuestions and the corresponding Answers from the quote page:\n",true);
		for (int i = 0;i < questionsAndAnswersOnThePage.size();i++){
			String questionAndAnswer = questionsAndAnswersOnThePage.get(i);
			Reporter.log(questionAndAnswer,true);
			Assert.assertEquals(pdfText.contains(questionAndAnswer),true);
		}
	}
	
	/**
	 * Validate information on the summary page matches with the data provided to get the quote for 2 drivers and 1 vehicle
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test
	public void auto_018() throws InterruptedException
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(quotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(quotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(quotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(quotePage.classOfLicenseGButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDown(),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDown(),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDown(),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
		
		oqsHelp.moveSlider(quotePage.howManyYearsContinuosAutoInsuranceSlider(), helper.getTestData("howManyYearsContinuosAutoInsuranceNumberInput"), quotePage.howManyYearsContinuosAutoInsuranceNumberInput(), true, false);	
		
		actions.click(quotePage.autoConvictionsNoButton());
		actions.click(quotePage.areYouRetiredNoButton());
		
		//Add one more driver
		actions.click(quotePage.addAnotherDriverTab());
		
		actions.set(quotePage.nameOfTheDriverTextInputDriver2(),helper.getTestData("nameOfTheDriverTextInputDriver2"));
		actions.select(quotePage.dayDOBDropDownDriver2(),helper.getTestData("dayDOBDropDownDriver2"));
		actions.select(quotePage.monthDOBDropDownDriver2(),helper.getTestData("monthDOBDropDownDriver2"));
		actions.select(quotePage.yearDOBDropDownDriver2(),helper.getTestData("yearDOBDropDownDriver2"));
		actions.click(quotePage.classOfLicenseDriver2GButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDownDriver2(),helper.getTestData("dayCurrentLicenseObtainedDropDownDriver2"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDownDriver2(),helper.getTestData("monthCurrentLicenseObtainedDropDownDriver2"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDownDriver2(),helper.getTestData("yearCurrentLicenseObtainedDropDownDriver2"));

		oqsHelp.moveSlider(quotePage.howManyYearsContinuosAutoInsuranceSliderDriver2(), helper.getTestData("howManyYearsContinuosAutoInsuranceNumberInput2"), quotePage.howManyYearsContinuosAutoInsuranceNumberInputDriver2(), true, false);	
		actions.click(quotePage.autoConvictionsNoButtonDriver2());
		actions.click(quotePage.areYouRetiredNoButtonDriver2());
		
		oqsHelp.moveSlider(quotePage.modelYearSlider(), helper.getTestData("modelYearNumberInput"), quotePage.modelYearNumberInput(), true, false);	
		actions.select(quotePage.manufacturerDropDown(), helper.getTestData("manufacturerDropDown"));
		actions.select(quotePage.modelDropDown(), helper.getTestData("modelDropDown"));
		actions.select(quotePage.whoWillBeMainDriverDropDown(), helper.getTestData("whoWillBeMainDriverDropDown"));
		actions.click(quotePage.willAnyOtherDriverBeUnder25YesButton());
		actions.select(quotePage.whoWillBeTheMainDriverUnder25DropDown(), helper.getTestData("whoWillBeTheMainDriverUnder25DropDown"));
		
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSlider"), quotePage.whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearLabel(), false, true);
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverPerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverPerYearSlider"), quotePage.whatIsTheTotalDistanceDriverPerYearLabel(), false, true);
		oqsHelp.moveSlider(quotePage.whatIsDistanceDrivenToWorkOneWaySlider(), helper.getTestData("whatIsDistanceDrivenToWorkOneWaySlider"), quotePage.whatIsDistanceDrivenToWorkOneWayLabel(), false, true);
		
		actions.click(quotePage.doesThisVehicleHaveWinterTiresNoButton());
		actions.click(quotePage.doYouHaveAnyPropertyFarmCommercialPoliciesWithUsNoButton());
		actions.click(quotePage.doYouHaveOtherPrivatePassengerVehiclesNoButton());
		actions.click(quotePage.increaseMyDeductibleAmount500Button());
		actions.click(quotePage.decreaseMyLiabilityInsurance2MillionButton());
		Thread.sleep(1000);
		oqsHelp.waitForTheSpinnerToDisappear();
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		helper.goBackTothePreviousPage(driver);
		oqsHelp.waitForTheSpinnerToDisappear();
		String quoteValueMonthly = actions.getText(quotePage.quoteValueMonthly());
		String quoteValueYearly = actions.getText(quotePage.quoteValueYearly());
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		List<String> brokerADetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		String brokerName = brokerADetails.get(0);
		String brokerAddress = brokerADetails.get(1);
		
		Assert.assertEquals(actions.getValue(brokerPage.postalCodeTextInput()), helper.getTestData("postalCodeTextInput"));
		
		oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		actions.click(brokerPage.continueWithThisBrokerButton(),true);
		Assert.assertEquals(actions.getText(summaryPage.summaryPageTitle()).contains("Auto"),true);		
		Assert.assertEquals(actions.getText(summaryPage.companyInformation()).contains("Mutual Concept Computer Group Inc."), true);
		Assert.assertEquals(actions.getText(summaryPage.companyInformation()).contains("785 Wonderland Road South London, ON N6K 1M6"), true);
		Assert.assertEquals(actions.getText(summaryPage.brokerName()), brokerName);
		Assert.assertEquals(actions.getText(summaryPage.brokerAddress()), brokerAddress);
		
		String quoteOnTheSummaryPage = actions.getText(summaryPage.autoQuote());
		int index = quoteValueMonthly.indexOf("/");
		quoteValueMonthly = quoteValueMonthly.substring(0, index) + " " + quoteValueMonthly.substring(index);
		index = quoteValueYearly.indexOf("/");
		quoteValueYearly = quoteValueYearly.substring(0, index) + " " + quoteValueYearly.substring(index);
		
		Assert.assertEquals(quoteOnTheSummaryPage.contains(quoteValueMonthly),true);
		Assert.assertEquals(quoteOnTheSummaryPage.contains(quoteValueYearly),true);
		String itemOnTheSummaryPage = actions.getText(summaryPage.item());
		Assert.assertEquals(itemOnTheSummaryPage.contains(helper.getTestData("modelYearNumberInput")), true);
		Assert.assertEquals(itemOnTheSummaryPage.contains(helper.getTestData("manufacturerDropDown")), true);
		Assert.assertEquals(itemOnTheSummaryPage.contains(helper.getTestData("modelDropDown")), true);
		Assert.assertEquals(actions.getText(summaryPage.driverName().get(0)), helper.getTestData("nameOfTheDriverTextInput"));
		Assert.assertEquals(actions.getText(summaryPage.driverName().get(1)), helper.getTestData("whoWillBeTheMainDriverUnder25DropDown"));
	}
	
	/**
	 * PDF Validation: Positive test case for auto insurance quote: 2 Drivers & 1 Vehicle
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 * @throws ParseException Signals that an error has been reached unexpectedly while parsing
	 */
	@Test(groups = {"PDF Validations"})
	public void auto_019() throws InterruptedException, ParseException
	{
		
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(quotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(quotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(quotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(quotePage.classOfLicenseGButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDown(),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDown(),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDown(),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
		
		oqsHelp.moveSlider(quotePage.howManyYearsContinuosAutoInsuranceSlider(), helper.getTestData("howManyYearsContinuosAutoInsuranceNumberInput"), quotePage.howManyYearsContinuosAutoInsuranceNumberInput(), true, false);	
		
		actions.click(quotePage.autoConvictionsNoButton());
		actions.click(quotePage.areYouRetiredNoButton());
		//Add one more driver
		actions.scrollToWebElement(quotePage.addAnotherDriverTab(),false);
		actions.click(quotePage.addAnotherDriverTab());
		
		actions.set(quotePage.nameOfTheDriverTextInputDriver2(),helper.getTestData("nameOfTheDriverTextInputDriver2"));
		actions.select(quotePage.dayDOBDropDownDriver2(),helper.getTestData("dayDOBDropDownDriver2"));
		actions.select(quotePage.monthDOBDropDownDriver2(),helper.getTestData("monthDOBDropDownDriver2"));
		actions.select(quotePage.yearDOBDropDownDriver2(),helper.getTestData("yearDOBDropDownDriver2"));
		actions.click(quotePage.classOfLicenseDriver2GButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDownDriver2(),helper.getTestData("dayCurrentLicenseObtainedDropDownDriver2"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDownDriver2(),helper.getTestData("monthCurrentLicenseObtainedDropDownDriver2"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDownDriver2(),helper.getTestData("yearCurrentLicenseObtainedDropDownDriver2"));

		oqsHelp.moveSlider(quotePage.howManyYearsContinuosAutoInsuranceSliderDriver2(), helper.getTestData("howManyYearsContinuosAutoInsuranceNumberInput2"), quotePage.howManyYearsContinuosAutoInsuranceNumberInputDriver2(), true, false);	
		actions.click(quotePage.autoConvictionsNoButtonDriver2());
		actions.click(quotePage.areYouRetiredNoButtonDriver2());
		
		oqsHelp.moveSlider(quotePage.modelYearSlider(), helper.getTestData("modelYearNumberInput"), quotePage.modelYearNumberInput(), true, false);	
		actions.select(quotePage.manufacturerDropDown(), helper.getTestData("manufacturerDropDown"));
		actions.select(quotePage.modelDropDown(), helper.getTestData("modelDropDown"));
		actions.select(quotePage.whoWillBeMainDriverDropDown(), helper.getTestData("whoWillBeMainDriverDropDown"));
		actions.click(quotePage.willAnyOtherDriverBeUnder25YesButton());
		actions.select(quotePage.whoWillBeTheMainDriverUnder25DropDown(), helper.getTestData("whoWillBeTheMainDriverUnder25DropDown"));
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverPerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverPerYearSlider"), quotePage.whatIsTheTotalDistanceDriverPerYearLabel(), false, true);
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSlider"), quotePage.whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearLabel(), false, true);
		oqsHelp.moveSlider(quotePage.whatIsDistanceDrivenToWorkOneWaySlider(), helper.getTestData("whatIsDistanceDrivenToWorkOneWaySlider"), quotePage.whatIsDistanceDrivenToWorkOneWayLabel(), false, true);
		
		actions.click(quotePage.doesThisVehicleHaveWinterTiresNoButton());
		actions.click(quotePage.doYouHaveAnyPropertyFarmCommercialPoliciesWithUsNoButton());
		actions.click(quotePage.doYouHaveOtherPrivatePassengerVehiclesNoButton());
		actions.click(quotePage.increaseMyDeductibleAmount500Button());
		actions.click(quotePage.decreaseMyLiabilityInsurance2MillionButton());
		Thread.sleep(1000);
		oqsHelp.waitForTheSpinnerToDisappear();
		
		List<String> questions = oqsHelp.getAllQuestions(quotePage.questions());
		List<String> answers = oqsHelp.getAllAnswers(quotePage.answers());
		
		actions.scrollToWebElement(quotePage.viewQuoteDetailsButton(),true);
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		helper.goBackTothePreviousPage(driver);
		oqsHelp.waitForTheSpinnerToDisappear();
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		actions.click(brokerPage.continueWithThisBrokerButton(),true);		
		actions.click(summaryPage.downloadQuoteButton());
		String pdfText = helper.parsePDF(fileWhereOQSQuotesWillBeSaved + "//Auto Quote.pdf").replace("\n", "").replace("\r", " ");	
		String companyName = actions.getText(summaryPage.companyName());
		Reporter.log("\nCompany Name: " + companyName + "\n",true);
		String companyAddress = actions.getText(summaryPage.companyInformation()).replace(companyName, "").replace("\n", "");
		Reporter.log("\nCompany Address: " + companyAddress + "\n",true);
		String brokerName = actions.getText(summaryPage.brokerName());
		Reporter.log("\nBroker name: " + brokerName + "\n",true);
		String brokerAddress = actions.getText(summaryPage.brokerAddress());
		Reporter.log("\nBroker Address: " + brokerAddress + "\n",true);
		
		List<String> summaryDateAndTime = oqsHelp.getSummaryPageDateAndTime((summaryPage.summaryDateAndTime()));
		String summaryDate = summaryDateAndTime.get(0);
		String summaryTime = summaryDateAndTime.get(1);
	    
		String quoteValueMonthly = actions.getText(summaryPage.quoteValueMonthly());
		String quoteValueYearly = actions.getText(summaryPage.quoteValueYearly());
		int index = quoteValueMonthly.indexOf("/");
		quoteValueMonthly = quoteValueMonthly.substring(0, index);
		Reporter.log("\nMonthly quote: " + quoteValueMonthly + "\n",true);
		index = quoteValueYearly.indexOf("/");
		quoteValueYearly = quoteValueYearly.substring(0, index);
		Reporter.log("\nYearly quote: " + quoteValueYearly + "\n",true);
		
		Assert.assertEquals(pdfText.contains(companyName),true);
		Assert.assertEquals(pdfText.contains(companyAddress),true);
		Assert.assertEquals(pdfText.contains(brokerName),true);
		Assert.assertEquals(pdfText.contains(brokerAddress),true);
		Assert.assertEquals(pdfText.contains(summaryDate),true);
		Assert.assertEquals(pdfText.contains(summaryTime),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("nameTextInput")),true);
		Assert.assertEquals(pdfText.contains("(519) 432-8553"),true);
		Assert.assertEquals(pdfText.contains(quoteValueMonthly),true);
		Assert.assertEquals(pdfText.contains(quoteValueYearly),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("modelYearNumberInput")),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("manufacturerDropDown")),true);
		Assert.assertEquals(pdfText.contains(helper.getTestData("modelDropDown")),true);
		
		//validate coverages 
		List<WebElement> coverages = summaryPage.coverages();
		for (int i = 0;i < coverages.size();i++){
			String coverage = coverages.get(0).getText();
			String coverageDeductibleNewValue;
			String coverageLimitNewValue;
			int indexOfCoverageDeductible = coverage.indexOf("$");
			int index2OfCoverageDeductible = coverage.indexOf("$",coverage.indexOf("$")+1);
			String coverageDeductibleActualValue = coverage.substring(indexOfCoverageDeductible+1, index2OfCoverageDeductible).trim();
			
			//ensure locale is English, have to use this because we cannot parse the value to double as the value has commas in it sometimes
			NumberFormat nf = NumberFormat.getInstance();
			double coverageDeductibleDoubleValue = nf.parse(coverageDeductibleActualValue).doubleValue();

			if (coverageDeductibleDoubleValue % 1 == 0){
				coverageDeductibleNewValue = String.valueOf((int)coverageDeductibleDoubleValue);
			}
			else{
				coverageDeductibleNewValue = String.valueOf(coverageDeductibleDoubleValue);
			}
			String coverageLimitActualValue = coverage.substring(index2OfCoverageDeductible+1).trim();
			double coverageLimitDoubleValue = nf.parse(coverageLimitActualValue).doubleValue();
			if (coverageLimitDoubleValue % 1 == 0){
				coverageLimitNewValue = String.valueOf((int)coverageLimitDoubleValue);
			}
			else{
				coverageLimitNewValue = String.valueOf(coverageLimitDoubleValue);
			}
			coverage = coverage.replace(coverageDeductibleActualValue, coverageDeductibleNewValue).replace(coverageLimitActualValue, coverageLimitNewValue);
			Assert.assertEquals(pdfText.contains(coverage),true);
		}
		List<String> questionsAndAnswersOnThePage = oqsHelp.getAllQuestionsAndAnswers(questions, answers);
		Reporter.log("\nQuestions and the corresponding Answers from the quote page:\n",true);
		for (int i = 0;i < questionsAndAnswersOnThePage.size();i++){
			String questionAndAnswer = questionsAndAnswersOnThePage.get(i);
			Reporter.log(questionAndAnswer,true);
			Assert.assertEquals(pdfText.contains(questionAndAnswer),true);
		}
	}
	
	/**
	 * Change broker from the summary page
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test(dependsOnMethods = { "auto_001" })
	public void auto_020() throws InterruptedException
	{
		this.auto_001();
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		List<String> brokerADetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		String brokerName1 = brokerADetails.get(0);
		String brokerAddress1 = brokerADetails.get(1);
		actions.click(brokerPage.continueWithThisBrokerButton(),true);
		actions.click(summaryPage.changeButton());
		List<String> brokerBDetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("B",true,true,true);
		String brokerName2 = brokerBDetails.get(0);
		String brokerAddress2 = brokerBDetails.get(1);
		Assert.assertNotEquals(brokerName1,brokerName2);
		Assert.assertNotEquals(brokerAddress1,brokerAddress2);
		actions.click(brokerPage.continueWithThisBrokerButton(),true);
		Assert.assertEquals(actions.getText(summaryPage.brokerName()), brokerName2);
		Assert.assertEquals(actions.getText(summaryPage.brokerAddress()), brokerAddress2);
	}

}
