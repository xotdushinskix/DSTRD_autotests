package com.distriread.autotests.config;

import com.distriread.autotests.pages.login.Login;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

/**
 * Created by nikita on 07.11.16.
 */
public class WebDriverConfig {

    protected FirefoxDriver driver;


    @Before
    public void setUp() throws IOException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://dev:q1w2e3r4@vr1.bintime.com/");
        Login login = new Login(driver);
        login.loginFunc();
    }



    @After
    public void tearDown() {
        driver.close();
    }

}
