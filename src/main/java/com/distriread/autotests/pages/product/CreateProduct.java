package com.distriread.autotests.pages.product;

import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.InputStream;

/**
 * Created by nikita on 08.11.16.
 */
public class CreateProduct {

    private WebDriver driver;
    private Waiter waiter;
    protected InputStream inputStream;
    private final String PROP_NAME = "create_product.properties";
    protected PropertyReader prop;


    public CreateProduct(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
    }



    public void fillRequiredFieldsAndAddProduct(String mpn, String brandName, String productName, String productCategory,
                                                String vendor1, String vendor2, int cost, int rrpExcValue, int prodStock,
                                                String warehouseName) {
        waiter.waitClickable(prop.xP_Val("cr.pr.moveAllVendors"));
        driver.findElement(prop.xP_Val("cr.pr.mpnField")).sendKeys(mpn);
        driver.findElement(prop.xP_Val("cr.pr.brandFieldSel2")).click();
        driver.findElement(prop.xP_Val("cr.pr.brandInput")).sendKeys(brandName);

        waiter.waitClickable(prop.xP_Val("cr.pr.brandNameSuggestion"));
        driver.findElement(prop.xP_Val("cr.pr.brandNameSuggestion")).click();
        driver.findElement(prop.xP_Val("cr.pr.prodNameInput")).sendKeys(productName);
        driver.findElement(prop.xP_Val("cr.pr.prodCategSelect2")).click();
        driver.findElement(prop.xP_Val("cr.pr.prodCategInput")).sendKeys(productCategory);

        waiter.waitClickable(prop.xP_Val("cr.pr.getProdCategSuggestion"));
        driver.findElement(prop.xP_Val("cr.pr.getProdCategSuggestion")).click();

        WebElement vendorSelect1 = driver.findElement(prop.xP_Val("cr.pr.vendorSelect"));
        Select vendorSelect = new Select(vendorSelect1);
        vendorSelect.selectByVisibleText(vendor1);
        vendorSelect.selectByVisibleText(vendor2);

        driver.findElement(prop.xP_Val("cr.pr.costInput")).sendKeys(String.valueOf(cost));
        driver.findElement(prop.xP_Val("cr.pr.stock")).sendKeys(String.valueOf(prodStock));

        WebElement warehouseSelect = driver.findElement(prop.xP_Val("cr.pr.warehouseDD"));
        Select selectWarehouse = new Select(warehouseSelect);
        selectWarehouse.selectByVisibleText(warehouseName);

        driver.findElement(prop.xP_Val("cr.pr.activeCheckBox")).click();
        driver.findElement(prop.xP_Val("cr.pr.rrpExc")).sendKeys(String.valueOf(rrpExcValue));
        waiter.waitClickable(prop.xP_Val("cr.pr.saveButton"));
        driver.findElement(prop.xP_Val("cr.pr.saveButton")).click();

        waiter.waitToVisible(prop.xP_Val("cr.pr.afterCreateNotification"), "Product successfully added");
    }

}
