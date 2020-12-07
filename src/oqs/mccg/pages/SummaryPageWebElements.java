package oqs.mccg.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helpers.WebDriverHelper;

/**
 * Summary page web elements
 * @author Ankit
 * @since 26-09-16
 *
 */
public class SummaryPageWebElements extends WebDriverHelper {
	
	private WebElement element;
	List<WebElement>elements = new ArrayList<WebElement>();
	
	/**
	 * Summary Page title	
	 * @return web element
	 */
	public WebElement summaryPageTitle()
	{
		element = findElement("class","oqs-title");
		return element.findElement(By.tagName("ng-transclude"));
	}
	
	/**
	 * Company Information
	 * @return web element
	 */
	public WebElement companyInformation()
	{
		element = findElement("xpath", "//div[@class='oqs-address']/div/div");
		return element;
	}
	
	/**
	 * Company Name
	 * @return web element
	 */
	public WebElement companyName()
	{
		element = findElement("xpath", "//div[@class='oqs-address']/div/div/strong");
		return element;
	}
	
	/**
	 * Summary Date	
	 * @return web element
	 */
	public WebElement summaryDateAndTime()
	{
		element = findElement("xpath", "//div[@class='oqs-summary-date']");
		return element;
	}
	
	/**
	 * Broker name
	 * @return web element
	 */
	public WebElement brokerName()
	{
		element = findElement("xpath", "//div[@class='oqs-address']/div/div[2]/strong");
		return element;
	}
	
	/**
	 * Broker address
	 * @return web element
	 */
	public WebElement brokerAddress()
	{
		element = findElement("xpath", "//div[@class='oqs-broker-address']");
		return element;
	}
	
	/**
	 * Auto Quote
	 * @return web element
	 */
	public WebElement autoQuote()
	{
		element = findElement("xpath", "//div[@class='oqs-totals']/div/div[2]");
		return element;
	}
	
	/**
	 * Car selected
	 * @return web element
	 */
	public WebElement item()
	{
		element = findElement("xpath", "//div[@class='oqs-item-name']");
		return element;
	}
	
	/**
	 * drivers name
	 * @return web element
	 */
	public List<WebElement> driverName()
	{
		elements = findElements("xpath", "//th[@ng-if='coverageGroup.name']");
		return elements;
	}
	
	/**
	 * get Quote button
	 * @return web element
	 */
	public WebElement getQuoteButton()
	{
		element = findElement("xpath","//span[text() = 'Get Quote']");
		return element;
	}
	
	public WebElement nextStepButton()
	{
		element = findElement("xpath","//span[text() = 'Next Step']");
		return element;
	}
	
	/**
	 * phone number Telephone Input
	 * @return web element
	 */
	public WebElement phoneNumberTelInput()
	{
		element = findElement("id","phoneNumber");
		return element;
	}
	
	/**
	 * For 'What is the best time to call you?' will select Anytime
	 * @return web element
	 */
	public WebElement bestTimeToCallAnytimeButton()
	{
		elements = findElements("xpath","//label[@ng-model='summaryContactModal.contactInfo.phoneTime']");
		return elements.get(0);
	}
	
	/**
	 * For 'What is the best time to call you?' will select Morning
	 * @return web element
	 */
	public WebElement bestTimeToCallMorningButton()
	{
		elements = findElements("xpath","//label[@ng-model='summaryContactModal.contactInfo.phoneTime']");
		return elements.get(1);
	}
	
	/**
	 * For 'What is the best time to call you?' will select Afternoon
	 * @return web element
	 */
	public WebElement bestTimeToCallAfternoonButton()
	{
		elements = findElements("xpath","//label[@ng-model='summaryContactModal.contactInfo.phoneTime']");
		return elements.get(2);
	}
	
	/**
	 * For 'What is the best time to call you?' will select Evening
	 * @return web element
	 */
	public WebElement bestTimeToCallEveningButton()
	{
		elements = findElements("xpath","//label[@ng-model='summaryContactModal.contactInfo.phoneTime']");
		return elements.get(3);
	}
	
	/**
	 * Submit button
	 * @return web element
	 */
	public WebElement submitButton()
	{
		element = findElement("xpath","//span[text() = 'Submit']");
		return element.findElement(By.xpath(".."));
	}
	
	/**
	 * Summary message seen when contact type is phone number
	 * @return web element
	 */
	public WebElement summaryMessagePhoneNumber()
	{
		element = findElement("xpath","//span[@ng-show=\"$parent.summaryContactModal.contactInfo.preferredMethod === 'Phone'\"]");
		return element;
	}
	
	/**
	 * Download Quote button	
	 * @return web element
	 */
	public WebElement downloadQuoteButton()
	{
		element = findElement("xpath","//span[text() = 'Download Quote']");
		return element;
	}
	
	/**
	 * Quote - Monthly value
	 * @return web element
	 */
	public WebElement quoteValueMonthly()
	{
		element = findElement("xpath","//div[@class='oqs-quote-response-success']/span");
		return element;
	}
	
	/**
	 * Quote - Yearly value
	 * @return web element
	 */
	public WebElement quoteValueYearly()
	{
		element = findElement("xpath","//div[@class='oqs-quote-response-success']/span[2]");
		return element;
	}
	
	/**
	 * Coverages in the summary page
	 * @return list of web elements
	 */
	public List<WebElement> coverages()
	{
		elements = findElements("repeater","coverage in coverageGroup.coverageList");
		return elements;
	}
	
	/**
	 * Coverage names in the summary page
	 * @return web element
	 */
	public List<WebElement> coverageNames()
	{
		List<WebElement> coverageElements = coverages();
		for(int i = 0; i < coverageElements.size(); i ++){
			WebElement coverageName = coverageElements.get(i).findElement(By.xpath("td"));
			elements.add(coverageName);
		}
		return elements;
	}
	
	/**
	 * Coverage deductibles in the summary page
	 * @return web element
	 */
	public List<WebElement> coverageDeductibles()
	{
		List<WebElement> coverageElements = coverages();
		for(int i = 0; i < coverageElements.size(); i ++){
			WebElement coverageDeductible = coverageElements.get(i).findElement(By.xpath("td[2]"));
			elements.add(coverageDeductible);
		}
		return elements;
	}
	
	/**
	 * Coverage limits in the summary page
	 * @return web element
	 */
	public List<WebElement> coverageLimits()
	{
		List<WebElement> coverageElements = coverages();
		for(int i = 0; i < coverageElements.size(); i ++){
			WebElement coverageLimit = coverageElements.get(i).findElement(By.xpath("td[3]"));
			elements.add(coverageLimit);
		}
		return elements;
	}
	
	/**
	 * Change button - Broker change
	 * @return web element
	 */
	public WebElement changeButton()
	{
		element = findElement("xpath","//a[text() = 'Change']");
		return element;
	}
}
