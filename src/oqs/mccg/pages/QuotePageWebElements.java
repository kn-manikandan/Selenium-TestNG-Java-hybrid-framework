package oqs.mccg.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helpers.WebDriverHelper;

/**
 * Quote page web elements
 * 
 * @author Ankit
 * @since 2016-09-01
 *
 */
public class QuotePageWebElements extends WebDriverHelper {
	
	private WebElement element;
	List<WebElement>elements = new ArrayList<WebElement>();
	
	/**
	 * All questions on the quote page
	 * @return list of questions 
	 */
	public List<WebElement>questions()
	{
		elements = findElements("css","span[ng-bind*='oqsQuestionControl.question.definition.description']");
		return elements;
	}
	
	/**
	 * All answers on the quote page
	 * @return list of answers
	 */
	public List<WebElement>answers()
	{
		elements = findElements("css","div[class*='oqs-question-control']");
		return elements;
	}

	/**
	 * Name text input
	 * @return Web element
	 */
	public WebElement nameTextInput()
	{
		element = findElement("xpath", "//span[text() = 'Name']");
		return element.findElement(By.xpath("../following-sibling::div/input"));	
	}
	
	/**
	 * Empty Name validation error message
	 * @return Web element
	 */
	public WebElement nameTextInputErrorMessage()
	{
		element = findElement("xpath", "//span[text() = 'Name']");
		return element.findElement(By.xpath("../following-sibling::div/div/div"));	
	}
	

	/**
	 * The spinner that shows up when quote changes
	 * @return web element
	 */
	public WebElement quoteSpinner()
	{
		element = findElement("class","'oqs-quote-response oqs-animate-flip'");
		return element;
	}
	
	/**
	 * The error response seen for invalid quote 
	 * @return web element
	 */
	public WebElement quoteErrorResponse()
	{
		element = findElement("class","oqs-quote-response-message");
		return element;
	}
	
	/**
	 * Broker logo on the quote page
	 * @return web element
	 */
	public WebElement brokerLogo()
	{
		element = findElement("class","oqs-broker-branding-logo");
		return element;
	}
	
	/**
	 *  Postal Code text input
	 * @return web element
	 */
	public WebElement postalCodeTextInput()
	{
		element = findElement("xpath", "//span[text() = 'Postal Code']");
		return element.findElement(By.xpath("../following-sibling::div/input"));			
	}
	
	/**
	 *  Empty Postal Code validation error message
	 * @return web element
	 */
	public WebElement postalCodeTextInputErrorMessage()
	{
		element = findElement("xpath", "//span[text() = 'Postal Code']");
		return element.findElement(By.xpath("../following-sibling::div/div/div"));			
	}
	
	/**
	 * Company logo on the quote page
	 * @return web element
	 */
	public WebElement logo()
	{
		element = findElement("class", "oqs-logo");
		return element;	
	}
	
	//------------------------------------------------------------------------------------------------------
	
	// home Owners web elements
	
	/**
	 * Home Owners label
	 * @return web element
	 */
	public WebElement homeOwnersLabel()
	{
		element = findElement("xpath", "//span[@class='fa fa-home']");
		return element;		
	}
	
	/**
	 * Auto label
	 * @return web element
	 */
	public WebElement autoLabel()
	{
		element = findElement("xpath", "//span[@class='fa fa-car']");
		return element;		
	}
	
	/**
	 * 'When was your home built?' slider
	 * @return web element
	 */
	public WebElement homeBuiltYearSlider()
	{
		elements = findElements("xpath","//div[@class='comboslider']/div[@ng-model='oqsQuestionControl.question.answer']");
		return elements.get(0);
	}
	
	/**
	 * 'When was your home built?' number input
	 * @return web element
	 */
	public WebElement homeBuiltYearNumberInput()
	{
		elements = findElements("xpath","//div[@class='comboslider-inputfield pull-left']/input");
		return elements.get(0);
	}
	
	//----------------------------------------------------------------------------------------------------------------------
	
	// Auto Web elements
	
	/**
	 * 'Tell us about who will be driving' header
	 * @return web element
	 */
	public WebElement tellUsAboutWhoWillBeDrivingHeader()
	{
		element = findElement("xpath", "//h5[contains(text(), 'Tell us about who will be driving')]");
		return element;	
	}
	
	/**
	 * 'Tell us about who will be driving' section
	 * @return web element
	 */
	public WebElement tellUsAboutWhoWillBeDrivingOQSSection()
	{
		element = tellUsAboutWhoWillBeDrivingHeader();
		element = findElement(element,"xpath","../following-sibling::div");
		return element;	
	}
	
