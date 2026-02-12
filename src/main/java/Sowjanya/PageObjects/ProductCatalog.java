package Sowjanya.PageObjects;

import Sowjanya.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {
    WebDriver driver;
    public ProductCatalog(WebDriver driver)
    {
        // Initilization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
//    List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

    @FindBy(css=".col-lg-4")
    List<WebElement> products;
    By productsBy = By.cssSelector(".col-lg-4");
    public List<WebElement> getProductList()
    {
        WaitForElementToAppear(productsBy);
        return products;
    }


}
