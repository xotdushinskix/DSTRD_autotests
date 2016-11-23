package com.distriread.autotests.pages.manufacturer;

import com.distriread.autotests.helpers.InfoGetter;
import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;
import java.util.List;

/**
 * Created by nikita on 08.11.16.
 */
public class Manufacturer {

    private WebDriver driver;
    private Waiter waiter;
    private InfoGetter infoGetter;
    protected InputStream inputStream;
    private final String PROP_NAME = "manufacturer.properties";
    private PropertyReader prop;


    public Manufacturer(FirefoxDriver driver) {
        this.driver = driver;
        this.prop = new PropertyReader(inputStream, PROP_NAME);
        this.waiter = new Waiter(driver);
        this.infoGetter = new InfoGetter(driver);

    }



    public void resetSearching() {
        waiter.waitClickable(prop.xP_Val("manuf.searchButtonManufac")).click();
        driver.findElement(prop.xP_Val("manuf.resetButton")).click();
    }



    public void searchingByManufacturerId(int manufacturerId) {
        waiter.waitClickable(prop.xP_Val("manuf.searchButtonManufac")).click();
        driver.findElement(prop.xP_Val("manuf.idInput")).sendKeys(String.valueOf(manufacturerId));
        driver.findElement(prop.xP_Val("manuf.searchButtonOnSearchGird")).click();
    }



    public int getManufacturerId() {
        return Integer.valueOf(driver.findElement(prop.xP_Val("manuf.idAfterSearch")).getText());
    }



    public void searchingByManufacturerName(String manufacturerName) {
        waiter.waitClickable(prop.xP_Val("manuf.searchButtonManufac")).click();
        driver.findElement(prop.xP_Val("manuf.manufNameInput")).clear();
        driver.findElement(prop.xP_Val("manuf.manufNameInput")).sendKeys(manufacturerName);
        driver.findElement(prop.xP_Val("manuf.searchButtonOnSearchGird")).click();
    }



    public String getManufacturerName() {
        return driver.findElement(prop.xP_Val("manuf.manufNameAfterSearch")).getText();
    }



    public void searchingByUpdate(String updateYMD) {
        waiter.waitClickable(prop.xP_Val("manuf.searchButtonManufac")).click();
        driver.findElement(prop.xP_Val("manuf.updateInput")).sendKeys(updateYMD);
        driver.findElement(prop.xP_Val("manuf.searchButtonOnSearchGird")).click();
    }



    public List<String> getUpdateDates() {
        return infoGetter.getStrigRequiredWord(prop.xP_Val("manuf.updatesAfterSearch"));
    }



    public String getNotificationAfterCreate() {
        return driver.findElement(prop.xP_Val("manuf.notifAfterCreate")).getText();
    }



    public void deleteManufacturerAfterCreate() {
        //searchingByManufacturerName(manufacturerName);
        waiter.waitClickable(prop.xP_Val("manuf.deleteButton")).click();
        driver.findElement(prop.xP_Val("manuf.applyDeleteButton")).click();
        waiter.waitToVisible(prop.xP_Val("manuf.notifAfterDelete"), "Manufacturer was successfully deleted.");
    }



    public void deleteManufacturer(String manufacturerName) {
        searchingByManufacturerName(manufacturerName);
        waiter.waitClickable(prop.xP_Val("manuf.deleteButton")).click();
        driver.findElement(prop.xP_Val("manuf.applyDeleteButton")).click();
        waiter.waitToVisible(prop.xP_Val("manuf.notifAfterDelete"), "Manufacturer was successfully deleted.");
    }



    public String getNotificationAfterDelete() {
        return driver.findElement(prop.xP_Val("manuf.notifAfterDelete")).getText();
    }



    public String noResFoundAfterDelete() {
        return driver.findElement(prop.xP_Val("manuf.noResultFound")).getText();
    }

}
