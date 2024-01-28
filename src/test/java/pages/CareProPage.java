package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareProPage {
    @FindBy(id="Username")
    WebElement txtUsername;
    @FindBy(id="Password")
    WebElement txtPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    @FindBy(className = "dropdown-toggle")
    List<WebElement> btnDropDownToggle;
    @FindBy(xpath = "//span[contains(text(),\"Sign Out\")]")
    WebElement btnLogOut;
    WebDriver driver;

    public CareProPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }
    public void doLogin(String username, String password) throws InterruptedException {
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnSubmit.click();
    }
    public void searchClient(String nrc){
        driver.findElement(By.cssSelector("[aria-controls=pills-nrc]")).click();
        driver.findElement(By.id("NRCSearchInput")).sendKeys(nrc); //search client
        driver.findElements(By.cssSelector("[type=button]")).get(4).click();
        driver.findElement(By.xpath("//a[contains(text(), \"Attend to Patient\")]")).click();
        driver.findElement(By.xpath("//p[contains(text(), \"Vital\")]")).click();
    }

    public void doLogOut(){
        btnDropDownToggle.get(2).click();
        btnLogOut.click();
    }
}
