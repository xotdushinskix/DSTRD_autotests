package com.distriread.autotests.manufacturer;

import com.distriread.autotests.config.WebDriverConfig;
import com.distriread.autotests.helpers.UrlSetter;
import com.distriread.autotests.pages.manufacturer.CreateManufacturer;
import com.distriread.autotests.pages.manufacturer.Manufacturer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by nikita on 08.11.16.
 */
public class TestManufacturer extends WebDriverConfig{

    private UrlSetter urlSetter;
    protected Manufacturer manufacturer;
    private CreateManufacturer createManufacturer;

    @Before
    public void setUp() throws IOException {
        super.setUp();
        urlSetter = new UrlSetter(driver);
        manufacturer = new Manufacturer(driver);
        createManufacturer = new CreateManufacturer(driver);
    }




    @Test
    public void testSearchingOnManufacturerPage() {
        urlSetter.setUrl("http://vr1.bintime.com/manufacturer/index");

        manufacturer.searchingByManufacturerId(2419);
        Assert.assertEquals(manufacturer.getManufacturerId(), 2419);
        manufacturer.resetSearching();

        manufacturer.searchingByManufacturerName("2GIG");
        Assert.assertEquals(manufacturer.getManufacturerName(), "2GIG");
        manufacturer.resetSearching();

        manufacturer.searchingByUpdate("2015-12-14");
        assert (manufacturer.getUpdateDates().contains("2015-12-14"));
    }




    @Test
    public void testCreateManufacturer() {
        urlSetter.setUrl("http://vr1.bintime.com/manufacturer/create");

        createManufacturer = new CreateManufacturer(driver);
        manufacturer = new Manufacturer(driver);

        createManufacturer.fillFieldAndSaveNewManufacturer("Delopaqed111", "tes_desc");
        Assert.assertEquals(manufacturer.getNotificationAfterCreate(), "Manufacturer was successfully saved.");

        manufacturer.searchingByManufacturerName("Delopaqed111");
        Assert.assertEquals(manufacturer.getManufacturerName(), "Delopaqed111");

        manufacturer.deleteManufacturerAfterCreate();
    }




    @Test
    public void  testDeleteManufacturer() {
        urlSetter.setUrl("http://vr1.bintime.com/manufacturer/create");

        createManufacturer.fillFieldAndSaveNewManufacturer("DeleteProductSha123", "tes_desc");
        manufacturer.deleteManufacturer("DeleteProductSha123");
        Assert.assertEquals(manufacturer.getNotificationAfterDelete(), "Manufacturer was successfully deleted.");

        manufacturer.searchingByManufacturerName("DeleteProductSha123");
        Assert.assertEquals(manufacturer.noResFoundAfterDelete(), "No results found.");
    }

}
