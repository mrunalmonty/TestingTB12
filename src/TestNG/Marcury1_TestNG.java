package TestNG;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Marcury1_TestNG {
	public WebDriver driver;
	Logger log = Logger.getLogger(Marcury1_TestNG.class.getClass());

	
	@BeforeSuite
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\JAVA TESTING\\Chrome95\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		log.info("open browser succesfuly");
	}

	@BeforeTest
	public void EnterApplicatiobURL() {
		driver.get("http://demo.guru99.com/test/newtours/");
		log.info("Url succesfully");
	}

	@BeforeClass
	public void MaximizeWindow() {
		driver.manage().window().maximize();
		System.out.println("Maiximize windows sucessfully");
	}

	@BeforeMethod
	public void beforeMethod() {
		Set<Cookie> cook=driver.manage().getCookies();
		for (Cookie cookie : cook) {
			System.out.println("name of the cookie"+cookie);
		}
	}

	@Test
	public void loginwithvalddetails() throws IOException {
		
		FileInputStream in = new FileInputStream("D:\\JAVA TESTING\\MarcuryLogin.properties");
		Properties pr = new Properties();
		pr.load(in);
		
		driver.findElement(By.name("userName")).sendKeys(pr.getProperty("uname"));
		driver.findElement(By.name("password")).sendKeys(pr.getProperty("pass"));
		driver.findElement(By.name("submit")).click();
		System.out.println("Succesfuully login");
	}
	
	

	@AfterMethod
	public void TakesScreenshot() throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File("D:\\JAVA TESTING"));
		System.out.println("Screenshot Takes successfully");

	}

	@AfterClass
	public void DeleteCookies() {
		driver.manage().deleteAllCookies();
		System.out.println("Delete all coookies");

	}

	@AfterTest
	public void dbConnectionClose() {
		System.out.println("dbconncetion Close");

	}

	@AfterSuite
	public void CloseBrowser() {
		//driver.close();
		System.out.println("Close all browser");
	}
}
