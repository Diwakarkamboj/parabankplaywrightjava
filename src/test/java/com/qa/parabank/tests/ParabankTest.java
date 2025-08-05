package com.qa.parabank.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.parabank.Abstractbasetest.AbstractBaseTest;
import com.qa.parabank.pages.AccountPage;
import com.qa.parabank.pages.HomePage;
import com.qa.parabank.pages.LoginPage;
import com.qa.parabank.pages.RegisterPage;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utils.Config;
import utils.Constants;
import utils.TestUtils;

public class ParabankTest extends AbstractBaseTest {

	 private String latestAccountId;
	 private String latestTransactionId;



	@Test(priority = 1)
	public void endToEndTest() {
		String username = TestUtils.generateRandomUsername();
		String password = testData.password();

		HomePage homePage = new HomePage(page);
		homePage.isAt();
		homePage.clickRegister();

		RegisterPage registerPage = new RegisterPage(page);
		registerPage.isAt();
		page.waitForTimeout(2000); // waits for 2 seconds
		registerPage.registerUser(username, password, testData.firstName(), testData.lastName(), testData.streetName(), testData.cityName(), testData.stateName(), testData.zipCode(), testData.ssn(), testData.phoneNumber());

		LoginPage loginPage = new LoginPage(page);
		loginPage.isAt();
		loginPage.login(username, password);

		Assert.assertTrue(homePage.isLoggedIn(), "Login failed!");
		Assert.assertTrue(homePage.isNavigationWorking(), "Navigation menu not working!");

		AccountPage accountPage = new AccountPage(page);
		accountPage.isAt();
		latestAccountId = accountPage.openNewSavingsAccount();
		Assert.assertNotNull(latestAccountId, "Account creation failed!");

		Assert.assertTrue(accountPage.verifyAccountOverviewDisplayed(), "Account overview not displayed!");

		accountPage.transferFunds(testData.transferFundsAmount(), latestAccountId);
		System.out.println(testData.transferFundsAmount()+"amount check");
		page.waitForTimeout(2000); // waits for 2 seconds
		System.out.println(testData.accNumber()+ "frm test");
		accountPage.payBill(latestAccountId,testData.firstName(), testData.streetName(), testData.cityName(), testData.stateName(), testData.zipCode(),testData.phoneNumber(),testData.accNumber(),testData.vfyAccNumber(),testData.transferFundsAmount());
	}

	 @Test(priority = 2, dependsOnMethods = "endToEndTest")
	    public void validateTransactionByAPI() {
		 AccountPage accountPage = new AccountPage(page);
		 accountPage.findTransaction(testData.findTransactionAmount());

		  String currentUrl = page.url();
	        if (currentUrl.contains("transaction.htm?id=")) {
	            latestTransactionId = currentUrl.split("id=")[1];
	        }

	        Response response =
	            given()
	            .when()
	                .get(Config.get(Constants.PARABANK_API) + latestTransactionId)
	            .then()
	                .statusCode(200)
	                .body("transaction[0].type", equalTo("Debit"))
	                .extract().response();

	        XmlPath xmlPath = new XmlPath(response.asString());

	        String type = xmlPath.getString("transaction.type");
	        String amount = xmlPath.getString("transaction.amount");
	        Assert.assertEquals(type, "Debit");
	        System.out.println(amount);
	        //Assert.assertEquals(amount, testData.findTransactionAmount());
	        System.out.println("\nAPI Response:\n" + response.asPrettyString());
	    }

}
