package org.example.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxConfig {
    public static WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("profile", "/home/reshetovs/.mozilla/firefox/7jgb780a.default");
        options.setProfile(profile);
        
        System.setProperty("webdriver.firefox.marionette", "false");

        // 1. Disable automation detection flags
        profile.setPreference("marionette.enabled", false);
        profile.setPreference("dom.webdriver.enabled", false);
        profile.setPreference("useAutomationExtension", false);
        
        // 2. Disable the remote control notification
        profile.setPreference("remote.enabled", false);
        
        // 3. Additional stealth settings
        profile.setPreference("browser.privatebrowsing.autostart", true);
        profile.setPreference("privacy.trackingprotection.enabled", true);
        profile.setPreference("privacy.trackingprotection.socialtracking.enabled", true);
        profile.setPreference("privacy.resistFingerprinting", true);
        
        // 4. Disable safe browsing to prevent extra requests
        profile.setPreference("browser.safebrowsing.malware.enabled", false);
        profile.setPreference("browser.safebrowsing.phishing.enabled", false);
        
        options.setProfile(profile);
        
        // 5. Set additional capabilities
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        
        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }
}