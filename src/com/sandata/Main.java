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
//            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32_2.2\\chromedriver.exe");
            WebDriver driver = new FirefoxDriver();
            window = new Window(driver);
            LoginPage loginPage = PageFactory.initElements(driver,
                    LoginPage.class);

            loginPage.login("george@sandata.com", "Test.1");
        }
}
