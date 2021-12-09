package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.server.Authentication.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video22_Topic09_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String chromeAutoIT = projectPath + "\\autoIT\\authen_chrome.exe";
	String firefoxAutoIT = projectPath + "\\autoIT\\authen_firefox.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC05_Verify_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
	}

	@Test
	public void TC06_Verify_Confirm_Alert_Accept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		sleepInSecond(3);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Ok");

	}

	@Test
	public void TC06_Verify_Confirm_Alert_Cancel() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		sleepInSecond(3);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC07_Verify_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		sleepInSecond(3);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("Verify the prompt alert.");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You entered: Verify the prompt alert.");

	}

	@Test
	public void TC08_Verify_Auth_Alert_By_Pass() {
		String userName = "admin";
		String passWord = "admin";
		String url = "http://" + userName + ":" + passWord + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@id='content']//p[contains (text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	@Test
	public void TC09_Verify_Auth_Alert_By_AuotoIT() throws IOException {
		String userName = "admin";
		String passWord = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { chromeAutoIT, userName, passWord });
		} else {
			Runtime.getRuntime().exec(new String[] { firefoxAutoIT, userName, passWord });
		}

		driver.get(url);
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@id='content']//p[contains (text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	@Test
	public void TC10_Verify_Auth_Alert_By_Get_Link() {
		String userName = "admin";
		String passWord = "admin";
		driver.get("http://the-internet.herokuapp.com");
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(basicAuthenLink);
		driver.get(getLinkByPassUser(basicAuthenLink, userName, passWord));
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@id='content']//p[contains (text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	public String getLinkByPassUser(String link, String userName, String passWord) {
		String[] links = link.split("//");
		link = links[0] + "//" + userName + ":" + passWord + "@" + links[1];
		return link;

	}

	public void sleepInSecond(long timeOutInSecond) {
		try {
			Thread.sleep(timeOutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
