package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_test {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
//		if (osName.startsWith("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		} else if (osName.startsWith("Mac")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
//		} else  {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");
//		}
//		
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		
		//Switch vào iframe chứa element này trước
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		
		//Element thuộc iframe facebook
		String facebookLikeKyna = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebookLikeKyna, "167K likes");
		System.out.println(facebookLikeKyna);
		
		//Switch to parent
		driver.switchTo().defaultContent();
		
		//Element thuộc iframe chat, switch vào iframe chat
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		driver.findElement(By.xpath("//div[@ng-click='openChatBox(1)']")).click();
		sleepInSecond(2);
		
		//input data to iframe chat
		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("Dinh Luong");
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0123456789");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Register new account");
		sleepInSecond(3);
		
		String keyword = "Excel";
		//Switch to parent để nhập từ khóa excel => Search
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys(keyword);
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		sleepInSecond(3);
		//Verify Excel course
		List<WebElement> courseName = driver.findElements(By.xpath("//div[@class='content']/h4"));
		for (WebElement course : courseName) {
			
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().toLowerCase().contains(keyword.toLowerCase()));
		}
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		//switch to frame
		driver.switchTo().frame("login_page");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		
		//Verify password textbox
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}