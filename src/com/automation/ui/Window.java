package com.automation.ui;

import com.google.common.base.Function;
import com.utils.Contants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.testng.ITestContext;

/**
 * Created by vumvo on 10/16/2015.
 */
public class Window {
    public static WebDriver driver;

    public static void initTest(String browserName){
        switch  (browserName) {
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
        // configure other settings
        driver.manage().timeouts().implicitlyWait(Contants.timeout,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Contants.timeoutPageload, TimeUnit.SECONDS);
    }

    public static  void open(String url){
        driver.get(url);
    }

    public static void switchToWindowTitle(final String title, int timeout){
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return doesWindowTitleExist(title);
            }
        });
    }

    public static boolean doesWindowTitleExist(String title){
        for (String handle: driver.getWindowHandles()){
            driver.switchTo().window(handle);
            if (driver.getTitle() == title) {
                return true;
            }
        }
        return false;
    }

    public static int countNumberOfWindow(){
           return driver.getWindowHandles().size();
    }

    public static void waitForNumberOfWindows(final int numberOfWindows, int timeout){
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
        wait.until(new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver){
                return  numberOfWindows == countNumberOfWindow();
            }
        });
    }

    public static String getWindowTitle(){
        return driver.getTitle();
    }

    public static void waitForPageLoad(int timeout){
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
        wait.until(new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver driver){
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
            }
        });
    }

    public static void waitForElementVisible(WebElement e, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(e));

    }

    public static void waitForElementClickable(WebElement e, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }

    public static void waitForElementClickable(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
