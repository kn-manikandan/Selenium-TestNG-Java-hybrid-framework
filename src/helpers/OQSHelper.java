package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import oqs.mccg.pages.BrokerPageWebElements;
import oqs.mccg.pages.QuotePageWebElements;

public class OQSHelper extends WebDriverHelper {
	
	UserActions actions = new UserActions();
	QuotePageWebElements quotePage = new QuotePageWebElements();
	BrokerPageWebElements brokerPage = new BrokerPageWebElements();
	long currentTime;
	long newTime;
	
	/**
	 * Waits for the quote spinner on the OQS page to appear
	 */
	public void waitForTheSpinnerToAppear(){
		try{
			WebDriverWait customWait = new WebDriverWait(driver,5);
			WebElement webElement = customWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oqs-quote-spinner")));
			if(browser.equals("firefox")){
				try{
					actions.scrollToWebElement(webElement,false);
				}
				catch(Exception e){
					actions.scrollToWebElement(webElement,true);
				}
			}
		}
		catch(Exception e){}
	}
	
	/**
	 * Waits for the quote spinner on the OQS page to disappear
	 */
	public void waitForTheSpinnerToDisappear(){
		try{
			WebDriverWait customWait = new WebDriverWait(driver,15);
			customWait = new WebDriverWait(driver,5);
			WebElement spinner = driver.findElement(By.className("oqs-quote-spinner"));
			if(browser.equals("firefox")){
				try{
					actions.scrollToWebElement(spinner,false);
				}
				catch(Exception e){
					actions.scrollToWebElement(spinner,true);
				}
			}
			customWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oqs-quote-spinner")));
		}
		catch(Exception e){}
	}
	
	/**
	 * This function helps us with moving the slider on the OQS page
	 * @param webElement Slider web element
	 * @param valueExpectedFromTheComboBox The value you want to set on the slider
	 * @param elementToBeUsedForValidation The element you want to use for validation to ensure the slider selects the correct value
	 * @param elementUsedForValdiationIsNumberInput Whether the element that is being used as validation is number input or not
	 * @param waitForSpinner Do you want to wait for the spinner to appear every time the quote changes 
	 */
	public void moveSlider(WebElement webElement, String valueExpectedFromTheComboBox, WebElement elementToBeUsedForValidation, boolean elementUsedForValdiationIsNumberInput, boolean waitForSpinner)
	{
		if(browser.equals("firefox")){
			try{
				actions.scrollToWebElement(webElement,false);
			}
			catch(Exception e){
				actions.scrollToWebElement(webElement,true);
			}
		}
		double by=0;
		double maxWidth=0;
		double maxYearValue = Integer.parseInt(webElement.getAttribute("end"));
		double minYearValue = Integer.parseInt(webElement.getAttribute("start"));
		int actualValue = 0;
		int expectedValue = Integer.parseInt(valueExpectedFromTheComboBox);
		newTime = System.currentTimeMillis() + 20000;
		
		while(actualValue!=expectedValue && System.currentTimeMillis() < newTime){
			if(minYearValue != expectedValue){
				maxWidth = webElement.getSize().getWidth();
				by = (expectedValue - minYearValue)/(maxYearValue - minYearValue);
				double width = Math.round(maxWidth * by);
				advancedActions.dragAndDropBy(webElement.findElement(By.xpath("div/div/div")), (int) - maxWidth, 0).build().perform();
				
				if (waitForSpinner){
				waitForTheSpinnerToAppear();
				}
				
				if(elementUsedForValdiationIsNumberInput){
				wait.until(ExpectedConditions.textToBePresentInElementValue(elementToBeUsedForValidation, String.valueOf((int)minYearValue)));
				}
				else{wait.until(ExpectedConditions.textToBePresentInElement(elementToBeUsedForValidation, String.valueOf((int)minYearValue)));}
				
				if(elementUsedForValdiationIsNumberInput){
				actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
				}
				else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
				
				while(actualValue == minYearValue){
					advancedActions.dragAndDropBy(webElement.findElement(By.xpath("div/div/div")), (int)width, 0).build().perform();
					if(elementUsedForValdiationIsNumberInput){
						actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
						}
						else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
				}
				
				if (waitForSpinner){
					waitForTheSpinnerToAppear();
					}
				
				if(elementUsedForValdiationIsNumberInput){
					actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
					}
					else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
				
				int i = 1;
				while(actualValue != expectedValue){
					double newWidth1 = width - i;
					advancedActions.dragAndDropBy(webElement.findElement(By.xpath("div/div/div")), (int) - maxWidth, 0).build().perform();
					
					if (waitForSpinner){
						waitForTheSpinnerToAppear();
						}
					
					if(elementUsedForValdiationIsNumberInput){
						wait.until(ExpectedConditions.textToBePresentInElementValue(elementToBeUsedForValidation, String.valueOf((int)minYearValue)));
						}
						else{wait.until(ExpectedConditions.textToBePresentInElement(elementToBeUsedForValidation, String.valueOf((int)minYearValue)));}
					
					if(elementUsedForValdiationIsNumberInput){
						actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
						}
						else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
					
					while(actualValue == (int)minYearValue){
						advancedActions.dragAndDropBy(webElement.findElement(By.xpath("div/div/div")), (int)newWidth1, 0).build().perform();
						
						if(elementUsedForValdiationIsNumberInput){
							actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
							}
							else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
					}
					
					if (waitForSpinner){
						waitForTheSpinnerToAppear();
						}
					
					if(elementUsedForValdiationIsNumberInput){
						actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
						}
						else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
					
					if(actualValue != expectedValue){
						double newWidth2 = width + i;
						advancedActions.dragAndDropBy(webElement.findElement(By.xpath("div/div/div")), (int) - maxWidth, 0).build().perform();
						
						if (waitForSpinner){
							waitForTheSpinnerToAppear();
							}
						
						if(elementUsedForValdiationIsNumberInput){
							wait.until(ExpectedConditions.textToBePresentInElementValue(elementToBeUsedForValidation, String.valueOf((int)minYearValue)));
							}
							else{wait.until(ExpectedConditions.textToBePresentInElement(elementToBeUsedForValidation, String.valueOf((int)minYearValue)));}
						
						if(elementUsedForValdiationIsNumberInput){
							actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
							}
							else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
						
						while(actualValue == (int)minYearValue){
							if(elementUsedForValdiationIsNumberInput){
								actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
								}
								else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
							
							advancedActions.dragAndDropBy(webElement.findElement(By.xpath("div/div/div")), (int)newWidth2, 0).build().perform();
							
							if(elementUsedForValdiationIsNumberInput){
								actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
								}
								else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
						}
						
						if (waitForSpinner){
							waitForTheSpinnerToAppear();
							}
						
						if(elementUsedForValdiationIsNumberInput){
							actualValue = Integer.parseInt(elementToBeUsedForValidation.getAttribute("value"));
							}
							else{actualValue = Integer.parseInt(elementToBeUsedForValidation.getText());}
					}
					else{break;}
					if(i == 3){
						Assert.fail("Could not select the required value from the slider");
						break;}
					i++;
					}
				Reporter.log("Selected " + actualValue + " from the slider...", true);
			}
			else{
				advancedActions.dragAndDropBy(webElement.findElement(By.xpath("div/div/div")), (int) - maxWidth, 0).build().perform();
				if (waitForSpinner){
					waitForTheSpinnerToAppear();}
			}
		}
	}
	
