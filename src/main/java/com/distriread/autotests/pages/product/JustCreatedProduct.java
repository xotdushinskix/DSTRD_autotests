package com.distriread.autotests.pages.product;

import com.distriread.autotests.helpers.InfoGetter;
import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;
import java.util.List;

/**
 * Created by nikita on 08.11.16.
 */
public class JustCreatedProduct {

    private WebDriver driver;
    private Waiter waiter;
    private InfoGetter infoGetter;
    protected InputStream inputStream;
    private PropertyReader prop;
    private final String PROP_NAME = "just_created_product.properties";


    public JustCreatedProduct(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.infoGetter = new InfoGetter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
    }



    public String getNotificationInfo() {
        waiter.waitToVisible(prop.xP_Val("j.cr.pr.afterCreateNotification"), "Product successfully added");
        return driver.findElement(prop.xP_Val("j.cr.pr.afterCreateNotification")).getText();
    }



    public String getMPN() {
        return driver.findElement(prop.xP_Val("j.cr.pr.mpnPath")).getText();
    }



    public String getBrand() {
        return driver.findElement(prop.xP_Val("j.cr.pr.brandPath")).getText();
    }



    public List<String> getProductNames() {
        return infoGetter.getStringInfos(prop.xP_Val("j.cr.pr.prodNames"));
    }



    public String getCategoryName() {
        return driver.findElement(prop.xP_Val("j.cr.pr.categPath")).getText();
    }



    public List<String> getWarehouseNames() {
        return infoGetter.getStringInfos(prop.xP_Val("j.cr.pr.warehouseNames"));
    }



    public List<String> getMPNs() {
        return infoGetter.getStringInfos(prop.xP_Val("j.cr.pr.mpnName"));
    }



    public List<Integer> getPrices() {
        return infoGetter.getIntegerInfos(prop.xP_Val("j.cr.pr.pricesPath"));
    }



    public int getRRPex() {
        return Integer.valueOf(driver.findElement(prop.xP_Val("j.cr.pr.rrpExc")).getText());
    }

}
