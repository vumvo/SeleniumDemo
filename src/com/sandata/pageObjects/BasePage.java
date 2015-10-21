package com.sandata.pageObjects;

import com.automation.ui.Window;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * Created by vumvo on 10/21/2015.
 */
public class BasePage {
    String title;
    WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;

        if (title != null){
            Assert.assertEquals(Window.getWindowTitle(), title, "Expected Window title must be: " + title + ". Current title is ["+ Window.getWindowTitle()+"]");
        } else {
            Reporter.log("Current Window Title [" + Window.getWindowTitle() + "]", true);
        }
    }

}
