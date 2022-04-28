package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_18_Upload_Sendkey {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String separatorChar = File.separator;
    String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
    
    //Image Name: Verify
    String seleniumImage = "Selenium.jpg";
    String appiumImage = "Appium.png";
    String apiImage = "API.jpg";
    
    //Image location: senkey
    String seleniumImageLocation = uploadFolderLocation + seleniumImage;
    String appiumImageLocation = uploadFolderLocation + appiumImage;
    String apiImageLocation = uploadFolderLocation + apiImage;

    @BeforeClass
    public void beforeClass() {
		//Both : Windows + MAC
    	if (osName.toUpperCase().startsWith("MAC")) {
    		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_mac");    		
    	} else {
    		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe"); 		
    	}
    	
    	driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_One_File_Per_Time() {  	
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 	
    	
    	By uploadFileBy = By.cssSelector("input[type='file']");
    	
    	//Load file
    	driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation);
    	sleepInSecond(1);
    	driver.findElement(uploadFileBy).sendKeys(appiumImageLocation);
    	sleepInSecond(1);
    	driver.findElement(uploadFileBy).sendKeys(apiImageLocation);
    	sleepInSecond(1);
    	
    	//Uploading
    	List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
    	for (WebElement start : startButtons) {
    		start.click();
    		sleepInSecond(1);
    	}
    	
    	//Verify Uploaded success
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + seleniumImage + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + appiumImage + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + apiImage + "']")).isDisplayed());
    	
    }
    
    @Test
    public void TC_02_Multiple_File_Per_Time() {  	
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 	
    	
    	By uploadFileBy = By.cssSelector("input[type='file']");
    	
    	//Load file
    	driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation + "\n" + appiumImageLocation + "\n" + apiImageLocation);
    	sleepInSecond(1);
    	
    	
    	//Uploading
    	List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
    	for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
    	
    	//Verify Uploaded success
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + seleniumImage + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + appiumImage + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='" + apiImage + "']")).isDisplayed());
    	
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