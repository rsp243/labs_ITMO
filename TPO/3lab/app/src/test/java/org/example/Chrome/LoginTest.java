package org.example.Chrome;

import static org.example.Utils.fluentWait;
import static org.example.Utils.fluentWaitForClickable;

import java.util.HashMap;
import java.util.Map;

import org.example.config.ChromeConfig;
import org.example.config.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    private final String EMAIL = ConfigReader.getProperty("email");
    private final String PASSWORD = ConfigReader.getProperty("password");

    @BeforeEach
    public void setUp() {
        driver = ChromeConfig.getChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        driver.get("https://www.dropbox.com/");
        driver.manage().window().setSize(new Dimension(1920, 1080));

    }

    @AfterEach
    public void tearDown() {       
        new LogoutTest().logout(driver);
        
        driver.quit();
    }

    public void login(WebDriver driver) {
        fluentWaitForClickable(driver, By.xpath("//div/div/div[2]/a/div/span"));

        fluentWait(driver, By.xpath(
                "//div[@id='login-or-register-page-content']/div/div/div/div/div/div[2]/div/div[3]/form/div/div/input"))
                .sendKeys(EMAIL);

        fluentWaitForClickable(driver,
                By.xpath(
                        "//div[@id='login-or-register-page-content']/div/div/div/div/div/div[2]/div/div[3]/form/div[2]/div/button"));

        fluentWait(driver, By.xpath(
                "//div[@id='login-or-register-page-content']/div/div/div/div/div/div[2]/div/div[3]/div/div/form/div[2]/div/input[3]"))
                .sendKeys(PASSWORD);

        fluentWaitForClickable(driver,
                By.xpath(
                        "//div[@id='login-or-register-page-content']/div/div/div/div/div/div[2]/div/div[3]/div/div/form/button"));

        fluentWait(driver, By.xpath("//button[@id=\'primary-button-menu-trigger\']/span/span"), 240, 15);
    }

    // @Test
    public void loginCorrect() {
        login(driver);
    }
}
