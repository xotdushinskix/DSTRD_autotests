package com.distriread.autotests.pages.login;

import com.distriread.autotests.helpers.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;

/**
 * Created by nikita on 07.11.16.
 */
public class Login {

    private WebDriver driver;
    private PropertyReader prop;
    protected InputStream inputStream;
    private final String PROP_NAME = "login.properties";

    public Login(FirefoxDriver driver) {
        this.driver = driver;
        this.prop = new PropertyReader(inputStream, PROP_NAME);
    }



    public void loginFunc() {
        driver.findElement(prop.xP_Val("login.input")).sendKeys(prop.str_Val("login.name"));
        driver.findElement(prop.xP_Val("password.input")).sendKeys(prop.str_Val("password.name"));
        driver.findElement((prop.xP_Val("login.button"))).click();
    }

}
