package webdriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_18_Upload_AutoIT {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String separatorChar = File.separator;
    String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
    String autoITFolderLocation = projectPath + separatorChar + "autoITScripts" + separatorChar;
    
    //Image Name: Verify
    String seleniumImage = "Selenium.jpg";
    String appiumImage = "Appium.png";
    String apiImage = "API.jpg";
    
    //Image location: senkey
    String seleniumImageLocation = uploadFolderLocation + seleniumImage;
    String appiumImageLocation = uploadFolderLocation + appiumImage;
    String apiImageLocation = uploadFolderLocation + apiImage;
    
    //AutoIT script locator
    String singleFirefox = autoITFolderLocation + "firefox.exe";
    String singleChrome = autoITFolderLocation + "chrome.exe";
    String multipleFirefox = autoITFolderLocation + "firefoxUploadMultiple.exe";
    String multipleChrome = autoITFolderLocation + "chromeUploadMultiple.exe";
    
    @BeforeClass
    public void beforeClass() {
		//Both : Windows + MAC
    	if (osName.toUpperCase().startsWith("MAC")) {
    		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_mac");
    		System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver_mac");
   		
    	} else {
    		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe"); 		
    		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\\\msedgedriver.exe"); 		
    	}
    	
    	driver = new EdgeDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_One_File_Per_Time() throws IOException {  	
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 	
    	
    	//Click để bật lên Open File Dialog
    	driver.findElement(By.cssSelector("span.fileinput-button")).click();
    	
    	//Load file
		Runtime.getRuntime().exec(new String[] { singleFirefox, seleniumImageLocation });
    	sleepInSecond(2);
    	
		//Click để bật lên Open File Dialog
    	driver.findElement(By.cssSelector("span.fileinput-button")).click();
    	
    	//Load file
		Runtime.getRuntime().exec(new String[] { singleFirefox, appiumImageLocation });
		sleepInSecond(2);
		
		//Click để bật lên Open File Dialog
    	driver.findElement(By.cssSelector("span.fileinput-button")).click();
    	
    	//Load file
		Runtime.getRuntime().exec(new String[] { singleFirefox, apiImageLocation });
		sleepInSecond(2);
		
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
    public void TC_02_Multiple_File_Per_Time() throws IOException {  	
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 	
    	
    	//Click để bật lên Open File Dialog
    	driver.findElement(By.cssSelector("span.fileinput-button")).click();
    	
    	//Load file
    	if (driver.toString().contains("Chrome") || driver.toString().contains("Edge")) {
    		Runtime.getRuntime().exec(new String[] { multipleChrome, seleniumImageLocation, appiumImageLocation, apiImageLocation  });
		} else {
			Runtime.getRuntime().exec(new String[] { multipleFirefox, seleniumImageLocation, appiumImageLocation, apiImageLocation  });
		}
		
    	sleepInSecond(2);
    	
    	
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