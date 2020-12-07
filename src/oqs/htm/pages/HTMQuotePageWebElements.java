package oqs.htm.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helpers.WebDriverHelper;

/**
 * HTM Quote page web elements
 * 
 * @author Mani
 * @since 2016-09-01
 *
 */
public class HTMQuotePageWebElements extends WebDriverHelper {
	
	private WebElement element;
	List<WebElement>elements = new ArrayList<WebElement>();
	
	// Auto Web elements
	
	/**
	 * 'What is your date of birth?', day drop down
	 * @return web element
	 */
	public WebElement dayDOBDropDown()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your  date of birth?')]");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/select[1]"));
	}
	
	/**
	 * 'What is your date of birth?', month drop down
	 * @return web element
	 */
	public WebElement monthDOBDropDown()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your  date of birth?')]");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/select[2]"));
	}
	
	/**
	 * 'What is your date of birth?', year drop down
	 * @return web element
	 */
	public WebElement yearDOBDropDown()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your  date of birth?')]");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/select[3]"));
	}
	
	/**
	 * Will select the class of license 'G'
	 * @return web element
	 */
	public WebElement currentLicenseClassGButton()
	{
		element = findElement("xpath", "//span[text() = 'What is your current class of license?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}
	
	/**
	 * Will select the class of license 'G2'
	 * @return web element
	 */
	public WebElement currentLicenseClassG2Button()
	{
		element = findElement("xpath", "//span[text() = 'What is your current class of license?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[1]"));		
	}
	
	/**
	 * For 'When did you obtain your G2 license?' will select the day from the day drop down
	 * @return web element
	 */
	public WebElement dayG2licenseObtainedDropDown()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.day']");
		return elements.get(2);	
	}
	
	/**
	 * For 'When did you obtain your G2 license?' will select the month from the month drop down
	 * @return web element
	 */
	public WebElement monthG2licenseObtainedDropDown()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.month']");
		return elements.get(2);	
	}
	
	/**
	 * For 'When did you obtain your G2 license?' will select the year from the year drop down
	 * @return web element
	 */
	public WebElement yearG2licenseObtainedDropDown()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.year']");
		return elements.get(2);	
	}

	/**
	 * For 'Have you had prior Automobile Insurance?' will select No
	 * @return web element
	 */
	public WebElement priorAutomobileInsuranceNoButton()
	{
		element = findElement("xpath", "//span[text() = 'Have you had prior Automobile Insurance?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * For 'Have you had prior Automobile Insurance?' will select Yes
	 * @return web element
	 */
	public WebElement priorAutomobileInsuranceYesButton()
	{
		element = findElement("xpath", "//span[text() = 'Have you had prior Automobile Insurance?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}
	
	/**
	 * For 'Have you had any auto convictions, accidents, suspensions or insurance cancellations in the last 6 years?' will select No
	 * @return web element
	 */
	public WebElement autoConvictionsInLast6YearsNoButton()
	{
		element = findElement("xpath", "//span[text() = 'Have you had any auto convictions, accidents, suspensions or insurance cancellations in the last 6 years?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * For 'Have you had any auto convictions, accidents, suspensions or insurance cancellations in the last 6 years?' will select Yes
	 * @return web element
	 */
	public WebElement autoConvictionsInLast6YearsYesButton()
	{
		element = findElement("xpath", "//span[text() = 'Have you had any auto convictions, accidents, suspensions or insurance cancellations in the last 6 years?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}
	
	/**
	 * 'What is the distance driven to work or school one way (kms)?' slider
	 * @return web element
	 */
	public WebElement whatIsDistanceDrivenToWorkOneWaySlider()
	{
		element = findElement("xpath","//span[contains(text(), 'What is  the distance driven to work or school one way (kms)?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div"));
	}
	
	/**
	 * 'What is the distance driven to work or school one way (kms)?' label
	 * @return web element
	 */
	public WebElement whatIsDistanceDrivenToWorkOneWayLabel()
	{
		element = findElement("xpath","//span[contains(text(), 'What is  the distance driven to work or school one way (kms)?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div[2]"));
	}
	
	/**
	 * 'For How are you using this vehicle?' selects Personal
	 * @return web element
	 */
	public WebElement howAreYouUsingThisVehiclePersonalButton()
	{
		element = findElement("xpath","//span[contains(text(), 'How are you using this vehicle?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'How are you using this vehicle?' selects Business
	 * @return web element
	 */
	public WebElement howAreYouUsingThisVehicleBusinessButton()
	{
		element = findElement("xpath","//span[contains(text(), 'How are you using this vehicle?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'How are you using this vehicle?' selects Farm
	 * @return web element
	 */
	public WebElement howAreYouUsingThisVehicleFarmButton()
	{
		element = findElement("xpath","//span[contains(text(), 'How are you using this vehicle?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[3]"));
	}
	
	/**
	 * For 'Do you have any Property or Farm policies with us?' select No
	 * @return web element
	 */
	public WebElement doYouHaveAnyPropertyOrFarmPoliciesWithUsNoButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Do you have any Property or Farm policies with us?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Do you have any Property or Farm policies with us?' select Yes
	 * @return web element
	 */
	public WebElement doYouHaveAnyPropertyOrFarmPoliciesWithUsYesButton()
	{
		element = findElement("xpath","//span[contains(text(),'Do you have any Property or Farm policies with us?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'Change my deductible amount?' will select 500
	 * @return web element
	 */
	public WebElement changeMyDeductibleAmount500Button()
	{
		element = findElement("xpath","//span[contains(text(), 'Change my deductible amount?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Change my deductible amount?' will select 1000
	 * @return web element
	 */
	public WebElement changeMyDeductibleAmount1000Button()
	{
		element = findElement("xpath","//span[contains(text(),'Change my deductible amount?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'Change my deductible amount?' will select 2500
	 * @return web element
	 */
	public WebElement changeMyDeductibleAmount2500Button()
	{
		element = findElement("xpath","//span[contains(text(),'Change my deductible amount?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[3]"));
	}
	
	/**
	 * For 'Change my Liability Insurance?' will select 1 million
	 * @return web element
	 */
	public WebElement changeMyLiabilityInsurance1MillionButton()
	{
		element = findElement("xpath","//span[contains(text(),'Change my Liability Insurance?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Change my Liability Insurance?' will select 2 million
	 * @return web element
	 */
	public WebElement changeMyLiabilityInsurance2MillionButton()
	{
		element = findElement("xpath","//span[contains(text(),'Change my Liability Insurance?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
}
