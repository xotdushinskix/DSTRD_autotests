package com.distriread.autotests.pages.product;

import com.distriread.autotests.helpers.InfoGetter;
import com.distriread.autotests.helpers.PropertyReader;
import com.distriread.autotests.helpers.Searcher;
import com.distriread.autotests.helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;
import java.util.List;

/**
 * Created by nikita on 07.11.16.
 */
public class Product {

    private WebDriver driver;
    private Waiter waiter;
    private InfoGetter infoGetter;
    private Searcher searcher;
    private PropertyReader prop;
    protected InputStream inputStream;
    private final String PROP_NAME = "product.properties";



    public Product(FirefoxDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.infoGetter = new InfoGetter(driver);
        this.searcher = new Searcher(driver);
        this.prop = new PropertyReader(inputStream, PROP_NAME);
    }



    public String mainPageTitle() {
        waiter.waitClickable(prop.xP_Val("pr.searchButton"));
        return driver.getTitle();
    }



    public List<String> categoryResultsAfterSearch() {
        return infoGetter.getStringInfos(prop.xP_Val("pr.categoryName"));
    }



    public List<String> manufacturerResultsAfterSearch() {
        return infoGetter.getStringInfos(prop.xP_Val("pr.manufacturerName"));
    }



    public List<String> brandResultsAfterSearch() {
        return infoGetter.getStringInfos(prop.xP_Val("pr.brandNames"));
    }



    public List<String> typeResultAfterSearch() {
        return infoGetter.getStringInfos(prop.xP_Val("pr.typeNames"));
    }



    public List<String> titleResultAfterSearch() {
        return infoGetter.getStrigRequiredWord(prop.xP_Val("pr.titleNames"));
    }



    public List<String> mpnResultAfterSearch() {
        return infoGetter.getStringInfos(prop.xP_Val("pr.mpnNames"));
    }



    public List<String> eanResultAfterSearch() {
        return infoGetter.getStringInfos(prop.xP_Val("pr.eanNames"));
    }



    public List<WebElement> statusResultAfterSearch(String status) {
        searcher.selectDropdown(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.statusSD"), status);
        List<WebElement> elements = null;
        if (status.equals("Active")) {
            elements =  driver.findElements(prop.xP_Val("pr.activeStatuses"));
        } else if (status.equals("Not active")) {
            elements = driver.findElements(prop.xP_Val("pr.notActiveStatuses"));
        }
        return elements;
    }




    public void deleteProduct(String mpn) {
        waiter.waitClickable(prop.xP_Val("pr.searchButton")).click();
        driver.findElement(prop.xP_Val("pr.mpnDD")).click();
        driver.findElement(prop.xP_Val("pr.inputField")).sendKeys(mpn);
        driver.findElement(prop.xP_Val("pr.mpnSuggestion")).click();
        driver.findElement(prop.xP_Val("pr.searchOnSearchGrid")).click();
        driver.findElement(prop.xP_Val("pr.deleteButton")).click();
        driver.findElement(prop.xP_Val("pr.applyDelete")).click();
        waiter.waitToInvisibility(prop.xP_Val("pr.deleteNotification"), "Product deleted successfully");
    }



    public void searchByProductCategory(String category) {
        searcher.select2Search(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.prodCategoryDD"),
                prop.xP_Val("pr.inputField"), prop.xP_Val("pr.categorySuggestion"),
                prop.xP_Val("pr.searchOnSearchGrid"), category);
    }



    public void searchProductByMPN(String mpn) {
        waiter.waitClickable(prop.xP_Val("pr.searchButton")).click();
        driver.findElement(prop.xP_Val("pr.mpnDD")).click();
        driver.findElement(prop.xP_Val("pr.inputField")).sendKeys(mpn);
        try {
            driver.findElement(prop.xP_Val("pr.mpnSuggestion")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(prop.xP_Val("pr.searchOnSearchGrid")).click();
    }



    public void searchByManufacturer(String manufacturerName) {
        searcher.select2Search(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.manufacturerDD"),
                prop.xP_Val("pr.inputField"), prop.xP_Val("pr.manufacturerSuggestion"), prop.xP_Val("pr.searchOnSearchGrid"),
                manufacturerName);
    }



    public void searchProductByBrand(String brandName) {
        searcher.select2Search(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.brandSelect2"), prop.xP_Val("pr.inputField"),
                prop.xP_Val("pr.prodSuggestion"), prop.xP_Val("pr.searchOnSearchGrid"), brandName);
    }



    public void searchProductType(String prodType) {
        searcher.selectDropdown(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.typeDD"), prodType);
        driver.findElement(prop.xP_Val("pr.searchOnSearchGrid")).click();
    }



    public void searchProductTitle(String titleName) {
        searcher.select2Search(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.titleDD2"), prop.xP_Val("pr.inputField"),
                prop.xP_Val("pr.titleSuggestion"), prop.xP_Val("pr.searchOnSearchGrid"), titleName);
    }



    public void searchProductMPN(String mpn) {
        searcher.select2Search(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.mpnDD"), prop.xP_Val("pr.inputField"),
                prop.xP_Val("pr.mpnSuggestion"), prop.xP_Val("pr.searchOnSearchGrid"), mpn);
    }



    public void searchByEAN(String ean) {
        searcher.select2Search(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.eanDD2"), prop.xP_Val("pr.inputField"),
                prop.xP_Val("pr.eanSuggestion"), prop.xP_Val("pr.searchOnSearchGrid"), ean);
    }



    public void searchByStatus(String status) {
        searcher.selectDropdown(prop.xP_Val("pr.searchButton"), prop.xP_Val("pr.statusSD"), status);
        driver.findElement(prop.xP_Val("pr.searchOnSearchGrid")).click();
    }



    public void resetSearching() {
        driver.findElement(prop.xP_Val("pr.searchButton")).click();
        driver.findElement(prop.xP_Val("pr.resetButton")).click();
    }



    public String getPageInfoAfterSearchDeletedProduct() {
        waiter.waitToVisible(prop.xP_Val("pr.noResultFoundAfterDelete"), "No results found.");
        return driver.findElement(prop.xP_Val("pr.noResultFoundAfterDelete")).getText();
    }

}
