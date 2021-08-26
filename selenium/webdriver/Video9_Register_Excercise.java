package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Video9_Register_Excercise {
	WebDriver driver;

	// Element
	By nameField = By.xpath("//input[@id='txtFirstname']");
	By emailField = By.xpath("//input[@id='txtEmail']");
	By cEmailField = By.xpath("//input[@id='txtCEmail']");
	By passField = By.xpath("//input[@id='txtPassword']");
	By cPassField = By.xpath("//input[@id='txtCPassword']");
	By phoneField = By.xpath("//input[@id='txtPhone']");
	By register_btn = By.xpath("//button[@type='submit']");

	// Error message
	By errorNameField = By.xpath("//label[@id='txtFirstname-error']");
	By errorEmailField = By.xpath("//label[@id='txtEmail-error']");
	By errorcEmailField = By.xpath("//label[@id='txtCEmail-error']");
	By errorPassField = By.xpath("//label[@id='txtPassword-error']");
	By errorcPassField = By.xpath("//label[@id='txtCPassword-error']");
	By errorPhoneField = By.xpath("//label[@id='txtPhone-error']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC01_Register_With_Empty_Data() {
		System.out.println("TC01 - Register with empty data");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorNameField).getText(), "Vui lòng nhập họ tên");
		AssertJUnit.assertEquals(driver.findElement(errorEmailField).getText(), "Vui lòng nhập email");
		AssertJUnit.assertEquals(driver.findElement(errorcEmailField).getText(), "Vui lòng nhập lại địa chỉ email");
		AssertJUnit.assertEquals(driver.findElement(errorPassField).getText(), "Vui lòng nhập mật khẩu");
		AssertJUnit.assertEquals(driver.findElement(errorcPassField).getText(), "Vui lòng nhập lại mật khẩu");
		AssertJUnit.assertEquals(driver.findElement(errorPhoneField).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC02_Register_With_Invalid_Email() {
		System.out.println("TC02 - Register with invalid email");
		driver.findElement(nameField).sendKeys("Trang Le");
		driver.findElement(emailField).sendKeys("123@");
		driver.findElement(cEmailField).sendKeys("123@");
		driver.findElement(passField).sendKeys("123456");
		driver.findElement(cPassField).sendKeys("123456");
		driver.findElement(phoneField).sendKeys("0932073845");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorEmailField).getText(), "Vui lòng nhập email hợp lệ");
		AssertJUnit.assertEquals(driver.findElement(errorcEmailField).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC03_Register_With_Incorrect_Confirm_Email() {
		System.out.println("TC03 - Register with incorrect confirm email");
		driver.findElement(nameField).sendKeys("Trang Le");
		driver.findElement(emailField).sendKeys("trangle@gmail.com");
		driver.findElement(cEmailField).sendKeys("trangle@gmail.vn");
		driver.findElement(passField).sendKeys("123456");
		driver.findElement(cPassField).sendKeys("123456");
		driver.findElement(phoneField).sendKeys("0932073845");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorcEmailField).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC04_Register_With_Pass_Less_Than_6Characters() {
		System.out.println("TC04 - Register with password less than 6 characters");
		driver.findElement(nameField).sendKeys("Trang Le");
		driver.findElement(emailField).sendKeys("trangle@gmail.com");
		driver.findElement(cEmailField).sendKeys("trangle@gmail.com");
		driver.findElement(passField).sendKeys("123");
		driver.findElement(cPassField).sendKeys("123");
		driver.findElement(phoneField).sendKeys("0932073845");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorPassField).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		AssertJUnit.assertEquals(driver.findElement(errorcPassField).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
	}

	@Test
	public void TC05_Register_With_Incorrect_Confirm_Password() {
		System.out.println("TC05 - Register with incorrect confirm password");
		driver.findElement(nameField).sendKeys("Trang Le");
		driver.findElement(emailField).sendKeys("trangle@gmail.com");
		driver.findElement(cEmailField).sendKeys("trangle@gmail.com");
		driver.findElement(passField).sendKeys("123456");
		driver.findElement(cPassField).sendKeys("12345678");
		driver.findElement(phoneField).sendKeys("0932073845");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorcPassField).getText(), "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC06_Register_With_Invalid_Phone_Number() {
		System.out.println("TC06 - Register with invalid phone number");
		driver.findElement(nameField).sendKeys("Trang Le");
		driver.findElement(emailField).sendKeys("trangle@gmail.com");
		driver.findElement(cEmailField).sendKeys("trangle@gmail.com");
		driver.findElement(passField).sendKeys("123456");
		driver.findElement(cPassField).sendKeys("123456");
		driver.findElement(phoneField).sendKeys("e");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorPhoneField).getText(), "Vui lòng nhập con số");
		driver.findElement(phoneField).clear();
		driver.findElement(phoneField).sendKeys("0939968");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorPhoneField).getText(),"Số điện thoại phải từ 10-11 số.");
		driver.findElement(phoneField).clear();
		driver.findElement(phoneField).sendKeys("123");
		driver.findElement(register_btn).click();
		AssertJUnit.assertEquals(driver.findElement(errorPhoneField).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
