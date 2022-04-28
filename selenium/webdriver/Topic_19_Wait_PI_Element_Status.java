package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PI_Element_Status {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    WebDriverWait explicitwait;
    
    
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
		
    	explicitwait = new WebDriverWait(driver, 15);
    	
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);        
              
        driver.get("https://www.facebook.com/");
        
    	}
       
    //@Test
    public void TC_01_Visible() {  	
    	// Visible: Có trên UI và có trong DOM / HTML
    	explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='email']")));	
    	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
    }
    
    
    //@Test
    public void TC_02_Invisible_In_DOM(){  	
    	
    	driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
    	
    	// Invisible : Ko có trong trên UI vẫn có trong DOM (ko bắt buộc)
    	//Kết quả như nhau nhưng thời gian chạy của mỗi case khác nhau
    	//~1s
    	explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    	
    	//Không hiển thị -> Pass -> ~1s
    	Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
    }
    
    
    //@Test
    public void TC_02_Invisible_Not_In_DOM(){  	
    	// Invisible : Ko có trong trên UI và ko có trong DOM (ko bắt buộc)
    	driver.findElement(By.xpath("//div[text()='Đăng ký']//parent::div//preceding-sibling::img")).click();
    	sleepInSecond(3);
    	
    	//chạy mất 15s
    	explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    	
    	//Không hiển thị -> Fail -> 20s
    	Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
    	
    }
    
    
    //@Test
    public void TC_03_Presence(){  	
    	// Presence: Có trong DOM / HTML và có trên UI -> Pass
    	explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));	
    	
    	driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();  
    	sleepInSecond(3);
    	
    	// Presence: Có trong DOM / HTML và ko có trên UI -> Pass
    	explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    }
    
    
    @Test
    //TC_04 Staleness : xuất hiện element trong DOM, rồi biến mất
    public void TC_04_Staleness(){ 	
    	//Bật Registration form lên
    	driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();  
    	sleepInSecond(3);
    	
    	//Tại thời điểm này element đang có trong DOM
    	WebElement confirmationEmailAddressTextbox= driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
    	
    	//đóng Registration form  lại
    	driver.findElement(By.xpath("//div[text()='Đăng ký']//parent::div//preceding-sibling::img")).click();
    	
    	//Wait cho Confirmation Email Address ko còn trong DOM nữa
    	//Vì 1 lí do nào đó mình cần wait cho nó ko tồn tại trong DOM nữa
    	explicitwait.until(ExpectedConditions.stalenessOf(confirmationEmailAddressTextbox));
    }
    
   
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

 
    public void sleepInSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}