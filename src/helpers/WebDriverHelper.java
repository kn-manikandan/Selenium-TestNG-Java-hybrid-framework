package helpers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import com.paulhammant.ngwebdriver.ByAngular;

/**
 * <h1>Helper Class for finding web element/elements on the page</h1>
 * 
 * @author Ankit
 * @since 2016-09-01
 *
 */
public class WebDriverHelper extends BaseClass {
	
	private WebElement element;
	List<WebElement>elements = new ArrayList<WebElement>();
	GenericHelper genericHelp = new GenericHelper();
	long currentTime;
	long newTime;
	
	/**
	 * This function helps in finding a web element
	 * on a page and then returns the web element
	 * @param method Here you specify whether you want use
	 * XPATH, CSS, ID, CLASSNAME, BINDING, MODEL or REPEATER method
	 * to find the element
	 * @param string The string to be used for the method selected
	 * @return This returns the web element found
	 */
	public WebElement findElement(String method, String string)
	{
		element = null;	
		Reporter.log("Trying to find the web element by " + method + ": " + string + ".....",true);
		try{
			switch(method.toLowerCase())
			{
				case "xpath":
					element = driver.findElement(By.xpath(string));
					Reporter.log("SUCCESS",true);
					break;
				case "css":
					element = driver.findElement(By.cssSelector(string));
					Reporter.log("SUCCESS",true);
					break;
				case "class":
					element = driver.findElement(By.className(string));
					Reporter.log("SUCCESS",true);
					break;
				case "id":
					element = driver.findElement(By.id(string));
					Reporter.log("SUCCESS",true);
					break;
				case "binding":
					element = driver.findElement(ByAngular.binding(string));
					Reporter.log("SUCCESS",true);
					break;
				case "model":
					element = driver.findElement(ByAngular.model(string));
					Reporter.log("SUCCESS",true);
					break;
				case "repeater":
					element = driver.findElement(ByAngular.repeater(string));
					Reporter.log("SUCCESS",true);
					break;
			}
		}
		catch(Exception e)
		{element = this.handlingWebdriverExceptionWhenElementNotFound(e,method,string);}
		return element;
	}
	
	/**
	 * This function helps in finding a web element using a given web element
	 * @param webElement the web element on the page that you want to use to navigate to a particular web element 
	 * @param method Here you specify whether you want use
	 * XPATH, CSS, ID, CLASSNAME, BINDING, MODEL or REPEATER method
	 * to find the element
	 * @param string The string to be used for the method selected
	 * @return This returns the web element found
	 */
	public WebElement findElement(WebElement webElement, String method, String string)
	{
		element = null;	
		Reporter.log("Trying to find a web element using the above web element by " + method + ": " + string,true);
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 30000;
		while (currentTime < newTime){
			try{
				switch(method.toLowerCase())
				{
					case "xpath":	
						element = webElement.findElement(By.xpath(string));
						Reporter.log("SUCCESS",true);
						return element;
					case "css":
						element = webElement.findElement(By.cssSelector(string));
						Reporter.log("SUCCESS",true);
						return element;
					case "class":
						element = webElement.findElement(By.className(string));
						Reporter.log("SUCCESS",true);
						return element;
					case "id":
						element = webElement.findElement(By.id(string));
						Reporter.log("SUCCESS",true);
						return element;
					case "binding":
						element = webElement.findElement(ByAngular.binding(string));
						Reporter.log("SUCCESS",true);
						return element;
					case "model":
						element = webElement.findElement(ByAngular.model(string));
						Reporter.log("SUCCESS",true);
						return element;
					case "repeater":
						element = driver.findElement(ByAngular.repeater(string));
						Reporter.log("SUCCESS",true);
						break;
				}
			}
			catch(Exception e){
				currentTime = System.currentTimeMillis();
			if(currentTime >= newTime){
				Assert.fail(e.getMessage());
				}
			}
		}
		return element;
	}
	
	/**
	 * This function helps in finding a list of web elements
	 * on a page and then returns the list
	 * @param method Here you specify whether you want use
	 * XPATH, CSS, ID, CLASSNAME, BINDING, MODEL or REPEATER method
	 * to find the element
	 * @param string The string to be used for the method selected
	 * @return This returns the list of web elements found
	 */
	public List<WebElement> findElements(String method, String string)
	{
		Reporter.log("Trying to find a list of web elements by " + method + ": " + string + ".....",true);
		try{
			switch(method.toLowerCase())
			{
				case "xpath":
					elements = driver.findElements(By.xpath(string));
					Reporter.log("SUCCESS",true);
					break;
				case "css":
					elements = driver.findElements(By.cssSelector(string));
					Reporter.log("SUCCESS",true);
					break;
				case "class":
					elements = driver.findElements(By.className(string));
					Reporter.log("SUCCESS",true);
					break;
				case "id":
					elements = driver.findElements(By.id(string));
					Reporter.log("SUCCESS",true);
					break;
				case "binding":
					elements = driver.findElements(ByAngular.binding(string));
					Reporter.log("SUCCESS",true);
					break;
				case "model":
					elements = driver.findElements(ByAngular.model(string));
					Reporter.log("SUCCESS",true);
					break;
				case "repeater":
					elements = driver.findElements(ByAngular.repeater(string));
					Reporter.log("SUCCESS",true);
					break;
			}
		}
		catch(Exception e)
		{elements = this.handlingWebdriverExceptionWhenListsOfElementsNotFound(e,method,string);}	
		return elements;
	}
	
