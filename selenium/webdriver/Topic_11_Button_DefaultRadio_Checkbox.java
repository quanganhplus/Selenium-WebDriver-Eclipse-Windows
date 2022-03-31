package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_Button_DefaultRadio_Checkbox {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");    
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Wait cho các trạng thái của element : visible/ presence/ invisible/ staleness
		explicitWait = new WebDriverWait(driver, 30);
		
		//Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    @Test
    public void TC_01_Button() {
    	
    	driver.get("https://www.fahasa.com/customer/account/create");
    	driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
    	
    	//Verify login button is disable
    	By loginButton = By.cssSelector("button.fhs-btn-login");
    	Assert.assertFalse(driver.findElement(loginButton).isEnabled());
    	
    	driver.findElement(By.cssSelector("input#login_username")).sendKeys("quanganh.plus@gmail.com");
    	driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
    	sleepInSecond(1);
    	
    	//Verify login button is enabled
    	Assert.assertTrue(driver.findElement(loginButton).isEnabled());
    	
    	//Verify login button background = Red
    	String loginButtonBackgroundColorRGB = driver.findElement(loginButton).getCssValue("background-color");
    	System.out.println("RGB color = " + loginButtonBackgroundColorRGB);
    	
    	//Verify = RGB
    	Assert.assertEquals(loginButtonBackgroundColorRGB, "rgba(201, 33, 39, 1)");
    	
    	//Convert qua Hexa
    	String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRGB).asHex();
    	System.out.println("Hexa color = " + loginButtonBackgroundColorHexa.toUpperCase());
    	Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(), "#C92127");
    }
    
    //@Test
    public void TC_02_Default_Radio() {
    	
    	
    	
    }
    
    //@Test
    public void TC_03_Default_Checkbox() {
    	
    	
    	
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
