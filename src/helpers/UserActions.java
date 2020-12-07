package helpers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * <h1>Defines the set of actions we will be using in our tests</h1>
 * 
 * @author Ankit
 * @since 2016-09-01
 *
 */
public class UserActions extends WebDriverHelper{
	
	long currentTime;
	long newTime;
	WebDriverHelper helper = new WebDriverHelper();
	
	/**
	 * This method will help in clicking on the web element
	 * @param webElement The web element you want to click on
	 * @return true if clickable
	 */
	public boolean click(WebElement webElement)
	{
		Reporter.log("Trying to click on the web element");
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 5000;
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		while (currentTime < newTime){
			try{
				webElement.click();
			return true;}
			catch(Exception e){
			currentTime = System.currentTimeMillis();
			if(currentTime >= newTime){
				Assert.fail("Could not click on the element\n" + e.getMessage());
			}
		  }
		}
		return false;
	}
	
	/**
	 * This method will help in clicking on the web element, you can specify for how long you want to keep trying
	 * @param webElement webElement The web element you want to click on
	 * @param waitTime custom wait time for which you want to keep trying with clicking on the web element 
	 * @param throwErrorMessage whether you want to throw an error if the element could not be clicked 
	 * @return true or false
	 */
	public boolean click(WebElement webElement, long waitTime, boolean throwErrorMessage)
	{
		Reporter.log("Trying to click on the web element");
		currentTime = System.currentTimeMillis();
		newTime = currentTime + waitTime;
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		while (currentTime < newTime){
			try{
				webElement.click();
			return true;}
			catch(Exception e){
			currentTime = System.currentTimeMillis();
			if(currentTime >= newTime){
				if (throwErrorMessage){
					Assert.fail("Could not click on the element\n" + e.getMessage());
				}
			}
		  }
		}
		return false;
	}
	
	/**
	 * This method will help in clicking on the web element
	 * @param webElement The web element you want to click on
	 * @param waitForTheElementToBeInvisible boolean value
	 * @return true if clickable
	 */
	public boolean click(WebElement webElement, boolean waitForTheElementToBeInvisible)
	{
		Reporter.log("Trying to click on the web element",true);
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 30000;
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		while (currentTime < newTime){
			try{
				if(browser.equals("phantomjs") || browser.equals("firefox")){
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("arguments[0].click();", webElement);
				}
				else{
				webElement.click();
				}
				if(waitForTheElementToBeInvisible){
					helper.waitForElementToBeInVisible(webElement,true);
				}
				Reporter.log("Click on the element was successful",true);
				return true;
			}
			catch(Exception e){
				currentTime = System.currentTimeMillis();
				if(currentTime >= newTime){
					Assert.fail("Could not click on the element\n" + e.getMessage());
			}
		  }
		}
		return false;
	}
	
	/**
	 * This method will help in entering text in a text field
	 * @param webElement The web element for which you want to enter text
	 * @param data The text to be entered
	 * @return true if text was entered successfully
	 */
	public boolean set(WebElement webElement, String data)
	{
		Reporter.log("The following data is being sent to the web element: " + data);
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 10000;
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		while (currentTime < newTime){
			try{webElement.sendKeys(data);
			Assert.assertEquals(webElement.getAttribute("value"), data);
			return true;
			}
			catch(Exception e){
			currentTime = System.currentTimeMillis();
			if(currentTime >= newTime){
				Assert.fail("Could not enter text\n" + e.getMessage());
			}
		  }
		}
		return false;
	}
	
	/**
	 * This method will help in retrieving text from a web element
	 * @param webElement The web element you want the text to be retrieved from
	 * @return Element text
	 */
	public String getText(WebElement webElement)
	{
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 10000;
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		while (currentTime < newTime){
			try{
				String text = webElement.getText();
				if(text!=null || text!=""){
					return text;
				}
			}
			catch(Exception e){
			currentTime = System.currentTimeMillis();
			if(currentTime >= newTime){
				Assert.fail("Could not get text\n" + e.getMessage());	
			}
		  }
		}
			return null;
	}
	
	/**
	 * Will get the values from the drop down
	 * @param dropDown The drop down you want the option values of!
	 * @return will return the list of options
	 */
	public List<String> getDropDownOptions(WebElement dropDown)
	{
		List<WebElement> options = dropDown.findElements(By.xpath("option"));
		List<String> optionsText = new ArrayList<String>();;
		for (int i=1; i<options.size(); i++)
		{
			optionsText.add(options.get(i).getText());
		}
		return optionsText;
	}
	
	/**
	 * This method will help in retrieving text of a value from a drop down
	 * @param webElement Drop down
	 * @return text
	 */
	public String getTextOfTheDropDownValue(WebElement webElement)
	{
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		String actualValue = webElement.getAttribute("value");
		WebElement dropDownValue = webElement.findElement(By.xpath("option[@value=\"" + actualValue + "\"]"));
		String text = dropDownValue.getText();
		return text;
	}
	
	/**
	 * This method will help in retrieving value attribute from a web element
	 * @param webElement The web element you want to get the attribute from
	 * @return Element value
	 */
	public String getValue(WebElement webElement)
	{
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		String value = webElement.getAttribute("value");
		return value;
	}
	
	/**
	 * This method will help in clearing text from a web element
	 * @param webElement The web element you want the text to be cleared from
	 * @return element value
	 */
	public boolean clearText(WebElement webElement)
	{
		if(browser == "firefox"){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		webElement.clear();
		return true;
	}
	
	/**
	 * This method will help us in selecting a value from a drop down
	 * @param webElement The drop down you want to select from
	 * @param data The value from the drop down you want select 
	 * @return true if successfully selected a value from the drop down
	 */
	public boolean select(WebElement webElement, String data)
	{
		ngWebDriver.waitForAngularRequestsToFinish();
		Reporter.log("The following value is being selected from the web element: " + data);
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 60000;
		if(browser.equals("firefox")){
			try{
				this.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				this.scrollToWebElement(webElement,true);
			}
		}
		while (currentTime < newTime){
			try{
			Select dropDown = new Select(webElement);
			dropDown.selectByVisibleText(data);
			return true;
			}
			catch(Exception e){
			try{
				List<String>optionValues = new ArrayList<String>();
				List<WebElement>options = new ArrayList<WebElement>();
				options = webElement.findElements(By.xpath("option"));
				for (int i=0; i < options.size(); i++){
					optionValues.add(options.get(i).getText());
				}
				Select dropDown = new Select(webElement);
				dropDown.selectByIndex(optionValues.indexOf(data));
				String actualValue = webElement.getAttribute("value");
				WebElement option = webElement.findElement(By.xpath("option[@value=\"" + actualValue + "\"]"));
				String text = option.getText();
				if(text == data){
				return true;
				}
			}
			catch(Exception e1){
			currentTime = System.currentTimeMillis();
			if(currentTime >= newTime){
				Assert.fail("Could not select the value from the drop down\n" + e.getMessage());
				}
			}
		  }
		}
		return false;
	}
	
	/**
	 * Will help in scrolling to a particular web element
	 * @param element Web Element that you want to scroll to
	 * @param elementIsBelow true if its below, false if its above
	 */
	public void scrollToWebElement(WebElement element, boolean elementIsBelow)
	{
		if(elementIsBelow){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
		else{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		}
	}
}
	
