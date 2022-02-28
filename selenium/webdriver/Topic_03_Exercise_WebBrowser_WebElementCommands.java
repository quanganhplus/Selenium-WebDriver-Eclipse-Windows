package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_03_Exercise_WebBrowser_WebElementCommands {

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
	public void TC_01_Verify_Url() {
    	//LinkText - click chuyển đến link trang MY ACCOUNT
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        sleepInSecond(3);
        
        // Login Page Url matching
     	String loginPageUrl = driver.getCurrentUrl();
     	Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
     	
     	//LinkText - click chuyển đến link trang CREATE AN ACCOUNT
        driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
        sleepInSecond(3);
        
     // Login Page Url matching
     	String registerPageUrl = driver.getCurrentUrl();
     	Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
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
