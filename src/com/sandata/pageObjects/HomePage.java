package com.sandata.pageObjects;

import com.automation.ui.Window;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by vumvo on 10/16/2015.
 */
public class HomePage {
    private WebDriver driver;
    private int timeout = 10;

    @FindBy(how= How.LINK_TEXT, using="Log out")
    WebElement lnkLogout;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void logout(){
        lnkLogout.click();
        Window.waitForPageLoad(timeout);
    }
}
