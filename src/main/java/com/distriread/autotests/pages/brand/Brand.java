package com.distriread.autotests.pages.brand;

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
public class Brand {

    private WebDriver driver;
    protected Waiter waiter;
    protected InfoGetter infoGetter;
    protected InputStream inputStream;
    private final String PROP_NAME = "brand.properties";
    protected PropertyReader prop;


    public Brand(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.infoGetter = new InfoGetter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
    }



    public void resetSearching() {
        waiter.waitClickable(prop.xP_Val("br.searchButtonBrandsPage")).click();
        driver.findElement(prop.xP_Val("br.resetButton")).click();
    }



    public void searchSomething(String propIem, String reqValue) {
        waiter.waitClickable(prop.xP_Val("br.searchButtonBrandsPage")).click();
        driver.findElement(prop.xP_Val(propIem)).clear();
        driver.findElement(prop.xP_Val(propIem)).sendKeys(reqValue);
        driver.findElement(prop.xP_Val("br.searchButtonOnSearchGrid")).click();
    }



    public void searchingByBrandId(String id) {
        searchSomething("br.idInput", id);
    }



    public void searchingByName(String brandName) {
        searchSomething("br.nameInput", brandName);
    }



    public void searchingByUpdate(String updateDate_YMD) {
        searchSomething("br.updateInput", updateDate_YMD);
    }



    public void deleteBrand() {
        driver.findElement(prop.xP_Val("br.deleteButton")).click();
        driver.findElement(prop.xP_Val("br.applyDeleteButton")).click();
        waiter.waitToVisible(prop.xP_Val("br.deleteNotification"), "Brand was successfully deleted.");
    }



    public String getIdFromPage() {
        return driver.findElement(prop.xP_Val("br.idResultAfterSearch")).getText();
    }



    public String getBrandNameFromPage() {
        return driver.findElement(prop.xP_Val("br.nameResultAfterSearch")).getText();
    }



    public List<String> getUpdateFromPage() {
        return infoGetter.getStrigRequiredWord(prop.xP_Val("br.updateResultAfterSearch"));
    }



    public String getNotificationAfterCreate() {
        return driver.findElement(prop.xP_Val("br.createBrandNotification")).getText();
    }



    public String getNotificationAfterDelete() {
        return driver.findElement(prop.xP_Val("br.deleteNotification")).getText();
    }



    public String getNoResultsFound() {
        return driver.findElement(prop.xP_Val("br.noResultsFound")).getText();
    }


}
