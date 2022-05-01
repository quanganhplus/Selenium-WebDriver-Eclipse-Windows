package webdriver;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.base.Function;


public class Topic_19_Wait_PVII_Fluent {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    
    FluentWait<WebElement> fluentElement;
    String projectPath = System.getProperty("user.dir");

    
    @BeforeClass
    public void beforeClass() {
//    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	
    }
       
    
    //@Test
    public void TC_01_() {   	
    	
    	//Find Element có tổng thời gian là 15s
    	//Tần số lặp lại polling để tìm nếu không thấy là 1s / 1 lần
    	fluentDriver = new FluentWait<WebDriver>(driver);
    	
    	//Tổng thời gian cho điều kiện
    	fluentDriver.withTimeout(Duration.ofSeconds(15))
    	                // Tần số mỗi 1s check 1 lần
    	                .pollingEvery(Duration.ofSeconds(1))
    	                // Nếu gặp exception là find ko thấy element sẽ bỏ qua
    	                .ignoring(NoSuchElementException.class)
    	                
    	                //Điều kiện
    	                .until(new Function<WebDriver, WebElement>() {
    	                	public WebElement apply(WebDriver driver) {
    	                		return driver.findElement(By.xpath("//input[@name='btnI-fail']"));
    	                	}
    	                });

    	WebElement loginButton = driver.findElement(By.xpath(""));
    	
    	fluentElement = new FluentWait<WebElement>(loginButton);
    	
    	//Setting time
    	fluentElement.withTimeout(Duration.ofSeconds(60))
    	.pollingEvery(Duration.ofMillis(200))
    	.ignoring(ElementNotVisibleException.class);
    	
    	//Apply điều kiện và trả về String
    	String loginButtonText = fluentElement.until(new Function<WebElement, String>(){
			public String apply(WebElement element) {
				return element.getText();
			}   		
    	});
    	
    	Assert.assertEquals(loginButtonText, "");
    	
    	//Apply điều kiện và trả về Boolean - class Wrap (viết hoa chữ cái đầu tiên)
    	boolean loginButtonStatus = fluentElement.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement element) {
				return element.isDisplayed();
			}   		
    	});
    	
    	//Dữ liệu dùng cho 1 step khác 
    	Assert.assertTrue(loginButtonStatus);
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