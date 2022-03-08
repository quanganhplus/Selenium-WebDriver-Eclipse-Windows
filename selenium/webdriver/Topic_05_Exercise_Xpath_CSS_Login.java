package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_05_Exercise_Xpath_CSS_Login {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();

       
    	}
    
    @Test
	public void TC_01_Login_with_empty_Email_and_Password() {
    	
    	
        
	}
    
    
    @Test
    public void TC_02_Login_with_invalid_Email() {
    	
    	
    	
    	
	}
    
    
    @Test
    public void TC_03_Login_with_lessthan_6characters() {
    	
    	
    	
	}
    
    
    @Test
    public void TC_04_Login_with_incorrect_Email_Password() {
    	
    	
    	
    	
    }
    
    
    @Test
    public void TC_05_Create_a_new_account() {
    	
    	
    	
    }
    
    
    @Test
    public void TC_06_Login_with_valid_Email_and_Password() {
    	
    	
    	
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
