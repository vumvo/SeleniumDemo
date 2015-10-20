package com.listener;

import com.automation.ui.Window;
import com.utils.Picture;
import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.utils.StringHelper;
/**
 * Created by vumvo on 10/20/2015.
 */
public class MyListener extends TestListenerAdapter{
//    private int m_count = 0;
//    @Override
//    public void onTestStart(ITestResult tr) {
//        log("Test Started....");
//    }

//    @Override
//    public void onTestSuccess(ITestResult tr) {
//
//        log("Test '" + tr.getName() + "' PASSED");
//
//        // This will print the class name in which the method is present
//        log(tr.getTestClass());
//
//        // This will print the priority of the method.
//        // If the priority is not defined it will print the default priority as
//        // 'o'
//        log("Priority of this method is " + tr.getMethod().getPriority());
//
//        System.out.println(".....");
//    }

    @Override
    public void onTestFailure(ITestResult tr) {
        String uniqueString = StringHelper.generateUniqueString();
        String fileName=tr.getName()+"_"+uniqueString;
        Picture picture = new Picture();
        picture.capturePage(Window.driver, "\\test-output", fileName);


    }

//    @Override
//    public void onTestSkipped(ITestResult tr) {
//        log("Test '" + tr.getName() + "' SKIPPED");
//        System.out.println(".....");
//    }

    private void log(String methodName) {
        System.out.println(methodName);
    }

    private void log(IClass testClass) {
        System.out.println(testClass);
    }

}
