package com.distriread.autotests.tests.product;

import com.distriread.autotests.config.WebDriverConfig;
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

    UrlSetter urlSetter;
    Product product;
    CreateProduct createProduct;
    JustCreatedProduct justCreatedProduct;


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
        createProduct.fillRequiredFieldsAndAddProduct("23ertre33", "Samson", "Pipiya", "notebooks", "AMT Zeltwanger s.r.o.",
                "Amazon", 120, 125, 500, "Distriread Office");

        Assert.assertEquals(justCreatedProduct.getNotificationInfo(), "Product successfully added");
        Assert.assertEquals(justCreatedProduct.getMPN(), "23ertre33");
        Assert.assertEquals(justCreatedProduct.getBrand(), "Samson");
        assert (justCreatedProduct.getProductNames().contains("Pipiya"));
        assert (justCreatedProduct.getWarehouseNames().contains("AMT Zeltwanger s.r.o."));
        assert (justCreatedProduct.getWarehouseNames().contains("Amazon"));
        assert (justCreatedProduct.getMPNs().contains("23ertre33"));
        assert (justCreatedProduct.getPrices().contains(120));
        Assert.assertEquals(justCreatedProduct.getRRPex(), 125);

        urlSetter.setUrl("http://vr1.bintime.com/product/producttool/index");
        product.deleteProduct("23ertre33");
    }




    @Test
    public void testDeleteProduct() {
        urlSetter.setUrl("http://vr1.bintime.com/product/producttool/addproduct");
        createProduct.fillRequiredFieldsAndAddProduct("3333www", "Samsung", "Test_delete", "notebooks", "Copaco",
                "Robin", 150, 160, 170, "Own warehouse №1 - ROBERTO");

        urlSetter.setUrl("https://erp.distriread.com/product/producttool/index");
        product.deleteProduct("3333www");

        Assert.assertEquals(product.getPageInfoAfterSearchDeletedProduct(), "No results found.");
        product.searchProductByMPN("3333www");
        Assert.assertEquals(product.getPageInfoAfterSearchDeletedProduct(), "No results found.");
    }




    @Test
    public void testSearchingOnProductPage() {
        urlSetter.setUrl("http://vr1.bintime.com/product/producttool");

        product.searchByProductCategory("AV cables");
        assert (product.categoryResultsAfterSearch().contains("AV cables"));
        product.resetSearching();

        product.searchByManufacturer("Nike");
        assert (product.manufacturerResultsAfterSearch().contains("Nike"));
        product.resetSearching();

        product.searchProductByBrand("Canon");
        assert (product.brandResultsAfterSearch().contains("Canon"));
        product.resetSearching();

        product.searchProductType("Good");
        assert (product.typeResultAfterSearch().contains("Good"));
        product.resetSearching();

        product.searchProductTitle("Nokia");
        assert (product.titleResultAfterSearch().contains("Nokia"));
        product.resetSearching();

        product.searchProductMPN("90-C1CNX1-S0UAN0YZ");
        assert (product.mpnResultAfterSearch().contains("90-C1CNX1-S0UAN0YZ"));
        product.resetSearching();

        product.searchByEAN("4948570114122");
        assert (product.eanResultAfterSearch().contains("4948570114122"));
        product.resetSearching();

        product.searchByStatus("Not active");
        Assert.assertFalse (product.statusResultAfterSearch("Not active").isEmpty());
    }






}
