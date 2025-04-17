package org.example.Chrome;

import static org.example.Utils.fluentWait;
import static org.example.Utils.fluentWaitForClickable;

import java.util.HashMap;
import java.util.Map;

import org.example.config.ChromeConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateFolderTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    private final String folder_name = "my_folder";

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
        new DeleteFolderTest().deleteFolder(driver, folder_name);

        new LogoutTest().logout(driver);

        driver.quit();
    }

    public void createFolder(WebDriver driver, String folder_name) {
        fluentWaitForClickable(driver, By.xpath("//div[2]/div/div/div/button/span/span"));
        fluentWaitForClickable(driver, By.xpath("//div/div/div[2]/div/div[2]/div/span"));

        fluentWaitForClickable(driver, By.xpath("//div[8]/div/div/nav/div/div/div/div/div"));
        fluentWait(driver, By.xpath("//div[@id=\'cdm-create-folder-modal\']/div/span/input")).sendKeys(folder_name);
        fluentWait(driver, By.xpath("//div[@id=\'cdm-create-folder-modal\']/div/span/input")).sendKeys(Keys.ENTER);
    }

    @Test
    public void createFolder() {
        createFolder(driver, folder_name);

        fluentWaitForClickable(driver, By.xpath("//div[2]/div[2]/div"));
        WebElement element_res = fluentWait(driver, By.xpath("//span[text()='" + folder_name + "']"));

        assert (element_res.getText() == folder_name);
    }
}