	/**
	 * Will help us validate whether the broker pin on the map has the correct information of the broker selected
	 * @param text Text you want to validate on the map
	 * @return true or false depending upon whether the element was found or not
	 */
	public boolean validateBrokerInformationOnTheMap(String text)
	{
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 20000;
		while (currentTime <= newTime){
			try{
				WebElement googleMap = findElement("class", "angular-google-map");
				Reporter.log("Using the above element, trying to find element by text: " + text, true);
				googleMap.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
				return true;
			}
			catch(Exception e){
				currentTime = System.currentTimeMillis();
				if(currentTime > newTime){
					Assert.fail(e.getMessage());
				}		
			}
		}
		return false;
	}
	
	/**
	 * Will click on the broker and retrieve the broker details - name, address, phone number, web site url 
	 * @param broker - String value of the broker, example A,B,C etc
	 * @param brokerHasPhoneNumber true or false
	 * @param brokerHasWebSite true or false
	 * @return list of broker details
	 */
	public List<String> clickOnTheBrokerAndGetBrokerDetails(String broker, boolean brokerHasLogo, boolean brokerHasPhoneNumber, boolean brokerHasWebSite)
	{
		WebElement element = null;
		List<String> brokerDetails = new ArrayList<String>();
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 30000;
		while (currentTime <= newTime){
				try{
					switch(broker.toUpperCase())
					{
						case "A":
							element = brokerPage.brokerA();
							break;
						case "B":
							element = brokerPage.brokerB();
							break;
						case "C":
							element = brokerPage.brokerC();
							break;
					}
					if(browser.equals("firefox")){
						try{
							actions.scrollToWebElement(element,false);
						}
						catch(Exception e){
							actions.scrollToWebElement(element,true);
						}
					}
					actions.click(element,1000,false);
					String brokerName = element.findElement(By.xpath("h5")).getText();
					brokerDetails.add(brokerName);
					String brokerAddress = element.findElement(By.className("oqs-broker-address")).getText();
					brokerDetails.add(brokerAddress);
					if (brokerHasLogo){
					String brokerLogoSource = element.findElement(By.xpath("div[2]/img")).getAttribute("src");
					brokerDetails.add(brokerLogoSource);
					}
					if (brokerHasPhoneNumber){
						String brokerPhoneNumber = element.findElement(By.className("oqs-broker-phone")).getText();
						brokerDetails.add(brokerPhoneNumber);
					}
					if (brokerHasWebSite){
						String brokerWebSite= element.findElement(By.xpath("div[3]/a")).getAttribute("title");
						brokerDetails.add(brokerWebSite);
					}
					return brokerDetails;
				}
				catch(Exception e){
					currentTime = System.currentTimeMillis();
					if(currentTime > newTime){
						Assert.fail(e.getMessage());
				}
			}
		}
		return brokerDetails;
	}
	
