package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video23_Topic10_User_Interaction_Part1 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		action = new Actions(driver);
		jsExcutor = (JavascriptExecutor) driver;

	}

	@Test
	public void TC01_Hover_To_Element_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC02_Hover_To_Element_Show_Menu() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[@href='/shop/kids']")))
				.perform();
		sleepInSecond(2);
		action.click(driver.findElement(By.xpath("//a[@data-reactid=\"337\" and text()='T-Shirts']"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class=\"breadcrumbs-crumb\" and text()='Kids Wear Online Store']"))
						.isDisplayed());
	}

	@Test
	public void TC03_Hover_To_Element_Left_Menu() {
		driver.get("https://www.fahasa.com");
		sleepInSecond(4);
//		WebElement closeIcon = driver.findElement(By.xpath("//a[@id='NC_CLOSE_ICON']"));
//		jsExcutor.executeScript("arguments[0].click();", closeIcon);

		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@id='NC_CLOSE_ICON']")).click();
		driver.switchTo().defaultContent();
		sleepInSecond(3);

		action.moveToElement(driver.findElement(By.xpath(
				"//div[@class='col-md-3 hidden-max-width-992']//span[@class='menu-title' and text()='Sách Trong Nước']")))
				.perform();
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='col-md-3 hidden-max-width-992']//div[@class='ves-widget' and @data-id='wid-98']//a[text()='Tiểu Thuyết']"))
				.isDisplayed());
	}

	@Test
	public void TC04_Click_And_Hold_Element_Select_Multiple_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(6)).release().perform();

		List<WebElement> selectedNumber = driver
				.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedNumber.size(), 6);
	}

	@Test
	public void TC05_Click_And_Select_Random_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		action.click(allNumber.get(0)).perform();
		//action.keyDown(Keys.CONTROL);
		action.click(allNumber.get(3)).perform();
		//action.keyDown(Keys.CONTROL);
		action.click(allNumber.get(5)).perform();
		//action.keyUp(Keys.CONTROL);

		List<WebElement> selectedNumber = driver
				.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedNumber.size(), 3);
	}
	
	@Test
	public void TC06_Double_Click_On_Button() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
		
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
		driver.quit();
	}

}
