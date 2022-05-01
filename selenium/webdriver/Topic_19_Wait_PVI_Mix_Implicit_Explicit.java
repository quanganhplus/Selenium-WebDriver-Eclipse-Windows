package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PVI_Mix_Implicit_Explicit {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");

    
    @BeforeClass
    public void beforeClass() {
//    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	
    }
       
    
    //@Test
    public void TC_01_Element_Found() {   	
    	driver.get("https://www.facebook.com/");
    	By emailIDBy = By.id("email");
    	
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	System.out.println("Start implicit = " + getCurrentTime());
    	driver.findElement(emailIDBy).isDisplayed();
    	System.out.println("End implicit = " + getCurrentTime());
    	
    	
    	explicitWait = new WebDriverWait(driver, 15);
    	System.out.println("Start explicit = " + getCurrentTime());
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
    	System.out.println("End explicit = " + getCurrentTime());
    	
    }
    
    //@Test
    public void TC_02_Element_Not_Found_Only_Implicit() {   	
    	driver.get("https://www.facebook.com/");
    	By emailIDBy = By.id("vietnam");
    	
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	System.out.println("Start implicit = " + getCurrentTime());
    	try {
    		driver.findElement(emailIDBy).isDisplayed();
    	} catch (Exception e) {
    		//e.printStackTrace();
    	}
    	System.out.println("End implicit = " + getCurrentTime());
    	
    }
    
    //@Test
    public void TC_03_Element_Not_Found_Only_Explicit_By() {   	
    	driver.get("https://www.facebook.com/");
    	By emailIDBy = By.id("vietnam");
    	
    	//Implicit = 0
    	explicitWait = new WebDriverWait(driver, 15);
    	
    	System.out.println("Start explicit = " + getCurrentTime());
    	try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		} catch (Exception e) {
			//e.printStackTrace();
		}
    	System.out.println("End explicit = " + getCurrentTime());
    	  	
    }
    
    //@Test
    public void TC_04_Element_Not_Found_Implicit_Explicit() {   	
    	driver.get("https://www.facebook.com/");
    	By emailIDBy = By.id("vietnam");
    	
    	//1 - Implicit < Explicit
    	//2 - Implicit = Explicit
    	//3 - Implicit > Explicit
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	System.out.println("Start implicit = " + getCurrentTime());
    	try {
			driver.findElement(emailIDBy).isDisplayed();
		} catch (Exception e) {
			//e.printStackTrace();
		}
    	System.out.println("End implicit = " + getCurrentTime());
    	
    	
    	explicitWait = new WebDriverWait(driver, 2);
    	System.out.println("Start explicit = " + getCurrentTime());
    	try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		} catch (Exception e) {
			//e.printStackTrace();
		}
    	System.out.println("End explicit = " + getCurrentTime());
    	
    }
    
    @Test
    public void TC_05_Element_Not_Found_Only_Explicit_WebElement() {   	
    	driver.get("https://www.facebook.com/");
    	WebElement emailID = driver.findElement(By.id("vietnam"));
    	
    	//Implicit = 0
    	explicitWait = new WebDriverWait(driver, 15);
    	
    	System.out.println("Start explicit = " + getCurrentTime());
    	try {
			explicitWait.until(ExpectedConditions.visibilityOf(emailID));
		} catch (Exception e) {
			//e.printStackTrace();
		}
    	System.out.println("End explicit = " + getCurrentTime());
    	  	
    }
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    
    public String getCurrentTime() {
    	Date date = new Date();
    	return date.toString();
    }
}