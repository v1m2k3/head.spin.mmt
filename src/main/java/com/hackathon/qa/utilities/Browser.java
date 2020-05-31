package com.hackathon.qa.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Browser {

    private final WebDriver driver;

    public Browser(WebDriver aDriver) {
        this.driver = aDriver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void openPageWithUrl(String aUrl) {
        this.driver.manage().window().maximize();
        this.driver.get(aUrl);
    }

    public void fillTextById(String aId, String aSendKeys) {
        this.getWebElementById(aId).sendKeys(aSendKeys);
    }

    public WebElement clearAndFillTextById(String aId, String aSendKeys) {
        WebElement webElement = this.getWebElementById(aId);
        webElement.clear();
        webElement.sendKeys(aSendKeys);
        return webElement;
    }

    private WebElement getWebElementById(String aId) {
        // Tell webdriver to wait
        WebDriverWait wait = new WebDriverWait(this.driver, 4);

        // Explicit Wait
        WebElement webElement = wait.until(webDriver -> webDriver.findElement(By.id(aId)));
        return webElement;
    }

    public void clickWebElementById(String aId) {
        this.getWebElementById(aId).click();
    }

    private WebElement getWebElementByCssSelector(String aCssSelector, long aTimeOutInSeconds) {
        // Tell webdriver to wait
        WebDriverWait wait = new WebDriverWait(this.driver, aTimeOutInSeconds);

        // Explicit Wait
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(aCssSelector)));
        return webElement;
    }

    public void clickWebElementByCSS(String aCssSelector) {
        this.clickWebElementByCSS(aCssSelector, 5);
    }

    public void clickSubmitForElementByCSS(String aCssSelector) {
        this.getWebElementByCssSelector(aCssSelector, 5).submit();
    }

    public void clickWebElementByCSS(String aCssSelector, int aTimeOutInSeconds) {
        WebElement webElement = this.getWebElementByCssSelector(aCssSelector, aTimeOutInSeconds);
        webElement.click();
    }

    public void implicitWait(long aTimeOutInSeconds) {
        this.driver.manage().timeouts().implicitlyWait(aTimeOutInSeconds, TimeUnit.SECONDS);
    }

    public void sleep(long aTimeOutInSeconds) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void quit() {
        this.driver.quit();
    }

    public WebElement fillTextByCSS(String aCssSelector, String aSendKeys) {
        WebElement webElement = this.getWebElementByCssSelector(aCssSelector, 2);
        webElement.sendKeys(aSendKeys);
        return webElement;
    }

    public void fillTextByCSSFollowedByEnterKey(String aCssSelector, String aSendKeys) {
        this.fillTextByCSS(aCssSelector, aSendKeys).sendKeys(Keys.ENTER);
    }

    public void waitUntilWebElementIsInvisible(String aCssSelector) {
        this.waitUntilWebElementIsInvisible(this.getWebElementByCssSelector(aCssSelector, 1));
    }

    private void waitUntilWebElementIsInvisible(WebElement aWebElement) {
        WebDriverWait wait = new WebDriverWait(this.driver, 60);

        wait.until(ExpectedConditions.invisibilityOf(aWebElement));
    }

    public boolean isElementAvailableByCss(String aCssSelector) {
        return this.getWebElementByCssSelector(aCssSelector, 1) != null;
    }

    public void slideWebElementCssSelector(String aCssSelector, int aXOffset, int aYOffset){
        WebElement slider = this.getWebElementByCssSelector(aCssSelector,0);

        Actions sliderAction = new Actions(driver);
        sliderAction.clickAndHold(slider)
                .moveByOffset(aXOffset, aYOffset)
                .release().perform();
    }

    public void scrollToBottomOfThePage(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        //This will scroll the web page till end.
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public String getHotelName(String aHotelId){
        WebElement webElement = this.getWebElementById(aHotelId);
        webElement = webElement.findElement(By.cssSelector("span[id^='htl_id_seo']"));
        return webElement.getText();

    }

    public void scrollToWebElement(String aCssSelector) {
        WebElement webElement = getWebElementByCssSelector(aCssSelector, 1);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void closeOtherTabs(String aPartialTitle){
        List<String> tabHandles = new ArrayList<String>(this.driver.getWindowHandles());
        for(String tabHandle : tabHandles){

            this.driver.switchTo().window(tabHandle);

            // Check Your Page Title
            if(!driver.getTitle().contains(aPartialTitle))
            {
                //Close the active/current tab
                driver.close();
            }
        }
    }
}
