package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_04_Exercise_Xpath_CSS_Register {

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

        // Mở trang tạo account techpanda lên
        driver.get("http://live.techpanda.org/");
    	}
    
    @Test
	public void TC_01_Register_with_emty_data() {
    	
    	
    	
    	
	}
    
    
    @Test
    public void TC_02_Register_with_invalid_email() {
    	
    	
    	
    	
	}
    
    
    @Test
    public void TC_03_Register_with_incorrect_confirm_email() {
    	
    	
    	
    	
	}
    
    
    @Test
    public void TC_04_Register_with_lessthan_6characters() {
    	
    	
    	
    	
    }
    
    
    @Test
    public void TC_05_Register_with_incorrect_confirm_password() {
    	
    	
    	
    	
    }
    
    
    @Test
    public void TC_06_Register_with_invalid_phone_number() {
    	
    	
    	
    	
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
