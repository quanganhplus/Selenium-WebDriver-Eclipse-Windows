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
    	//click vao MY ACCOUNT duoi footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);
        
        // Login Page Url matching
     	String loginPageUrl = driver.getCurrentUrl();
     	Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
     	
     	//click vao button CREATE AN ACCOUNT
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(3);
        
        // Register Page Url matching
     	String registerPageUrl = driver.getCurrentUrl();
     	Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}
    
    
    @Test
    public void TC_02_Verify_Title() {
    	//click vao MY ACCOUNT duoi footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);
        
        // Kiểm tra login Page title
     	String loginPageTitle = driver.getTitle();
     	Assert.assertEquals(loginPageTitle, "Customer Login");
     	
     	//click vao button CREATE AN ACCOUNT
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(3);
        
        // Kiểm tra Register Page title
     	String registerPageTitle = driver.getTitle();
     	Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
    
    
    @Test
    public void TC_03_Navigate_Function() {
    	//click vao MY ACCOUNT duoi footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);
     	
        //click vao button CREATE AN ACCOUNT 
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(3); 
     	
     	// Register Page Url matching
     	String registerPageUrl = driver.getCurrentUrl();
     	Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
     	
     	//click Back chuyển ve link trang Login Page
        driver.navigate().back();
        sleepInSecond(3);
     	
        // Register Page Url matching
     	String loginPageUrl = driver.getCurrentUrl();
     	Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
     	
     	//click Foward chuyển đến link trang Register Page
        driver.navigate().forward();
        sleepInSecond(3);
        
        // Kiểm tra Register Page title
     	String registerPageTitle = driver.getTitle();
     	Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
    
    
    @Test
    public void TC_04_PageSource() {
    	//click vao MY ACCOUNT duoi footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);
        
        //Kiem tra xem Loigin Page chua text : Login or Create an Account
        String homePageSource = driver.getPageSource();
        Assert.assertTrue(homePageSource.contains("Login or Create an Account"));
        
        //click vao button CREATE AN ACCOUNT
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(3);
        
        //Kiem tra xem Register Page chua text : Create an Account
        String registerPageSource = driver.getPageSource();
        Assert.assertTrue(registerPageSource.contains("Create an Account"));
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
