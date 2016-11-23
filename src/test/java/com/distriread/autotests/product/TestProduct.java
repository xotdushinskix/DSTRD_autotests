package com.distriread.autotests.product;

import com.distriread.autotests.config.WebDriverConfig;
import com.distriread.autotests.helpers.Searcher;
import com.distriread.autotests.helpers.UrlSetter;
import com.distriread.autotests.pages.product.CreateProduct;
import com.distriread.autotests.pages.product.JustCreatedProduct;
import com.distriread.autotests.pages.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by nikita on 07.11.16.
 */
public class TestProduct extends WebDriverConfig {

    private UrlSetter urlSetter;
    private Product product;
    private CreateProduct createProduct;
    private JustCreatedProduct justCreatedProduct;


    private String MPN = "23ertre33";
    private String brandName = "Samson";
    private String productName = "Pipiya";
    private String productCategory = "notebooks";
    private String vendor1 = "AMT Zeltwanger s.r.o.";
    private String vendor2 = "Amazon";
    private String cost = "100";
    private String rrpExclTax = "125";



    @Before
    public void setUp() throws IOException {
        super.setUp();
        urlSetter = new UrlSetter(driver);
        product = new Product(driver);
        createProduct = new CreateProduct(driver);
        justCreatedProduct = new JustCreatedProduct(driver);
    }




    @Test
    public void testPageTitle() {
        urlSetter.setUrl("http://vr1.bintime.com/product/producttool/index");
        Assert.assertEquals(product.mainPageTitle(), "Distriread - Producttool");
    }




    @Test
    public void testCreateProduct() {
        urlSetter.setUrl("http://vr1.bintime.com/product/producttool/addproduct");
        createProduct.fillRequiredFieldsAndAddProduct(MPN, brandName, productName, productCategory, vendor1, vendor2,
                cost, rrpExclTax);

        Assert.assertEquals(justCreatedProduct.getNotificationInfo(), "Product successfully added");
        Assert.assertEquals(justCreatedProduct.getMPNBasicInfo(), MPN);
        Assert.assertEquals(justCreatedProduct.getBrandBasicInfo(), brandName);
        Assert.assertEquals(justCreatedProduct.getCostValueBasicInfo(), cost);
        Assert.assertEquals(justCreatedProduct.getRRP_ExclTax(), rrpExclTax);


        urlSetter.setUrl("http://vr1.bintime.com/product/producttool/index");
        product.deleteProduct("23ertre33");
    }




    @Test
    public void testDeleteProduct() {
        urlSetter.setUrl("http://vr1.bintime.com/product/producttool/addproduct");
        createProduct.fillRequiredFieldsAndAddProduct(MPN, brandName, productName, productCategory, vendor1,
                vendor2, cost, rrpExclTax);

        urlSetter.setUrl("http://vr1.bintime.com/product/producttool/index");
        product.deleteProduct(MPN);

        Assert.assertEquals(product.getPageInfoAfterSearchDeletedProduct(), "No results found.");
        product.searchProductByMPN(MPN);
        Assert.assertEquals(product.getPageInfoAfterSearchDeletedProduct(), "No results found.");
    }




    @Test
    public void testSearchingOnProductPage() {
        urlSetter.setUrl("http://vr1.bintime.com/product/producttool");

        product.searchByProductCategory("Grid-IT");
        assert (product.categoryResultsAfterSearch().contains("Grid-IT"));
        product.resetSearching();



//        product.searchByManufacturer("");
//        assert (product.manufacturerResultsAfterSearch().contains(""));
//        product.resetSearching();



        product.searchProductByBrand("Ergo");
        assert (product.brandResultsAfterSearch().contains("Ergo"));
        product.resetSearching();

        product.searchProductType("Good");
        assert (product.typeResultAfterSearch().contains("Good"));
        product.resetSearching();

        product.searchProductTitle("Icarus C014BK");
        assert (product.titleResultAfterSearch().contains("Icarus"));
        product.resetSearching();

        product.searchProductMPN("EM1010BK");
        assert (product.mpnResultAfterSearch().contains("EM1010BK"));
        product.resetSearching();

        product.searchByEAN("8437009734902");
        assert (product.eanResultAfterSearch().contains("8437009734902"));
        product.resetSearching();

        product.searchByStatus("Not active");
        Assert.assertFalse (product.statusResultAfterSearch("Not active").isEmpty());
    }



}
