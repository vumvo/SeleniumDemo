package com.testcase;


import com.sandata.pageObjects.HomePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.automation.ui.Window;
import com.sandata.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.ui.Window;
import org.testng.log4testng.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by vumvo on 10/19/2015.
 */
public class LoginTestCase {

    WebDriver driver;
    Window window;

    @BeforeSuite(alwaysRun = true)
    public void setupBeforeSuite(ITestContext context){
        String seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
        String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
        String timeout = context.getCurrentXmlTest().getParameter("selenium.implicitTimeout");
        Logger logger = Logger.getLogger(LoginTestCase.class);

        switch  (seleniumBrowser) {
            case "ff": driver = new FirefoxDriver(); break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "libs\\IEDriverServer.exe");
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
                ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
                //driver = new InternetExplorerDriver(); break;
                driver = new InternetExplorerDriver(InternetExplorerDriverService.createDefaultService(), ieCapabilities);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "libs\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                System.err.println("Using default Firefox Driver");
                driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(timeout), TimeUnit.SECONDS);
        Window.driver = driver;
        Window.open(seleniumUrl);
    }

    @AfterSuite(alwaysRun = true)
    public void setupAfterSuite(){
        driver.quit();
    }

    @Test(description="Login with valid credential")
    public void loginWithValidCredential(){
        LoginPage loginPage = PageFactory.initElements(driver,
                LoginPage.class);
        loginPage.login("george@sandata.com", "Test.1");
        HomePage homePage = PageFactory.initElements(driver,
                HomePage.class);
        homePage.logout();
    }

    @Test(description="Login with Mismatch Element")
    public void loginWithMismatchElement(){
        LoginPage loginPage = PageFactory.initElements(driver,
                LoginPage.class);
        loginPage.loginWithMismatchElement("george@sandata.com", "Test.1");
    }
}