	/**
	 * Click on the broker and then on their web site
	 * @param broker String broker - A,B,C etc
	 * @return true or false
	 */
	public boolean clickOnBrokerAndThenClickOnTheirWebSite(String broker)
	{
		WebElement element = null;
		currentTime = System.currentTimeMillis();
		newTime = currentTime + 30000;
		while (currentTime <= newTime){
				try{
					switch(broker.toUpperCase())
					{
						case "A":
							element = brokerPage.brokerA();
							break;
						case "B":
							element = brokerPage.brokerB();
							break;
						case "C":
							element = brokerPage.brokerC();
							break;
					}
					if(browser.equals("firefox")){
						try{
							actions.scrollToWebElement(element,false);
						}
						catch(Exception e){
							actions.scrollToWebElement(element,true);
						}
					}
					actions.click(element,1000,false);
					element.findElement(By.xpath("div[3]/a")).click();
					return true;
				}
				catch(Exception e){
					currentTime = System.currentTimeMillis();
					if(currentTime > newTime){
						Assert.fail(e.getMessage());
				}
		     }
		}
		return false;
	}
	
	/**
	 * Will give you all the questions on the page
	 * @param webElements list of web elements from which you will retrieve the questions
	 * @return list of questions
	 */
	public List<String> getAllQuestions(List<WebElement> webElements)
	{
		List<WebElement> questionsOnThePage = webElements;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		List<String>questions = new ArrayList<String>();
		String question = "";
		int numberOfQuestions = questionsOnThePage.size();
		for(int i=0;i < numberOfQuestions; i++){
			String displayAttribute = questionsOnThePage.get(i).findElement(By.xpath("../..")).getCssValue("display");
			if((!displayAttribute.equals("none"))){
				question = (String) js.executeScript("return arguments[0].innerHTML", questionsOnThePage.get(i));
				if(!question.isEmpty() && question!=null){
					question = question.replace("\n", "").replace("\r", "");	
					questions.add(question);
				}
			}
		}
		return questions;
	}
	
