package oqs.htm.tests;

import java.time.LocalDateTime;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.BaseClass;
import helpers.GenericHelper;
import helpers.OQSHelper;
import helpers.UserActions;
import oqs.htm.pages.HTMQuotePageWebElements;
import oqs.mccg.pages.BrokerPageWebElements;
import oqs.mccg.pages.QuotePageWebElements;
import oqs.mccg.pages.SummaryPageWebElements;

/**
 * HTM Auto tests
 * @author Mani
 * @since 2016-09-01
 *
 */

@Test(groups = {"OQS Tests"})
public class HTMAutoTests extends BaseClass {
	QuotePageWebElements quotePage = new QuotePageWebElements();
	HTMQuotePageWebElements htmQuotePage = new HTMQuotePageWebElements();
	BrokerPageWebElements brokerPage = new BrokerPageWebElements();
	SummaryPageWebElements summaryPage = new SummaryPageWebElements();
	UserActions actions = new UserActions();
	GenericHelper helper = new GenericHelper();
	OQSHelper oqsHelp = new OQSHelper();
	String dataFileLocation = System.getProperty("user.dir") + "/data/oqs/htm/";
	LocalDateTime now = LocalDateTime.now();
	
	/**
	 * Check for validation errors when name and postal code are not entered
	 */
	@Test(groups = {"OQS Tests","Positive Tests"})
	public void auto_001()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());	
		Assert.assertEquals(actions.getText(quotePage.nameTextInputErrorMessage()), "Don't be shy! Tell us your name.");
		Assert.assertEquals(actions.getText(quotePage.postalCodeTextInputErrorMessage()), "Tell us where you live.");
	}
	

	/**
	 * Successfully get a quote by entering all the required information
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test(groups = {"OQS Tests","HTM Tests"})
	public void auto_002() throws InterruptedException
	{
		helper.setTestData(dataFileLocation);
		String quoteValueMonthly,quoteValueYearly;
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(htmQuotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(htmQuotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(htmQuotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(htmQuotePage.currentLicenseClassGButton());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDown(),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDown(),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDown(),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
		
		actions.select(htmQuotePage.dayG2licenseObtainedDropDown(),helper.getTestData("dayG2licenseObtainedDropDown"));
		actions.select(htmQuotePage.monthG2licenseObtainedDropDown(),helper.getTestData("monthG2licenseObtainedDropDown"));
		actions.select(htmQuotePage.yearG2licenseObtainedDropDown(),helper.getTestData("yearG2licenseObtainedDropDown"));
		
		actions.click(htmQuotePage.priorAutomobileInsuranceYesButton());
		actions.click(htmQuotePage.autoConvictionsInLast6YearsNoButton());
		actions.click(quotePage.areYouRetiredNoButton());
		oqsHelp.moveSlider(quotePage.modelYearSlider(), helper.getTestData("modelYearNumberInput"), quotePage.modelYearNumberInput(), true, false);	
		actions.select(quotePage.manufacturerDropDown(), helper.getTestData("manufacturerDropDown"));
		actions.select(quotePage.modelDropDown(), helper.getTestData("modelDropDown"));
		actions.select(quotePage.whoWillBeMainDriverDropDown(), helper.getTestData("whoWillBeMainDriverDropDown"));
		actions.click(quotePage.willAnyOtherDriverBeUnder25NoButton());
		
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverPerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverPerYearSlider"), quotePage.whatIsTheTotalDistanceDriverPerYearLabel(), false, true);
		oqsHelp.moveSlider(htmQuotePage.whatIsDistanceDrivenToWorkOneWaySlider(), helper.getTestData("whatIsDistanceDrivenToWorkOneWaySlider"), htmQuotePage.whatIsDistanceDrivenToWorkOneWayLabel(), false, true);
		
		actions.click(htmQuotePage.howAreYouUsingThisVehiclePersonalButton());
		actions.click(quotePage.doesThisVehicleHaveWinterTiresNoButton());
		actions.click(htmQuotePage.doYouHaveAnyPropertyOrFarmPoliciesWithUsNoButton());
		actions.click(quotePage.doYouHaveOtherPrivatePassengerVehiclesNoButton());
		actions.click(htmQuotePage.changeMyDeductibleAmount500Button());
		actions.click(htmQuotePage.changeMyLiabilityInsurance2MillionButton());
		Thread.sleep(1000);
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		helper.goBackTothePreviousPage(driver);
		oqsHelp.waitForTheSpinnerToDisappear();
		quoteValueMonthly = actions.getText(quotePage.quoteValueMonthly());
		quoteValueYearly = actions.getText(quotePage.quoteValueYearly());
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		List<String> brokerADetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		String brokerName = brokerADetails.get(0);
		String brokerAddress = brokerADetails.get(1);
		
		Assert.assertEquals(actions.getValue(brokerPage.postalCodeTextInput()), helper.getTestData("postalCodeTextInput"));
		
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
		
		actions.click(summaryPage.nextStepButton());
		actions.set(summaryPage.phoneNumberTelInput(),helper.getTestData("phoneNumberTelInput"));
		actions.click(summaryPage.bestTimeToCallEveningButton());
		actions.click(summaryPage.submitButton(),true);
		String summaryMessage = actions.getText(summaryPage.summaryMessagePhoneNumber());
		Assert.assertEquals(summaryMessage.contains("Thank you for submitting your quote to us. We will call you to review the details"),true);
	}
	
	/**
	 * Check for validation error when postal code is incorrect
	 */
	@Test(groups = {"OQS Tests","Positive Tests"})
	public void auto_003()
	{
		helper.setTestData(dataFileLocation);
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());	
		Assert.assertEquals(actions.getText(quotePage.postalCodeTextInputErrorMessage()), "Invalid postal code format.");
	}
	
	/**
	 * Clicking on the logo should take you to the company site
	 */
	@Test(groups = {"OQS Tests","Positive Tests"})
	public void auto_004()
	{
		actions.click(quotePage.logo());
		helper.switchTabs(driver, 1);
		Assert.assertEquals(helper.getUrl(driver), "https://www.htminsurance.ca/");
		helper.closeTab(driver, 1);
		helper.switchTabs(driver, 0);
	}
	
	/**
	 * Current license class drop down fields should be automatically populated to D.O.B + 17 years
	 */
	@Test(groups = {"OQS Tests","Positive Tests"})
	public void auto_005()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(htmQuotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(htmQuotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(htmQuotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(htmQuotePage.currentLicenseClassGButton());
		
		Assert.assertEquals(actions.getTextOfTheDropDownValue(quotePage.dayCurrentLicenseObtainedDropDown()),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		Assert.assertEquals(actions.getTextOfTheDropDownValue(quotePage.monthCurrentLicenseObtainedDropDown()),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		Assert.assertEquals(actions.getTextOfTheDropDownValue(quotePage.yearCurrentLicenseObtainedDropDown()),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
	}
	
	/**
	 * Gender and Marital Status options should be seen if the age of the driver is below 25
	 */
	@Test(groups = {"OQS Tests","Positive Tests"})
	public void auto_006()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(htmQuotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(htmQuotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(htmQuotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		// Validation to check for Gender and Marital Status options
		Assert.assertEquals(actions.click(quotePage.genderMaleButton()),true);
		Assert.assertEquals(actions.click(quotePage.genderMaritalStatusMarriedButton()),true);
	}
	
	/**
	 * Successfully get a quote by entering all the required information
	 * @throws InterruptedException Thrown when thread is sleeping and there is an interruption
	 */
	@Test(groups = {"OQS Tests"})
	public void auto_007() throws InterruptedException
	{
		helper.setTestData(dataFileLocation);
		String quoteValueMonthly,quoteValueYearly;
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.autoLabel());
		actions.set(quotePage.nameOfTheDriverTextInput(),helper.getTestData("nameOfTheDriverTextInput"));
		
		actions.select(htmQuotePage.dayDOBDropDown(),helper.getTestData("dayDOBDropDown"));
		actions.select(htmQuotePage.monthDOBDropDown(),helper.getTestData("monthDOBDropDown"));
		actions.select(htmQuotePage.yearDOBDropDown(),helper.getTestData("yearDOBDropDown"));
		
		actions.click(htmQuotePage.currentLicenseClassG2Button());
		
		actions.select(quotePage.dayCurrentLicenseObtainedDropDown(),helper.getTestData("dayCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.monthCurrentLicenseObtainedDropDown(),helper.getTestData("monthCurrentLicenseObtainedDropDown"));
		actions.select(quotePage.yearCurrentLicenseObtainedDropDown(),helper.getTestData("yearCurrentLicenseObtainedDropDown"));
		
		actions.click(htmQuotePage.priorAutomobileInsuranceNoButton());
		actions.click(htmQuotePage.autoConvictionsInLast6YearsNoButton());
		actions.click(quotePage.areYouRetiredYesButton());
		actions.clearText(quotePage.modelYearNumberInput());
		actions.set(quotePage.modelYearNumberInput(),helper.getTestData("modelYearNumberInput"));	
		actions.select(quotePage.manufacturerDropDown(), helper.getTestData("manufacturerDropDown"));
		actions.select(quotePage.modelDropDown(), helper.getTestData("modelDropDown"));
		actions.select(quotePage.whoWillBeMainDriverDropDown(), helper.getTestData("whoWillBeMainDriverDropDown"));
		actions.click(quotePage.willAnyOtherDriverBeUnder25NoButton());
		
		oqsHelp.moveSlider(quotePage.whatIsTheTotalDistanceDriverPerYearSlider(), helper.getTestData("whatIsTheTotalDistanceDriverPerYearSlider"), quotePage.whatIsTheTotalDistanceDriverPerYearLabel(), false, true);
		oqsHelp.moveSlider(htmQuotePage.whatIsDistanceDrivenToWorkOneWaySlider(), helper.getTestData("whatIsDistanceDrivenToWorkOneWaySlider"), htmQuotePage.whatIsDistanceDrivenToWorkOneWayLabel(), false, true);
		actions.click(htmQuotePage.howAreYouUsingThisVehicleBusinessButton());
		actions.click(quotePage.doesThisVehicleHaveWinterTiresYesButton());
		actions.click(htmQuotePage.doYouHaveAnyPropertyOrFarmPoliciesWithUsYesButton());
		actions.click(quotePage.doYouHaveOtherPrivatePassengerVehiclesNoButton());
		actions.click(htmQuotePage.changeMyDeductibleAmount2500Button());
		actions.click(htmQuotePage.changeMyLiabilityInsurance2MillionButton());
		Thread.sleep(1000);
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		helper.goBackTothePreviousPage(driver);
		oqsHelp.waitForTheSpinnerToDisappear();
		quoteValueMonthly = actions.getText(quotePage.quoteValueMonthly());
		quoteValueYearly = actions.getText(quotePage.quoteValueYearly());
		actions.click(quotePage.viewQuoteDetailsButton());
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		List<String> brokerADetails = oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		String brokerName = brokerADetails.get(0);
		String brokerAddress = brokerADetails.get(1);
		
		Assert.assertEquals(actions.getValue(brokerPage.postalCodeTextInput()), helper.getTestData("postalCodeTextInput"));
		
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
		
		actions.click(summaryPage.nextStepButton());
		actions.set(summaryPage.phoneNumberTelInput(),helper.getTestData("phoneNumberTelInput"));
		actions.click(summaryPage.bestTimeToCallEveningButton());
		actions.click(summaryPage.submitButton(),true);
		String summaryMessage = actions.getText(summaryPage.summaryMessagePhoneNumber());
		Assert.assertEquals(summaryMessage.contains("Thank you for submitting your quote to us. We will call you to review the details"),true);
	}
}
