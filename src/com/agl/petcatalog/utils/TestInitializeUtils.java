package com.agl.petcatalog.utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class TestInitializeUtils {

	public WebDriver initializeDriver() 
	{
		WebDriver driver=null;
		for(int x=0;x<3;x++){
			try{
				String browser=ConfigFileUtils.readConfigFile("Selenium_Test_Browser", "./test.properties").toLowerCase();
				String baseSite=ConfigFileUtils.readConfigFile("Selenium_Test_Url", "./test.properties");
				if(browser.contains("ff")||browser.contains("firefox")||browser.contains("fire")){
					DesiredCapabilities firefoxCapability=DesiredCapabilities.firefox();
					driver=new FirefoxDriver(firefoxCapability);
				}
				else if(browser.contains("google")||browser.contains("chrome")||browser.contains("googlechrome")){
					DesiredCapabilities chromeCapability=DesiredCapabilities.chrome();
					  ChromeOptions options = new ChromeOptions();
			          options.addArguments("--disable-web-security");
	 
					System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
					driver=new ChromeDriver(chromeCapability);
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(70*20*1000,TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(70*20*1000,TimeUnit.SECONDS);
				driver.get(baseSite);
				break;
			}
			catch(Exception e){
				System.out.println(e);
				try{driver.close();}
				catch(Exception e1){}
				try{driver.quit();}
				catch(Exception e1){}
				if(x==3){
					throw new RuntimeException(e.toString());
				}
			}
		}
		return driver;
	}

	public  void closeDriver(WebDriver driver) 
	{
		try{
			try{
				driver.close();
			}
			catch(Exception e1){}
			driver.quit();
		}
		catch(Exception e){}
	}

	public static void onTestFailureScreenshot(WebDriver driver,String testCaseName,Exception exception) {
		System.out.println(testCaseName+" : Failed");
		// taking screenshot
		String path;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
			path = "./Fireball/build/test-output/screenshots/" + testCaseName+".png";
			FileUtils.copyFile(source, new File(path));
			Reporter.log(path);
		}
		catch(IOException e) {
			try{
				driver.close();
			}catch(Exception e1){}
			driver.quit();
			path = "Failed to capture screenshot: " + e.getMessage();
			Reporter.log(path);
		}
	}

	public static void onTestPass(String testCaseName){
		System.out.println(testCaseName+" : Passed");
	}

}
