package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Video31_Topic15_16_Selenium_Webdriver_Wait_ElementStatus {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC01_Validate_Visible_Element_UI_DOM() {
		System.out.println("TC 01: Validate visible element on UI and DOM");
		System.out.println("Step 1: Open Facebook homepage");
		driver.get("https://www.facebook.com/");

		System.out.println("Step 2: Wait until the Registration button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")));

		System.out.println("Step 3: Click on Registration button");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		System.out.println("Step 4: Wait until the Sign up button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

		System.out.println("Step 5: Input the email address");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("trangle@gmail.com");

		System.out.println("Step 6: Wait until the confirm email address field is displayed");
		WebElement confirmEmailField = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		System.out.println("Step 7: Get height and width size of confirm email field when it is visible");
		Dimension confirmEmailSize = confirmEmailField.getSize();
		System.out.println("Confirm email height = " + confirmEmailSize.getHeight());
		System.out.println("Confirm email width = " + confirmEmailSize.getWidth());
	}

	@Test
	public void TC02_Validate_Invisible_Element_UI_Visible_DOM() {
		System.out.println("TC 02: Validate invisible element on UI and visible in DOM");
		System.out.println("Step 1: Open Facebook homepage");
		driver.get("https://www.facebook.com/");

		System.out.println("Step 2: Wait until the Registration button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")));

		System.out.println("Step 3: Click on Registration button");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		System.out.println("Step 4: Wait until the Sign up button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

		System.out.println("Step 5: Check email confirmation is not displayed on UI");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		System.out.println("Step 6: Get height and width size of confirm email field when it is NOT visible on UI");
		Dimension confirmEmailSize = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).getSize();
		System.out.println("Confirm email height = " + confirmEmailSize.getHeight());
		System.out.println("Confirm email width = " + confirmEmailSize.getWidth());
	}

	@Test
	public void TC03_Validate_Invisible_Element_UI_DOM() {
		System.out.println("TC 03: Validate invisible element on UI and DOM");
		System.out.println("Step 1: Open Facebook homepage");
		driver.get("https://www.facebook.com/");

		System.out.println("Step 2: Check email confirmation is not displayed on UI and DOM");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC04_Validate_Presense_Element_UI_DOM() {
		System.out.println("TC04: Validate presence element on UI and DOM");
		System.out.println("Step 1: Open Facebook homepage");
		driver.get("https://www.facebook.com/");

		System.out.println("Step 2: Wait until the registration button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")));

		System.out.println("Step 3: Click on registration button");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		System.out.println("Step 4: Wait until the Sign up button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

		System.out.println("Step 5: Input the email address");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("trangle@gmail.com");

		System.out.println("Step 6: Wait until the confirm email address field is displayed");
		WebElement confirmEmailField = explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		System.out.println("Step 7: Get height and width size of confirm email field when it is visible");
		Dimension confirmEmailSize = confirmEmailField.getSize();
		System.out.println("Confirm email height = " + confirmEmailSize.getHeight());
		System.out.println("Confirm email width = " + confirmEmailSize.getWidth());

	}

	@Test
	public void TC05_Validate_Presense_Element_In_DOM() {
		System.out.println("TC05: Validate presence element in DOM");
		System.out.println("Step 1: Open Facebook homepage");
		driver.get("https://www.facebook.com/");

		System.out.println("Step 2: Wait until the registration button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")));

		System.out.println("Step 3: Click on registration button");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		System.out.println("Step 4: Wait until the Sign up button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

		System.out.println("Step 6: Check confirm email address field is presence in DOM");
		WebElement confirmEmailField = explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		System.out.println("Step 7: Get height and width size of confirm email field when it is visible");
		Dimension confirmEmailSize = confirmEmailField.getSize();
		System.out.println("Confirm email height = " + confirmEmailSize.getHeight());
		System.out.println("Confirm email width = " + confirmEmailSize.getWidth());

	}
	
	@Test
	public void TC06_Validate_Staleness_Element() {
		System.out.println("TC06: Validate staleness element in DOM");
		System.out.println("Step 1: Open Facebook homepage");
		driver.get("https://www.facebook.com/");
		
		System.out.println("Step 2: Wait until the registration button is displayed");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")));
		
		System.out.println("Step 3: Click on registration button");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		System.out.println("Step 4: Check confirm email address field is presence in DOM");
		WebElement confirmEmailField = explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		System.out.println("Step 5: Click on close icon on the Sign Up popup");
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		System.out.println("Step 6: Validate confirm email field is staleness");
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailField));
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("===============START TEST CASE===============");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("================END TEST CASE=================");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("==================TEST RESULT=================");
		driver.quit();
	}

}
