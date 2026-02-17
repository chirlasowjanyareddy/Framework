package Sowjanya;

import Sowjanya.PageObjects.CartCatalog;
import Sowjanya.PageObjects.ProductCatalog;
import TestComponents.BaseTest;
import TestComponents.Retry;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException {
        landingpage.loginApplication("chirlasownyareddy@gmail.com", "Sowjanyareddy@123");
        Assert.assertEquals(landingpage.getErrorMsg(), "Incorrect email password.");

    }
    @Test
    public void SubmitOrder() throws IOException {
        String product_name = "ZARA COAT 3";
        ProductCatalog productCatalog = (ProductCatalog) landingpage.loginApplication("chirlasowjanyareddy@gmail.com", "Sowjanyareddy@123");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(product_name);
        CartCatalog CartCatalog = (CartCatalog) productCatalog.ClicktheCart();
        List<WebElement> cartprods = CartCatalog.getTheCartProductsList();
        Boolean match = CartCatalog.CheckWeatherProductisAddedOrNot("adidas");
        Assert.assertFalse(match);

    }

}
