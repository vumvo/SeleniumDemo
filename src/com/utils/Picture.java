package com.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

	
public class Picture {
	public void capturePage(WebDriver driver, String pathDestination, String pictureName){
		String destFile = pathDestination+File.separator +pictureName+".png";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Cannot copy captured picture to the destination");
		}
	}
	
	
	public void captureElement(WebDriver driver, WebElement element,String pathDestination,String pictureName){
		String destFile = pathDestination+File.separator +pictureName+".png";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// Try to crop the screenshot based on the element top,left,width, height
		try {
			BufferedImage image = ImageIO.read(srcFile);
			BufferedImage subImage = image.getSubimage(element.getLocation().getX(), element.getLocation().getY()
													, element.getSize().getWidth(), element.getSize().getHeight());
			
			BufferedImage resizedImage = new BufferedImage(element.getSize().getWidth(),element.getSize().getHeight(),subImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(subImage, 0, 0, null);
			g.dispose();
			ImageIO.write(resizedImage, "png", new File(destFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.print(e.getMessage());
		}
	}
		
}
