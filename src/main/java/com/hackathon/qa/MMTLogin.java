package com.hackathon.qa;

import com.hackathon.qa.utilities.Browser;

public class MMTLogin {

    Browser browser;

    public MMTLogin(Browser aBrowser){
        this.browser = aBrowser;
    }

    public void doLogin(String aUserName, String aPassword){
        this.clickOnloginOrCreateAccountElement();
        this.sendKeysToUsername(aUserName);
        this.clickOnContinueButtonOnUserNameDialog();
        this.sendKeysToPassword(aPassword);
        this.clickLoginButton();
    }

    private void clickLoginButton() {
        this.browser.clickSubmitForElementByCSS("button[data-cy$='login'");
    }

    private void clickOnContinueButtonOnUserNameDialog() {
        this.browser.clickSubmitForElementByCSS("button[data-cy$='continueBtn']");
    }

    private void sendKeysToUsername(String aUserName) {
        this.browser.fillTextById(Constants.USERNAME_ID, aUserName);
    }

    private void sendKeysToPassword(String aPassword) {
        this.browser.fillTextById(Constants.PASSWORD_ID, aPassword);
    }

    private void clickOnloginOrCreateAccountElement() {
        this.browser.clickWebElementByCSS("li[data-cy$='account']");
    }
}