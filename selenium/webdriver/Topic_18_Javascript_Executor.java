package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_18_Javascript_Executor {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");
    

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    @Test
    public void TC_01_() {  		
    	
    	driver.get("http://live.techpanda.org/");
    	
    	//Selenium WebElement click()
    	driver.findElement(By.xpath("")).click();
    	
    	//Selenium: Actions click()
    	
    	//Selenium: JavascriptExecutor: click()   	
    	jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("")));
    	
    	
    	
    }
    
    
    //@Test
    public void TC_02_Cambride_Dictionary_Title() {  	
    	
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
