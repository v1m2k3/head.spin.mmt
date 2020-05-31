package com.hackathon.qa.tests;

import com.hackathon.qa.*;
import com.hackathon.qa.utilities.Browser;
import com.hackathon.qa.utilities.ChromeBrowser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MMTTest {

    Browser browser;

    @BeforeClass
    public void start() {
        this.browser = new ChromeBrowser();
    }

    @Test
    public void hackathonTest() {
        //Open makemytrip page in the browser
        this.browser.openPageWithUrl(Constants.URL);

        //Check if title of the page is as expected.
        String title = this.browser.getDriver().getTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.startsWith("MakeMyTrip"), "Title mismatch...");

        //Login into MMT
        MMTLogin loginPage = new MMTLogin(this.browser);
        loginPage.doLogin(Constants.USERNAME_VALUE, Constants.PASSWORD_VALUE);

        //Click hotels link
        MMTHotelReservation hotelReservation = new MMTHotelReservation(this.browser);
        hotelReservation.selectCity("Manali");
        hotelReservation.selectCheckinDate("Jun 14");
        hotelReservation.selectCheckoutDate("Jun 20");
        hotelReservation.selectRoomsAndGuests(1, "2", "0");
        hotelReservation.selectTravellingForPurpose("Leisure");
        hotelReservation.applySearch();

        //list of hotels are populated, select 5th hotel in the list.
        //print the hotel name
        MMTHotelListing hotelListing = new MMTHotelListing(this.browser);
        hotelListing.setMinimumPriceRange(1000);
        hotelListing.setUserRating();
        String hotelName = hotelListing.selectHotel(5);
        System.out.println("Name of the selected hotel is : " + hotelName);

        //After selecting the hotel a new tab is opened.
        //lets wait for the page to be loaded with all the content.
        this.browser.sleep(10);

        //Close all other tabs but leaving the tab that contains text \"Hotel Details Page\" in the title.
        this.browser.closeOtherTabs("Hotel Details Page");
        String newPageTitle = this.browser.getDriver().getTitle();
        Assert.assertTrue(newPageTitle.contains("Hotel Details Page"), "Unable to set driver to \"Hotel Details Page\" tab." );
        System.out.println(newPageTitle);

        //Navigate to the first Room option
        //Select room
        HotelDetails hotelDetails = new HotelDetails(this.browser);
        hotelDetails.selectFirstRoomOption();

        //Wait for the page to load
        this.browser.sleep(5);

        //Enter traveller details
        MMTReviewBooking reviewBooking = new MMTReviewBooking(this.browser);
        reviewBooking.closeBlockingModal();
        reviewBooking.clickToSelectPayEntire();
        reviewBooking.fillFirstName("Manoj");
        reviewBooking.fillLastName("V");
        reviewBooking.selectTwoSpecialRequests();
        reviewBooking.unCheckDonation();
        reviewBooking.clickPayNow();
    }

    @AfterClass
    public void close() {
        this.browser.quit();
    }
}
