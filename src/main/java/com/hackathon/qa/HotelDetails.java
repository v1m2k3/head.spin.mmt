package com.hackathon.qa;

import com.hackathon.qa.utilities.Browser;

public class HotelDetails {
    private final Browser browser;

    public HotelDetails(Browser aBrowser){
        this.browser = aBrowser;
        this.navigateToRoomSection();
    }

    private void navigateToRoomSection() {
        this.browser.scrollToWebElement("div[class='aboutProp']");
    }

    public void selectFirstRoomOption(){
        this.browser.clickWebElementByCSS("div[class=roomWrap] [class*=primaryBtn]");
    }
}
