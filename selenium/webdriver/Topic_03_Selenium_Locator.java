package webdriver;

import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class Topic_03_Selenium_Locator {
		WebDriver driver;

		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		}

		@Test
		public void TC_01_findElementByID() {
			driver.findElement(By.id("email")).sendKeys("a@gmail.com");
			
		}

		@Test
		public void TC_02_findElementByName(){
			driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		}

		@Test
		public void TC_03_findElementByClassName() {
			driver.findElement(By.className("large")).click();
		}
		
		@Test
		public void TC_04_findElementByLinkText() {
			driver.findElement(By.linkText("ABOUT US")).click();
		}
		
		@Test
		public void TC_05_findElementByPartialLinkText() {
			driver.findElement(By.partialLinkText("ORDERS AND")).click();
		}
		
		@Test
		public void TC_06_findElementByTagName() {
			System.out.println("Links on page = " + driver.findElements(By.tagName("a")).size());          
		}

		@Test
		public void TC_07_findElementByCssID() {
			driver.findElement(By.cssSelector("input[id='oar_order_id']")).sendKeys("ID_1001");
		}
		
		@Test
		public void TC_08_findElementByCssName() {
			driver.findElement(By.cssSelector("input[name ='oar_billing_lastname']")).sendKeys("Name_1001");
		}
		
		@Test
		public void TC_09_findElementByCssClassName() {
			driver.findElement(By.cssSelector("div[class ='page-title']"));
		}
		
		@Test
		public void TC_10_findElementByXPathID() {
			driver.findElement(By.xpath("//input[@id='oar_email']")).sendKeys("Email_1001");
		}
		
		@Test
		public void TC_11_findElementByXPathName() {
			driver.findElement(By.xpath("//input[@name='oar_order_id']")).clear();
		}
		
		@Test
		public void TC_12_findElementByXPathClassName() {
			driver.findElement(By.xpath("//div[@class='page-title']"));
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

	}
