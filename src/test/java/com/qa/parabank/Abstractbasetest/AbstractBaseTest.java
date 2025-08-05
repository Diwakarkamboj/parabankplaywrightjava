package com.qa.parabank.Abstractbasetest;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import listenerfile.Listener;
import parabankTestData.ParaBankTestData;
import utils.Config;
import utils.Constants;
import utils.JsonUtil;

@Listeners({ Listener.class })
public class AbstractBaseTest {

	protected static Playwright playwright;
	protected static Browser browser;
	BrowserContext browserContext;
	protected static Page page;
	protected ParaBankTestData testData;

	@BeforeClass
	@Parameters("testDataPath")
	public void setup(String testDataPath, ITestContext context) {
		Config.initialized();
		playwright = Playwright.create();

		if (Constants.CHROME.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		} else if (Constants.CHROMIUM.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		} else if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
		}

		browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
		page = browserContext.newPage();
		page.navigate(Config.get(Constants.PARABANK_URL));
		context.setAttribute("page", page);

		this.testData = JsonUtil.getTestData(testDataPath, ParaBankTestData.class);
	}

	@AfterClass
	public void tearDownClass() {
		if (browser != null) {
			browser.close();
		}
		if (playwright != null) {
			playwright.close();
		}
	}

}
