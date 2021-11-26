package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video20_Topic09_TC01_Button {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Button() {
		By login_btn = By.xpath("//button[@class='fhs-btn-login']");
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		Assert.assertFalse(isElementEnabled(login_btn));

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0774896322");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");

		sleepInSecond(3);

		String login_RGBAColor = driver.findElement(login_btn).getCssValue("background-color");
		System.out.println("RGBA color is " + login_RGBAColor);

		String login_HexaColor = Color.fromString(login_RGBAColor).asHex().toUpperCase();
		System.out.println("Hexa color is" + login_HexaColor);

		Assert.assertEquals("#C92127", login_HexaColor);

		driver.navigate().refresh();
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(login_btn));
		driver.findElement(login_btn).click();

		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}

	@Test
	public void TC_02_Default_Checkbox() {
		By dualZone_checkbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		By rearSide_checkbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		checkToCheckbox(dualZone_checkbox);
		Assert.assertTrue(isElementSelected(dualZone_checkbox));
		uncheckToCheckbox(dualZone_checkbox);
		Assert.assertFalse(isElementSelected(dualZone_checkbox));
		
		checkToCheckbox(rearSide_checkbox);
		Assert.assertTrue(isElementSelected(rearSide_checkbox));
		uncheckToCheckbox(rearSide_checkbox);
		Assert.assertFalse(isElementSelected(rearSide_checkbox));
	}

	@Test
	public void TC_03_Default_Radio() {
		By petro_radio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		driver.findElement(petro_radio).click();
		Assert.assertTrue(isElementSelected(petro_radio));

	}

	public void sleepInSecond(long timeOutInSecond) {
		try {
			Thread.sleep(timeOutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disabled");
		}
		return false;
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;

		} else {
			System.out.println("Element [" + by + "] is not selected");
			return false;
		}
	}

	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
