package Home_work_9;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Bolshakov_HW_12_part2 extends BaseTestClass {

    @Test(testName = "Перевірка тексту в CONTACT US")
    public void createAnAccount() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        js.executeScript("document.querySelector(\"#block_various_links_footer > ul > li:nth-child(5) > a\").click()");
        actualResult = (String) js.executeScript("return document.querySelector(\"#center_column > h1\").innerText");
        Assert.assertEquals(actualResult, "CUSTOMER SERVICE - CONTACT US", "Text not found!");
    }
}