package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_test {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.startsWith("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
		} else  {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");
		}
		
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Visible() {
		//Apply wait cho element trước khi tương tác với nó
		driver.get("https://www.facebook.com/");
		
		//Wait đến khi nút register xuất hiện và click (return về kiểu dữ liệu element)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		
		//Wait đến khi email xuất hiện và sendkey
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("luong123@gmail.com");
		
		//Wait cho element được visible/ displayed : Dimension là class kích thước, displayed = có Dimension
		Dimension confirmEmailSize = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']"))).getSize();
		
		System.out.println("Confirm email height : "+confirmEmailSize.getHeight());
		System.out.println("Confirm email width  : "+confirmEmailSize.getWidth());
		 
	}
	
	//@Test
	public void TC_02_Invisible_In_DOM() {
		driver.get("https://www.facebook.com/");
		
		//Wait đến khi nút register xuất hiện và click (return về kiểu dữ liệu element)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		sleepInSecond(3);
		
		//Element k hiển thị : k có trên UI + có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	//@Test
	public void TC_03_Invisible_Not_In_DOM() {
		driver.get("https://www.facebook.com/");

		//Element k hiển thị : k có trên UI + k có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	//@Test
	public void TC_04_Presence_In_UI() {
		driver.get("https://www.facebook.com/");
		
		//Wait đến khi nút register xuất hiện và click (return về kiểu dữ liệu element)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		
		//Wait đến khi email xuất hiện và sendkey
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("luong123@gmail.com");
		
		//Wait for presence
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	//@Test
	public void TC_05_Presence_Not_In_UI_Pass() {
		driver.get("https://www.facebook.com/");
		
		//Wait đến khi nút register xuất hiện và click (return về kiểu dữ liệu element)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		
		//Wait for presence
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	//@Test
	public void TC_06_Presence_Not_In_DOM_Fail() {
		driver.get("https://www.facebook.com/");
		
		//Wait for presence - Fail Do chưa click nên không có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	//@Test
	public void TC_07_Standless() {
		driver.get("https://www.facebook.com/");
		
		//Wait đến khi nút register xuất hiện và click (return về kiểu dữ liệu element)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		sleepInSecond(3);
		
		//Wait đến khi email xuất hiện và sendkey
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("luong123@gmail.com");
		sleepInSecond(3);
		
		//Element confirm email đang có trong DOM (visible/presence/invisible in DOM)
		WebElement confirmEmail = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		//Close popup đăng kí
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img"))).click();
		
		//Verify confirmEmail không xuất hiện trong DOM/UI : confirmEmail staleness
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
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