package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video30_Topic14_UploadFile_Part2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String pic1 = "corgi.jpg";
	String pic2 = "poodle1.jpg";
	String pic1Path = projectPath + "\\uploadFiles\\" + pic1;
	String pic2Path = projectPath + "\\uploadFiles\\" + pic2;
	String singleUpload = projectPath + "\\autoIT\\" + "chrome1.exe";
	String firefoxSingleUpload = projectPath + "\\autoIT\\" + "firefox.exe";
	String multipleUpload = projectPath + "\\autoIT\\" + "multipleChrome.exe";
	String browserDriverName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		// System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		browserDriverName = driver.toString().toLowerCase();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// Chrome

	@Test
	public void TC01_AutoIT_Upload_SingleFile() throws IOException {
		System.out.println("==== TC01: Upload single files ===");

		System.out.println("Step 01: Launch the webpage");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("Step 02: Click on Add button");
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		sleepInSecond(2);

		/*
		 * if(browserDriverName.contains("firefox")) { Runtime.getRuntime().exec(new String[] {firefoxSingleUpload, pic1Path}); }else if (browserDriverName.contains("chrome")) { Runtime.getRuntime().exec(new String[] {singleUpload, pic1Path});
		 * 
		 * }
		 */

		System.out.println("Step 03: AutoIT single file upload");
		Runtime.getRuntime().exec(new String[] { singleUpload, pic2Path });
		sleepInSecond(3);

		System.out.println("Step 04: Verify uploaded files are displayed on upload page");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic2 + "']")).isDisplayed());

	}

	@Test
	public void TC02_AutoIT_Upload_MultipleFile() throws IOException {
		System.out.println("==== TC01: Upload multiple files on Chrome ===");

		System.out.println("Step 01: Launch the webpage");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("Step 02: Click on Add button");
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		sleepInSecond(2);

		System.out.println("Step 03: AutoIT single file upload");
		Runtime.getRuntime().exec(new String[] { multipleUpload, pic1Path, pic2Path });
		sleepInSecond(3);

		System.out.println("Step 04: Verify uploaded file 1 is displayed on upload page");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1 + "']")).isDisplayed());

		System.out.println("Step 05: Verify uploaded file 2 is displayed on upload page");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic2 + "']")).isDisplayed());

	}

	@Test
	public void TC03_Robot_Upload() throws IOException, AWTException {
		System.out.println("Step 01: Launch the webpage");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("Step 02: Click on Add button");
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		sleepInSecond(2);

		// copy file path
		StringSelection select = new StringSelection(pic1Path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		// load file
		Robot robot = new Robot();
		sleepInSecond(1);

		// Click CTRL + V
		System.out.println("Step 03: Click Ctrl V key");
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Release CTRL + V
		System.out.println("Step 04: Release Ctrl V key");
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);

		// Click Enter
		System.out.println("Step 04: Click Enter key");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);

		System.out.println("Step 03: Verify uploaded file 1 is displayed on upload page");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1 + "']")).isDisplayed());
	}

	// Firefox

	public void TC_AutoIT_Upload_FirefoxSingleFile() throws IOException {
		System.out.println("==== TC01: Upload single files ===");

		System.out.println("Step 01: Launch the webpage");
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		System.out.println("Step 02: Click on Add button");
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		sleepInSecond(2);

		System.out.println("Step 03: AutoIT single file upload");
		Runtime.getRuntime().exec(new String[] { firefoxSingleUpload, pic2Path });
		sleepInSecond(3);

		System.out.println("Step 04: Verify uploaded files are displayed on upload page");
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic2 + "']")).isDisplayed());

	}

	public void sleepInSecond(long timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
