package com.testcase;


import com.sandata.pageObjects.FindStaffPage;
import com.sandata.pageObjects.HomePage;
import com.utils.Contants;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
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

    @BeforeSuite(alwaysRun = true)
    public void setupBeforeSuite(ITestContext context){
        String seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
        String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
        //String timeout = context.getCurrentXmlTest().getParameter("selenium.implicitTimeout");
        //Logger logger = Logger.getLogger(LoginTestCase.class);

        Window.initTest(seleniumBrowser);
        driver = Window.driver;
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
    }

    @Test(description="User is able to search an existing staff")
    public void searchExistingStaff(){
        FindStaffPage findStaffPage = PageFactory.initElements(driver, FindStaffPage.class);
        findStaffPage.searchStaff("KMS_Staff", null, null, null);
        findStaffPage.checkStaffExist(1,"KMS_Staff");
        HomePage homePage = PageFactory.initElements(driver,
                HomePage.class);
        homePage.logout();
    }


    @DataProvider(name = "InvalidCredential")
    public String[][] createInvalidCredentialData(){
        return new String[][]{
                {"george@sandata.com","",null,"The Password field is required."},
                {"","Test.1","The Email field is required.",null},
                {"george1@sandata.com","Test.2","",""}
            };
    }

    @Test(description="Login with Invalid credentials", dataProvider = "InvalidCredential")
    public void loginWithInvalidCredential(String uname, String pwd, String emailError, String pwdError){
        LoginPage loginPage = PageFactory.initElements(driver,
                LoginPage.class);
        loginPage.loginWithInvalidCredential(uname, pwd, emailError, pwdError);
    }

    @Test(description="Login with Mismatch Element")
    public void loginWithMismatchElement(){
        LoginPage loginPage = PageFactory.initElements(driver,
                LoginPage.class);
        loginPage.loginWithMismatchElement("george@sandata.com", "Test.1");
    }

    @Test(description="Login with Mismatch Element 2")
    public void loginWithMismatchElement2(){
        LoginPage loginPage = PageFactory.initElements(driver,
                LoginPage.class);
        loginPage.loginWithMismatchElement2("george@sandata.com", "Test.1");
    }
}
