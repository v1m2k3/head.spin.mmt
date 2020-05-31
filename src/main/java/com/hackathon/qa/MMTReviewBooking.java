package com.hackathon.qa;

import com.hackathon.qa.utilities.Browser;

public class MMTReviewBooking {
    private final Browser browser;

    public MMTReviewBooking(Browser aBrowser) {
        this.browser = aBrowser;
    }

    public void clickToSelectPayEntire() {
        this.browser.clickWebElementByCSS("label[for=payEntire]");
    }

    public void fillFirstName(String aFirstName) {
        this.browser.fillTextById("fName", aFirstName);
    }

    public void fillLastName(String aLastName){
        this.browser.fillTextById("lName", aLastName);
    }

    public void closeBlockingModal() {
        this.browser.clickWebElementByCSS("[class='_Modal modalCont'] [class=close]");
    }

    public void selectTwoSpecialRequests(){
        this.browser.clickWebElementByCSS("label[for='101']");
        this.browser.clickWebElementByCSS("label[for='102']");
    }

    public void unCheckDonation() {
        this.browser.clickWebElementByCSS("[for='donation']");
    }

    public void clickPayNow() {
        this.browser.clickWebElementByCSS("a[class$='btnPayNow']");
    }
}
