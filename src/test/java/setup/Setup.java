package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.CareProPage;

import java.time.Duration;

public class Setup {
    public WebDriver driver;
    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://carepro-training.ihmafrica.com/");
    }
    @AfterTest
    public void quitBrowser(){
        CareProPage careProPage=new CareProPage(driver);
        careProPage.doLogOut();
        driver.quit();
    }
}
