package Sowjanya;

import Sowjanya.PageObjects.CartCatalog;
import Sowjanya.PageObjects.Landing_Page;
import Sowjanya.PageObjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v126.layertree.model.Layer;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class POM {
    public static void main(String[] args)
    {
        String product_name ="ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().window().maximize();
        Landing_Page landingpage = new Landing_Page(driver);
        landingpage.goTo();
        ProductCatalog productCatalog = (ProductCatalog) landingpage.loginApplication("chirlasowjanyareddy@gmail.com","Sowjanyareddy@123");


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
        driver.close();

    }
}
