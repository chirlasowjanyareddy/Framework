package Sowjanya;

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
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
        Landing_Page landingpage = new Landing_Page(driver);
        landingpage.goTo();
        landingpage.loginApplication("chirlasowjanyareddy@gmail.com","Sowjanyareddy@123");

        ProductCatalog productCatalog = new ProductCatalog(driver);
        List<WebElement> products = productCatalog.getProductList();



        String product_name ="ZARA COAT 3";

        WebElement prod = products.stream().filter(product
                ->product.findElement(By.cssSelector("b")).getText().equals(product_name)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
        List<WebElement> cartprods = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartprods.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(product_name));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".subtotal button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:nth-last-of-type(2)")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String conform_msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(conform_msg.equalsIgnoreCase("Thankyou for the order."));
        driver.close();

    }
}
