package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_06_Exercise_WebElement_Commands {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        
    	}
    
    @Test
	public void TC_01_isDisplayed() {
    	
    	// Mở trang automationfc
        driver.get("https://automationfc.github.io/basic-form/index.html");
        
    	// Kiểm tra các phần tử hiển thị trên trang
    	//email
    	WebElement emailID = driver.findElement(By.xpath("//input[@id='mail']"));
    	Assert.assertTrue(emailID.isDisplayed());
    	
    	emailID.sendKeys("Automation Testing");
    	sleepInSecond(2);
    	
    	//in ra  màn hình nếu có hiển thị và ngược lại
    	if (emailID.isDisplayed()) {
    		System.out.println("Element is displayed");
    	}
    	else {
    		System.out.println("Element is not displayed");
    	}
    	
    	
    	//Under 18
    	WebElement under18 = driver.findElement(By.xpath("//label[text()='Under 18']"));
    	Assert.assertTrue(under18.isDisplayed());
    	
    	under18.click();
    	sleepInSecond(2);
    	
    	//in ra  màn hình nếu có hiển thị và ngược lại
    	if (under18.isDisplayed()) {
    		System.out.println("Element is displayed");
    	}
    	else {
    		System.out.println("Element is not displayed");
    	}
    	
    	
    	//Education
    	WebElement educationlID = driver.findElement(By.xpath("//textarea[@id='edu']"));
    	Assert.assertTrue(educationlID.isDisplayed());
    	
    	educationlID.sendKeys("Automation Testing");
    	sleepInSecond(2);
    	
    	//in ra  màn hình nếu có hiển thị và ngược lại
    	if (educationlID.isDisplayed()) {
    		System.out.println("Element is displayed");
    	}
    	else {
    		System.out.println("Element is not displayed");
    	}
    	
    	
    	//Name: User5
    	//Đếm số lượng User5, nếu = 0 thì in ra ko hiển thị và ngược lại
    	int countUser5 = driver.findElements(By.name("Name: User5")).size();
    	
    	//in ra  màn hình nếu có hiển thị và ngược lại
    	if (countUser5 == 0) {
    		System.out.println("Element is not displayed");
    	}
    	else {
    		System.out.println("Element is displayed");
    	}
	}
    
    
    @Test
    public void TC_02_isEnabled() {
    	
	}
    
    
    @Test
    public void TC_03_isSelected() {
    	
	}
    
    
    @Test
    public void TC_04_Register_function_at_MailChimp() {
    	
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
