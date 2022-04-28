package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Frame_Iframe {

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_Kyna() {  	
    	driver.get("https://kyna.vn/"); 
    	
    	By iframeFb = By.xpath("//div[@class='fanpage ']//iframe");
    	Assert.assertTrue(driver.findElement(iframeFb).isDisplayed());
    	
    	//Switch vao farme/iframe trc sau đó mới thao tác lên element thuộc frame/iframe đó
    	driver.switchTo().frame(driver.findElement(iframeFb));
    
    	
    	//Verify số lượt like fb là 167K
    	//Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "167K lượt thích");
    	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='lfloat']//div[text()='167K likes']")).getText(), "167K likes");
    	
    	//Switch ra khỏi iframe fb
    	driver.switchTo().defaultContent();
    	
    	//Switch vào iframe Chat
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
    	
    	driver.findElement(By.cssSelector("div.border_overlay")).click();
    	
    	driver.findElement(By.cssSelector("input.input_name")).sendKeys("quang anh");
    	driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0977825566");
    	driver.findElement(By.xpath("//select[@id='serviceSelect']//option[text()='TƯ VẤN TUYỂN SINH']")).click();
    	driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java BootCamp");
    	
    	//Switch ra khỏi iframe Chat
    	driver.switchTo().defaultContent();
    	
    	//Nhập và search
    	String keyword = "Excel";
    	driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
    	driver.findElement(By.cssSelector("button.search-button")).click();
    	
    	//Verify course name chứa từ khóa vừa nhập
    	List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
    	for (WebElement course : courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
		}
    	
    	sleepInSecond(3);
    }
    
    
    @Test
    public void TC_02_Blog() {  	
    	//A (automationfc.com)
    	driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
    	
    	//A -> B(Youtube)
    	driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.youtube-player")));
    	
    	//B
    	driver.findElement(By.cssSelector("button.ytp-large-play-button")).click();
    	sleepInSecond(5);
    	
    	//B -> A
    	driver.switchTo().defaultContent();
    	
    	//A -> C (iframe fb)
    	driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page>span>iframe")));
    	
    	//C
    	Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Automation FC']//parent::div//following-sibling::div")).getText(), "3,306 likes");
    }
    
    //@Test
    public void TC_03_HDFC() {  	
    	//A (HDFC)
    	driver.get("https://netbanking.hdfcbank.com/netbanking/");
    	
    	//A -> B(frame login)
    	driver.switchTo().frame("login_page");
    	
    	driver.findElement(By.cssSelector("input.form-control")).sendKeys("quanganh");
    	driver.findElement(By.cssSelector("a.login-btn")).click();
    	sleepInSecond(3);
    	
    	Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
    	 
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
