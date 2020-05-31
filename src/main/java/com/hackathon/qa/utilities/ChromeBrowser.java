package com.hackathon.qa.utilities;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser extends Browser {

    static {
        System.setProperty("webdriver.chrome.driver", "D:/ws/head.spin.mmt/chromedriver.exe");
    }

    public ChromeBrowser(){
        super(new ChromeDriver());
    }

}

