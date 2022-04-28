package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PIV_Static {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();     
        
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	}
       
    
    @Test
    public void TC_01_Equal() throws InterruptedException{ 
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	//Loading icon biến mất
    	Thread.sleep(5000);
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    @Test
    public void TC_02_Less() throws InterruptedException{ 
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	//3s ko đủ để laoding icon biến mất = thiếu 2s
    	Thread.sleep(3000);
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    @Test
    public void TC_03_Greater() throws InterruptedException{ 
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	//10s dư để laoding icon biến mất, dư 5s
    	Thread.sleep(10000);
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}