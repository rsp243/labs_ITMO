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

public class LogoutTest {
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
        
        new LoginTest().login(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    public void logout(WebDriver driver) {
        fluentWait(driver, By.xpath("//button[@id=\'primary-button-menu-trigger\']/span/span"));

        fluentWaitForClickable(driver, By.xpath("//div[4]/div/div/div/div/div/button/div/div/div"));
        fluentWaitForClickable(driver, By.xpath("//a[4]/div/div"));
    }

    // @Test
    public void logoutTest() {
        fluentWait(driver, By.xpath("//button[@id=\'primary-button-menu-trigger\']/span/span"), 240, 15);

        logout(driver);
    }
}
