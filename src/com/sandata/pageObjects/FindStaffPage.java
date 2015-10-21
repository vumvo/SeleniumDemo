package com.sandata.pageObjects;

import com.automation.ui.ElementImpl;
import com.automation.ui.TableImpl;
import com.automation.ui.Window;
import com.utils.Contants;
import javafx.scene.control.Tab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vumvo on 10/21/2015.
 */
public class FindStaffPage extends BasePage{
    By txtFirstName = By.id("FirstName");
    By txtLastName = By.id("LastName");
    By txtHomePhone = By.id("HomePhone");
    By txtStaffID = By.id("StaffID");
    By btnGo = By.id("searchStaff");
    By tblStaff = By.id("viewStaffGrid");

    public FindStaffPage(WebDriver driver){
        super(driver);
    }

    public void searchStaff(String firstName, String lastName, String homePhone, String staffID){
        if (firstName != null){
            ElementImpl eFirstName=  new ElementImpl(txtFirstName);
            eFirstName.enter(firstName);
        }
        if (lastName != null){
            ElementImpl eLastName=  new ElementImpl(txtLastName);
            eLastName.enter(lastName);
        }
        if (homePhone != null){
            ElementImpl eHomePhone=  new ElementImpl(txtHomePhone);
            eHomePhone.enter(homePhone);
        }
        if (staffID != null){
            ElementImpl eStaffID=  new ElementImpl(txtStaffID);
            eStaffID.enter(staffID);
        }
        ElementImpl eGo = new ElementImpl(btnGo);
        eGo.click(Contants.MOUSE.LEFT);
        Window.waitForElementClickable(tblStaff, Contants.timeout);
    }

    public void checkStaffExist(int rowIndex, String firstName){
        TableImpl tbl = new TableImpl(tblStaff);
        tbl.checkTableCellText(rowIndex, 2, firstName);
    }



}
