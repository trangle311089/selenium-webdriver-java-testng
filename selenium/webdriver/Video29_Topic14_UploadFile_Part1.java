package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Video29_Topic14_UploadFile_Part1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String corgiPic = "corgi.jpg";
	String poodle1Pic = "poodle1.jpg";
	String poodle2Pic = "poodle2.jpg";
	String poodle3Pic = "poodle3.jpg";
	String corgiPath = projectPath + "\\uploadFiles\\" + corgiPic;
	String poodle1Path = projectPath + "\\uploadFiles\\" + poodle1Pic;
	String poodle2Path = projectPath + "\\uploadFiles\\" + poodle2Pic;
	String poodle3Path = projectPath + "\\uploadFiles\\" + poodle3Pic;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 20);
	}

	@Test
	public void TC01_Upload_Single_File() {
		System.out.println("==== TC01: Upload single files ===");

		System.out.println("Step 01: Launch the webpage");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("Step 02: Select corgi pic and upload");
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys("D:\\Online_Class_22\\08-Data\\Pictures\\corgi.jpg");

		System.out.println("Step 03: Verify corgi pic is loaded successfully");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + corgiPic + "']")).isDisplayed());

	}

	@Test
	public void TC02_Upload_Multiple_Files() {
		System.out.println("===== TC02: Upload multiple files =====");
		System.out.println("Step 01: Launch the webpage");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("Step 02: Select corgi file");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(corgiPath);

		System.out.println("Step 03: Select poodle 1 file");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(poodle1Path);

		System.out.println("Step 04: Select poodle 2 file");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(poodle2Path);

		System.out.println("Step 05: Select poodle 3 file");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(poodle3Path);

		System.out.println("Step 06: Verify corgi pic is displayed");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + corgiPic + "']")).isDisplayed());

		System.out.println("Step 07: Verify poodle 1 pic is displayed");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + poodle1Pic + "']")).isDisplayed());

		System.out.println("Step 08: Verify poodle 2 pic is displayed");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + poodle2Pic + "']")).isDisplayed());

		System.out.println("Step 09: Verify poodle 3 pic is displayed");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + poodle3Pic + "']")).isDisplayed());
	}

	@Test
	public void TC03_Upload_Multiple_Files_At_Same_Time() {
		System.out.println("===== TC03: Upload multiple files at same time =====");
		System.out.println("Step 01: Launch the webpage");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("Step 02: Select 3 files to upload");
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(poodle1Path + "\n" + poodle2Path + "\n" + poodle3Path);
		sleepInSecond(1);

		System.out.println("Step 07: Verify poodle 1 pic is displayed");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + poodle1Pic + "']")).isDisplayed());

		System.out.println("Step 08: Verify poodle 2 pic is displayed");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + poodle2Pic + "']")).isDisplayed());

		System.out.println("Step 09: Verify poodle 3 pic is displayed");
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + poodle3Pic + "']")).isDisplayed());

	}

	@Test
	public void TC04_Upload_File_On_GoFile() {
		System.out.println("===========TC04: Upload file on Go File page==========");
		System.out.println("Step 01: Launch the webpage");
		driver.get("https://gofile.io/uploadFiles");

		System.out.println("Step 02: Select 3 files to upload");
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(poodle1Path + "\n" + poodle2Path + "\n" + poodle3Path);
		
		System.out.println("Step 3: Wait until loading icon disappeared");
		explicitWait.until(
				ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("div.progress"))));
		
		System.out.println("Step 4: Wait until spinner icon disappeared");
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(("div#mainContent i.fa-spinner"))));
		
		System.out.println("Step 5: Wait successful statement display");
		explicitWait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//h5[ text()='Your files have been successfully uploaded']"))));

		System.out.println("Step 6: Verify upload files successfully");
		Assert.assertTrue(driver.findElement(By.xpath("//h5[ text()='Your files have been successfully uploaded']"))
				.isDisplayed());

		System.out.println("Step 7: Get link upload file");
		String uploadLink = driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']"))
				.getAttribute("href");

		System.out.println("Step 8: Open upload link");
		driver.get(uploadLink);
		sleepInSecond(1);

		System.out.println("Step 9: Verify uploaded files are displayed on upload page");
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='poodle1.jpg']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='poodle2.jpg']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='poodle3.jpg']")).isDisplayed());

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
