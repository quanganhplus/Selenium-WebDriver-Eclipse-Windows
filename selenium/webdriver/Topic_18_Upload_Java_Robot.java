package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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


public class Topic_18_Upload_Java_Robot {

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
    	
    	driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);        
        driver.manage().window().maximize();
        
    	}
    
    
    @Test
    public void TC_01_One_File_Per_Time() throws IOException, AWTException {  	
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 	
    	//File 1
    	//Copy đường dẫn của file vào clipboard
    	StringSelection select = new StringSelection(seleniumImageLocation);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        //Click để bật lên Open File Dialog
    	driver.findElement(By.cssSelector("span.fileinput-button")).click();  	
    	loadFileByJavaRobot();
    	
    	
    	//File 2
    	//Copy đường dẫn của file vào clipboard
    	select = new StringSelection(appiumImageLocation);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        //Click để bật lên Open File Dialog
    	driver.findElement(By.cssSelector("span.fileinput-button")).click();   	
    	loadFileByJavaRobot();
    	
    	
    	//File 3
    	//Copy đường dẫn của file vào clipboard
    	select = new StringSelection(apiImageLocation);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        //Click để bật lên Open File Dialog
    	driver.findElement(By.cssSelector("span.fileinput-button")).click();    	
    	loadFileByJavaRobot();
		
    	
    	
    	
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

    public void loadFileByJavaRobot() {
    	//Load file
    	Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sleepInSecond(1);

        // Nhan phim Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Nhan xuong Ctrl - V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Nha Ctrl - V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        sleepInSecond(1);

        // Nhan Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleepInSecond(1);
    }
    
    public void sleepInSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}