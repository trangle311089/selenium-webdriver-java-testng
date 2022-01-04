package webdriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video25_Topic11_HandlePopup {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1")).isDisplayed());
		driver.findElement(By.cssSelector("button.login_.icon-before")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1")).isDisplayed());
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");
	}

	// @Test
	public void TC02_Random_Popup_In_Dom() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(30);
		By randomPopup = By.cssSelector("div.mailch-wrap");

		if (driver.findElement(randomPopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(randomPopup).isDisplayed());
		}

		Assert.assertTrue(isPageLoadedSuccess(driver));

		driver.findElement(By.cssSelector("section#search-2 input[type=\"search\"]")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		Assert.assertTrue(isPageLoadedSuccess(driver));

		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title a"));
		System.out.println("All Selenium title: " + postTitles.size());

		for (WebElement postTitle : postTitles) {
			System.out.println("Selenium Title:" + postTitle.getText());
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
	}

	// @Test
	public void TC03_Random_Popup_In_Dom2() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(10);
		By randomPopup = By.cssSelector("div.tve_p_lb_content");

		if (driver.findElement(randomPopup).isDisplayed()) {
			System.out.println("Click on the close icon on the popup");
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(randomPopup).isDisplayed());
		}
		Assert.assertTrue(isPageLoadedSuccess(driver));
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[@class='title-content']/h1[text()='Lịch Khai Giảng – Gói combo ưu đãi']"))
				.isDisplayed());
	}

	// @Test
	public void TC04_Random_Popup_Not_In_Dom() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(3);
		By randomPopup = By.cssSelector("div.popup-content");

		List<WebElement> popupNotInDom = driver.findElements(randomPopup);

		if (popupNotInDom.size() > 0) {
			System.out.println("------------Show and Close popup------------");
			System.out.println("Click on the close icon");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElements(randomPopup).size(), 0);
		}

		driver.findElement(By.xpath("//li/a[text()='Đăng ký']")).click();
		Assert.assertTrue(isPageLoadedSuccess(driver));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.sign-up-form")).isDisplayed());

	}

	@Test
	public void TC05_Random_Popup_Not_In_Dom() {
		driver.get("https://shopee.vn/");
		sleepInSecond(3);

		WebElement root1 = driver.findElement(By.tagName("shopee-banner-popup-stateful"));
		WebElement shadowRoot1 = expandRootElement(root1);

		WebElement root2 = shadowRoot1.findElement(By.tagName("shopee-banner-simple"));
		WebElement shadowRoot2 = expandRootElement(root2);

		WebElement randomPopup = shadowRoot2.findElement(By.cssSelector("div.simple-banner"));
		Assert.assertTrue(randomPopup.isDisplayed());

		List<WebElement> randomPopupElement = shadowRoot2.findElements(By.cssSelector("div.simple-banner"));

		if (randomPopupElement.size() > 0) {
			System.out.println("-------------The popup is displayed and Close-------------");
			WebElement closeButton = shadowRoot1.findElement(By.cssSelector("div.shopee-popup__close-btn"));
			closeButton.click();
			sleepInSecond(2);
		} 
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("macbook pro");
		driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();

		//Assert.assertTrue(isPageLoadedSuccess(driver));
		sleepInSecond(15);
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div.shopee-search-item-result__item div.ZG__4J>div")));
		sleepInSecond(1);
		
		List <WebElement> allItemTitles = driver.findElements(By.cssSelector("div.shopee-search-item-result__item div.ZG__4J>div"));
		//List <WebElement> allItemTitles = driver.findElements(By.cssSelector("div.shopee-search-item-result__items div a"));
		System.out.println("All item Titles:" + allItemTitles.size());
		
		for (WebElement itemTitle : allItemTitles ) {
			System.out.println("Title has Macbook Pro:" + itemTitle.getText());
			Assert.assertTrue(itemTitle.getText().toLowerCase().contains("pro"));
			//Assert.assertTrue(itemTitle.getAttribute("href").toString().toLowerCase().contains("-pro"));
		}
	}

// Returns webelement
//	public WebElement expandRootElement(WebElement element) {
//		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
//		return ele;
//	}

	public WebElement expandRootElement(WebElement shadowHost) {
		WebElement returnObj = null;
		Object shadowRoot = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowHost);
		if (shadowRoot instanceof WebElement) {
			// ChromeDriver 95
			returnObj = (WebElement) shadowRoot;
		} else if (shadowRoot instanceof Map) {
			// ChromeDriver 96+
			// Based on
			// https://github.com/SeleniumHQ/selenium/issues/10050#issuecomment-974231601
			Map<String, Object> shadowRootMap = (Map<String, Object>) shadowRoot;
			String shadowRootKey = (String) shadowRootMap.keySet().toArray()[0];
			String id = (String) shadowRootMap.get(shadowRootKey);
			RemoteWebElement remoteWebElement = new RemoteWebElement();
			remoteWebElement.setParent((RemoteWebDriver) driver);
			remoteWebElement.setId(id);
			returnObj = remoteWebElement;
		} else {
			Assert.fail("Unexpected return type for shadowRoot in expandRootElement()");
		}
		return returnObj;
	}

	public void sleepInSecond(long timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		JavascriptExecutor jsExecutor;
		WebDriverWait explicitWait;

		explicitWait = new WebDriverWait(driver, 160);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
