package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video18_TC04_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExcutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC01_Handle_Custom_Dropdown_On_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		By parentBy = By.id("number-button");
		By childBy = By.xpath("//ul[@id='number-menu']//div");

		selectItemInDropdownList(parentBy, childBy, "5");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//span[@class='ui-selectmenu-text' and text()='5']")));

		selectItemInDropdownList(parentBy, childBy, "10");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//span[@class='ui-selectmenu-text' and text()='10']")));

		selectItemInDropdownList(parentBy, childBy, "15");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//span[@class='ui-selectmenu-text' and text()='15']")));

		selectItemInDropdownList(parentBy, childBy, "19");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//span[@class='ui-selectmenu-text' and text()='19']")));
	}
	
	@Test
	public void TC02_Handle_Custom_Dropdown_On_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		By parentBy = By.xpath("//i[@class='dropdown icon']");
		By childBy = By.xpath("//div[@role='option']//span");
		
		selectItemInDropdownList(parentBy, childBy, "Elliot Fu");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//div[@role='alert' and text()='Elliot Fu']")));
		
		selectItemInDropdownList(parentBy, childBy, "Christian");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//div[@role='alert' and text()='Christian']")));
		
		selectItemInDropdownList(parentBy, childBy, "Justen Kitsune");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//div[@role='alert' and text()='Justen Kitsune']")));	
	}
	
	@Test
	public void TC03_Handle_Custom_Dropdown_On_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		By parentBy = By.className("dropdown-toggle");
		By childBy = By.xpath("//ul[@class='dropdown-menu']//a");
		
		selectItemInDropdownList(parentBy, childBy, "Second Option");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")));
		
		selectItemInDropdownList(parentBy, childBy, "Third Option");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")));
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectItemInDropdownList(By parentBy, By childBy, String expectedSelectedItem) {
		driver.findElement(parentBy).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		List<WebElement> allItems = driver.findElements(childBy);

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedSelectedItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExcutor.executeScript("agruments[0].scrollIntoView(true);", item);
					sleepInSecond(2);
					item.click();
				}
			}
		}

	}

	public boolean isElementDisplay(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is displayed");
			return false;
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
