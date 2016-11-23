package com.distriread.autotests.pages.bundle;

import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Searcher;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;

/**
 * Created by nikita on 22.11.16.
 */
public class CreateBundle {

    private WebDriver driver;
    protected Waiter waiter;
    protected InputStream inputStream;
    protected PropertyReader prop;
    protected Searcher searcher;
    private final String PROP_NAME = "create_bundle.properties";


    public CreateBundle(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
        this.searcher = new Searcher(driver);
    }




    public void createBundleFirstPart(String MPN, String bundlingCost, String fullTitle, String shortDescrip,
                             String summary, String skuID) {
        searcher.waitClickable(prop.xP_Val("cr.bundle.saveButton"));

        driver.findElement(prop.xP_Val("cr.bundl.mpnField")).sendKeys(MPN);
        driver.findElement(prop.xP_Val("cr.bundl.bundlCost")).sendKeys(bundlingCost);
        driver.findElement(prop.xP_Val("cr.bundl.activeCheckBox")).click();
        driver.findElement(prop.xP_Val("cr.bundl.fullTitle")).sendKeys(fullTitle);
        driver.findElement(prop.xP_Val("cr.bundl.shortDescr")).sendKeys(shortDescrip);
        driver.findElement(prop.xP_Val("cr.bundl.summary")).sendKeys(summary);

        driver.findElement(prop.xP_Val("cr.bundl.addMainProductButton")).click();
        searcher.searchInput(prop.xP_Val("cr.bundl.searchButOnAddMainProd"), prop.xP_Val("cr.bundl.skuIdInputOnAddMainProd"),
                skuID, prop.xP_Val("cr.bundl.secondSearchButOnAddMainProd"));
        waiter.waitClickable(prop.xP_Val("cr.bundle.plusButtonOnAddMainProd"));
        driver.findElement(prop.xP_Val("cr.bundle.plusButtonOnAddMainProd")).click();
    }




    public String notificationAfterAddMainProduct() {
        waiter.waitToVisible(prop.xP_Val("cr.bundl.notifAfterAddMainProd"), "Main product was successfully added to the bundle.");
        return driver.findElement(prop.xP_Val("cr.bundl.notifAfterAddMainProd")).getText();
    }




    public void createBundleSecondPart(String componentSkuId) {
        waiter.waitToInvisibility(prop.xP_Val("cr.bundl.notifAfterAddMainProd"),
                "Main product was successfully added to the bundle.");

        waiter.waitClickable(prop.xP_Val("cr.bundl.addComponentsButton"));
        driver.findElement(prop.xP_Val("cr.bundl.addComponentsButton")).click();

        waiter.waitClickable(prop.xP_Val("cr.bundle.searchButOnAddComponent"));

        searcher.searchInput(prop.xP_Val("cr.bundle.searchButOnAddComponent"), prop.xP_Val("cr.bundle.skuIdOnAddComponent"),
                componentSkuId, prop.xP_Val("cr.bundle.secondSearchButtonOnAddComponent"));
        waiter.waitClickable(prop.xP_Val("cr.bundl.plusButtonOnAddComponent")).click();
    }




    public String notificationAfterAddComponent() {
        waiter.waitToVisible(prop.xP_Val("cr.bundle.notifAfterAddComponent"),
                "Component was successfully added to the bundle!");
        return driver.findElement(prop.xP_Val("cr.bundle.notifAfterAddComponent")).getText();
    }




    public void finalizeBundleCreation(String rrpExclTax) {
        waiter.waitClickable(prop.xP_Val("cr.bundle.closeButtonOnSearchWindow")).click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        waiter.waitClickable(prop.xP_Val("cr.bundl.rrpExcl"));
        driver.findElement(prop.xP_Val("cr.bundl.rrpExcl")).click();
        driver.findElement(prop.xP_Val("cr.bundl.rrpExcl")).clear();
        driver.findElement(prop.xP_Val("cr.bundl.rrpExcl")).sendKeys(rrpExclTax);
        waiter.waitClickable(prop.xP_Val("cr.bundle.saveButton")).submit();
    }




    public String notificationAfterCreateBundle() {
        waiter.waitToVisible(prop.xP_Val("cr.bundle.notifAfterCreateBundle"), "Product was successfully saved.");
        return driver.findElement(prop.xP_Val("cr.bundle.notifAfterCreateBundle")).getText();
    }


}
