package com.sandata;
import com.automation.ui.Window;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.sandata.pageObjects.LoginPage;


public class Main {
        public static Window window;
        public static void main(String... args) {
            WebDriver driver = new FirefoxDriver();
            Window.driver = driver;
            LoginPage loginPage = PageFactory.initElements(driver,
                    LoginPage.class);

            loginPage.login("george@sandata.com", "Test.1");
        }
}
