package com.hackathon.qa;

import com.hackathon.qa.utilities.Browser;

public class MMTHotelListing {
    public static final String DIV_CLASS_MM_BACKDROP_WHOLE_BLACK = "div[class='mmBackdrop wholeBlack']";
    Browser browser;

    public MMTHotelListing(Browser aBrowser) {
        this.browser = aBrowser;
        this.clickModal();
    }

    private void clickModal() {
        if (this.browser.isElementAvailableByCss(DIV_CLASS_MM_BACKDROP_WHOLE_BLACK)) {
            this.browser.clickWebElementByCSS(DIV_CLASS_MM_BACKDROP_WHOLE_BLACK);
        }
    }

    public void setMinimumPriceRange(int aAmount){
        //3.5 Offset leads to 500Rs.
        int xOffset = (int) (aAmount/500 * 3.5);
        this.browser.slideWebElementCssSelector("span[class*='input-range__slider']", xOffset, 0);
    }

    //Select second element from the list of check boxes
    public  void setUserRating(){
        this.browser.clickWebElementByCSS("#hlistpg_fr_user_rating > ul > li:nth-child(2) > span.checkmarkOuter", 1);
    }
}
