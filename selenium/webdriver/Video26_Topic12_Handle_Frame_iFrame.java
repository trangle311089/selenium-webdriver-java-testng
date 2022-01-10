package webdriver;

import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video26_Topic12_Handle_Frame_iFrame {
	WebDriver driver;
	//JavascriptExecutor jsExecutor;
	//NgWebDriver ngDriver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//jsExecutor = (JavascriptExecutor) driver;
		//ngDriver = new NgWebDriver(jsExecutor);

	}

	@Test
	public void TC06_Iframe() {
		System.out.println("STEP 01: Launch the Kyna website");
		driver.get("https://kyna.vn/");
		
		System.out.println("STEP 02: Declare the fb Iframe");
		WebElement fbIframe = driver.findElement(By.cssSelector(
				"div.fanpage div.face-content iframe[src='//www.facebook.com/plugins/likebox.php?href=https://www.facebook.com/kyna.vn&colorscheme=light&show_faces=true&stream=false&header=false&height=350&width=255']"));
		
		System.out.println("STEP 03: Verify the fb Ifarme is displayed in DOM");
		Assert.assertTrue(fbIframe.isDisplayed());
		
		System.out.println("STEP 04 : Switch to fbIframe");
		driver.switchTo().frame(fbIframe);
		
		System.out.println("STEP 05: Verify the number of likes is displayed");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(), "167K likes");
		
		System.out.println("STEP 06: Switch to the parent Iframe");
		driver.switchTo().parentFrame();
		
		System.out.println("STEP 07: Declare the chatIframe");
		WebElement chatIframe = driver.findElement(By.cssSelector("iframe#cs_chat_iframe"));
		
		System.out.println("STEP 08: Switch to the chatIframe");
		driver.switchTo().frame(chatIframe);
		sleepInSecond(5);
		
		System.out.println("STEP 09: Find the Chat popup");
		driver.findElement(ByAngular.cssContainingText("body.ng-scope", "")).click();
		sleepInSecond(3);
		
		System.out.println("STEP 10: Input information on the chat popup");
		driver.findElement(ByAngular.model("login.username")).sendKeys("Trang Le");
		driver.findElement(ByAngular.model("login.phone")).sendKeys("0793652133");
		driver.findElement(ByAngular.model("login.selectedService")).sendKeys("TƯ VẤN TUYỂN SINH");
		driver.findElement(ByAngular.model("login.content")).sendKeys("Selenium Java");
		
		System.out.println("STEP 11: Switch back to the parant iframe");
		driver.switchTo().parentFrame();
		
		System.out.println("STEP 12: Enter excel text on the search field");
		driver.findElement(By.cssSelector("input.live-search-bar")).sendKeys("excel");
		
		System.out.println("STEP 13: Click on the search icon");
		driver.findElement(By.cssSelector("button.search-button")).click();

		System.out.println("STEP 14: Verify the list of post titles that included excel text");
		List<WebElement> postTitles = driver.findElements(By.cssSelector("li.k-box-card img"));
		System.out.println("All posts contain excel text are:" + postTitles.size() + " posts");
		for (WebElement postTitle : postTitles) {
			System.out.println("Post name:" + postTitle.getAttribute("title"));
			Assert.assertTrue(postTitle.getAttribute("title").toLowerCase().contains("excel"));
		}

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
		driver.quit();
	}

}
