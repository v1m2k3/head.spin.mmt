package com.hackathon.qa;

import com.hackathon.qa.utilities.Browser;

public class MMTHotelReservation {

    private final Browser browser;

    public MMTHotelReservation(Browser aBrowser) {
        this.browser = aBrowser;
        this.navigateToHotels();
    }

    private void navigateToHotels() {
        this.clickWebElementByCSS("li[data-cy$='menu_Hotels']");
    }

    public void selectCity(String aCity) {
        this.browser.clickWebElementById("city");
        this.browser.fillTextByCSSFollowedByEnterKey("input[placeholder^='Enter city']", aCity);
        this.browser.sleep(5);
        this.browser.clickWebElementById("react-autowhatever-1-section-0-item-0");
    }

    public void selectCheckinDate(String aCheckinDate) {
        this.browser.clickWebElementById("checkin");
        this.clickWebElementByCSS("div[class*='DayPicker-Day'][aria-label*='" + aCheckinDate + "']");
    }

    public void selectCheckoutDate(String aCheckoutDate) {
        this.browser.clickWebElementById("checkin");
        this.clickWebElementByCSS("div[class*='DayPicker-Day'][aria-label*='" + aCheckoutDate + "']");
    }

    public void selectRoomsAndGuests(int aNumberOfRooms, String aNumberOfAdultsPerRoom, String aNumberOfChildernPerRoom) {
        this.browser.clickWebElementById("guest");
        int count = 0;
        do {
            //Not required when doing for first room.
            if (count != 0) {
                this.addRoom();
            }
            addGuestsToRoom(aNumberOfAdultsPerRoom, aNumberOfChildernPerRoom);
            ++count;
        } while (count < aNumberOfRooms);

        this.submitRoomsAndGuests();
    }

    private void submitRoomsAndGuests() {
        this.clickWebElementByCSS("button[data-cy='submitGuest']");
    }

    private void addRoom() {
        this.clickWebElementByCSS("button[data-cy='addAnotherRoom']");
    }

    private void clickWebElementByCSS(String s) {
        this.browser.clickWebElementByCSS(s);
    }

    private void addGuestsToRoom(String aNumberOfAdultsPerRoom, String aNumberOfChildrenPerRoom) {
        this.clickWebElementByCSS("li[data-cy='adults-" + aNumberOfAdultsPerRoom + "']");
        this.clickWebElementByCSS("li[data-cy='children-" + aNumberOfChildrenPerRoom + "']");
    }
}
