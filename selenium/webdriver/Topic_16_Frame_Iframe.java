package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Frame_Iframe {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    @Test
    public void TC_01_Kyna() {  	
    	driver.get("https://kyna.vn/"); 
    	
    	By iframeFb = By.xpath("//div[@class='fanpage ']//iframe");
    	Assert.assertTrue(driver.findElement(iframeFb).isDisplayed());
    	
    	//Switch vao farme/iframe trc sau đó mới thao tác lên element thuộc frame/iframe đó
    	driver.switchTo().frame(driver.findElement(iframeFb));
    
    	
    	//Verify số lượt like fb là 167K
    	//Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "167K lượt thích");
    	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='lfloat']//div[text()='167K likes']")).getText(), "167K likes");
    	
    	driver.findElement(By.cssSelector("div.border_overlay")).click();
    	driver.findElement(By.cssSelector("input.input_name")).sendKeys("quang anh");
    	driver.findElement(By.cssSelector("input.input.input_phone")).sendKeys("0977825566");
    	driver.findElement(By.xpath("//select[@id='serviceSelect']//option[text()='TƯ VẤN TUYỂN SINH']")).click();
    	driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java BootCamp");
    	
    	
    }
    
    
    //@Test
    public void TC_02_Random_Popup_In_Dom() {  	
    	
    	driver.get("https://vnk.edu.vn/");
    	
    }
    
    //@Test
    public void TC_03_Random_Popup_In_Dom_KMPlayer(){  	
    	
    	driver.get("https://www.kmplayer.com/home");
    	
    	
    }
    
    //@Test
    public void TC_04_Random_Popup_Not_In_Dom(){  	
    	
    	driver.get("https://dehieu.vn/");
    	sleepInSecond(5);
    	
    	
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

