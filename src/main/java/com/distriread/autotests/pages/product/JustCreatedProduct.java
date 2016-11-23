package com.distriread.autotests.pages.product;

import com.distriread.autotests.helpers.InfoGetter;
import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.StringCropper;
import com.distriread.autotests.helpers.Waiter;
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
    private StringCropper stringCropper;
    private final String PROP_NAME = "just_created_product.properties";


    public JustCreatedProduct(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.infoGetter = new InfoGetter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
        this.stringCropper = new StringCropper();
    }



    public String getNotificationInfo() {
        waiter.waitToVisible(prop.xP_Val("j.cr.pr.afterCreateNotification"), "Product successfully added");
        return driver.findElement(prop.xP_Val("j.cr.pr.afterCreateNotification")).getText();
    }



    public String getMPNBasicInfo() {
        return driver.findElement(prop.xP_Val("j.cr.pr.mpnBasicInfo")).getText();
    }



    public String getBrandBasicInfo() {
        return driver.findElement(prop.xP_Val("j.cr.pr.brandBasicInfo")).getText();
    }



    public String getCostValueBasicInfo() {
        String costWithCurrency = driver.findElement(prop.xP_Val("j.cr.pr.costBasicInfo")).getText();
        return stringCropper.cropString(costWithCurrency, 0, 3);
    }



    public String getRRP_ExclTax() {
        return driver.findElement(prop.xP_Val("j.cr.pr.rrpExclTaxBasicInfo")).getText();
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
