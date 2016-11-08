package com.distriread.autotests.pages.brand;

import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;

/**
 * Created by nikita on 08.11.16.
 */
public class CreateBrand {

    private WebDriver driver;
    protected Waiter waiter;
    protected InputStream inputStream;
    protected PropertyReader prop;
    private final String PROP_NAME = "create_brand.properties";

    public CreateBrand(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
    }



    public void fillFieldsAndCreateBrand(String brandName, String description) {
        driver.findElement(prop.xP_Val("j.cr.br.nameInput")).sendKeys(brandName);
        driver.findElement(prop.xP_Val("j.cr.br.descriptionArea")).sendKeys(description);
        waiter.waitClickable(prop.xP_Val("j.cr.br.brandSaveButton")).click();
        waiter.waitToVisible(prop.xP_Val("j.cr.br.createBrandNotification"), "Brand was successfully saved.");
    }

}