	/**
	 * 'Tell us about your vehicles' header
	 * @return web element
	 */
	public WebElement tellUsAboutYourVehiclesHeader()
	{
		element = findElement("xpath", "//h5[contains(text(), 'Tell us about your vehicles')]");
		return element;	
	}
	
	/**
	 * 'Tell us about your vehicles' section
	 * @return web element
	 */
	public WebElement tellUsAboutYourVehiclesOQSSection()
	{
		element = tellUsAboutYourVehiclesHeader();
		element = findElement(element,"xpath","../following-sibling::div");
		return element;	
	}
	
	/**
	 * 'How can I save even more money?' header
	 * @return web element
	 */
	public WebElement howCanISaveYourMoneyHeader()
	{
		element = findElement("xpath", "//h5[contains(text(), 'How can I save even more money?')]");
		return element;	
	}
	
	/**
	 * 'How can I save even more money?' section
	 * @return web element
	 */
	public WebElement howCanISaveYourMoneyOQSSection()
	{
		element = howCanISaveYourMoneyHeader();
		element = findElement(element,"xpath","../following-sibling::div");
		return element;	
	}
	
	/**
	 * 'What is the name of the driver?' text input
	 * @return web element
	 */
	public WebElement nameOfTheDriverTextInput()
	{
		element = findElement("xpath", "//span[contains(text(), 'What is the name of the driver?')]");
		return element.findElement(By.xpath("../following-sibling::div/input"));		
	}
	
