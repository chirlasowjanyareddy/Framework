package Sowjanya;

import Sowjanya.PageObjects.CartCatalog;
import Sowjanya.PageObjects.Orderpage;
import Sowjanya.PageObjects.ProductCatalog;
import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
//    String product_name = "ZARA COAT 3";
    @Test(dataProvider= "getData",groups = {"Purchase"})
    public void SubmitOrder(HashMap<String,String>  input) throws IOException {

        ProductCatalog productCatalog = (ProductCatalog) landingpage.loginApplication((String) input.get("email"), (String) input.get("password"));
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart((String) input.get("product_name"));
        CartCatalog CartCatalog = (CartCatalog) productCatalog.ClicktheCart();
        List<WebElement> cartprods = CartCatalog.getTheCartProductsList();
        Boolean match = CartCatalog.CheckWeatherProductisAddedOrNot((String) input.get("product_name"));
        Assert.assertTrue(match);
        CartCatalog.checkout();
        productCatalog.SelectCountry("India");
        productCatalog.placeorder();
        String conform_msg = productCatalog.CheckOrderPlacedOrNot();
        Assert.assertTrue(conform_msg.equalsIgnoreCase("Thankyou for the order."));
    }
    // check if zara coat 3 is in the orders section or not
    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistory()
    {
        String product_name = "ZARA COAT 3";
        ProductCatalog productCatalog = (ProductCatalog) landingpage.loginApplication("chirlasowjanyareddy@gmail.com", "Sowjanyareddy@123");
        Orderpage orderpage = productCatalog.GoToOrders();
        Assert.assertTrue(orderpage.OrderVerify(product_name));

    }


    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
//    public Object[][] getData() {
//        return new Object[][]{{"chirlasowjanyareddy@gmail.com", "Sowjanyareddy@123", "ZARA COAT 3"}, {"sowjanyareddychirla@gmail.com", "Sowjanyareddy@123", "ADIDAS ORIGINAL"}};
//    }
//    or
//        HashMap<Object,Object> map = new HashMap<Object,Object>();
//        map.put("email","chirlasowjanyareddy@gmail.com");
//        map.put("password","Sowjanyareddy@123");
//        map.put("product_name","ZARA COAT 3");
//
//        HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//        map1.put("email","sowjanyareddychirla@gmail.com");
//        map1.put("password","Sowjanyareddy@123");
//        map1.put("product_name","ADIDAS ORIGINAL");



}
