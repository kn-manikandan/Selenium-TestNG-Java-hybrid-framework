package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.paulhammant.ngwebdriver.NgWebDriver;

/**
 * Base class for all our tests
 * 
 * @author Mani
 * @since 2016-09-01
 *
 */
public class BaseClass {
	
	GenericHelper genericHelp = new GenericHelper();
	JSONObject configFile;
	Properties log4jProperties;
	
	public static Logger log = Logger.getLogger(BaseClass.class);
	public static Date date = new Date();
	public static SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy");
	public static SimpleDateFormat sdf2 = new SimpleDateFormat("h_mm_ss");

	public static String app = "";
	public static String url = "";
	public static String browser = "";
	public static String runOnBrowserStack = "";
	public static String currentDirectory = System.getProperty("user.dir");
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static NgWebDriver ngWebDriver;
	public static JavascriptExecutor jse;
	public static Actions advancedActions;
	public static final String USERNAME = "ankitreddypati1";
	public static final String AUTOMATE_KEY = "xPzgGJPZgxyce8qBuq4B";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static String fileWhereOQSQuotesWillBeSaved = "";
	
	/**
	 * This will run before the test suite starts, helping us set all the things required for running the tests
	 * @param appTheTestsShouldRunOn The application tests will run against
	 * @param urlToBeUsed The url that will be used
	 * @param browserToBeUsed The browser the tests will run on
	 * @throws FileNotFoundException If the configuration file does not exist
	 * @throws IOException If the file cannot be written to or closed
	 * @throws ParseException If the json file cannot be parsed
	 */
	@BeforeClass
	@Parameters({"app","url","browser"})
	public void setup(@Optional("oqs")String appTheTestsShouldRunOn, @Optional("https://test-1.ibnx-hse.com/")String urlToBeUsed, @Optional("chrome")String browserToBeUsed) throws FileNotFoundException, IOException, ParseException
	{	
		PropertyConfigurator.configure("log4j.properties");
		app = appTheTestsShouldRunOn;
		url = urlToBeUsed;
		browser = browserToBeUsed.toLowerCase();
		Reporter.log("This is the environment: " + "\n" + "\"" + url + "\"" + "\n",true);
		driver = getDriver(browser);
		wait = waitTime();
		ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
		jse = (JavascriptExecutor)driver;
		ngWebDriver.waitForAngularRequestsToFinish();
		advancedActions = new Actions(driver);
	}
	
	/**
	 * This method will get us the required browser instance we want to run our tests on
	 * @param browser The browser you want to run the tests on - chrome, firefox, ie, phantomjs
	 * @return Will return reference variable driver which is an instance of a class(FireFoxDriver,ChromeDriver,MicrosoftWebDriver, phantomJSDriver, RemoteWebDriver)
	 * that implements the WebDriver interface
	 */
	public WebDriver getDriver(String browser)
	{
		WebDriver driver = null;
		Capabilities caps = new DesiredCapabilities();
		if(app.toLowerCase().equals("oqs")){
			fileWhereOQSQuotesWillBeSaved = currentDirectory + "\\quotes\\" + browser + "_" + sdf.format(date) + "_" +  sdf2.format(date);
			genericHelp.createFolder(fileWhereOQSQuotesWillBeSaved);
		}
		
		switch(browser.toLowerCase())
		{
			case "chrome":
				System.setProperty("webdriver.chrome.driver", currentDirectory + "/lib/chromedriver.exe");
				if(app.toLowerCase().equals("oqs")){
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", fileWhereOQSQuotesWillBeSaved);
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", chromePrefs);
					caps = new DesiredCapabilities();
					caps = DesiredCapabilities.chrome();
					((DesiredCapabilities) caps).setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					((DesiredCapabilities) caps).setCapability(ChromeOptions.CAPABILITY, options);
					driver = new ChromeDriver(caps);
				}
				else{
					driver = new ChromeDriver();
				}
				break;
			case "firefox": 
				System.setProperty("webdriver.gecko.driver", currentDirectory + "/lib/geckodriver.exe");
				caps = new DesiredCapabilities();
				caps=DesiredCapabilities.firefox();
				if(app.toLowerCase().equals("oqs")){
					FirefoxProfile profile = new FirefoxProfile();
			        profile.setPreference("browser.download.folderList", 2);
			        profile.setPreference("browser.download.dir", fileWhereOQSQuotesWillBeSaved);
			        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			        profile.setPreference("pdfjs.disabled", true);
				    ((DesiredCapabilities) caps).setCapability(FirefoxDriver.PROFILE, profile);
				}
					((DesiredCapabilities) caps).setCapability("marionette", true);
					driver = new FirefoxDriver(caps);
				break;
			case "ie edge":
				System.setProperty("webdriver.edge.driver", currentDirectory + "/lib/MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				break;
			case "phantomjs":
				caps = new DesiredCapabilities();
                ((DesiredCapabilities) caps).setJavascriptEnabled(true);                
                ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);  
                ((DesiredCapabilities) caps).setCapability(
                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        currentDirectory + "/lib/phantomjs.exe");
                ((DesiredCapabilities) caps).setCapability(
                		PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[]{"--ignore-ssl-errors=yes", "--ssl-protocol=TLSv1", "--webdriver-loglevel=NONE"});
                driver = new  PhantomJSDriver(caps);
                break;
			case "browserstack":
				DesiredCapabilities dcaps = new DesiredCapabilities();
				dcaps.setCapability("browser", "Chrome");
				dcaps.setCapability("browser_version", "52.0");
				dcaps.setCapability("os", "Windows");
				dcaps.setCapability("os_version", "10");
			    dcaps.setCapability("browserstack.debug", "true");
			    dcaps.setCapability("acceptSslCerts", "true");
			try {
				driver = new RemoteWebDriver(new URL(URL), dcaps);
			} catch (MalformedURLException e) {
				Assert.fail(e.toString());
			}

		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}
	
	/**
	 * This method defines the wait time we will use across tests
	 * @return Returns the WebDriverWait instance
	 */
	public WebDriverWait waitTime()
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		return wait;
	}
	
	/**
	 * For phantomjs, after every test, will take a screenshot if  test fails and also will delete cookies and refresh after every test
	 * @param testResult result of the test	
	 * @throws IOException thrown if unable to capture and copy the screenshot
	 */
	@AfterMethod
	public void takeScreenShotOnFailureAndRefresh(ITestResult testResult) throws IOException 
	{
		if(testResult.getStatus() == ITestResult.FAILURE){
			try{
				String fileName = currentDirectory + "/screenshots/" + app + "_" + browser + "_" + sdf.format(date) + "/";
				File file = new File(fileName);
				if(!file.exists()){
				new File(fileName).mkdirs();
				}
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(fileName + testResult.getMethod().getMethodName() + "_" + sdf2.format(date) + ".png"));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		driver.manage().deleteAllCookies();
		if(browser.contains("edge")){
			driver.get(url);
		}
		else{
			driver.navigate().refresh();
		}
	}
	
	/**
	 * This method runs after all the tests in a particular class are completed
	 */
	@AfterClass
	public void closeDriver()
	{
		driver.quit();
	}
	
}


