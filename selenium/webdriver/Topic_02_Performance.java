package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


	public class Topic_02_Performance {

		//Khai báo 1 biến đại diện cho Selenium WebDriver
	    WebDriver driver;
	    String projectPath = System.getProperty("user.dir");
	    
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();
  	
    	
    
    @Test
	public void TC_01_ID() {
    	
    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        
        //Find element = ID 1000 lần
        for (int i = 0 ; i < 1000 ; i++) {
        	System.out.println("ID Lần thứ : " + i);
        	driver.findElement(By.id("email"));
        }
        
    	driver.quit();
	}
    
    
    @Test
	public void TC_02_Css() {
    	
    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);   	
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        
        //Find element = Css 1000 lần
        for (int i = 0 ; i < 1000 ; i++) {
        	System.out.println("Css Lần thứ : " + i);
        	driver.findElement(By.cssSelector("input[id='email']"));
        }
        
    	driver.quit();
	}
    
    
    @Test
	public void TC_03_Xpath() {
    	
    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        
        //Find element = Xpath 1000 lần
        for (int i = 0 ; i < 1000 ; i++) {
        	System.out.println("Xpath Lần thứ : " + i);
        	driver.findElement(By.xpath("//input[@id='email']"));
        }
        
    	driver.quit();
	}
        
    

   
}
