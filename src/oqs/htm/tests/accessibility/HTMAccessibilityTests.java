package oqs.htm.tests.accessibility;

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
 * OQS accessibility tests
 * @author Mani
 * @since 19/09/2016
 */
public class HTMAccessibilityTests extends BaseClass {
	
	QuotePageWebElements quotePage = new QuotePageWebElements();
	HTMQuotePageWebElements htmQuotePage = new HTMQuotePageWebElements();
	BrokerPageWebElements brokerPage = new BrokerPageWebElements();
	SummaryPageWebElements summaryPage = new SummaryPageWebElements();
	UserActions actions = new UserActions();
	GenericHelper helper = new GenericHelper();
	OQSHelper oqsHelp = new OQSHelper();
	String dataFileLocation = System.getProperty("user.dir") + "/data/oqs/htm/accessibility/";
	
	/**
	 * Run accessibility test on the quote page
	 */
	@Test
	public void accessibility_quotePage(){
		helper.runAccessibilityScan(driver, log);
	}
	
	/**
	 * Run accessibility test on the broker page
	 */
	@Test
	public void accessibility_brokerPage(){
		
		helper.setTestData(dataFileLocation);
		
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
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		
		oqsHelp.waitForTheSpinnerToAppear();
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		
		oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		
		helper.runAccessibilityScan(driver, log);
	}
	
	/**
	 * Run accessibility test on the summary page
	 */
	@Test
	public void accessibility_summaryPage(){
		
		helper.setTestData(dataFileLocation);
		
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
		actions.click(quotePage.viewQuoteDetailsButton(),true);
		
		oqsHelp.waitForTheSpinnerToAppear();
		oqsHelp.waitForTheSpinnerToDisappear();
		ngWebDriver.waitForAngularRequestsToFinish();
		oqsHelp.clickOnTheBrokerAndGetBrokerDetails("A",false,false,false);
		actions.click(brokerPage.continueWithThisBrokerButton());
		
		helper.runAccessibilityScan(driver, log);
	}
	
}

