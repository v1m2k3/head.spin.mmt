package com.hackathon.qa.tests;

import com.hackathon.qa.Constants;
import com.hackathon.qa.MMTHotelListing;
import com.hackathon.qa.MMTHotelReservation;
import com.hackathon.qa.MMTLogin;
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
    public void mmtLoginTest() {
        //Open makemytrip page in the browser
        this.browser.openPageWithUrl(Constants.URL);

        //Check if title of the page is as expected.
        String title = this.browser.getDriver().getTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.startsWith("MakeMyTrip"), "Title mismatch...");


        //Login into MMT
        MMTLogin loginPage = new MMTLogin(this.browser);
        loginPage.doLogin(Constants.USERNAME_VALUE, Constants.PASSWORD_VALUE);
    }

    @Test
    public void mmtReservationTest() {
        //Open makemytrip page in the browser
        this.browser.openPageWithUrl(Constants.URL);

        //Check if title of the page is as expected.
        String title = this.browser.getDriver().getTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.startsWith("MakeMyTrip"), "Title mismatch...");

        //Click hotels link
        MMTHotelReservation hotelReservation = new MMTHotelReservation(this.browser);
        hotelReservation.selectCity("Manali");
        hotelReservation.selectCheckinDate("Jun 14");
        hotelReservation.selectCheckoutDate("Jun 20");
        hotelReservation.selectRoomsAndGuests(2, "2", "2");
        hotelReservation.selectTravellingForPurpose("Leisure");
        hotelReservation.applySearch();
    }

    @Test
    public void mmtReservationList() {
        String url = "https://www.makemytrip.com/hotels/hotel-listing/?_uCurrency=INR&checkin=06142020&checkout=06202020&city=CTDEL&country=IN&locusId=CTDEL&locusType=city&roomStayQualifier=2e2e1e1e2e2e1e1e&searchText=Delhi&visitorId=4cefad61-9197-490c-b63f-84430eb15e18";
        //Open makemytrip page in the browser
        this.browser.openPageWithUrl(url);

        MMTHotelListing hotelListing = new MMTHotelListing(this.browser);
        hotelListing.setMinimumPriceRange(1000);
        hotelListing.setUserRating();
        String hotelName = hotelListing.selectHotel(5);
        System.out.println("Name of the selected hotel is : " + hotelName);

    }

    @AfterClass
    public void close() {
        this.browser.quit();
    }
}
