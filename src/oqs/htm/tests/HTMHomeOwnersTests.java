package oqs.htm.tests;

import org.testng.annotations.*;

import helpers.UserActions;
import oqs.mccg.pages.QuotePageWebElements;
import helpers.BaseClass;
import helpers.GenericHelper;
import helpers.OQSHelper;

/**
 * HomeOwners Tests
 * 
 * @author Ankit
 * @since 2016-09-01
 *
 */
public class HTMHomeOwnersTests extends BaseClass {
	
	QuotePageWebElements quotePage = new QuotePageWebElements();
	UserActions actions = new UserActions();
	GenericHelper helper = new GenericHelper();
	OQSHelper oqsHelp = new OQSHelper();
	String dataFileLocation = System.getProperty("user.dir") + "/data/oqs/htm/";

	/**
	 * Home owners test case 1
	 */
	@Test(groups = {"OQS Tests","Positive Tests"})
	public void homeOwners_TC1()
	{
		helper.setTestData(dataFileLocation);
		
		actions.set(quotePage.nameTextInput(),helper.getTestData("nameTextInput"));
		actions.set(quotePage.postalCodeTextInput(),helper.getTestData("postalCodeTextInput"));
		actions.click(quotePage.homeOwnersLabel());
		oqsHelp.moveSlider(quotePage.homeBuiltYearSlider(), helper.getTestData("homeBuiltYearNumberInput"), quotePage.homeBuiltYearNumberInput(), true, true);
	}
}
