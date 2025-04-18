// Generated by Selenium IDE
package org.example;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class UploadfileTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void uploadfile() {
    driver.get("https://www.dropbox.com/home");
    driver.manage().window().setSize(new Dimension(1920, 1080));
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/div/div/div/div/div/div/button/span/span")));
    }
    driver.findElement(By.xpath("//button[@id=\'primary-button-menu-trigger\']/span/span")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//nav/div/div/div/div/div/div[2]/div/span")));
    }
    driver.findElement(By.xpath("//nav/div/div/div/div/div/div[2]/div/span")).click();
    driver.findElement(By.xpath("//input[@id=\'uploader-file-field\']")).sendKeys("/home/reshetovs/Documents/viTMO/labs/reports/TPO/lab1_Reshetov.odt");
  }
}
