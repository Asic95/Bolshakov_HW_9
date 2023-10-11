package Home_work_9;

import org.testng.Assert;
import org.testng.annotations.*;

public class Bolshakov_HW_12 extends BaseTestClass {

    private static final String EMAIL1 = "test@testh2.com";
    private static final String EMAIL2 = "test@test.test";
    private static final String EMAIL3 = "111";

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
}