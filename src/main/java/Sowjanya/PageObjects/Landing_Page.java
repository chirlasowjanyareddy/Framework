package Sowjanya.PageObjects;

import Sowjanya.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landing_Page extends AbstractComponent {
//    WebDriver driver;
    public Landing_Page(WebDriver driver)
    {
        // Initilization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
//    WebElement useremail = driver.findElement(By.id("userEmail"));
    @FindBy(id="userEmail")
    WebElement useremail;
//    driver.findElement(By.id("userPassword"))
    @FindBy(id="userPassword")
    WebElement passwordele;
//    driver.findElement(By.id("login"))
    @FindBy(id="login")
    WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    WebElement Errormsg;

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMsg()
    {
        WaitForWebElementToAppear(Errormsg);
        return Errormsg.getText();
    }

    public Object loginApplication(String email,String password)
    {
        useremail.sendKeys(email);
        passwordele.sendKeys(password);
        submit.click();
        ProductCatalog productCatalog  = new ProductCatalog(driver);
        return productCatalog;
    }
}
