package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video20_Topic09_TC03_04_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC03_Custom_Radio_AngularPage() {
		driver.get("https://material.angular.io/components/radio/examples");
		By customRadio_Summer = By.xpath("//input[@value='Autumn']");
		clickOnRadioByJS(customRadio_Summer);
		Assert.assertTrue(driver.findElement(customRadio_Summer).isSelected());
	}

	@Test
	public void TC03_Custom_Checkbox_AngularPage() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By customCheckbox_Checked = By.xpath("//span[text() = 'Checked']/preceding-sibling::span/input");
		By customCheckbox_Indeterminate = By.xpath("//span[text() = 'Indeterminate']/preceding-sibling::span/input");
		checkToCheckboxByJS(customCheckbox_Checked);
		checkToCheckboxByJS(customCheckbox_Indeterminate);
		Assert.assertTrue(driver.findElement(customCheckbox_Checked).isSelected());
		Assert.assertTrue(driver.findElement(customCheckbox_Indeterminate).isSelected());

		uncheckToCheckbox(customCheckbox_Checked);
		uncheckToCheckbox(customCheckbox_Indeterminate);
		Assert.assertFalse(driver.findElement(customCheckbox_Checked).isSelected());
		Assert.assertFalse(driver.findElement(customCheckbox_Indeterminate).isSelected());
	}

	@Test
	public void TC04_Custom_Radio_Googlepage() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		By radiobtn_CanTho = By.xpath("//div[@aria-label='Cần Thơ']");
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		driver.findElement(radiobtn_CanTho).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}
	
	@Test
	public void TC04_Custom_Checkbox_Googlepage() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		List <WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		sleepInSecond(2);
		
		for (WebElement checkbox:checkboxes) {
			checkbox.click();
			sleepInSecond(1);
		}
		
		for (WebElement checkbox:checkboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"),"true");
		}
		
	}

	public void clickOnRadioByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	public void checkToCheckboxByJS(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
	}

	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		}

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
		//driver.quit();
	}

}
