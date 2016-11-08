package com.distriread.autotests.helpers;

import com.distriread.autotests.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by nikita on 07.11.16.
 */
public class UrlSetter {

    private WebDriver driver;

    public UrlSetter(FirefoxDriver driver) {
        this.driver = driver;
    }

    public void setUrl(String reqURL) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(reqURL);
    }

}
