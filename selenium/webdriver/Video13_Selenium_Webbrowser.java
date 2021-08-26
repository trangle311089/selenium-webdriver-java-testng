package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Video13_Selenium_Webbrowser {
	WebDriver driver;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC01_Verify_Current_URL() {
		driver.findElement(By.xpath("//a[@href='/login/']")).click();
		String loginPageURL = driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "https://www.facebook.com/login/");
		driver.findElement(By.xpath("//a[@href='/r.php']")).click();
		String registerPageURL = driver.getCurrentUrl();
		Assert.assertEquals(registerPageURL, "https://www.facebook.com/r.php");
	}

	@Test
	public void TC02_Verify_Current_Title() {
		driver.findElement(By.xpath("//a[@href='/login/']")).click();
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Đăng nhập Facebook");
		driver.findElement(By.xpath("//a[@href='/r.php']")).click();
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Đăng ký Facebook | Facebook");
	}

	@Test
	public void TC03_Verify_Navigate_Function_Back_Forward() {
		driver.findElement(By.xpath("//a[@href='/login/']")).click();
		driver.findElement(By.xpath("//a[@href='/r.php']")).click();
		String registerPageURL = driver.getCurrentUrl();
		Assert.assertEquals(registerPageURL,"https://www.facebook.com/r.php");
		driver.navigate().back();
		String loginPageURL = driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "https://www.facebook.com/login/");
		driver.navigate().forward();
		Assert.assertEquals(registerPageURL,"https://www.facebook.com/r.php");
	}

	@Test
	public void TC04_Verify_Get_SourcePage() {
		driver.findElement(By.xpath("//a[@href='/login/']")).click();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue((loginPageSource).contains("Đăng nhập Facebook"));
		driver.findElement(By.xpath("//a[@href='/r.php']")).click();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue((registerPageSource).contains("Đăng ký Facebook"));	
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
