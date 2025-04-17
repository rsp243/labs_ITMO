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
import org.openqa.selenium.WebDriver;

public class DeleteFolderTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    private final String folder_name = "my_deleting_folder";
    
    @BeforeEach
    public void setUp() {
        driver = ChromeConfig.getChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        driver.get("https://www.dropbox.com/");
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // new LoginTest().login(driver);

        new CreateFolderTest().createFolder(driver, folder_name);
    }

    @AfterEach
    public void tearDown() {
        // new LogoutTest().logout(driver);

        driver.quit();
    }

    public void deleteFolder(WebDriver driver, String folder_name) {
        fluentWaitForClickable(driver, By.xpath("//div[2]/div[2]/div"));
        fluentWaitForClickable(driver, By.xpath("//span[text()='" + folder_name + "']"));
        fluentWaitForClickable(driver, By.xpath("//span/div/div/button/span"));
        fluentWaitForClickable(driver, By.xpath("//div[2]/div[5]/div[2]/div"));
        fluentWaitForClickable(driver, By.xpath("//div[3]/button[2]/span"));
    }

    public void deleteFolderTest() {
        fluentWait(driver, By.xpath("//span/div/div/div/div/div/div/button/span/span"));
        deleteFolder(driver, folder_name);

        driver.navigate().refresh();
        fluentWaitForClickable(driver, By.xpath("//div[2]/div[2]/div"));
        System.err.println(driver.findElements(By.xpath("//span[text()='" + folder_name + "']")).size());
    }
}
