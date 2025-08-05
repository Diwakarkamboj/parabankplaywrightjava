package listenerfile;


import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.microsoft.playwright.Page;





public class Listener implements ITestListener{

	  @Override
	    public void onTestFailure(ITestResult result) {
	        Page page = (Page) result.getTestContext().getAttribute("page");

	        if (page != null) {
	            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
	            String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshot);

	            String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
	            String htmlImage = String.format(htmlImageFormat, base64Screenshot);

	            Reporter.log("Test Failed: " + result.getName(), true);
	            Reporter.log(htmlImage, true);
	        } else {
	            Reporter.log("Page not found in context to take screenshot", true);
	        }
	    }



}
