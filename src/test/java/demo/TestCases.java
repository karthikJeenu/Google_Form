package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import demo.wrappers.Wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
        ChromeDriver driver;
        Wrappers wrapper;

        @Test
        public void testCase01() throws InterruptedException {
                // navigate to google form link
                driver.get(
                                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
               
          Assert.assertEquals(driver.getTitle(), "QA Assignment - Automate Google Forms", "Page title did not match!");
               // Entering name and why are you practicing
                WebElement name = driver.findElement(
                                By.xpath("//div[contains(@class,'k3kHxc')]//div//div//div//input"));
                 Assert.assertTrue(name.isDisplayed(), "Name input field is not displayed.");
                wrapper.Entertext(name, "Crio Learner");

                long epochTime = Instant.now().getEpochSecond();
                WebElement reason = driver.findElement(By.xpath("//textarea[contains(@class,'tL9Q4c')]"));
                String text = "I want to be the bestg QA Engineer! " + epochTime;
                Assert.assertTrue(reason.isDisplayed(), "Reason textarea is not displayed.");
                wrapper.Entertext(reason, text);

                // selecting practice,skills,title
                WebElement experience = driver.findElement(By.xpath("//*[@id=\'i19\']/div[3]/div"));
                wrapper.waitandclick(experience);
                WebElement java = driver.findElement(By.xpath("//div[@class='eBFwI'][1]"));
                wrapper.waitandclick(java);
                WebElement Selenium = driver.findElement(By.xpath("//div[@class='eBFwI'][2]"));
                wrapper.waitandclick(Selenium);
                WebElement TestNG = driver.findElement(By.xpath("//div[@class='eBFwI'][4]"));
                wrapper.waitandclick(TestNG);

                WebElement choose_title = driver.findElement(
                                By.xpath("//*[@id='mG61Hd']/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div[1]/div[1]"));
                wrapper.waitandclick(choose_title);
                Thread.sleep(2000);
                WebElement Mr = driver
                                .findElement(By.xpath(
                                                "//*[@id=\'mG61Hd\']/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[2]/div[3]"));
                wrapper.waitandclick(Mr);

                // Setting date for 7 days back
                String currentDate = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("MMddyyyy"));
                WebElement date = driver.findElement(By.xpath("//input[@type='date']"));
                System.out.println(currentDate);
                wrapper.Entertext(date, currentDate);

                Thread.sleep(2000);

                // Updating time
                WebElement Hour = driver.findElement(By.xpath(
                                "//*[@id='mG61Hd']/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/input"));
                wrapper.Entertext(Hour, "07");
                WebElement Minutes = driver.findElement(By.xpath(
                                "//*[@id='mG61Hd']/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[3]/div/div[1]/div/div[1]/input"));
                wrapper.Entertext(Minutes, "30");

                WebElement Submit_btn = driver.findElement(By.xpath(
                                "//div[contains(@class,'VkkpIf')]"));
                Assert.assertTrue(Submit_btn.isDisplayed(), "Submit button is not displayed.");
                wrapper.waitandclick(Submit_btn);
                Thread.sleep(3000);
                WebElement Sucess = driver.findElement(By.className(
                                "vHW8K"));
                wrapper.Success_Text(Sucess);
                System.out.println("Successfully completed");

        }

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
                wrapper = new Wrappers(driver);
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}