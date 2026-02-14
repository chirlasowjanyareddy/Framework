package Sowjanya;

import Sowjanya.PageObjects.CartCatalog;
import Sowjanya.PageObjects.Orderpage;
import Sowjanya.PageObjects.ProductCatalog;
import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrder extends BaseTest {
//    String product_name = "ZARA COAT 3";
    @Test(dataProvider= "getData",groups = {"Purchase"})
    public void SubmitOrder(String email,String password,String product_name) throws IOException {

        ProductCatalog productCatalog = (ProductCatalog) landingpage.loginApplication(email, password);
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(product_name);
        CartCatalog CartCatalog = (CartCatalog) productCatalog.ClicktheCart();
        List<WebElement> cartprods = CartCatalog.getTheCartProductsList();
        Boolean match = CartCatalog.CheckWeatherProductisAddedOrNot(product_name);
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
    public Object[][] getData()
    {
        return new Object[][] {{"chirlasowjanyareddy@gmail.com","Sowjanyareddy@123","ZARA COAT 3"},{"sowjanyareddychirla@gmail.com","Sowjanyareddy@123","ADIDAS ORIGINAL"}};
    }


}
