package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video28_Topic13_JavaScriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;

	}

	@Test
	public void TC01_Javascript_Executor() {
		System.out.println("Step 01: Open the url");
		navigateToUrlByJS("http://live.techpanda.org/");

		System.out.println("Step 02: Use JS to get domain");
		String domain = (String) executeForBrowser(" return document.domain;");

		System.out.println("Step 03: Verify domain is correct");
		Assert.assertEquals(domain, "live.techpanda.org");

		System.out.println("Step 04: Use JS to get URL");
		String url = (String) executeForBrowser("return document.URL;");

		System.out.println("Step 05: Verify url is correct");
		Assert.assertEquals(url, "http://live.techpanda.org/");

		System.out.println("Step 06: Use JS to hightlight the MOBILE button");
		hightlightElement("//a[text()='Mobile']");

		System.out.println("Step 07: Use JS to click on MOBILE button");
		clickToElementByJS("//a[text()='Mobile']");

		System.out.println("Step 08: Use JS to click on Add to Cart button under Samsung device");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		System.out.println("Step 09: Verify SamSung device is added to cart successfully");
		String innerTextValue = getInnerText();
		Assert.assertTrue(innerTextValue.contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		System.out.println("Step 10: Open Custom Service page");
		clickToElementByJS("//a[text()='Customer Service']");

		System.out.println("Step 11: Get the page title");
		String title = (String) executeForBrowser("return document.title;");

		System.out.println("Step 12: Verify the title is correct");
		Assert.assertEquals(title, "Customer Service");

		System.out.println("Step 13: Use JS to scroll to element");
		scrollToElementAtBottom("//input[@id='newsletter']");

		System.out.println("Step 14: Use JS to input value");
		sendkeyToElementByJS("//input[@id='newsletter']", "tle" + randomNumber() + "@gmail.com");

		System.out.println("Step 15: Use JS to click on Subcribe button");
		clickToElementByJS("//button[@title='Subscribe']");

		System.out.println("Step 16: Verify subscription message is displayed");
		String subscriptionMsg = getInnerText();
		Assert.assertTrue(subscriptionMsg.contains("Thank you for your subscription."));

		System.out.println("Step 17: Use JS to navigate to Guru");
		navigateToUrlByJS("http://demo.guru99.com/v4/");

		System.out.println("Step 18: Get Guru domain");
		String guruDomain = (String) executeForBrowser("return document.domain;");

		System.out.println("Step 19: Verify Guru domain is correct");
		Assert.assertEquals(guruDomain, "demo.guru99.com");

	}

	@Test
	public void TC02_Verify_HTML5_Validation_Message() {
		System.out.println("Step 01: Open the url");
		driver.get("https://automationfc.github.io/html5/index.html");

		System.out.println("Step 02: Click on Submit button");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		sleepInSecond(2);

		System.out.println("Step 03: Verify validation message of NAME field");
		Assert.assertEquals(getElementValidationMessage("//input[@type='name']"), "Please fill out this field.");

		System.out.println("Step 04: Input value on NAME field");
		driver.findElement(By.cssSelector("#fname")).sendKeys("TLe");

		System.out.println("Step 05: Click on Submit button");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		sleepInSecond(2);

		System.out.println("Step 06: Verify validation message on PASSWORD field");
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");

		System.out.println("Step 07: Input value on PASSWORD field");
		driver.findElement(By.cssSelector("#pass")).sendKeys("123");

		System.out.println("Step 08: Click on Submit button");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		sleepInSecond(2);

		System.out.println("Step 09: Verify validation message on EMAIL field");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

		System.out.println("Step 10: Input invalid value on EMAIL field");
		driver.findElement(By.cssSelector("#em")).sendKeys("trangle" + randomNumber() + "@mail");

		System.out.println("Step 11: Click on Submit button");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		sleepInSecond(2);

		System.out.println("Step 12: Verify validation message on EMAIL field");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");

		System.out.println("Step 13: Input valid email");
		driver.findElement(By.cssSelector("#em")).clear();
		driver.findElement(By.cssSelector("#em")).sendKeys("tle" + randomNumber() + "gmail.com");

		System.out.println("Step 14: Click on Submit button");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		System.out.println("Step 15: Verify validation message on Address field");
		Assert.assertEquals(getElementValidationMessage("//li/select"), "Please select an item in the list.");

	}

	@Test
	public void TC03_Verify_HTML_Validation_Message_Ubuntu_Page() {
		System.out.println("Step 01: Open the Ubuntu page");
		navigateToUrlByJS("https://login.ubuntu.com/");
		sleepInSecond(3);

		System.out.println("Step 02: Click button to close popup");
		clickToElementByJS("//button[@id='cookie-policy-button-accept']");

		System.out.println("Step 03: Input value on email field");
		sendkeyToElementByJS("//input[@id='id_email']", "a");

		System.out.println("Step 04: Click on Login button");
		clickToElementByJS("//button[@type='submit']");
		sleepInSecond(2);

		System.out.println("Step 05: Verify email validation message");
		Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"),
				"Please include an '@' in the email address. 'a' is missing an '@'.");
	}

	@Test
	public void TC03_Verify_HTML_Validation_Message_Warranty_Page() {
		System.out.println("Step 01: Open the Warranty page");
		navigateToUrlByJS("https://warranty.rode.com/");

		System.out.println("Step 02: Click on Login button at Login section");
		clickToElementByJS("//button[@type='submit']");
		sleepInSecond(2);

		System.out.println("Step 03: Verify email validation message");
		Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");
	}

	@Test
	public void TC04_Remove_Attribute() {
		System.out.println("Step 01: Access to Guru page");
		navigateToUrlByJS("http://demo.guru99.com/v4");

		System.out.println("Step 02: Input user id");
		sendkeyToElementByJS("//input[@name='uid']", "mngr409642");

		System.out.println("Step 03: Input password");
		sendkeyToElementByJS("//input[@name='password']", "ajYrAjU");

		System.out.println("Step 04: Click on Login button");
		clickToElementByJS("//input[@name='btnLogin']");

		System.out.println("Step 05: Click on New Customer menu");
		clickToElementByJS("//a[text()='New Customer']");

		/*
		 * System.out.println("Step 06: Close Ad popup");
		 * clickToElementByJS("//div[@id='dismiss-button']");
		 */

		System.out.println("Step 07: Input Customer Name");
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("tle");
		// sendkeyToElementByJS("//input[@name='name']", "tle");

		System.out.println("Step 08: Select Gender");
		driver.findElement(By.xpath("//input[@value='f']")).click();
		// clickToElementByJS("//input[@value='f']");

		System.out.println("Step 09: Remove attribute date of DOB field");
		removeAttributeInDOM("//input[@id='dob']", "type");
		sleepInSecond(2);

		System.out.println("Step 10: Input DOB");
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("10/31/1989");
		// sendkeyToElementByJS("//input[@id='dob']", "10/31/1989");

		System.out.println("Step 11: Input address");
		// sendkeyToElementByJS("//textarea[@name='addr']", "123");
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("123");

		System.out.println("Step 12: Input City");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("HCM");
		// sendkeyToElementByJS("//input[@name='city']", "HCM");

		System.out.println("Step 13: Input State");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("SG");
		// sendkeyToElementByJS("//input[@name='state']", "Sai Gon");

		System.out.println("Step 14: Input PIN");
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("111111");
		// sendkeyToElementByJS("//input[@name='pinno']", "111111");

		System.out.println("Step 15: Input Mobile Number");
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0776328599");
		// sendkeyToElementByJS("//input[@name='telephoneno']", "0779993566");

		System.out.println("Step 16: Input Email");
		// sendkeyToElementByJS("//input[@name='emailid']", "tle@gmail.com");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("tle1" + randomNumber() + "@mail.com");

		System.out.println("Step 17: Input Password");
		// sendkeyToElementByJS("//input[@name='password']", "123456");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");

		System.out.println("Step 18: Click on Submit button");
		clickToElementByJS("//input[@type='submit']");
		sleepInSecond(3);

		System.out.println("Step 19: Verify registered successfully");
		String customerRegisterSuccessfully = getInnerText();
		Assert.assertTrue(customerRegisterSuccessfully.contains("Customer Registered Successfully!!!"));

	}
	
	@Test
	public void TC05_TechPanda_Create_Account() {
		System.out.println("Step 01: Open URL");
	    navigateToUrlByJS("http://live.techpanda.org/");
	    
	    System.out.println("Step 02: Click on the Account button");
	    clickToElementByJS("//span[@class='label' and text()='Account']");
	    
	    System.out.println("Step 03: Click on My Account button");
	    clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
	    
	    System.out.println("Step 04: Click on create an account button");
	    clickToElementByJS("//a[@title='Create an Account']");
	    
	    System.out.println("Step 05: Input First name");
	    sendkeyToElementByJS("//input[@id='firstname']", "trang");
	    
	    System.out.println("Step 06: Input Last name ");
	    sendkeyToElementByJS("//input[@id='lastname']", "le");
	    
	    System.out.println("Step 07: Input email address");
	    sendkeyToElementByJS("//input[@id='email_address']", "trang" +randomNumber()+ "@mail.com");
	    
	    System.out.println("Step 08: Input password");
	    sendkeyToElementByJS("//input[@id='password']", "123456");
	    
	    System.out.println("Step 09: Input confirmation password");
	    sendkeyToElementByJS("//input[@id='confirmation']", "123456");
	    
	    System.out.println("Step 10: Click on Register button");
	    clickToElementByJS("//span[text()='Register']");
	    sleepInSecond(3);
	    
	    System.out.println("Step 11: Verify register successfully");
	    String registernMsg = getInnerText();
	    Assert.assertTrue(registernMsg.contains("Thank you for registering with Main Website Store."));
	    
	    System.out.println("Step 12: Click on Account button");
	    clickToElementByJS("//span[@class='label' and text()='Account']");
	    
	    System.out.println("Step 13: Click on Log out button");
	    clickToElementByJS("//div[@id='header-account']//a[@title='Log Out']");
	    sleepInSecond(5);
	    
	    System.out.println("Step 14: Verify back to home page successfully");
	    String homePageTitle = driver.getTitle();
	    Assert.assertEquals(homePageTitle,"Home page");	    
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementAtBottom(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void sleepInSecond(long timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		System.out.println("----------TEST RESULT-----------");
		driver.quit();
	}

}
