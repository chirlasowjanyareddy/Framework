package Sowjanya.PageObjects;

import Sowjanya.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @FindBy(css=".ng-animating")
    WebElement spinner;




    @FindBy(css="[placeholder='Select Country']")
    WebElement CountryCheckbox;

    @FindBy(css=".ta-item:nth-last-of-type(2)")
    WebElement SelecttheCountry;

    @FindBy(css=".action__submit")
    WebElement placeorder;


    @FindBy(css=".hero-primary")
    WebElement thankyoumsg;



    By productsBy = By.cssSelector(".col-lg-4");
    By Addtocart = By.cssSelector(".card-body button:last-of-type");
    By toastmsg = By.id("toast-container");
    By results =  By.cssSelector(".ta-results");

    public List<WebElement> getProductList()
    {
        WaitForElementToAppear(productsBy);
        return products;
    }
    public WebElement getProductByName(String productname)
    {
        return  getProductList().stream().filter(product
                ->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);

    }

    public void addProductToCart(String productname)
    {
        WebElement prod = getProductByName(productname);
        prod.findElement(Addtocart).click();
        WaitForElementToAppear(toastmsg);
        WaitForElemnetToDisapper(spinner);

    }





    public void SelectCountry(String country)
    {
        Actions a = new Actions(driver);
        a.sendKeys(CountryCheckbox,country).build().perform();
        WaitForElementToAppear(results);

        SelecttheCountry.click();
    }

    public Object placeorder()
    {
        placeorder.click();
        CartCatalog cartCatalog = new CartCatalog(driver);
        return  cartCatalog;
    }
    public String CheckOrderPlacedOrNot()
    {
        return thankyoumsg.getText();
    }


}
