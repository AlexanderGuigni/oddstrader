package com.acourse.base;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class General extends Base {
	
	public void waitOneOfTheElements(By[] Targets)
	{
		int Cont = 0;
		boolean Exist = false;
		
		do {
			for (By Target : Targets)
			{
				Exist = exist(Target,0);
				
				if (Exist)
					break;
			
			}
			
			if (Exist)
				break;
			else
				pause(1);
			
			Cont++;
			
			if (Cont == Integer.parseInt(config.getProperty("timeout")))
				Assert.fail("Any of the expected elements was found");
				
		}while (Exist = false);

	}
	
	public void scrollIntoView(By target) {
		WebElement element = driver.findElement(target);
		JavascriptExecutor jExecutor = (JavascriptExecutor) this.driver;
		jExecutor.executeScript("arguments[0].scrollIntoView(true);", element);	
		pause(1);
	}
	
	public boolean exist (By Target, int timeOut)
	{
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		boolean exist;
		try {
			elementExist(Target);
			exist = true;
		}
		catch(Exception e){
			exist = false;
		}
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("timeout")), TimeUnit.SECONDS);

		return exist;
	}
	
	public void waitTheElementsIsNotDisplayed(By Target, int timeOut)
	{
		int Cont = 0;
		boolean Exist = false;
		
		do {
			
			Exist = exist(Target,0);
						
			if (!Exist)
				break;
			else
				pause(1);
			
			Cont++;
			
			if (Cont == timeOut)
				Assert.fail("TimeOut The Target never dissapear");
				
		}while (Exist = true);

	}

	
	public void elementExist(By Target)
	{
		driver.findElement(Target);
	}
	
	public String getAtributeValue(By Target, String Atribute)
	{
		String Value = driver.findElement(Target).getAttribute(Atribute);
		driver.findElement(Target).getText();
		
		return Value;
	}
	
	public String getTextValue(By Target)
	{
		String Value = driver.findElement(Target).getText();
		return Value;
	}

	public void elementExist(String Element, int timeout)
	{
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
		driver.findElement(By.xpath(Element));
	}

	public boolean exist (By Target)
	{
		boolean exist;
		try {
			elementExist(Target);
			exist = true;
		}
		catch(Exception e){
			exist = false;
		}

		return exist;

	}
	
	public void sendKeys (By target, String text)
	{
		driver.findElement(target).sendKeys(text);
	}
	
	public void sendKeys (By target, Keys key)
	{
		driver.findElement(target).sendKeys(key);
	}
	
	public void waitElementClickable(By target)
	{
		wait.until(ExpectedConditions.elementToBeClickable(target));
	}
	
	public void waitElement(By target)
	{
		//wait.until(ExpectedConditions.visibilityOfElementLocated(target));
		wait.until(ExpectedConditions.presenceOfElementLocated(target));
	}
	
	
	public void click(By target)
	{
		waitElementClickable(target);
		driver.findElement(target).click();
	}
	
	public void pause (int seconds)
	{
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String sumSubDays(Date date, int days ) 
	{		
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		
		return formater.format(calendar.getTime());
	}
	

}
