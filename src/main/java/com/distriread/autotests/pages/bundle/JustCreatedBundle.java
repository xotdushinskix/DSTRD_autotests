package com.distriread.autotests.pages.bundle;

import com.distriread.autotests.helpers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;

/**
 * Created by nikita on 23.11.16.
 */
public class JustCreatedBundle {

    private WebDriver driver;
    private InfoGetter infoGetter;
    private PropertyReader prop;
    protected InputStream inputStream;
    private final String PROP_NAME = "just_created_bundle.properties";
    private Searcher searcher;
    private Waiter waiter;
    private StringCropper stringCropper;



    public JustCreatedBundle(FirefoxDriver driver) {
        this.driver = driver;
        this.infoGetter = new InfoGetter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
        this.searcher = new Searcher(driver);
        this.waiter = new Waiter(driver);
        stringCropper = new StringCropper();
    }




    public String getMPN() {
        waiter.waitClickable(prop.xP_Val("j.cr.bundl.PDFleafletButton"));
        return driver.findElement(prop.xP_Val("j.cr.bundl.MPN")).getText();
    }




    public String getCost() {
        return stringCropper.cropString(driver.findElement(prop.xP_Val("j.cr.bundl.cost")).getText(), 0, 3);
    }




    public String getRRpExcTax() {
        return driver.findElement(prop.xP_Val("j.cr.bundl.rrpExclTax")).getText();
    }




    public String getActivePostion() {
        return driver.findElement(prop.xP_Val("j.cr.bundl.ActiveIcon")).getAttribute("class");
    }


}
