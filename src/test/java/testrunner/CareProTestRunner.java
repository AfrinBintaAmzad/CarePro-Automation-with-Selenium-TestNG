package testrunner;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CareProPage;
import setup.Setup;
import utils.DataSet;
import utils.Utils;

import java.time.Duration;


public class CareProTestRunner extends Setup {
    @BeforeTest
    public void login() throws InterruptedException {
        CareProPage careProPage=new CareProPage(driver);
        careProPage.doLogin("tester","tester2023!");
    }
    @Test(priority = 1, description = "Select doctors chamber")
    public void selectDoctorsChamber(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-secondary")));

        Select select=new Select(driver.findElement(By.id("ProvinceId")));
        select.selectByVisibleText("Lusaka");

        select =new Select(driver.findElement(By.id("DistrictId")));
        select.selectByVisibleText("Lusaka");

        select =new Select(driver.findElement(By.id("FacilityId")));
        select.selectByVisibleText("Dr. Watson Dental Clinic");

        driver.findElement(By.cssSelector("[type=submit]")).click();
    }
    @Test(priority = 2, dataProvider = "data-provider", dataProviderClass = DataSet.class)
    public void addPatientDetails(String nrc, String time, double weight, double height, int temp, int sys, int dia, int pulse, int res, int ox ) throws InterruptedException {
        CareProPage careProPage=new CareProPage(driver);
        careProPage.searchClient(nrc);

        driver.findElement(By.className("bi-plus-circle")).click(); //add new record
        WebElement dateInput = driver.findElement(By.name("date"));
        dateInput.click();
        driver.findElement(By.className("ui-state-highlight")).click();

        driver.findElement(By.name("time")).sendKeys(time);
        WebElement weightElement=driver.findElement(By.id("Weight"));
        weightElement.clear();
        weightElement. sendKeys(String.valueOf(weight));
        WebElement heightElement=driver.findElement(By.id("Height"));
        heightElement.clear();
        heightElement.sendKeys(String.valueOf(height));
        driver.findElement(By.id("Temperature")).sendKeys(String.valueOf(temp));
        driver.findElement(By.id("SystolicForCreate")).sendKeys(String.valueOf(sys));
        driver.findElement(By.id("DiastolicForCreate")).sendKeys(String.valueOf(dia));
        driver.findElement(By.id("PulseRate")).sendKeys(String.valueOf(pulse));
        driver.findElement(By.id("RespiratoryRate")).sendKeys(String.valueOf(res));
        driver.findElement(By.id("OxygenSaturation")).sendKeys(String.valueOf(ox));
        Utils.scrollDown(driver);
        driver.findElement(By.cssSelector("[type=submit]")).click(); //click save button
        Utils.scrollDown(driver);
        driver.findElement(By.className("justify-content-center")).click(); //click finish button
        driver.findElement(By.xpath("//span[contains(text(),\"Client Search\")]")).click(); //click search icon
    }

}