package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video27_Topic12_Handle_Window_Tab {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);

	}

	@Test
	public void TC09_Switch_Tab() {
		System.out.println("TC09 - Switch between window/ tab");
		System.out.println("Step 01: Open the web page");
		driver.get("https://automationfc.github.io/basic-form/index.html");

		System.out.println("Step 02: Get parent tab ID");
		String parentTabID = driver.getWindowHandle();
		System.out.println("=> The parent tab ID is: " + parentTabID);

		System.out.println("Step 03: Click on google link");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		System.out.println("Step 04: Switch to google tab");
		switchToWindowByID(parentTabID);

		System.out.println("Step 05: Get google tab ID");
		String googleTabID = driver.getWindowHandle();
		System.out.println("=> The google tab ID is: " + googleTabID);

		System.out.println("Step 06: Check google title");
		String googleTitle = driver.getTitle();

		System.out.println("=> The GG title is: " + googleTitle);
		Assert.assertEquals(googleTitle, "Google");

		System.out.println("Step 07: Switch to parent tab");
		switchToWindowByID(googleTabID);

		System.out.println("Step 08: Click on facebook link");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		System.out.println("Step 09: Switch to FB tab");
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(2);
		String fbTitle = driver.getTitle();
		System.out.println("=> The FB title is: " + fbTitle);

		System.out.println("Step 10: Check the FB title");
		Assert.assertEquals(fbTitle, "Facebook – log in or sign up");

		System.out.println("Step 11: Click othe Tiki link");
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();

		System.out.println("Step 12: Swich to Tiki tab");
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		System.out.println("Step 13: Check the Tiki title");
		String tikiTitle = driver.getTitle();
		System.out.println("=> The tiki title is: " + tikiTitle);
		Assert.assertEquals(tikiTitle, "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		System.out.println("Step 14: Close all windows except parent");
		closeAllWindowExceptParent(parentTabID);
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		System.out.println("Step 15: Check parent url");
		String parentURL = driver.getCurrentUrl();
		System.out.println("The parent url is:" + parentURL);
		Assert.assertEquals(parentURL, "https://automationfc.github.io/basic-form/index.html");

	}

	@Test
	public void TC10_Switch_And_Close_Tabs() {
		System.out.println("Step 01: Open Kyna page");
		driver.get("https://kyna.vn/");
		String kynaTitle = driver.getTitle();
		System.out.println("=> The title of Kyna page is: " + kynaTitle);

		System.out.println("Step 02: Get Kyna tab ID");
		String KynaID = driver.getWindowHandle();
		System.out.println("=> The Kyna tab ID is: " + KynaID);

		System.out.println("Step 03: Click on facebook icon");
		clickToElementByJS("//div[@id='k-footer-mb']//a[@href='https://www.facebook.com/kyna.vn']");

		System.out.println("Step 04: Check the title of KynaFB page");
		switchToWindowByTitle("Kyna.vn - Home | Facebook");
		String kynaFBTitle = driver.getTitle();
		System.out.println("=> The title of KynaFB is: " + kynaFBTitle);
		Assert.assertEquals(kynaFBTitle, "Kyna.vn - Home | Facebook");

		System.out.println("Step 05: Back to Kyna page");
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

		System.out.println("Step 06: Click on the YouTube icon");
		clickToElementByJS("//div[@id='k-footer-mb']//a[@href='https://www.youtube.com/user/kynavn']");

		System.out.println("Step 07: Check the title of Kyna Youtube page");
		switchToWindowByTitle("Kyna.vn - YouTube");
		String kynaYouTubeTitle = driver.getTitle();
		System.out.println("=> The title of KynaYouTube is: " + kynaYouTubeTitle);
		Assert.assertEquals(kynaYouTubeTitle, "Kyna.vn - YouTube");

		System.out.println("Step 08: Close all windowns except Kyna page");
		closeAllWindowExceptParent(KynaID);
		switchToWindowByTitle(kynaTitle);
		Assert.assertEquals(kynaTitle, "Kyna.vn - Học online cùng chuyên gia");

	}

	@Test
	public void TC11_Add_Mobile_Product_To_Compare() {
		System.out.println("Step 01: Open the parent page");
		driver.get("http://live.techpanda.org/");

		System.out.println("Step 02: Click on Mobile tab");
		driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();
		sleepInSecond(1);

		System.out.println("Step 03: Click on Add to Compare of IPHONE");
		driver.findElement(By.xpath("(//a[text()='Add to Compare'])[3]")).click();
		sleepInSecond(1);

		System.out.println("Step 04: Check the message added Iphone to comparison list");
		String iPhonemsg = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(iPhonemsg, "The product IPhone has been added to comparison list.");
		sleepInSecond(1);

		System.out.println("Step 05: Click on Add to Compare of SONY");
		driver.findElement(By.xpath("(//a[text()='Add to Compare'])[2]")).click();
		sleepInSecond(1);

		System.out.println("Step 06: Check the message added SONY to comparison list");
		String sonyMsg = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(sonyMsg, "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(1);

		System.out.println("Step 07: Click on Compare button");
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(1);

		System.out.println("Step 08: Switch to comparison window");
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		driver.manage().window().maximize();
		String comparisonTitle = driver.getTitle();
		System.out.println("=> The comparison page title is: " + comparisonTitle);
		sleepInSecond(1);

		System.out.println("Step 09: Check comparison page title");
		Assert.assertEquals(comparisonTitle, "Products Comparison List - Magento Commerce");
		sleepInSecond(1);

		/*
		 * System.out.println("Step 10: Close all tabs and back to parent page");
		 * closeAllWindowExceptParent("Home page");
		 */
		System.out.println("Step 10: Close Comparison page");
		driver.close();
		sleepInSecond(1);

		System.out.println("Step 11: Click on Clear All button");
		switchToWindowByTitle("Home page");
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(1);

		System.out.println("Step 12: Accept to clear all products from comparison list");
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println("=> The alert message is: " + alert.getText());
		sleepInSecond(1);
		alert.accept();

		System.out.println("Step 13: Check comparison list is cleared successfully");
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}

	@Test
	public void TC12_Cambridge_Login() {
		System.out.println("Step 01: Open the Cambridge page");
		driver.get("https://dictionary.cambridge.org/vi/");
		sleepInSecond(2);

		System.out.println("Step 02: Click on Dang nhap link");
		driver.findElement(
				By.xpath("//span[@class='lpr-0 hbtn hbtn-t lmt-5 fs15 cdo-login-button']//span[text()='Đăng nhập']"))
				.click();
		sleepInSecond(2);

		System.out.println("Step 03: Click on login button");
		switchToWindowByTitle("Login");
		sleepInSecond(2);
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		
		System.out.println("Step 04: Check validation message displayed for account field");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@data-bound-to='loginID' and text()='This field is required']")).isDisplayed());
		
		System.out.println("Step 05: Check validation message displayed for password field");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@data-bound-to='password' and text()='This field is required']")).isDisplayed());
		
		sleepInSecond(2);
		
		System.out.println("Step 06: Input user name");
		driver.findElement(By.xpath("//input[@class='gigya-input-text gigya-error' and @name='username']")).sendKeys("automationfc.com@gmail.com");
		
		sleepInSecond(2);
		
		System.out.println("Step 07: Input password");
		driver.findElement(By.xpath("//input[@name='password' and @class='gigya-input-password gigya-error']")).sendKeys("Automation000***");
		
		System.out.println("Step 08: Click on Login button");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Log in']")).click();
		driver.findElement(By.xpath("//input[@type='submit' and @value='Log in']")).click();
		
		sleepInSecond(2);
		
		System.out.println("Step 09: Verify login successfully");
		switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		String loginName = driver.findElement(By.xpath("//span[@class='tb lpl-2 cdo-username']")).getText();
		Assert.assertEquals(loginName, "Automation FC");
		

	}

	public void clickToElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void sleepInSecond(long timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void switchToWindowByID(String windowTabID) {
		// get all current IDs
		Set<String> allWindows = driver.getWindowHandles();
		// use for to check all ID
		for (String window : allWindows) {
			if (!window.equals(windowTabID)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String expectedPageTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			sleepInSecond(2);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowExceptParent(String parentPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentPageID)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
	}

	@AfterClass
	public void afterClass() {
		System.out.println("--------- TEST RESULT ------------");
		driver.quit();
	}

}
