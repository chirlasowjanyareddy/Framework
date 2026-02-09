package Sowjanya;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("chirlasowjanyareddy@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Sowjanyareddy@123");
        driver.findElement(By.id("login")).click();
        List<WebElement> products = driver.findElements(By.cssSelector("col-lg-4"));
    }
}
