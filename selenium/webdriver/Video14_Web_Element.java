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

public class Video14_Web_Element {
	WebDriver driver;

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
		WebElement emailTextField = driver.findElement(By.id("mail"));

		if (emailTextField.isDisplayed()) {
			emailTextField.sendKeys("Automation Testing");
			System.out.println("The Email field is displayed.");
		} else {
			System.out.println("The Email field is not displayed.");
		}

		WebElement ageUnder18Radiobutton = driver.findElement(By.id("under_18"));
		if (ageUnder18Radiobutton.isDisplayed()) {
			ageUnder18Radiobutton.click();
			System.out.println("The Under 18 radio button is displayed");
		} else {
			System.out.println("The Under 18 radio button is not displayed");
		}

		WebElement educationTextField = driver.findElement(By.id("edu"));
		if (educationTextField.isDisplayed()) {
			educationTextField.sendKeys("Automation Testing");
			System.out.println("The Education text field is displayed");
		} else {
			System.out.println("The Education text field is not displayed");
		}

		WebElement user5Label = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (user5Label.isDisplayed()) {
			System.out.println("The User 5 label is displayed");
		} else {
			System.out.println("The User 5 label is not displayed");
		}

	}

	@Test
	public void TC02_Verify_Element_Is_Enabled() {
		WebElement emailTextField = driver.findElement(By.id("mail"));
		if (emailTextField.isEnabled()) {
			System.out.println("The email text field is enabled");
		} else {
			System.out.println("The email text field is disabled");
		}

		WebElement ageUnder18Radiobutton = driver.findElement(By.id("under_18"));
		if (ageUnder18Radiobutton.isEnabled()) {
			System.out.println("The age under 18 radio button is enabled");
		} else {
			System.out.println("The age under 18 radio button is disabled");
		}

		WebElement educationTextField = driver.findElement(By.id("edu"));
		if (educationTextField.isEnabled()) {
			System.out.println("The edutation text field is enabled");
		} else {
			System.out.println("The education text field is disabled");
		}

		WebElement jobRole1SelectField = driver.findElement(By.id("job1"));
		if (jobRole1SelectField.isEnabled()) {
			System.out.println("The Job Role 1 is enabled");
		} else {
			System.out.println("The Job Role 1 is disabled");
		}
		
		WebElement interestDevelopment_checkbox = driver.findElement(By.id("development"));
		if(interestDevelopment_checkbox.isEnabled()) {
			System.out.println("The interest development checkbox is enabled");
		}else {
			System.out.println("The interest developement checkbox is disabled");
		}

		WebElement slider1 = driver.findElement(By.id("slider-1"));
		if (slider1.isEnabled()) {
			System.out.println("The slider 1 is enabled");
		} else {
			System.out.println("The slider 1 is disabled");
		}

		WebElement passwordTextField = driver.findElement(By.id("password"));
		if (passwordTextField.isEnabled()) {
			System.out.println("The password text field is enabled");
		} else {
			System.out.println("The password text field is disabled");
		}

		WebElement ageRadioDisabled = driver.findElement(By.id("radio-disabled"));
		if (ageRadioDisabled.isEnabled()) {
			System.out.println("The age radio is enabled");
		} else {
			System.out.println("The age radio is disabled");
		}

		WebElement bioTextField = driver.findElement(By.id("bio"));
		if (bioTextField.isEnabled()) {
			System.out.println("The biography text field is enabled");
		} else {
			System.out.println("The biography text field is disabled");
		}

		WebElement jobRole3 = driver.findElement(By.id("job3"));
		if (jobRole3.isEnabled()) {
			System.out.println("The job role 3 is enabled");
		} else {
			System.out.println("The job role 3 is disabled");

		}

		WebElement interestCheckbox = driver.findElement(By.id("check-disbaled"));
		if (interestCheckbox.isEnabled()) {
			System.out.println("The interest checkbox is enabled");

		} else {
			System.out.println("The interest checkbox is disabled");

		}
		
		WebElement slider2 = driver.findElement(By.id("slider-2"));
		if (slider2.isEnabled()) {
			System.out.println("The slider 2 is enabled");

		} else {
			System.out.println("The slider 2 is disabled");

		}
	}
	
	@Test
	public void TC_03_Verify_Element_Is_Selected() {
		WebElement ageUnder18radiobutton = driver.findElement(By.id("under_18"));
		ageUnder18radiobutton.click();
		Assert.assertTrue(ageUnder18radiobutton.isSelected());
		if (ageUnder18radiobutton.isSelected()) {
			System.out.println("The age under 18 radio button is selected");
			
		} else {
			System.out.println("The age under 18 radio button is not selected");

		}
		
		WebElement languageJavecheckbox = driver.findElement(By.id("java"));
		languageJavecheckbox.click();
		Assert.assertTrue(languageJavecheckbox.isSelected());
		if (languageJavecheckbox.isSelected()) {
			System.out.println("The language java checkbox is selected");
			
		} else {
			System.out.println("The lanaguage java checkbox is not selected");
		}
		
		languageJavecheckbox.click();
		Assert.assertFalse(languageJavecheckbox.isSelected());
		if (languageJavecheckbox.isSelected()) {
			System.out.println("The language java checkbox is selected");
			
		} else {
			System.out.println("The lanaguage java checkbox is not selected");
		}
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
