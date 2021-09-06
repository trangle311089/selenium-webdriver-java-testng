package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video14_Exercise04_Register_Function_At_MailChimp {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/");
	}

	@Test
	public void TC04_Register_Function_At_MailChimp() {
		WebElement passwordTextField = driver.findElement(By.id("new_password"));
		WebElement signUp_btn = driver.findElement(By.id("create-account"));
		WebElement marketing_checkbox = driver.findElement(By.xpath("//input[@id='marketing_newsletter']"));

		driver.findElement(By.id("email")).sendKeys("mailchimp@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("mailchimp");

		// Verify entering number on password field
		passwordTextField.sendKeys("1");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(signUp_btn.isEnabled());

		// Verify entering lower case on password field
		passwordTextField.clear();
		passwordTextField.sendKeys("a");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(signUp_btn.isEnabled());

		// Verify entering upper case on password field
		passwordTextField.clear();
		passwordTextField.sendKeys("A");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(signUp_btn.isEnabled());

		// Verify entering special character on password field
		passwordTextField.clear();
		passwordTextField.sendKeys("@");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(signUp_btn.isEnabled());

		// Verify entering more than 8 characters on password field
		passwordTextField.clear();
		passwordTextField.sendKeys("123456789");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertFalse(signUp_btn.isEnabled());
		
		// Verify marketing checkbox is selected when entering valid password
		passwordTextField.clear();
		passwordTextField.sendKeys("12345678x@X");
		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		marketing_checkbox.click();
		Assert.assertTrue(marketing_checkbox.isSelected());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
