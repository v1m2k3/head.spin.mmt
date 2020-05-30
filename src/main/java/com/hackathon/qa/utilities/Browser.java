package com.hackathon.qa.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Browser {

    private  WebDriver driver;

    public Browser(WebDriver aDriver){
        this.driver = aDriver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void openPageWithUrl(String aUrl){
        this.driver.get(aUrl);
    }

    public WebElement fillTextById(String aId, String aSendKeys){

        WebElement webElement = this.getWebElementById(aId);
        webElement.clear();
        webElement.sendKeys(aSendKeys);
        return webElement;
    }

    private WebElement getWebElementById(String aId){
        // Tell webdriver to wait
        WebDriverWait wait = new WebDriverWait(this.driver, 4);

        // Explicit Wait
        WebElement webElement = wait.until(webDriver -> webDriver.findElement(By.id(aId)));
        return webElement;
    }

    public void clickWebElementByCSS(String aCssSelector){
        WebElement webElement = this.driver.findElement(By.cssSelector(aCssSelector));
        webElement.click();
    }

    public void clickSubmitForElementByCSS(String aSelector){
        WebElement webElement = this.driver.findElement(By.cssSelector(aSelector));
        webElement.submit();
    }

    public void quit(){
        this.driver.quit();
    }
}
