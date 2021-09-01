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

public class Video14_Web_Element_Refactor {
	WebDriver driver;

	By emailTextField = By.id("mail");
	By passTextField = By.id("password");

	By ageUnder18Radio_btn = By.id("under_18");
	By ageDisabled_btn = By.id("radio-disabled");

	By educationTextField = By.id("edu");
	By bioTextField = By.id("bio");

	By jobRole1SelectField = By.id("job1");
	By jobRole3SelectField = By.id("job3");

	By interestDevelopment_checkbox = By.id("development");
	By interestDisabled_checkbox = By.id("check-disbaled");

	By languageJava_checkbox = By.id("java");

	By slider1 = By.id("slider-1");
	By slider2 = By.id("slider-2");

	By user5Label = By.xpath("//h5[text()='Name: User5']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC01_Verify_Element_Is_Displayed() {
		if(isElementDisplay(emailTextField)) {
			sendKeyToElement(emailTextField, "Automation Testing");
		}
		if(isElementDisplay(educationTextField)) {
			sendKeyToElement(educationTextField, "Automation Testing");
		}
		if (isElementDisplay(ageUnder18Radio_btn)) {
			clickOnElement(ageUnder18Radio_btn);
		}
		Assert.assertFalse(isElementDisplay(user5Label));
	}
	
	@Test
	public void TC02_Verify_Element_Is_Enabled() {
		Assert.assertTrue(isElementEnabled(emailTextField));
		Assert.assertTrue(isElementEnabled(ageUnder18Radio_btn));
		Assert.assertTrue(isElementEnabled(educationTextField));
		Assert.assertTrue(isElementEnabled(jobRole1SelectField));
		Assert.assertTrue(isElementEnabled(interestDevelopment_checkbox));
		Assert.assertTrue(isElementEnabled(slider1));
		
		Assert.assertFalse(isElementEnabled(passTextField));
		Assert.assertFalse(isElementEnabled(ageDisabled_btn));
		Assert.assertFalse(isElementEnabled(bioTextField));
		Assert.assertFalse(isElementEnabled(jobRole3SelectField));
		Assert.assertFalse(isElementEnabled(interestDisabled_checkbox));
		Assert.assertFalse(isElementEnabled(slider2));
		
	}

	@Test
	public void TC03_Verify_Element_Is_Selected() {
		Assert.assertFalse(isElementSelected(ageUnder18Radio_btn));
		Assert.assertFalse(isElementSelected(languageJava_checkbox));
		
		clickOnElement(ageUnder18Radio_btn);
		clickOnElement(languageJava_checkbox);
	
		Assert.assertTrue(isElementSelected(ageUnder18Radio_btn));
		Assert.assertTrue(isElementSelected(languageJava_checkbox));
		
		clickOnElement(languageJava_checkbox);
		Assert.assertFalse(isElementSelected(languageJava_checkbox));
		
	}
	
	public boolean isElementDisplay(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disabled");
			return false;
		}
	}
	
	public boolean isElementSelected (By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not selected");
			return false;
		}
	}
	
	public void sendKeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickOnElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
