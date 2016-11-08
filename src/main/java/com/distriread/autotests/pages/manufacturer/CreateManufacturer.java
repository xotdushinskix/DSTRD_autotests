package com.distriread.autotests.pages.manufacturer;

import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;

/**
 * Created by nikita on 08.11.16.
 */
public class CreateManufacturer {

    private WebDriver driver;
    private Waiter waiter;
    protected InputStream inputStream;
    private PropertyReader prop;
    private final String PROP_NAME = "create_manufacturer.properties";


    public CreateManufacturer(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
    }



    public void fillFieldAndSaveNewManufacturer(String manufacturerName, String description) {
        driver.findElement(prop.xP_Val("cr.manuf.nameInput")).sendKeys(manufacturerName);
        driver.findElement(prop.xP_Val("cr.manuf.descripField")).sendKeys(description);
        driver.findElement(prop.xP_Val("cr.manuf.saveButton")).click();
        waiter.waitToVisible(prop.xP_Val("cr.manuf.afterCreateNotif"), "Manufacturer was successfully saved.");
    }

}
