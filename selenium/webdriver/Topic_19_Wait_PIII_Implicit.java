package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PIII_Implicit {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();     
    	
    	//chỉ cẩn set Implicit 1 lần là đủ , nó sẽ tự áp dụng time wait cho các testcase tiếp theo
    	//Implicit nó chỉ liên quan đến findElement / findElements chứ ko liên quan đến hàm get URL lên đặt trc hay sau cũng đc
    	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
    	//driver.get("https://automationfc.github.io/dynamic-loading/");
    	}
       
    
    @Test
    public void TC_01(){ 
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    @Test
    public void TC_02(){ 
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);     	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    @Test
    public void TC_03(){ 
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}