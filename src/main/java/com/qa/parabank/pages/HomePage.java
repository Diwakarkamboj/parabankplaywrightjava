package com.qa.parabank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.qa.parabank.abstractclass.AbstractBase;

public class HomePage extends AbstractBase {

	private Locator loginTxt;
	private Locator regstrBtn;

	public HomePage(Page page) {
		super(page);
		this.loginTxt = page.locator("//h2[text()='Customer Login']");
		this.regstrBtn = page.locator("//a[text()='Register']");
	}


	@Override
	public boolean isAt() {
		loginTxt.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return loginTxt.isVisible();
	}

	public void clickRegister() {
		regstrBtn.click();
	}

	public boolean isLoggedIn() {
		return page.isVisible("text=Welcome");
	}

	public boolean isNavigationWorking() {
		page.click("text=Accounts Overview");
		return page.url().contains("overview.htm");
	}

}
