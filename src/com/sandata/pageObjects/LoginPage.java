package com.sandata.pageObjects;

import com.automation.ui.ElementImpl;
import com.utils.Contants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.automation.ui.Window;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage extends BasePage{

    String pageTitle = "George | Login";

	WebDriver driver;
	@FindBy(how=How.ID, using="Email")
	WebElement txtUserName;

    @FindBy(how=How.ID, using="Email-error")
    WebElement lblEmailError;

	@FindBy(how=How.ID, using="Password")
	WebElement txtPassword;

    @FindBy(how=How.ID, using="Password-error")
    WebElement lblPasswordError;

    @FindBy(how=How.ID, using="Password2")
    WebElement txtPassword2;

	@FindBy(how=How.XPATH, using="//input[@value='Log in']")
	WebElement btnLogin;

	public LoginPage(WebDriver driver){
        super(driver);
        this.title = "George | Login";
    }
	
	public void login(String uname, String pwd){
            txtUserName.sendKeys(uname);
            txtPassword.sendKeys(pwd);
            btnLogin.click();
            Window.waitForPageLoad(Contants.timeoutPageload);
	}

    public void loginWithInvalidCredential(String uname, String pwd, String emailError, String pwdError){
        txtUserName.clear();
        txtUserName.sendKeys(uname);
        txtPassword.clear();
        txtPassword.sendKeys(pwd);
        btnLogin.click();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if (emailError != null){
            Window.waitForElementVisible(lblEmailError, Contants.timeout);
            Assert.assertEquals(lblEmailError.getText(), emailError, "The actual error '" + lblEmailError.getText()+"' isnot match with '" + emailError +"'");
        }
        if (pwdError != null){
            Window.waitForElementVisible(lblPasswordError, Contants.timeout);
            Assert.assertEquals(lblPasswordError.getText(), pwdError, "The actual error '" + lblPasswordError.getText()+"' isnot match with '" + pwdError +"'");
        }
//        Assert.assertEquals(driver.getTitle(), pageTitle);
    }

    public void loginWithMismatchElement(String uname, String pwd){
            txtUserName.sendKeys(uname);
            txtPassword2.sendKeys(pwd);
            btnLogin.click();
            Window.waitForPageLoad(Contants.timeoutPageload);
    }

    public void loginWithMismatchElement2(String uname, String pwd){
        txtUserName.sendKeys(uname);
        ElementImpl txtPassword2 = new ElementImpl(getBy("txtPassword2"));
        txtPassword2.click(Contants.MOUSE.LEFT);
    }

    private By getBy(String fieldName) {
        try {
            return new Annotations(this.getClass().getDeclaredField(fieldName)).buildBy();
        } catch (NoSuchFieldException e) { return null; }
    }
}
