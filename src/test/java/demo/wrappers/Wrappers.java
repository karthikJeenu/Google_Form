package demo.wrappers;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Wrappers {
    public WebDriver driver;
    public WebDriverWait wait;

    public Wrappers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // Wrapper for Success message 
     public void Success_Text(WebElement element) { 
        String Expected_Msg="Thanks for your response, Automation Wizard!";
         if (element.isDisplayed()) {
        String Success_msg=element.getText();
        System.out.println(Success_msg);
        Assert.assertEquals(Success_msg, Expected_Msg);
         }
     }


    // Wrapper for sending keys to an input field
    public void Entertext(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    // Wrapper for waiting for an element to be clickable
    public void waitandclick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}

