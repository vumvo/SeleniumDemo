package com.sandata.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.automation.ui.Window;
import org.testng.Reporter;

public class LoginPage{

    String pageTitle = "Login";

	WebDriver driver;
	@FindBy(how=How.ID, using="Email")
	WebElement txtUserName;

	@FindBy(how=How.ID, using="Password")
	WebElement txtPassword;

    @FindBy(how=How.ID, using="Password2")
    WebElement txtPassword2;
	
	@FindBy(how=How.XPATH, using="//input[@value='Log in']")
	WebElement btnLogin;

    private int timeout = 10;
    	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		//driver.get("http://dev-lab-web01.sandata.com/");
        //Window.waitForPageLoad(timeout);
        Reporter.log("Current Window Title:  " + Window.getWindowTitle(),true);
    }
	
	public void login(String uname, String pwd){
            txtUserName.sendKeys(uname);
            txtPassword.sendKeys(pwd);
            btnLogin.click();
            Window.waitForPageLoad(timeout);
	}

    public void loginWithMismatchElement(String uname, String pwd){
        Reporter.log("Test Result");
        try {
            txtUserName.sendKeys(uname);
            txtPassword2.sendKeys(pwd);
            btnLogin.click();
            Window.waitForPageLoad(timeout);
        }catch (NoSuchElementException e){
            Reporter.log("khong tim thay element" + pageTitle, true);
        }
    }
}
