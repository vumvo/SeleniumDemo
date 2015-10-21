package com.utils;

/**
 * Created by VUMVO on 10/20/2015.
 */
public class Contants {
    public static String url = "http://dev-lab-web01.sandata.com/";
    public static int timeout = 3;
    public static int timeoutPageload = 60;
    public static BrowserType browser = BrowserType.FF;

    public enum BrowserType {
        IE, FF, CHROME
    }


}
