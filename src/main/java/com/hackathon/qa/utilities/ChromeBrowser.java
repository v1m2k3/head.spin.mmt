package com.hackathon.qa.utilities;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser extends Browser {

    static {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
    }

    public ChromeBrowser(){
        super(new ChromeDriver());
    }

}

