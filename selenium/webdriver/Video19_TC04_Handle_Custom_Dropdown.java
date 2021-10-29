package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video19_TC04_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		explicitWait = new WebDriverWait(driver, 15);
		jsExcutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC01_Handle_Custom_Dropdown_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInDropdownList(By.cssSelector("span[aria-owns='games_options']"),
				By.cssSelector("ul#games_options>li"), "Basketball");
		sleepInSecond(1);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("select#games_hidden + input")).getAttribute("aria-label"),
				"Basketball");

		selectItemInDropdownList(By.cssSelector("span[aria-owns='games_options']"),
				By.cssSelector("ul#games_options>li"), "Football");
		sleepInSecond(1);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("select#games_hidden + input")).getAttribute("aria-label"),
				"Football");

		selectItemInDropdownList(By.cssSelector("span[aria-owns='games_options']"),
				By.cssSelector("ul#games_options>li"), "Hockey");
		sleepInSecond(1);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("select#games_hidden + input")).getAttribute("aria-label"),
				"Hockey");
	}

	@Test
	public void TC02_Handle_Custom_Dropdown_Editable_JQuery() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		selectItemInEditableDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "Citroen");
		sleepInSecond(3);
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']/ul/li[@class='es-visible
		// selected' and contains (text(), 'Citroen')]")).isDisplayed());
		driver.navigate().refresh();

		selectItemInEditableDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "BMW");
		sleepInSecond(3);
		driver.navigate().refresh();

		selectItemInEditableDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "Ford");
		sleepInSecond(3);
		driver.navigate().refresh();

	}

	@Test
	public void TC03_Hanlde_Custom_Dropdown_Editable_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown(By.xpath("//div[@role='combobox']/input"),
				By.xpath("//div[@role='listbox']/div[@role='option']/span"), "Aland Islands");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='alert' and contains(text(),'Aland Islands')]"))
				.isDisplayed());

		selectItemInEditableDropdown(By.xpath("//div[@role='combobox']/input"),
				By.xpath("//div[@role='listbox']/div[@role='option']/span"), "Andorra");
		sleepInSecond(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@role='alert' and contains(text(),'Andorra')]")).isDisplayed());

		selectItemInEditableDropdown(By.xpath("//div[@role='combobox']/input"),
				By.xpath("//div[@role='listbox']/div[@role='option']/span"), "Algeria");
		sleepInSecond(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@role='alert' and contains(text(),'Algeria')]")).isDisplayed());
	}

	@Test
	public void TC04_Handle_Custome_Dropdown_Select_Multiple() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		String[] firstmonth = { "January", "April", "October" };
		String[] secondmonth = { "June", "August", "October", "December" };
		String[] thirdmonth = { "[Select all]" };

		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]",
				"(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", firstmonth);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(firstmonth));

		driver.navigate().refresh();

		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]",
				"(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", secondmonth);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(secondmonth));

		driver.navigate().refresh();

		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]",
				"(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", thirdmonth);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(thirdmonth));

	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Function for finding and selecting data in the dropdown list
	public void selectItemInDropdownList(By parentBy, By childBy, String expectedItem) {
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();

		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(3);
					item.click();
				}
				break;
			}
		}
	}

	// Function for sending key in editable dropdown list
	public void selectItemInEditableDropdown(By parentBy, By childBy, String expectedItem) {
		// get correct parent as input type to send key
		driver.findElement(parentBy).clear();
		driver.findElement(parentBy).sendKeys(expectedItem);

		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(3);
					item.click();
				}
				break;
			}
		}
	}

	// Function for selecting many items in dropdown list
	public void selectMultipleItemInDropdown(String parentXPath, String childXpath, String[] expecteValueItem) {
		driver.findElement(By.xpath(parentXPath)).click();

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		for (WebElement childElement : allItems) {
			for (String item : expecteValueItem) {
				if (childElement.getText().equals(item)) {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);

					childElement.click();
					sleepInSecond(1);

					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expecteValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}

	// Function for verifying selected multiple item
	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Selected text = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else {
			return false;
		}
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element  [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
