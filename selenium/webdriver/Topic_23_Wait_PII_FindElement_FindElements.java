package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_23_Wait_PII_FindElement_FindElements {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    
    
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);        
              
        driver.get("https://www.facebook.com/");
        
    	}
       
    @Test
    public void TC_01_Find_Element() {  	
    	// - Có duy nhất 1 element
    	// Nếu như element xuất hiện ngay -> thì trả về element đó
    	// Nếu như element chưa xuất hiện -> thì sau 0.5s sẽ tìm lại cho đến khi nào hết time thì thôi
    	//Trả về duy nhất 1 element
    	System.out.println("Start time = " + getCurrentTime());
    	driver.findElement(By.xpath("//input[@id='email']"));
    	System.out.println("End time = " + getCurrentTime());
    	
    	
    }
    
    //@Test
    public void TC_02_Find_Elements(){  	
    	
    	
    }
    
    
    //@Test
    public void TC_03_Presence(){  	
    	
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
    
    public String getCurrentTime() {
    	Date date = new Date();
    	return date.toString();
    }
}