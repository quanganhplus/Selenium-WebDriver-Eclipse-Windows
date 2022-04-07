package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Action_Part_I {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    Actions action;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
    	if (osName.startsWith("Windows")) {
    		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
    	} else if (osName.startsWith("Mac")) {
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_mac");
    	} else {
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_linux");
    	}
    	
		driver = new ChromeDriver();
		//khởi tạo action
		action = new Actions(driver);
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_Hover() {  	
    	driver.get("https://automationfc.github.io/jquery-tooltip/");
    	WebElement yourAgeTextbox = driver.findElement(By.id("age"));
    	
    	//Hover chuột vào textbox
    	action.moveToElement(yourAgeTextbox).perform();
    	sleepInSecond(3);
    	
    	Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }
    
    //@Test
    public void TC_02_Hover() {  	
    	driver.get("https://www.myntra.com/");
    	
    	//Gọi hàm action cần dùng và gọi perform() cuối cùng
    	action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
    	sleepInSecond(3);
    	
    	action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();
    	Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
    }
  
    //@Test
    public void TC_03_Click_And_Hold() {  	
    	driver.get("https://automationfc.github.io/jquery-selectable/");
    	
    	//khai báo tất cả 12 elements
    	List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
    	
    	//1->4
    	//Click and hold vào 1
    	//Hover đến 4 
    	//Nhả chuột trái ra
    	action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
    	sleepInSecond(3);
    	
    	List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
    	Assert.assertEquals(allNumbersSelected.size(), 4);
    }
    
    @Test
    public void TC_04_Click_And_Hold_Random() {  	
    	driver.get("https://automationfc.github.io/jquery-selectable/");
    	
    	//khai báo tất cả 12 elements
    	List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
    	
    	Keys controlKey;
    	if (osName.contains("win") || osName.contains("nux")) {
    		controlKey = Keys.CONTROL;
    	} else {
    		controlKey = Keys.COMMAND;
    	}
    	
    	//1->5->11
    	//Nhấn phím Ctrl xuống
    	//Click vào 1
    	//Click vào 5
    	//Click vào 11
    	//Thực thi các câu lệnh
    	//Nhả phím Ctrl ra
    	action.keyDown(controlKey).perform();
    	action.click(allNumbers.get(0)).click(allNumbers.get(4)).click(allNumbers.get(10)).perform();
    	action.keyUp(controlKey).perform();
    	sleepInSecond(3);
    	
    	List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
    	Assert.assertEquals(allNumbersSelected.size(), 3);
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
