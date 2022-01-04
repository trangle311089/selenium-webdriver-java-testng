package webdriver;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Method_For_Use {
	public static void main(String[] args) {
		// HOW TO CREATE THE RANDOM NUMBER
		Random rand = new Random();
		rand.nextInt(99999);
		System.out.println(rand);

		// HOW TO CHECK CURRENT OS AND THEN CLICK CTRL OR COMMAND BUTTON
		WebDriver driver;
		driver = new ChromeDriver();
		Keys key;
		Actions action;
		action = new Actions(driver);
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		action.keyDown(key).perform();

		// HOW TO SPLIT URL
		String link = "http://the-internet.herokuapp.com/basic_auth";

		String[] links = link.split("//");
		System.out.println(links[0]);
		System.out.println(links[1]);

		// HOW TO TRIM TEXT
		String text = "\r\n" + "                                First Option\r\n" + "                       ";

		System.out.println("-------" + text + "----------");
		System.out.println("-------" + text.trim() + "---------");

		Assert.assertEquals(text.trim(), "First Option");

		// HOW TO GET BACKGROUND COLOR THEN CONVERT FROM RGBA TO HEX
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getText(), "You did great!");

		String targetCircle_RGBAColor = targetCircle.getCssValue("background-color");
		System.out.println("The RGBAColor is:" + targetCircle_RGBAColor);

		String targetCircle_HEXColor = Color.fromString(targetCircle_RGBAColor).asHex().toUpperCase();
		System.out.println("The HEX color is:" + targetCircle_HEXColor);

		Assert.assertEquals("#03A9F4", targetCircle_HEXColor);
	}


	// HOW TO CHECK THE PAGE LOADED SUCCESSFULLY
	public boolean isPageLoadedSuccess(WebDriver driver) {
		JavascriptExecutor jsExecutor;
		WebDriverWait explicitWait;

		explicitWait = new WebDriverWait(driver, 120);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
	//HOW TO DEFINE LIST OF ELEMENTS
	/*List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title a"));
	//use "for" to validate each item in the list
	for (WebElement postTitle : postTitles) {
		System.out.println("Selenium Title:" + postTitle.getText());
		Assert.assertTrue(postTitle.getText().contains("Selenium"));
	}}*/
}








	