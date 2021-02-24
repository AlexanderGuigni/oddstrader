package com.acourse.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class Base {

	public WebDriver driver;
	public Logger log = Logger.getLogger("devpinoyLogger");
	public Properties config = new Properties();
	public Properties objects = new Properties();
	public WebDriverWait wait;
	public Actions act;
	
	
	@BeforeSuite
	public void setUp() {
		
		loadPropertiesFiles();
		
		if (config.getProperty("browser").equals("chrome"))
		{
			SetChromeDriverConfiguration();
		}
		
		/*if (config.getProperty("browser").equals("firefox"))
		{}
		
		if (config.getProperty("browser").equals("edge"))
		{}
		
		if (config.getProperty("browser").equals("safary"))
		{}*/
		
		driver.get(config.getProperty("url"));
		log.debug("Url '"+ config.getProperty("url") + "' loaded");//Adding Log
		if (config.getProperty("customsize").equals("true")) 
		{			
			Dimension windowSize = new Dimension(Integer.parseInt(config.getProperty("height")),Integer.parseInt(config.getProperty("width")));
			driver.manage().window().setSize(windowSize);
			log.debug("Browser window resized (width = "+ config.getProperty("width") + "/ height = " + config.getProperty("height"));//Adding Log
		}
		else driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("timeout")), TimeUnit.SECONDS);
		log.debug("Browser window maximazed");
		
		wait = new WebDriverWait(driver,Integer.parseInt(config.getProperty("timeout")));
		act = new Actions(driver);
	}
	
	
	
	//------------------------
	
	private void SetChromeDriverConfiguration() {
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		if(config.getProperty("browser").equals("chrome"))
			options.addArguments("--incognito");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new ChromeDriver(options);
		log.debug("Chrome driver set and browser lunched");
	}
	
	//------------------------

	@AfterSuite
	public void tearDown() {
		
		if (driver!=null)
			driver.quit();
		
	}
	
	private void loadPropertiesFiles() {
			try {
			FileInputStream configFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			FileInputStream objectsFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Objects.properties");
			config.load(configFile);
			log.debug("Config file loaded");//Adding Log
			objects.load(objectsFile);
			log.debug("Objects file loaded");//Adding Log
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	
	
	
	
	
}
