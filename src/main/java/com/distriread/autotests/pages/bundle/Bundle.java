package com.distriread.autotests.pages.bundle;

import com.distriread.autotests.helpers.InfoGetter;
import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Searcher;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;

/**
 * Created by nikita on 22.11.16.
 */
public class Bundle {

    private WebDriver driver;
    protected Waiter waiter;
    protected InfoGetter infoGetter;
    protected InputStream inputStream;
    private final String PROP_NAME = "bundle.properties";
    protected PropertyReader prop;
    protected Searcher searcher;


    public Bundle(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.infoGetter = new InfoGetter(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
        this.searcher = new Searcher(driver);
    }




    public void deleteBundle(String MPN) {
        waiter.waitClickable(prop.xP_Val("bundl.searchButton"));
        searcher.select2(prop.xP_Val("bundl.searchButton"), prop.xP_Val("bundl.MPNsel2"), prop.xP_Val("bundl.MPNsel2Input"),
                prop.xP_Val("bundl.MPNsel2Suggestion"), prop.xP_Val("bundl.searchButtonOnSearchGrid"), MPN);
        waiter.waitClickable(prop.xP_Val("bundl.deleteBundle")).click();
        driver.findElement(prop.xP_Val("bundl.applyDeleteButton")).click();
    }

}
