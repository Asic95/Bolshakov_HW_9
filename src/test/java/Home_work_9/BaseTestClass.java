package Home_work_9;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTestClass {

    private static final String URL = "http://prestashop.qatestlab.com.ua/en/authentication?back=my-account#account-creation";

    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    String actualResult = null;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
        js = (JavascriptExecutor) driver;
    }

    @BeforeMethod
    public void UrlOpen() {
        driver.get(URL);
    }

    @AfterMethod
    public void pauseBetweenTests() throws InterruptedException {
        Thread.sleep(3000); // пауза перед виконанням наступного тесту
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    public void enterEmail(String email) {
        String emailInput = "document.getElementById('email_create').value='" + email + "';";
        js.executeScript(emailInput);
    }

    public void buttonClick() {
        js.executeScript("document.getElementById('SubmitCreate').click()");
    }

    public String findResultText(boolean create) {

        if (create) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#account-creation_form > div.account_creation > h3"))); // очікуємо на появу тексту "YOUR PERSONAL INFORMATION", якого немає на стартовій сторінці
            actualResult = (String) js.executeScript("return document.getElementsByClassName('page-heading')[0].innerText;"); //textContent
        } else {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"create_account_error\"]/ol/li")));
            actualResult = (String) js.executeScript("return document.getElementById('create_account_error').innerText;"); //textContent
        }
        return actualResult;
    }
}
