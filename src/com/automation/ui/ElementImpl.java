package com.automation.ui;

import com.utils.Contants;
import org.apache.bcel.generic.BREAKPOINT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.NoSuchElementException;

/**
 * Created by vumvo on 10/20/2015.
 */
public class ElementImpl {

    By locator;
    WebElement element;

    public ElementImpl(By locator){
        this.locator = locator;
        try {
            this.element = Window.driver.findElement(locator);
        } catch (NoSuchElementException ex){
            StackTraceElement[] stack = ex.getStackTrace();

            for (StackTraceElement e : stack){
                String pageObjectPattern = ".pageObjects.";
                if (e.getClassName().contains(pageObjectPattern)){
                    String className = e.getClassName();
                    String message = className.substring(className.indexOf(pageObjectPattern)+pageObjectPattern.length());
                    message = "No such Element with locator [" +this.locator.toString() + "] - Page: " + message + "!!!";
                    throw new NoSuchElementException(message);
                }
            }
            throw new NoSuchElementException ("Cannot find the element with locator = "+ this.locator.toString());
        }
    }

    public ElementImpl(WebElement e){
        this.element = e;
    }

    public void click(Contants.MOUSE type){
        Window.waitForElementClickable(element, Contants.timeout);
        Actions action = new Actions(Window.driver);
        switch (type) {
            case LEFT:
                element.click(); break;
            case RIGHT:
                action.contextClick(element).build().perform();
                break;
            case MIDDLE:
                break;
            case DOUBLE:
                action.doubleClick(element).build().perform();
                break;
            default:
                element.click();
        }
    }

    public void enter(String txt){
        element.clear();
        element.sendKeys(txt);
    }

    public void setText(String txt){
        element.sendKeys(txt);
    }

    public void clear(){
        element.clear();
    }

    public void selectByVisibleText(String itemText){
        Select select = new Select(element);
        select.selectByVisibleText(itemText);
    }

    public void selectByIndex(int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }
}
