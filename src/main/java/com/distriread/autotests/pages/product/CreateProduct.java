package com.distriread.autotests.pages.product;

import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Searcher;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by nikita on 08.11.16.
 */
public class CreateProduct {

    private WebDriver driver;
    private Waiter waiter;
    protected InputStream inputStream;
    private final String PROP_NAME = "create_product.properties";
    protected PropertyReader prop;
    private Searcher searcher;


    public CreateProduct(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
        this.searcher = new Searcher(driver);
    }



    public void fillRequiredFieldsAndAddProduct(String mpn, String brandName, String productName, String productCategory,
                                                String vendor1, String vendor2, String cost, String rrpExcValue) {
        waiter.waitClickable(prop.xP_Val("cr.pr.saveButton"));

        driver.findElement(prop.xP_Val("cr.pr.mpnField")).sendKeys(mpn);
        searcher.select2(prop.xP_Val("cr.pr.brandFieldSel2"), prop.xP_Val("cp.pr.brandInputS2"), brandName,
                prop.xP_Val("cp.pr.brandS2Suggestion"));

        searcher.selectDropDown(prop.xP_Val("cr.pr.typeDD"), "Good");

        driver.findElement(prop.xP_Val("cr.pr.prodNameInput")).sendKeys(productName);

        searcher.select2(prop.xP_Val("cp.pr.prodCategoryS2"), prop.xP_Val("cp.pr.prodCategoryInput"), productCategory,
                prop.xP_Val("cp.pr.prodCategSuggest"));

        searcher.selectDropDown(prop.xP_Val("cr.pr.vendorSelect"), vendor1);
        searcher.selectDropDown(prop.xP_Val("cr.pr.vendorSelect"), vendor2);

        driver.findElement(prop.xP_Val("cr.pr.costInput")).sendKeys(cost);

        driver.findElement(prop.xP_Val("cr.pr.rrpExc")).click();
        driver.findElement(prop.xP_Val("cr.pr.rrpExc")).clear();
        driver.findElement(prop.xP_Val("cr.pr.rrpExc")).sendKeys(rrpExcValue);

        driver.findElement(prop.xP_Val("cr.pr.activeCheckBox")).click();

        waiter.waitClickable(prop.xP_Val("cr.pr.saveButton"));
        driver.findElement(prop.xP_Val("cr.pr.saveButton")).click();

        waiter.waitToVisible(prop.xP_Val("cr.pr.afterCreateNotification"), "Product successfully added");
    }

}
