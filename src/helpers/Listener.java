package helpers;

import org.testng.IInvokedMethod;

import org.testng.IInvokedMethodListener;
 
import org.testng.ISuite;
 
import org.testng.ISuiteListener;
 
import org.testng.ITestContext;
 
import org.testng.ITestListener;
 
import org.testng.ITestNGMethod;
 
import org.testng.ITestResult;
 
import org.testng.Reporter;

/**
 * Listener Class
 * @author Ankit
 *
 */
public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener{

	@Override
	public void onFinish(ISuite arg0) {
		Reporter.log("Completed executing Suite: " + arg0.getName(), true);
		
	}

	@Override
	public void onStart(ISuite arg0) {
		Reporter.log("About to begin executing Suite: " + arg0.getName(), true);
		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		Reporter.log("\nAbout to finish executing Tests: " + arg0.getName() + "\n", true);
	}

	@Override
	public void onStart(ITestContext arg0) {
		Reporter.log("\nAbout to begin executing Tests: " + arg0.getName() + "\n", true);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Reporter.log("The execution of the test starts now", true);
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
		
	}
	
	/**
	 * This will print the parameters used and the test status of the test
	 * @param result
	 */
	private void printTestResults(ITestResult result) {
		 
//		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
 
		if (result.getParameters().length != 0) {
 
			String params = null;
 
			for (Object parameter : result.getParameters()) {
 
				params += parameter.toString() + ",";
 
			}
 
			Reporter.log("Test Method had the following parameters : " + params, true);
 
		}
 
		String status = null;
 
		switch (result.getStatus()) {
 
		case ITestResult.SUCCESS:
 
			status = "Pass";
 
			break;
 
		case ITestResult.FAILURE:
 
			status = "Failed";
 
			break;
 
		case ITestResult.SKIP:
 
			status = "Skipped";
 
		}
 
		Reporter.log("\nTest Status: " + status, true);
		Reporter.log("----------------------------------------", true);
		
	}
 
	/**
	 * This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test
	 */
 
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
 
		String textMsg = "\nAbout to begin executing: " + returnMethodName(arg0.getTestMethod());
 
		Reporter.log(textMsg, true);
 
	}
 
	/**
	 * This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
	 */
 
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
 
		String textMsg = "Completed executing: " + returnMethodName(arg0.getTestMethod());
 
		Reporter.log(textMsg, true);
 
	}
 
	/**
	 * This will return method names to the calling function
	 * @param method The method for which you want to retrieve the name
	 * @return will return the method name
	 */
 
	private String returnMethodName(ITestNGMethod method) {
 
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
 
	}

}
