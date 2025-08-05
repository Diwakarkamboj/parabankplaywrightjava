package com.qa.parabank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.qa.parabank.abstractclass.AbstractBase;

public class LoginPage extends AbstractBase {

	private Locator loginBtn;
	private Locator usernameInput;
	private Locator passwordInput;

	public LoginPage(Page page) {
		super(page);
		this.usernameInput = page.locator("input[name='username']");
		this.passwordInput = page.locator("input[name='password']");
		this.loginBtn = page.locator("//input[@value='Log In']");
	}

	public void login(String username, String password) {
		usernameInput.fill(username);
		passwordInput.fill(password);
		loginBtn.click();
	}

	@Override
	public boolean isAt() {
		loginBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return loginBtn.isVisible();
	}
}
