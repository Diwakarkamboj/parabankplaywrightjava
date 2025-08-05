package com.qa.parabank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.qa.parabank.abstractclass.AbstractBase;

public class RegisterPage extends AbstractBase {

	private Locator signUpTxt;
	private Locator firstNameInput;
	private Locator lastNameInput;
	private Locator streetInput;
	private Locator cityInput;
	private Locator stateInput;
	private Locator zipCodeInput;
	private Locator phoneNumberInput;
	private Locator ssnInput;
	private Locator usernameInput;
	private Locator passwordInput;
	private Locator repeatedPasswordInput;
	private Locator registerBtn;
	private Locator logoutBtn;

	public RegisterPage(Page page) {
		super(page);
		this.signUpTxt = page.locator("//h1[text()='Signing up is easy!']");
		this.firstNameInput = page.locator("//input[@id='customer.firstName']");
		this.lastNameInput = page.locator("//input[@id='customer.lastName']");
		this.streetInput = page.locator("//input[@id='customer.address.street']");
		this.cityInput = page.locator("//input[@id='customer.address.city']");
		this.stateInput = page.locator("//input[@id='customer.address.state']");
		this.zipCodeInput = page.locator("//input[@id='customer.address.zipCode']");
		this.phoneNumberInput = page.locator("//input[@id='customer.phoneNumber']");
		this.ssnInput = page.locator("//input[@id='customer.ssn']");
		this.usernameInput = page.locator("//input[@id='customer.username']");
		this.passwordInput = page.locator("//input[@id='customer.password']");
		this.repeatedPasswordInput = page.locator("//input[@id='repeatedPassword']");
		this.registerBtn = page.locator("//input[@value='Register']");
		this.logoutBtn = page.locator("//a[text()='Log Out']");
	}

	public void registerUser(String username, String password, String name, String lastName, String streetName, String cityName, String stateName, String zipcode, String ssn, String phonenumber) {
		firstNameInput.fill(name);
		lastNameInput.fill(lastName);
		streetInput.fill(streetName);
		cityInput.fill(cityName);
		stateInput.fill(stateName);
		zipCodeInput.fill(zipcode);
		phoneNumberInput.fill(phonenumber);
		ssnInput.fill(ssn);
		usernameInput.fill(username);
		passwordInput.fill(password);
		repeatedPasswordInput.fill(password);
		registerBtn.click();
		logoutBtn.click();
	}

	@Override
	public boolean isAt() {
		signUpTxt.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return signUpTxt.isVisible();
	}

}