	/**
	 * This function handles any exceptions that occurred
	 * when trying to find an element by waiting for it to be
	 * visible 
	 * @param e The exception that was seen
	 * @param method Here you specify the method that was used
	 * to find the element - XPATH, CSS, ID, CLASSNAME, BINDING MODEL or REPEATER method
	 * @param string The string that was used for the method selected
	 * @return This returns the web element found
	 */
	public WebElement handlingWebdriverExceptionWhenElementNotFound(Exception e, String method, String string)
	{
		WebElement element = this.waitForElementToBeVisible(method, string);
		return element;
	}
	
	/**
	 * This function handles any exceptions that occurred
	 * when trying to find a list of elements by waiting for the elements
	 * to be visible 
	 * @param e The exception that was seen
	 * @param method Here you specify the method that was used
	 * to find the element - XPATH, CSS, ID, CLASSNAME, BINDING, MODEL or REPEATER method
	 * @param string The string that was used for the method selected
	 * @return This returns the list of web elements found
	 */
	public List<WebElement> handlingWebdriverExceptionWhenListsOfElementsNotFound(Exception e, String method, String string)
	{
		if(e.getMessage().contains("element not visible") || e.getMessage().contains("no such element") || e.getMessage().contains("stale element reference") || e.getMessage().contains("invalid element state"))
		{elements = this.waitForListOfElementsToBeVisible(method, string);}
		return elements;
	}
	
	/**
	 * This function handles 'waiting for the web element to be visible'
	 * @param method Here you specify whether you want use
	 * XPATH, CSS, ID, CLASSNAME, BINDING, MODEL or REPEATER method
	 * to find the element
	 * @param string The string to be used for the method selected
	 * @return This returns the web element found
	 */
	public WebElement waitForElementToBeVisible(String method, String string)
	{
		Reporter.log("Waiting for the web element to be visible, finding element by " + method + ": " + string + ".....",true);
		try{
			switch(method.toLowerCase())
			{
			case "xpath":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "css":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "class":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "id":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "binding":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByAngular.binding(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "model":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByAngular.model(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "repeater":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByAngular.repeater(string)));
				Reporter.log("SUCCESS",true);
				break;
			}
		}
		catch(Exception e){
			exceptionLogging(e, "Timed out waiting for the web element to be visible, for " + method + ": " + string);}	
		return element;
	}
	
	/**
	 * This function handles 'waiting for the web elements to be visible'
	 * @param method Here you specify whether you want use
	 * XPATH, CSS, ID, CLASSNAME, BINDING, MODEL or REPEATER method
	 * to find the element
	 * @param string The string to be used for the method selected
	 * @return This returns the list of web elements found
	 */
	public List<WebElement> waitForListOfElementsToBeVisible(String method, String string)
	{
		Reporter.log("Waiting for the web element to be visible, finding list of elements by " + method + ": " + string + ".....",true);
		List<WebElement>elements = null;
		try{
			switch(method.toLowerCase())
			{
			case "xpath":
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "css":
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "class":
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "id":
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "binding":
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.binding(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "model":
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.model(string)));
				Reporter.log("SUCCESS",true);
				break;
			case "repeater":
				elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.repeater(string)));
				Reporter.log("SUCCESS",true);
				break;
			}
		}
		catch(Exception e){
			exceptionLogging(e, "Timed out waiting for the list of web elements to be visible, for " + method + ": " + string);}	
		return elements;
	}
	
	public void exceptionLogging(Exception e, String logErrorMessage){
		String errorMessage = null;
		errorMessage = e.getMessage();
		Assert.fail(errorMessage);
		Reporter.log(logErrorMessage,true);
	}
/**
 * This function handles 'wait for the element to be invisible'
 * @param webElement web element that needs to be invisible
 * @return boolean value telling us whether it could successfully wait for the element to be invisible
 */
public boolean waitForElementToBeInVisible(WebElement webElement, boolean ignoreError)
{
	try{
		elements.add(webElement);
		wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	catch(Exception e){
		if(!ignoreError){
			exceptionLogging(e, "Timed out waiting for the web element to be invisible");	
		}
	}
	return false;
 }

}

