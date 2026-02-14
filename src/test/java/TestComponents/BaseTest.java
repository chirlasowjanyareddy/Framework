package TestComponents;

import Sowjanya.PageObjects.Landing_Page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver ;
    public Landing_Page landingpage;
    public WebDriver initilizeWebDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Sowjanya//resources//GlobalData.properties");
        prop.load(fis);
        String browsername = prop.getProperty("browser");

        if(browsername.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;

    }
    @BeforeMethod(alwaysRun = true)
    public Landing_Page launchApplication() throws IOException {
        driver = initilizeWebDriver();
        landingpage = new Landing_Page(driver);
        landingpage.goTo();
        return  landingpage;
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }
}
