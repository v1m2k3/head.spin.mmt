package com.hackathon.qa.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public WebElement fillTextById(String aId, String aSendKeys) {
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
        this.getWebElementByCssSelector(aCssSelector, aTimeOutInSeconds).click();
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
}
