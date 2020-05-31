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
        this.browser.fillTextById("city", aCity);
    }
}
