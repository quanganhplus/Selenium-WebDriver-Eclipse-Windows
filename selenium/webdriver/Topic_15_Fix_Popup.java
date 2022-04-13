package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Fix_Popup {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    WebDriverWait expliciwait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
    	
		expliciwait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
		
		
		//khởi tạo action
		action = new Actions(driver);
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_Right_Click() {  	
    	driver.get("https://ngoaingu24h.vn/");
    	
    	
    	
    	
    }
    
    //@Test
    public void TC_02_Double_Click() {  	
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	
    	
    	
    }
    
    //@Test
    public void TC_03_Drag_And_Drop_HTML4() {  	
    	driver.get("https://automationfc.github.io/kendo-drag-drop/");
    	
    	
    	
    }
    
    //@Test
    public void TC_04_Drag_And_Drop_HTML5_Css() throws IOException {  	
    	
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

