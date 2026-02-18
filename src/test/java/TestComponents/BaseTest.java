package TestComponents;

import Sowjanya.PageObjects.Landing_Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver ;
    public Landing_Page landingpage;
    public WebDriver initilizeWebDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Sowjanya//resources//GlobalData.properties");
        prop.load(fis);
        // to read browser value which is comming from maven
        String browsername = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
//        String browsername = prop.getProperty("browser");

        if(browsername.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browsername.equalsIgnoreCase("Firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
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
    public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
        // read json to string
        String JsonContent = FileUtils.readFileToString(new File(FilePath));
        // string to haskmap
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }



    public File getScreenShot(String TestCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts =  (TakesScreenshot)driver;
        File Source = ts.getScreenshotAs(OutputType.FILE);
        // destination path
        File file = new File(System.getProperty("user.dir") + "//reports//"
                + TestCaseName+".png");
        FileUtils.copyFile(Source,file );
        return file;
    }


}
