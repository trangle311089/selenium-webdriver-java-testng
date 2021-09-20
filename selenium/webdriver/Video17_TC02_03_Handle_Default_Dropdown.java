package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video17_TC02_03_Handle_Default_Dropdown {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@Test
	public void TC02_Handle_Default_Dropdown_On_Rode_Page() {
		driver.get("https://rode.com/wheretobuy");
		select = new Select(driver.findElement(By.id("where_country")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Vietnam");
		String chooseCountry_value = select.getFirstSelectedOption().getText();
		Assert.assertEquals(chooseCountry_value, "Vietnam");
		driver.findElement(By.id("search_loc_submit")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='result_count']//span[text()='29']")).isDisplayed());
		List<WebElement> storeName = driver
				.findElements(By.xpath("//div[@class='result_item']//div[@class='store_name']"));
		Assert.assertEquals(storeName.size(), 29);
		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
		System.out.println("TC_02_Handle_Default_Dropdown_On_Rode_Page");

	}

	@Test
	public void TC03_Handle_Default_Dropdown_On_Nopcommerce_Page() {
		String firstName = "Trang";
		String lastName = "Le";
		String day = "31";
		String month = "October";
		String year = "1989";
		String email = "trangle" + getRandomNumber() + "@gmail.com";
		String company = "tma";
		String password = "123456";

		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();
		
		WebElement gender_radio = driver.findElement(By.id("gender-female"));
		WebElement firstName_field = driver.findElement(By.id("FirstName"));
		WebElement lastName_field = driver.findElement(By.id("LastName"));
		WebElement email_field = driver.findElement(By.id("Email"));
		WebElement company_field = driver.findElement(By.id("Company"));
		WebElement password_field = driver.findElement(By.id("Password"));
		WebElement confirmPass_field = driver.findElement(By.id("ConfirmPassword"));


		gender_radio.click();
		firstName_field.sendKeys(firstName);
		lastName_field.sendKeys(lastName);

		System.out.println("step 1: select and verify day of birth dropdown field");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText(day);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		Assert.assertEquals(select.getOptions().size(), 32);

		System.out.println("step 2: select and verify month of birth dropdown field");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);

		System.out.println("step 3: verify year of brith dropdown field");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);

		email_field.sendKeys(email);
		company_field.sendKeys(company);
		password_field.sendKeys(password);
		confirmPass_field.sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		System.out.println("step 4: verify register successfully");
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='result' and text()='Your registration completed']"))
						.isDisplayed());
		driver.findElement(By.className("ico-account")).click();

		System.out.println("step 5: verify day month year of birth after register successfully");
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		System.out.println("step 6: verify other informations after registered successfully");
		Assert.assertTrue(driver.findElement(By.id("gender-female")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);
		//Assert.assertTrue(gender_radio.isSelected());
		//Assert.assertEquals(firstName_field.getText(), firstName);
		//Assert.assertEquals(lastName_field.getText(), lastName);
		//Assert.assertEquals(email_field.getText(), email);
		//Assert.assertEquals(company_field, company);

	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