	/**
	 * Will give you all the answers on the page
	 * @param webElements list of web elements from which you will retrieve the answers
	 * @return list of answers
	 */
	public List<String> getAllAnswers(List<WebElement> webElements)
	{
		List<WebElement> answersOnThePage = webElements;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		List<String>answers = new ArrayList<String>();
		WebElement answerWebElement;
		String answer = "";
		for(int i=0;i < answersOnThePage.size(); i++){
			answerWebElement = answersOnThePage.get(i);
			if(!answerWebElement.findElement(By.xpath("..")).getCssValue("display").equals("none")){
				if(answerWebElement.findElements(By.xpath("input")).size() > 0){
					answer = answerWebElement.findElement(By.xpath("input")).getAttribute("value");
				}
				else if(answerWebElement.findElements(By.xpath("div[@ng-switch-when='DATE']")).size() > 0){
					if(answerWebElement.findElements(By.xpath("div[@ng-switch-when='DATE']/select")).size() == 3){
						List<String> dobValues = new ArrayList<String>();
						for(int j=answerWebElement.findElements(By.xpath("div/select")).size();j > 0 ;j--){
							String dobValue = answerWebElement.findElement(By.xpath("div/select["+ String.valueOf(j) + "]")).getAttribute("value");
							dobValue = dobValue.substring(dobValue.indexOf(":")+1);
							if(j == 2){
								DateTimeFormatter format = DateTimeFormat.forPattern("MMM");
								DateTime instance    = format.parseDateTime(dobValue);  
								int monthNumber = instance.getMonthOfYear();
								dobValues.add(String.valueOf(monthNumber));
							}
							else{
							dobValues.add(dobValue);
							}
							if(j!=1){
								dobValues.add("-");
							}
						}
						String finalDOBValue = "";
						for (String s : dobValues){
							finalDOBValue += s;
						}
						answer = finalDOBValue;
					}
				}
				else if(answerWebElement.findElements(By.xpath("div[@ng-switch-when='DROPDOWN']")).size() > 0){
					answer = answerWebElement.findElement(By.xpath("div/select")).getAttribute("value");
					if(!answer.isEmpty()){
						answer = (String) js.executeScript("return arguments[0].innerHTML", answerWebElement.findElement(By.xpath("div/select/option[@value='" + answer + "']")));
					}
				}
				else if(answerWebElement.findElements(By.xpath("div[@ng-switch-when='RADIO']")).size() > 0){
					if(answerWebElement.findElements(By.cssSelector("div[ng-switch-when='RADIO']>div>label[class*='active']")).size() > 0){
						answer = (String) js.executeScript("return arguments[0].innerHTML", answerWebElement.findElement(By.cssSelector("div[ng-switch-when='RADIO']>div>label[class*='active']")));
					}
					else{
						answer="";
					}
				}
				else if(answerWebElement.findElements(By.xpath("div[@ng-switch-when='COMBOSLIDER']")).size() > 0){
					if(answerWebElement.findElements(By.cssSelector("div[ng-switch-when='COMBOSLIDER']>div>input")).size() > 0){
					answer = answerWebElement.findElement(By.cssSelector("div[ng-switch-when='COMBOSLIDER']>div>input")).getAttribute("value");
					answer = answer.substring(answer.indexOf(":")+1);
					}
					else{
						answer="";
					}
				}
				else if(answerWebElement.findElements(By.xpath("div[@ng-switch-when='SLIDER']")).size() > 0){
					if(answerWebElement.findElements(By.cssSelector("div[ng-switch-when='SLIDER']>div[class='slider-value']")).size() > 0){
						answer = (String) js.executeScript("return arguments[0].innerHTML", answerWebElement.findElement(By.cssSelector("div[ng-switch-when='SLIDER']>div[class='slider-value']")));
					}
					else{
						answer="";
					}
				}
				else{
					answer="";
				}
				
				if(!answer.isEmpty() && answer!=null){
					answer = answer.replace("\n", "").replace("\r", "");	
					answers.add(answer);
				}
			}
		}
		return answers;
	}
	
	/**
	 * Will concatenate the questions and answers into one sentence
	 * @param questions list of questions
	 * @param answers list of answers
	 * @return list of question and answers
	 */
	public List<String> getAllQuestionsAndAnswers(List<String> questions, List<String>answers) 
	{
		List<String> questionAndAnswers= new ArrayList<String>();
		if(questions.size()==answers.size()){
			for(int i=0;i < questions.size();i++){
				String questionAndAnswer = questions.get(i) + " " + answers.get(i);
				questionAndAnswers.add(questionAndAnswer);
			}
		}
		else{
			Assert.fail("Numbers of questions and answers are not equal");
		}
		return questionAndAnswers;
	}
	
	/**
	 * Will retrieve the summary page date and time
	 * @param webElement date and time web element
	 * @return summary date and time
	 * @throws ParseException Signals that an error has been reached unexpectedly while parsing
	 */
	public List<String> getSummaryPageDateAndTime(WebElement webElement) throws ParseException
	{
		List<String>summaryDateAndtime = new ArrayList<String>();
		String summaryDate = actions.getText(webElement);
		int index = summaryDate.indexOf(",");
		summaryDate = summaryDate.substring(index+1).trim();
		index = summaryDate.indexOf(",");
		String summaryTime = summaryDate.substring(index+1);
		summaryDate = summaryDate.substring(0,index);
		int space1Index = summaryDate.indexOf(" ");
		int space2Index = summaryDate.indexOf(" ",summaryDate.indexOf(" ") + 1);
		String dayValue = summaryDate.substring(space1Index,space2Index);
		String modifiedDayValue = dayValue.substring(0,dayValue.length()-2);
		
		// if the day is between 1 - 9, we will add 0 to it..for example 1 will be 01
		if(modifiedDayValue.length()==2){
			modifiedDayValue = modifiedDayValue.replace(modifiedDayValue.substring(1),"0"+ modifiedDayValue.substring(1));
		}
		summaryDate = summaryDate.replace(dayValue, modifiedDayValue);
		summaryDate = summaryDate.substring(0,space1Index+3) + "," + summaryDate.substring(space1Index+3);
		Reporter.log("\nsummary date: " + summaryDate + "\n",true);
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mma");
	    Date date = parseFormat.parse(summaryTime);
	    summaryTime = displayFormat.format(date);
	    Reporter.log("\nsummary time: " + summaryTime + "\n",true);
	    summaryDateAndtime.add(summaryDate);
	    summaryDateAndtime.add(summaryTime);
		return summaryDateAndtime;  
	}
}
