package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video24_Topic10_User_Interaction_Part2 {
	WebDriver driver;
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions (driver);
	}

	@Test
	public void TC07_Right_Click_To_Element() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text() =\"right click me\"]"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-item.context-menu-icon.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		driver.switchTo().alert().accept();
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-item.context-menu-icon.context-menu-icon-quit")).isDisplayed());
	}
	
	@Test
	public void TC08_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getText(), "You did great!");
		
		String targetCircle_RGBAColor = targetCircle.getCssValue("background-color");
		System.out.println("The RGBAColor is:" + targetCircle_RGBAColor);
		
		String targetCircle_HEXColor = Color.fromString(targetCircle_RGBAColor).asHex().toUpperCase();
		System.out.println("The HEX color is:" + targetCircle_HEXColor);
		
		Assert.assertEquals("#03A9F4",targetCircle_HEXColor);
		
	}

	public void sleepInSecond(long timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
