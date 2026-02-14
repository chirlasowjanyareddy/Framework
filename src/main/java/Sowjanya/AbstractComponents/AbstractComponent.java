package Sowjanya.AbstractComponents;

import Sowjanya.PageObjects.CartCatalog;
import Sowjanya.PageObjects.Orderpage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    protected WebDriver driver ;
    public AbstractComponent(WebDriver driver)
    {
         this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[routerlink*='/dashboard/cart']")
    WebElement cart;

    @FindBy(css="[routerlink='/dashboard/myorders']")
    WebElement Orders;

    public void WaitForElementToAppear(By FindBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }

    public void WaitForWebElementToAppear(WebElement FindBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(FindBy));
    }
    public void WaitForElemnetToDisapper(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       wait.until(ExpectedConditions.invisibilityOf(ele));
    }
    public Object ClicktheCart()
    {
        cart.click();
        CartCatalog cartCatalog = new CartCatalog(driver);
        return  cartCatalog;
    }

    public Orderpage GoToOrders()
    {
        Orders.click();
        Orderpage orderpage = new Orderpage(driver);
        return orderpage;
    }

}
