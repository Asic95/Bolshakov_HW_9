package Home_work_9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Bolshakov_HW_9 {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL = "http://prestashop.qatestlab.com.ua/en/authentication?back=my-account#account-creation";
    private static final String EMAIL1 = "test@testh2.com";
    private static final String EMAIL2 = "test@test.test";
    private static final String EMAIL3 = "111";

    @BeforeClass
    private void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void openUrl() {
        driver.get(URL);
    }

    @Test(priority = 1, testName = "Перевірка реєстрації")
    public void createAnAccount() {
        enterEmail(EMAIL1);
        buttonClick();
        Assert.assertEquals(findResultText(true), "CREATE AN ACCOUNT", "Text not found!");
    }

    @Test(priority = 2, testName = "Вже зареєстрований email")
    public void alreadyExistsEmail() {
        enterEmail(EMAIL2);
        buttonClick();
        Assert.assertEquals(findResultText(false), "An account using this email address has already been registered. Please enter a valid password or request a new one.", "Text not found!");
    }

    @Test(priority = 3, testName = "Некоректний email")
    public void incorrectEmail() {
        enterEmail(EMAIL3);
        buttonClick();
        Assert.assertEquals(findResultText(false), "Invalid email address.", "Text not found!");
    }

    public void enterEmail(String email) {
        driver.findElement(By.name("email_create")).sendKeys(email);
    }

    public void buttonClick() {
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public String findResultText(boolean create) {

        List<WebElement> text;

        if (create) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#account-creation_form > div.account_creation > h3"))); // очікуємо на появу тексту "YOUR PERSONAL INFORMATION", якого немає на стартовій сторінці
            text = driver.findElements(By.cssSelector("#noSlide > h1"));
        } else {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"create_account_error\"]/ol/li")));
            text = driver.findElements(By.xpath("//*[@id=\"create_account_error\"]/ol/li"));
        }

        return text.get(0).getText();
    }

    @AfterMethod
    public void pauseBetweenTests() throws InterruptedException {
        Thread.sleep(3000); // пауза перед виконанням наступного тесту
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}