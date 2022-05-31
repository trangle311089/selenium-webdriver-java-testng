package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video16_Handle_Textbox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String email, userID, password, loginURLPage, customerID;
	String newCustomerName, newDateOfBirth, newAddress, newCity, newState, newPin, newMobileNumber, newEmail,
			newPassword;
	String genderOutput, dateOfBirthOutput, addressOutput;
	String editAddress, editCity, editState, editPin, editMobileNumber, editEmail, editAddressOutput;

	By customerName_txt = By.name("name");
	By gender_radio_btn = By.xpath("//input[@value='f']");
	By dob_txt = By.id("dob");
	By address_textarea = By.name("addr");
	By city_txt = By.name("city");
	By state_txt = By.name("state");
	By pin_txt = By.name("pinno");
	By mobile_txt = By.name("telephoneno");
	By email_txt = By.name("emailid");
	By pass_txt = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");

		email = "trangle" + getRandomNumber() + "@gmail.com";

		newCustomerName = "Trang Le";
		newDateOfBirth = "09/10/2021";
		newAddress = "123 Quang Trung\n Quan 12";
		newCity = "Ho Chi Minh";
		newState = "VN";
		newPin = "123456";
		newMobileNumber = "0905968369";
		newEmail = "trangle" + getRandomNumber() + "@gmail.com";
		newPassword = "123456";
		
		genderOutput = "female";
		dateOfBirthOutput = "2021-09-10";
		addressOutput = "123 Quang Trung Quan 12";
		
		editAddress = "12345 Quang Trung\n Quan 12";
		editCity = "HCM";
		editState = "VietNam";
		editPin = "224466";
		editMobileNumber = "0780000001";
		editEmail = "trangle_edit" + getRandomNumber() + "@gmail.com";
		
		editAddressOutput = "12345 Quang Trung Quan 12";
		

	}

	@Test
	public void TC01_Register() {
		loginURLPage = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		System.out.println("TC01_Register with email");
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC02_Login() {
		driver.get(loginURLPage);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		String homepageURL = driver.getCurrentUrl();
		Assert.assertEquals(homepageURL, "http://demo.guru99.com/v4/manager/Managerhomepage.php");
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());
		System.out.println("TC02_Login with userID and password");
	}

	@Test
	public void TC03_New_Customer() {
		System.out.println("Step 01: Access to Guru page");
		driver.get("http://demo.guru99.com/v4");

		System.out.println("Step 02: Input user id");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr409642");

		System.out.println("Step 03: Input password");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ajYrAjU");

		System.out.println("Step 04: Click on Login button");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(customerName_txt).sendKeys(newCustomerName);
		driver.findElement(gender_radio_btn).click();

		jsExcutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dob_txt));
		sleepInSecond(5);

		driver.findElement(dob_txt).sendKeys(newDateOfBirth);
		driver.findElement(address_textarea).sendKeys(newAddress);
		driver.findElement(city_txt).sendKeys(newCity);
		driver.findElement(state_txt).sendKeys(newState);
		driver.findElement(pin_txt).sendKeys(newPin);
		driver.findElement(mobile_txt).sendKeys(newMobileNumber);
		driver.findElement(email_txt).sendKeys(newEmail);
		driver.findElement(pass_txt).sendKeys(newPassword);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("The customer ID is:" + customerID);

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				newCustomerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				genderOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
				newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				newMobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				newEmail);
		System.out.println("TC03: Verify create new user successfully");
	}
	
	@Test
	public void TC04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
				driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		driver.findElement(address_textarea).clear();
		driver.findElement(address_textarea).sendKeys(editAddress);
		
		driver.findElement(city_txt).clear();
		driver.findElement(city_txt).sendKeys(editCity);
		
		driver.findElement(state_txt).clear();
		driver.findElement(state_txt).sendKeys(editState);
		
		driver.findElement(pin_txt).clear();
		driver.findElement(pin_txt).sendKeys(editPin);
		
		driver.findElement(mobile_txt).clear();
		driver.findElement(mobile_txt).sendKeys(editMobileNumber);
		
		driver.findElement(email_txt).clear();
		driver.findElement(email_txt).sendKeys(editEmail);
		
		driver.findElement(By.name("sub")).click();
		
		driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
				editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editMobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);
		System.out.println("TC04: Verify edit user successfully");
		
		
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);

	}

	public void sleepInSecond(long timeoutInSecond) {
			try {
				Thread.sleep(timeoutInSecond * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
