package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PV_Explicit {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    
    
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();     
        
    	explicitWait = new WebDriverWait(driver, 15);
    	
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	}
       
    
    @Test
    public void TC_01_Equal() { 
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}