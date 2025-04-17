package org.example.Chrome;

import static org.example.Utils.fluentWait;
import static org.example.Utils.fluentWaitForClickable;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class UploadfileTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

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
    new LogoutTest().logout(driver);
    driver.quit();
  }

  // @Test
  public void uploadfile() {
    fluentWait(driver, By.xpath("//span/div/div/div/div/div/div/button/span/span"));

    fluentWaitForClickable(driver, By.xpath("//span/div/div/div/div/div/div/button/span/span"));
    fluentWaitForClickable(driver, By.xpath("//nav/div/div/div/div/div/div[2]/div/span"));
    // fluentWait(driver, By.xpath("//input[@id=\'uploader-file-field\']"));
    // .sendKeys("/home/reshetovs/Documents/viTMO/labs/reports/TPO/lab1_Reshetov.odt");

    // /home/reshetovs/Documents/viTMO/labs/reports/TPO/lab1_Reshetov.odt
    fluentWait(driver, By.xpath("//span/div/div/div/div/div/div/button/span/span"));
    assertTrue(fluentWait(driver,
        By.xpath("//*[@id='files-view-table-container']/div/tr/td[2]/div[2]/div/div[1]/div/div/a/div/span"), 120, 10)
        .getText().equals("lab1_Reshetov.odt"));
  }
}
