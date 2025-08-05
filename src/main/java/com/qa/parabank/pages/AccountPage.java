package com.qa.parabank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.qa.parabank.abstractclass.AbstractBase;

public class AccountPage extends AbstractBase {

	private Locator accServices;
	private Locator openNewAccClk;
	private Locator acctypeDropDown;
	private Locator openNewAccBtn;
	private Locator newAccID;
	private Locator accOverview;
	private Locator accTable;
	private Locator transferFunds;
	private Locator amountField;
	private Locator fromAccount;
	private Locator toAccount;
	private Locator transferBtn;
	private Locator billPay;
	private Locator payeeName;
	private Locator address;
	private Locator cityName;
	private Locator stateName;
	private Locator zipCode;
	private Locator PhoneNum;
	private Locator accountNum;
	private Locator vfyAccNum;
	private Locator billPayFromAcc;
	private Locator amount;
	private Locator sendPaymentBtn;
	private Locator findTransaction;
	private Locator findByAmt;
	private Locator fundTransferCheck;

	public AccountPage(Page page) {
		super(page);
		this.accServices = page.locator("//h2[text()='Account Services']");
		this.openNewAccClk = page.locator("//a[text()='Open New Account']");
		this.acctypeDropDown = page.locator("//select[@id='type']");
		this.openNewAccBtn = page.locator("//div[@id='openAccountForm']//self::input[@value='Open New Account']");
		this.newAccID = page.locator("//a[@id='newAccountId']");
		this.accOverview = page.locator("//a[text()='Accounts Overview']");
		this.accTable = page.locator("//table[@id='accountTable']");
		this.transferFunds = page.locator("//a[text()='Transfer Funds']");
		this.amountField = page.locator("//input[@id='amount']");
		this.fromAccount = page.locator("//select[@id='fromAccountId']");
		this.toAccount = page.locator("//select[@id='toAccountId']");
		this.transferBtn = page.locator("//input[@value='Transfer']");
		this.billPay = page.locator("//a[text()='Bill Pay']");
		this.payeeName = page.locator("//input[@name='payee.name']");
		this.address = page.locator("//input[@name='payee.address.street']");
		this.cityName = page.locator("//input[@name='payee.address.city']");
		this.stateName = page.locator("//input[@name='payee.address.state']");
		this.zipCode = page.locator("//input[@name='payee.address.zipCode']");
		this.PhoneNum = page.locator("//input[@name='payee.phoneNumber']");
		this.accountNum = page.locator("//input[@name='payee.accountNumber']");
		this.vfyAccNum = page.locator("//input[@name='verifyAccount']");
		this.billPayFromAcc = page.locator("//div[@id='billpayForm']//self::select[@name='fromAccountId']");
		this.amount = page.locator("//input[@name='amount']");
		this.sendPaymentBtn = page.locator("//input[@value='Send Payment']");
		this.findTransaction = page.locator("//a[text()='Find Transactions']");
		this.findByAmt = page.locator("//div[@id='formContainer']//self::button[@id='findByAmount']");
		this.fundTransferCheck = page.locator("//div[@id='resultContainer']//self::a[text()='Funds Transfer Sent']");
	}

	public String openNewSavingsAccount() {
		openNewAccClk.click();
		acctypeDropDown.selectOption(new SelectOption().setLabel("SAVINGS")); // Savings
		fromAccount.selectOption(new SelectOption().setIndex(0));
		openNewAccBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		System.out.println("Is button visible? " + openNewAccBtn.isVisible());
		openNewAccBtn.click(new Locator.ClickOptions().setForce(true));
		newAccID.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return newAccID.innerText();
	}

	public boolean verifyAccountOverviewDisplayed() {
		accOverview.click();
		return accTable.isVisible();
	}

	public void transferFunds(String tAmount, String fromAcc) {
		transferFunds.click();
		System.out.println("Transfer amount is: >" + tAmount + "<");
		amountField.fill("100");
		fromAccount.selectOption(new SelectOption().setLabel(fromAcc));
		toAccount.selectOption(new SelectOption().setIndex(0));
		transferBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		System.out.println("Is button visible? " + transferBtn.isVisible());
		transferBtn.click(new Locator.ClickOptions().setForce(true));
	}

	public void payBill(String accountId, String pName,String address1, String cityName1,String stateName1,String zipcode, String phoneNumber, String accNums1, String vfyAccNums1, String amtPay) {
		billPay.click();
		payeeName.fill(pName);
		address.fill(address1);
		cityName.fill(cityName1);
		stateName.fill(stateName1);
		zipCode.fill(zipcode);
		PhoneNum.fill(phoneNumber);
		accountNum.fill(phoneNumber);
		vfyAccNum.fill(phoneNumber);
		amount.fill(amtPay);
		System.out.println(accountId);
		billPayFromAcc.selectOption(new SelectOption().setLabel(accountId));
		sendPaymentBtn.click();
	}

	public void findTransaction(String amount) {
		findTransaction.click();
		amountField.waitFor();
		amountField.fill("");
		amountField.type(amount, new Locator.TypeOptions().setDelay(100));
		amountField.fill(amount);
		findByAmt.click(new Locator.ClickOptions().setForce(true));
		fundTransferCheck.click();
	}

	@Override
	public boolean isAt() {
		accServices.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		return accServices.isVisible();
	}

}
