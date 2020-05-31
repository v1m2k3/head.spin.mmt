package com.hackathon.qa.tests;

import com.hackathon.qa.Constants;
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
    }

    @AfterClass
    public void close() {
        this.browser.quit();
    }
}
