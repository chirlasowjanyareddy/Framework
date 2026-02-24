package StepDefinations;

import Sowjanya.PageObjects.CartCatalog;
import Sowjanya.PageObjects.Landing_Page;
import Sowjanya.PageObjects.ProductCatalog;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefination extends BaseTest {
    public Landing_Page landingpage;
    public ProductCatalog productCatalog ;
    @Given("Given I landed on Ecommerce Page") // it is static so we wont fo anything
    public void I_landed_on_Ecommerce_Page() throws IOException {
        //code to implement
        landingpage = launchApplication();
    }
    @Given("^Given Logged in with the username (.+) and password (.+)$")
    public void Logged_in_with_the_username_and_password(String username,String password)
    {
        productCatalog = (ProductCatalog) landingpage.loginApplication(username,password);
    }
    @When("^When  I add product (.+) to  Cart$") // keep regular expression for the parameterized values
    public void When_I_add_product_to_cart(String productname)
    {
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productname);
    }
    @When("^And checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productname)
    {
        CartCatalog CartCatalog = (CartCatalog) productCatalog.ClicktheCart();
        Boolean match = CartCatalog.CheckWeatherProductisAddedOrNot(productname);
        Assert.assertTrue(match);
        CartCatalog.checkout();
        productCatalog.SelectCountry("India");
        productCatalog.placeorder();

    }
    @Then("{string} is Displayed on Conformation page")
    public void is_Displayed_on_Conformation_page(String msg)
    {
        String conform_msg = productCatalog.CheckOrderPlacedOrNot();
        Assert.assertTrue(conform_msg.equalsIgnoreCase(msg));
        driver.close();
    }
}
