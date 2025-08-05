package com.qa.parabank.abstractclass;

import com.microsoft.playwright.Page;

public abstract class AbstractBase {


	 public AbstractBase(Page page) {
		this.page = page;
	}

	 protected final Page page;

	 public abstract boolean isAt();





}
