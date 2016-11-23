package com.distriread.autotests.bundle;

import com.distriread.autotests.config.WebDriverConfig;
import com.distriread.autotests.helpers.UrlSetter;
import com.distriread.autotests.pages.bundle.Bundle;
import com.distriread.autotests.pages.bundle.CreateBundle;
import com.distriread.autotests.pages.bundle.JustCreatedBundle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by nikita on 22.11.16.
 */
public class TestBundle extends WebDriverConfig {

    private UrlSetter urlSetter;
    private CreateBundle createBundle;
    private Bundle bundle;
    private JustCreatedBundle justCreatedBundle;


    private String MPN = "test_bundle123";
    private String buildingCost = "100";
    private String rrpExclTax = "222";
    private String fullTitle = "Test full title";
    private String shortDescrip = "Some short test description";
    private String summary = "Some test summary";
    private String mainProductSkuId = "00000000014";
    private String componentSkuId = "00000000010";



    @Before
    public void setUp() {
        try {
            super.setUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.urlSetter = new UrlSetter(driver);
        this.createBundle = new CreateBundle(driver);
        this.bundle = new Bundle(driver);
        this.justCreatedBundle = new JustCreatedBundle(driver);
    }



    @Test
    public void testCreateBundle() {
        urlSetter.setUrl("http://vr1.bintime.com/product/bundle/addbundle");
        createBundle.createBundleFirstPart(MPN, buildingCost, fullTitle, shortDescrip, summary, mainProductSkuId);

        Assert.assertEquals(createBundle.notificationAfterAddMainProduct(),
                "Main product was successfully added to the bundle.");

        createBundle.createBundleSecondPart(componentSkuId);
        Assert.assertEquals(createBundle.notificationAfterAddComponent(),
                "Component was successfully added to the bundle!");

        createBundle.finalizeBundleCreation(rrpExclTax);
        Assert.assertEquals(createBundle.notificationAfterCreateBundle(), "Product was successfully saved.");


        Assert.assertEquals(justCreatedBundle.getMPN(), MPN);
        Assert.assertEquals(justCreatedBundle.getCost(),buildingCost);
        Assert.assertEquals(justCreatedBundle.getRRpExcTax(), rrpExclTax);
        Assert.assertEquals(justCreatedBundle.getActivePostion(), "fa fa-check fa-lg icon-blue");


        urlSetter.setUrl("http://vr1.bintime.com/product/bundle");

        bundle.deleteBundle(MPN);
    }




}
