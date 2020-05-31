package com.hackathon.qa;

import com.hackathon.qa.utilities.Browser;

public class MMTHotelReservation {

    private final Browser browser;

    public MMTHotelReservation(Browser aBrowser) {
        this.browser = aBrowser;
        this.navigateToHotels();
    }

    private void navigateToHotels() {
        this.browser.clickWebElementByCSS("li[data-cy$='menu_Hotels']");
    }

    public void selectCity(String aCity) {
        this.browser.clickWebElementById("city");
        this.browser.fillTextByCSSFollowedByEnterKey("input[placeholder^='Enter city']", aCity);
        this.browser.clickWebElementById("react-autowhatever-1-section-0-item-0");
    }

    public void selectCheckinDate(String aCheckinDate) {
        this.browser.clickWebElementById("checkin");
        this.browser.clickWebElementByCSS("div[class*='DayPicker-Day'][aria-label*='" + aCheckinDate + "']");
    }

    public void selectCheckoutDate(String aCheckoutDate) {
        this.browser.clickWebElementById("checkin");
        this.browser.clickWebElementByCSS("div[class*='DayPicker-Day'][aria-label*='" + aCheckoutDate + "']");
    }
}
