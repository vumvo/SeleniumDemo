package com.automation.ui;

import com.utils.Contants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by vumvo on 10/21/2015.
 */
public class TableImpl extends ElementImpl {
    int headerIndex = 1;

    public TableImpl(By locator){
        super(locator);
    }

    public void clickTableCell(int rowIndex, int columnIndex, Contants.MOUSE type){
        String cellLocator = "descendant::tr[" + (rowIndex + headerIndex) + "]/td[" + columnIndex + "]";
        WebElement cell = this.element.findElement(By.xpath(cellLocator));
        new ElementImpl(cell).click(type);
    }

    public void checkTableCellText(int rowIndex, int columnIndex, String expectedText){
        String cellLocator = "descendant::tr[" + (rowIndex + headerIndex) + "]/td[" + columnIndex + "]";
        try {
            WebElement cell = this.element.findElement(By.xpath(cellLocator));
            String actualText = cell.getText();
            Assert.assertEquals(actualText, expectedText, "Expected text is [" + expectedText + "]. Actual text is ["+
                    actualText +"]");
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Cannot find the cell with index [" + rowIndex + "," + columnIndex+"]");
        }
    }

}
