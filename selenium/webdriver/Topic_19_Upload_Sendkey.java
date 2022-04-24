package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Upload_Sendkey {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    String seleniumImage = "Selenium.jpg";
    String appiumImage = "Appium.png";
    String apiImage = "API.jpg";
    
    String seleniumImageLocation = projectPath + "/uploadFiles/" + seleniumImage;

    @BeforeClass
    public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);        
        driver.manage().window().maximize();
        
    	}
    
    
    @Test
    public void TC_01_Fixed_Popup() {  	
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 	
    	
    	driver.findElement(By.cssSelector("input[type='file']")).sendKeys("C:\\Users\\quang\\git\\Selenium-WebDriver-Eclipse-Windows\\uploadFiles\\Selenium.jpg");
    	
    
    }
    
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void sleepInSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}