	/**
	 * 'What is the name of the driver?' text input
	 * @return web element
	 */
	public WebElement nameOfTheDriverTextInputDriver2()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is the name of the driver?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/input"));		
	}
	
	/**
	 * Will select the class of license 'G'
	 * @return web element
	 */
	public WebElement classOfLicenseGButton()
	{
		elements = findElements("xpath", "//span[text() = 'What is your class of license?']");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}
	
	/**
	 * Will select the class of license 'G2'
	 * @return web element
	 */
	public WebElement classOfLicenseG2Button()
	{
		elements = findElements("xpath", "//span[text() = 'What is your class of license?']");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/div/label[1]"));
	}
	
	/**
	 * Will select the class of license 'G'
	 * @return web element
	 */
	public WebElement classOfLicenseDriver2GButton()
	{
		elements = findElements("xpath", "//span[text() = 'What is your class of license?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * Will select the class of license 'G2'
	 * @return web element
	 */
	public WebElement classOfLicenseDriver2G2Button()
	{
		elements = findElements("xpath", "//span[text() = 'What is your class of license?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label[1]"));
	}
	
	/**
	 * 'What is your date of birth?', day drop down
	 * @return web element
	 */
	public WebElement dayDOBDropDown()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your date of birth?')]");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/select[1]"));
	}
	
	/**
	 * 'What is your date of birth?', month drop down
	 * @return web element
	 */
	public WebElement monthDOBDropDown()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your date of birth?')]");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/select[2]"));
	}
	
	/**
	 * 'What is your date of birth?', year drop down
	 * @return web element
	 */
	public WebElement yearDOBDropDown()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your date of birth?')]");
		return elements.get(0).findElement(By.xpath("../following-sibling::div/div/select[3]"));
	}
	
	/**
	 * 'What is your date of birth?', year drop down
	 * @return web element
	 */
	public WebElement yearDOBDropDownDriver2()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your date of birth?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/select[3]"));
	}
	
	/**
	 * 'What is your date of birth?', day drop down
	 * @return web element
	 */
	public WebElement dayDOBDropDownDriver2()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your date of birth?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/select[1]"));
	}
	
	/**
	 * 'What is your date of birth?', month drop down
	 * @return web element
	 */
	public WebElement monthDOBDropDownDriver2()
	{
		elements = findElements("xpath", "//span[contains(text(), 'What is your date of birth?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/select[2]"));
	}
	
	/**
	 * For Gender, it will select Male
	 * @return web element
	 */
	public WebElement genderMaleButton()
	{
		element = findElement("xpath", "//span[text() = 'Gender?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * For Gender, it will select Female
	 * @return web element
	 */
	public WebElement genderFemaleButton()
	{
		element = findElement("xpath", "//span[text() = 'Gender?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}
	

	/**
	 * For Marital Status, it will select Single
	 * @return web element
	 */
	public WebElement genderMaritalStatusSingleButton()
	{
		element = findElement("xpath", "//span[text() = 'Marital status?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * For Marital Status, it will select Married
	 * @return web element
	 */
	public WebElement genderMaritalStatusMarriedButton()
	{
		element = findElement("xpath", "//span[text() = 'Marital status?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}

	/**
	 * For 'When did you obtain your current license class?' will select the day from the day drop down
	 * @return web element
	 */
	public WebElement dayCurrentLicenseObtainedDropDown()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.day']");
		return elements.get(1);	
	}
	
	/**
	 * For 'When did you obtain your current license class?' will select the day from the day drop down for driver 2
	 * @return web element
	 */
	public WebElement dayCurrentLicenseObtainedDropDownDriver2()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.day']");
		return elements.get(3);	
	}
	
	/**
	 * For 'When did you obtain your current license class?' will select the month from the month drop down
	 * @return web element
	 */
	public WebElement monthCurrentLicenseObtainedDropDown()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.month']");
		return elements.get(1);	
	}
	
	/**
	 * For 'When did you obtain your current license class?' will select the month from the month drop down for driver 2
	 * @return web element
	 */
	public WebElement monthCurrentLicenseObtainedDropDownDriver2()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.month']");
		return elements.get(3);	
	}
	
	/**
	 * For 'When did you obtain your current license class?' will select the year from the year drop down
	 * @return web element
	 */
	public WebElement yearCurrentLicenseObtainedDropDown()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.year']");
		return elements.get(1);	
	}
	
	/**
	 * For 'When did you obtain your current license class?' will select the year from the year drop down for driver2
	 * @return web element
	 */
	public WebElement yearCurrentLicenseObtainedDropDownDriver2()
	{
		elements = findElements("xpath", "//select[@ng-model='oqsQuestionControl.year']");
		return elements.get(3);	
	}
	
	/**
	 * 'How many years have you had continuous auto insurance?' slider
	 * @return web element
	 */
	public WebElement howManyYearsContinuosAutoInsuranceSlider()
	{
		element = findElement("xpath", "//span[text() = 'How many years have you had continuous auto insurance?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div[2]/div"));
	}
	
	/**
	 * 'How many years have you had continuous auto insurance?' slider for driver 2
	 * @return web element
	 */
	public WebElement howManyYearsContinuosAutoInsuranceSliderDriver2() 
	{
		elements = findElements("xpath", "//span[text() = 'How many years have you had continuous auto insurance?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div[2]/div"));
	}
	
	/**
	 * How many years have you had continuous auto insurance? input type number
	 * @return web element
	 */
	public WebElement howManyYearsContinuosAutoInsuranceNumberInput()
	{
		element = findElement("xpath", "//span[text() = 'How many years have you had continuous auto insurance?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/input"));
	}
	
	/**
	 * How many years have you had continuous auto insurance? input type number for driver 2
	 * @return web element
	 */
	public WebElement howManyYearsContinuosAutoInsuranceNumberInputDriver2()
	{
		elements = findElements("xpath", "//span[text() = 'How many years have you had continuous auto insurance?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/input"));
	}
	
	/**
	 * For 'Have you had any auto convictions, accidents, suspensions or insurance cancellations?' will select No
	 * @return web element
	 */
	public WebElement autoConvictionsNoButton()
	{
		element = findElement("xpath", "//span[text() = 'Have you had any auto convictions, accidents, suspensions or insurance cancellations?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * For 'Have you had any auto convictions, accidents, suspensions or insurance cancellations?' will select No for driver 2
	 * @return web element
	 */
	public WebElement autoConvictionsNoButtonDriver2()
	{
		elements = findElements("xpath", "//span[text() = 'Have you had any auto convictions, accidents, suspensions or insurance cancellations?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * For 'Have you had any auto convictions, accidents, suspensions or insurance cancellations?' will select Yes
	 * @return web element
	 */
	public WebElement autoConvictionsYesButton()
	{
		element = findElement("xpath", "//span[text() = 'Have you had any auto convictions, accidents, suspensions or insurance cancellations?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}
	
	/**
	 * For 'Have you had any auto convictions, accidents, suspensions or insurance cancellations in the last 6 years?' will select Yes
	 * @return web element
	 */
	public WebElement areYouRetiredYesButton()
	{
		element = findElement("xpath", "//span[text() = 'Are you retired?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));	
	}
	
	/**
	 * For 'Are you retired?' will select No
	 * @return web element
	 */
	public WebElement areYouRetiredNoButton()
	{
		element = findElement("xpath", "//span[text() = 'Are you retired?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * For 'Are you retired?' will select No for driver 2
	 * @return web element
	 */
	public WebElement areYouRetiredNoButtonDriver2()
	{
		elements = findElements("xpath", "//span[text() = 'Are you retired?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label"));	
	}
	
	/**
	 * Model Year slider
	 * @return web element
	 */
	public WebElement modelYearSlider()
	{
		element = findElement("xpath","//span[text() = 'Model Year']");
		return element.findElement(By.xpath("../following-sibling::div/div/div[2]/div"));
	}
	
	/**
	 * Model year number input
	 * @return web element
	 */
	public WebElement modelYearNumberInput()
	{
		element = findElement("xpath","//span[text() = 'Model Year']");
		return element.findElement(By.xpath("../following-sibling::div/div/div/input"));
	}
	
	/**
	 * Manufacturer drop down
	 * @return web element
	 */
	public WebElement manufacturerDropDown()
	{
		element = findElement("xpath","//span[text() = 'Manufacturer']");
		return element.findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * Model drop down
	 * @return web element
	 */
	public WebElement modelDropDown()
	{
		element = findElement("xpath","//span[text() = 'Model']");
		return element.findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * 'Who will be the main driver of this vehicle?' drop down
	 * @return web element
	 */
	public WebElement whoWillBeMainDriverDropDown()
	{
		element = findElement("xpath","//span[text() = 'Who will be the main driver of this vehicle?']");
		return element.findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * For 'Will any other driver be under 25?' will select No 
	 * @return web element
	 */
	public WebElement willAnyOtherDriverBeUnder25NoButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Will any other driver be under 25?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Will any other driver be under 25?' will select Yes
	 * @return web element
	 */
	public WebElement willAnyOtherDriverBeUnder25YesButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Will any other driver be under 25?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'Who will be the main driver under 25?' drop down
	 * @return web element
	 */
	public WebElement whoWillBeTheMainDriverUnder25DropDown()
	{
		element = findElement("xpath","//span[text() = 'Who will be the main driver under 25?']");
		return element.findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * 'What is the total distance driven per year (kms)?' slider
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverPerYearSlider()
	{
		element = findElement("xpath","//span[text() = 'What is the total distance driven per year (kms)?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div"));
	}
	
	/**
	 * 'What is the total distance driven per year (kms)?' label
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverPerYearLabel()
	{
		element = findElement("xpath","//span[text() = 'What is the total distance driven per year (kms)?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div[2]"));
	}
	
	/**
	 * 'What is the distance driven for business/commercial/farm usage per year (kms)?' slider
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSlider()
	{
		element = findElement("xpath","//span[text() = 'What is the distance driven for business/commercial/farm usage per year (kms)?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div"));
	}
	
	/**
	 * 'What is the distance driven for business/commercial/farm usage per year (kms)?' label
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearLabel()
	{
		element = findElement("xpath","//span[text() = 'What is the distance driven for business/commercial/farm usage per year (kms)?']");
		return element.findElement(By.xpath("../following-sibling::div/div/div[2]"));
	}
	
	/**
	 * 'What is the distance driven to work or school one way (kms)?' slider
	 * @return web element
	 */
	public WebElement whatIsDistanceDrivenToWorkOneWaySlider()
	{
		element = findElement("xpath","//span[contains(text(), 'What is the distance driven to work or school one way (kms)?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div"));
	}
	
	/**
	 * 'What is the distance driven to work or school one way (kms)?' label
	 * @return web element
	 */
	public WebElement whatIsDistanceDrivenToWorkOneWayLabel()
	{
		element = findElement("xpath","//span[contains(text(), 'What is the distance driven to work or school one way (kms)?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div[2]"));
	}

	/**
	 * For 'Does this vehicle have winter/snow tires installed during the winter season?' will select No
	 * @return web element
	 */
	public WebElement doesThisVehicleHaveWinterTiresNoButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Does this vehicle have winter/snow tires installed during the winter season?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 *For 'Does this vehicle have winter/snow tires installed during the winter season?' will select Yes
	 * @return web element
	 */
	public WebElement doesThisVehicleHaveWinterTiresYesButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Does this vehicle have winter/snow tires installed during the winter season?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	
	//-----------Driver 2---------------
	/**
	 * Model Year slider
	 * @return web element
	 */
	public WebElement modelYearSliderDriver2()
	{
		elements = findElements("xpath","//span[text() = 'Model Year']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div[2]/div"));
	}
	
	/**
	 * Model year number input
	 * @return web element
	 */
	public WebElement modelYearNumberInputDriver2()
	{
		elements = findElements("xpath","//span[text() = 'Model Year']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/input"));
	}
	
	/**
	 * Manufacturer drop down
	 * @return web element
	 */
	public WebElement manufacturerDropDownDriver2()
	{
		elements = findElements("xpath","//span[text() = 'Manufacturer']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * Model drop down
	 * @return web element
	 */
	public WebElement modelDropDownDriver2()
	{
		elements = findElements("xpath","//span[text() = 'Model']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * 'Who will be the main driver of this vehicle?' drop down
	 * @return web element
	 */
	public WebElement whoWillBeMainDriverDropDownDriver2()
	{
		elements = findElements("xpath","//span[text() = 'Who will be the main driver of this vehicle?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * For 'Will any other driver be under 25?' will select No 
	 * @return web element
	 */
	public WebElement willAnyOtherDriverBeUnder25NoButtonDriver2()
	{
		elements = findElements("xpath","//span[contains(text(), 'Will any other driver be under 25?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Will any other driver be under 25?' will select Yes
	 * @return web element
	 */
	public WebElement willAnyOtherDriverBeUnder25YesButtonDriver2()
	{
		elements = findElements("xpath","//span[contains(text(), 'Will any other driver be under 25?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'Who will be the main driver under 25?' drop down
	 * @return web element
	 */
	public WebElement whoWillBeTheMainDriverUnder25DropDownDriver2()
	{
		elements = findElements("xpath","//span[text() = 'Who will be the main driver under 25?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/select"));
	}
	
	/**
	 * 'What is the total distance driven per year (kms)?' slider
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverPerYearSliderDriver2()
	{
		elements = findElements("xpath","//span[text() = 'What is the total distance driven per year (kms)?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div"));
	}
	
	/**
	 * 'What is the total distance driven per year (kms)?' label
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverPerYearLabelDriver2()
	{
		elements = findElements("xpath","//span[text() = 'What is the total distance driven per year (kms)?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div[2]"));
	}
	
	/**
	 * 'What is the distance driven for business/commercial/farm usage per year (kms)?' slider
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearSliderDriver2()
	{
		elements = findElements("xpath","//span[text() = 'What is the distance driven for business/commercial/farm usage per year (kms)?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div"));
	}
	
	/**
	 * 'What is the distance driven for business/commercial/farm usage per year (kms)?' label
	 * @return web element
	 */
	public WebElement whatIsTheTotalDistanceDriverForBusinessCommercialFarmUsagePerYearLabelDriver2()
	{
		elements = findElements("xpath","//span[text() = 'What is the distance driven for business/commercial/farm usage per year (kms)?']");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div[2]"));
	}
	
	/**
	 * 'What is the distance driven to work or school one way (kms)?' slider
	 * @return web element
	 */
	public WebElement whatIsDistanceDrivenToWorkOneWaySliderDriver2()
	{
		elements = findElements("xpath","//span[contains(text(), 'What is the distance driven to work or school one way (kms)?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div"));
	}
	
	/**
	 * 'What is the distance driven to work or school one way (kms)?' label
	 * @return web element
	 */
	public WebElement whatIsDistanceDrivenToWorkOneWayLabelDriver2()
	{
		elements = findElements("xpath","//span[contains(text(), 'What is the distance driven to work or school one way (kms)?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div[2]"));
	}

	/**
	 * For 'Does this vehicle have winter/snow tires installed during the winter season?' will select No
	 * @return web element
	 */
	public WebElement doesThisVehicleHaveWinterTiresNoButtonDriver2()
	{
		elements = findElements("xpath","//span[contains(text(), 'Does this vehicle have winter/snow tires installed during the winter season?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 *For 'Does this vehicle have winter/snow tires installed during the winter season?' will select Yes
	 * @return web element
	 */
	public WebElement doesThisVehicleHaveWinterTiresYesButtonDriver2()
	{
		elements= findElements("xpath","//span[contains(text(), 'Does this vehicle have winter/snow tires installed during the winter season?')]");
		return elements.get(1).findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	//-----------------------------------------------------------------------------
	
	/**
	 * For 'Do you have any Property/Farm/Commercial policies with us?' select No
	 * @return web element
	 */
	public WebElement doYouHaveAnyPropertyFarmCommercialPoliciesWithUsNoButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Do you have any Property/Farm/Commercial policies with us?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Do you have any Property/Farm/Commercial policies with us?' select Yes
	 * @return web element
	 */
	public WebElement doYouHaveAnyPropertyFarmCommercialPoliciesWithUsYesButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Do you have any Property/Farm/Commercial policies with us?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'Do you have other private passenger vehicles in the household insured with us?' select No
	 * @return web element
	 */
	public WebElement doYouHaveOtherPrivatePassengerVehiclesNoButton()
	{
		element = findElement("xpath","//span[contains(text(),'Do you have other private passenger vehicles in the household insured with us?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Do you have other private passenger vehicles in the household insured with us?' select Yes
	 * @return web element
	 */
	public WebElement doYouHaveOtherPrivatePassengerVehiclesYesButton()
	{
		element = findElement("xpath","//span[contains(text(), 'Do you have other private passenger vehicles in the household insured with us?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'Increase my deductible amount?' will select 500
	 * @return web element
	 */
	public WebElement increaseMyDeductibleAmount500Button()
	{
		element = findElement("xpath","//span[contains(text(), 'Increase my deductible amount?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Increase my deductible amount?' will select 1000
	 * @return web element
	 */
	public WebElement increaseMyDeductibleAmount1000Button()
	{
		element = findElement("xpath","//span[contains(text(),'Increase my deductible amount?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * For 'Increase my deductible amount?' will select 2500
	 * @return web element
	 */
	public WebElement increaseMyDeductibleAmount2500Button()
	{
		element = findElement("xpath","//span[contains(text(),'Increase my deductible amount?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[3]"));
	}
	
	/**
	 * For 'Decrease my Liability Insurance?' will select 1 million
	 * @return web element
	 */
	public WebElement decreaseMyLiabilityInsurance1MillionButton()
	{
		element = findElement("xpath","//span[contains(text(),'Decrease my Liability Insurance?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label"));
	}
	
	/**
	 * For 'Decrease my Liability Insurance?' will select 2 million
	 * @return web element
	 */
	public WebElement decreaseMyLiabilityInsurance2MillionButton()
	{
		element = findElement("xpath","//span[contains(text(),'Decrease my Liability Insurance?')]");
		return element.findElement(By.xpath("../following-sibling::div/div/div/label[2]"));
	}
	
	/**
	 * Click on 'View Quote Details' from the bottom of the page
	 * @return web element
	 */
	public WebElement viewQuoteDetailsButton()
	{
		element = findElement("xpath","//span[contains(text(),'View Quote Details')]");
		return element.findElement(By.xpath("../../a"));
	}
	
	/**
	 * Quote value seen for every month
	 * @return web element
	 */
	public WebElement quoteValueMonthly()
	{
		element = findElement("xpath","//span[@ng-if = 'quote.data.quoteResponse.data.showQuote']/span");
		return element;
	}
	
	/**
	 * Quote value seen for every year
	 * @return web element
	 */
	public WebElement quoteValueYearly()
	{
		element = findElement("xpath","//span[@ng-if = 'quote.data.quoteResponse.data.showQuote']/span[2]");
		return element;
	}
	
	/**
	 * Add Another driver tab
	 * @return web element
	 */
	public WebElement addAnotherDriverTab()
	{
		element = findElement("xpath","//span[contains(text(),'Add another Driver')]");
		return element;
	}
	
	/**
	 * After adding multiple drivers by clicking on 'Add Another tab', will retrieve the driver names
	 * @return list of elements
	 */
	public List<WebElement> driversTabs()
	{
		element = findElement("xpath","//h5[contains(text(),'Tell us about who will be driving')]");
		return element.findElements(By.xpath("../following-sibling::div/div/ul/li/a/uib-tab-heading"));
	}
	
	/**
	 * Add another vehicle tab
	 * @return web element
	 */
	public WebElement addAnotherVehicleTab()
	{
		element = findElement("xpath","//span[contains(text(),'Add another Vehicle')]");
		return element;
	}
	
	/**
	 * After adding multiple vehicles by clicking on 'Add Another Vehicle', will retrieve the vehicle names
	 * @return web element
	 */
	public List<WebElement> vehiclesTabs()
	{
		element = findElement("xpath","//h5[contains(text(),'Tell us about your vehicles')]");
		return element.findElements(By.xpath("../following-sibling::div/div/ul/li/a/uib-tab-heading"));
	}
}

