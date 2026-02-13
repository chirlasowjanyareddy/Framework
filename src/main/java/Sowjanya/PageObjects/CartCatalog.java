package Sowjanya.PageObjects;

import Sowjanya.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartCatalog extends AbstractComponent {

    public CartCatalog(WebDriver driver) {
        // Initilization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".cartSection h3")
    List<WebElement> cartproducts;

    @FindBy(css=".subtotal button")
    WebElement checkoutbutton;

    public List<WebElement> getTheCartProductsList()
    {
        return cartproducts;
    }

    public Boolean CheckWeatherProductisAddedOrNot(String product_name)
    {
        return getTheCartProductsList().stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(product_name));
    }

    public void checkout()
    {
        checkoutbutton.click();
    }
}
