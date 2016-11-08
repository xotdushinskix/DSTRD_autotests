package com.distriread.autotests.brand;

import com.distriread.autotests.config.WebDriverConfig;
import com.distriread.autotests.helpers.UrlSetter;
import com.distriread.autotests.pages.brand.Brand;
import com.distriread.autotests.pages.brand.CreateBrand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by nikita on 08.11.16.
 */
public class TestBrand extends WebDriverConfig {

    private UrlSetter urlSetter;
    protected Brand brand;
    protected CreateBrand createBrand;

    @Before
    public void setUp() throws IOException {
        super.setUp();
        urlSetter = new UrlSetter(driver);
        brand = new Brand(driver);
        createBrand = new CreateBrand(driver);
    }



    @Test
    public void testSearchingOnBrandsPage() {
        urlSetter.setUrl("http://vr1.bintime.com/brand/brand/index");
        brand.searchingByBrandId("9313");

        Assert.assertEquals(brand.getIdFromPage(), "9313");

        brand.resetSearching();
        brand.searchingByName("Nokia");

        Assert.assertEquals(brand.getBrandNameFromPage(), "Nokia");

        brand.resetSearching();
        brand.searchingByUpdate("2015-12-14");
        assert (brand.getUpdateFromPage().contains("2015-12-14"));

        brand.resetSearching();
    }



    @Test
    public void testCreateBrand() {
        urlSetter.setUrl("http://vr1.bintime.com/brand/brand/create");
        createBrand.fillFieldsAndCreateBrand("TestBrand123", "test_description_123");

        Assert.assertEquals(brand.getNotificationAfterCreate(), "Brand was successfully saved.");

        brand.searchingByName("TestBrand123");
        Assert.assertEquals(brand.getBrandNameFromPage(), "TestBrand123");

        brand.deleteBrand();
    }



    @Test
    public void testDeleteBrand() {
        urlSetter.setUrl("http://vr1.bintime.com/brand/brand/create");
        createBrand.fillFieldsAndCreateBrand("TestBrand123", "test_description_123");
        brand.searchingByName("TestBrand123");
        brand.deleteBrand();

        Assert.assertEquals(brand.getNotificationAfterDelete(), "Brand was successfully deleted.");

        brand.searchingByName("TestBrand123");
        Assert.assertEquals(brand.getNoResultsFound(), "No results found.");
    }

}
