package Sowjanya.PageObjects;

import Sowjanya.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orderpage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> cartproducts;

    public Orderpage(WebDriver driver) {
        // Initilization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public Boolean OrderVerify(String productname)
    {
        return cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
    }


}
