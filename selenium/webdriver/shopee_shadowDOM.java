package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class shopee_shadowDOM {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;   
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;
    
    @BeforeClass
    public void beforeClass() {
//    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	jsExecutor = (JavascriptExecutor) driver;
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);    	
    	
    	
    }
       
    @Test
    public void TC_01(){   	
    	driver.get("https://shopee.vn/");
    	WebElement popup = (WebElement) jsExecutor.executeScript("document.querySelector('shopee-banner-popup-stateful').shadowRoot.querySelector('shopee-banner-simple').shadowRoot.querySelector('a>img')");
    	Assert.assertTrue(popup.isDisplayed());
    	sleepInSecond(7);   	
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