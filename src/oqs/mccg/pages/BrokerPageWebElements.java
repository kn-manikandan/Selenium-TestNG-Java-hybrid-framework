package oqs.mccg.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import helpers.WebDriverHelper;

/**
 * Broker page web elements
 * @author Ankit
 * @since 23-09-16
 *
 */
public class BrokerPageWebElements extends WebDriverHelper {
	
	private WebElement element;
	List<WebElement>elements = new ArrayList<WebElement>();
	
	/**
	 * Broker A
	 * @return web element
	 */
	public WebElement brokerA()
	{
		element = findElement("xpath","//span[text() = 'A']");
		return element.findElement(By.xpath("../.."));
	}
		
	/**
	 * Broker B
	 * @return web element
	 */
	public WebElement brokerB()
	{
		element = findElement("xpath","//span[text() = 'B']");
		return element.findElement(By.xpath("../.."));
	}
	
	/**
	 * Broker C
	 * @return web element
	 */
	public WebElement brokerC()
	{
		element = findElement("xpath","//span[text() = 'C']");
		return element.findElement(By.xpath("../.."));
	}
	
	/**
	 * Continue with this broker button
	 * @return web element
	 */
	public WebElement continueWithThisBrokerButton()
	{
		element = findElement("xpath","//span[text() = 'Continue with this Broker']");
		return element;
	}
	
	/**
	 * Postal code
	 * @return web element
	 */
	public WebElement postalCodeTextInput()
	{
		element = findElement("id","brokerPostalCode");
		return element;
	}

}
