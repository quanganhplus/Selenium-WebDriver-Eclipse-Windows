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


public class Topic_08_Exerciese_Textbox_TextArea {

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
	public void TC_01_Textbox_TextArea() {
    	driver.get("https://demo.guru99.com/v4/");
    	driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr394771");
    	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("bEterEp");
    	driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
    	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
    	driver.findElement(By.xpath("//input[@name='name']")).sendKeys("quanganh");
    	
    	//Find the date time picker control
    	WebElement dateBox = driver.findElement(By.xpath("//input[@id='dob']"));

        //Fill date as mm/dd/yyyy as 03/24/2022
        dateBox.sendKeys("03242022");
        
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("17 duy tân \nnguyễn đình chiểu \nhbt \nhà nội");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("hanoi");
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys("hbt");
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0977825562");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("anhtq10@fpt.com.vn");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@name='sub']")).click();
        sleepInSecond(3);
	}
    
    
    //@Test
    public void TC_02_Register_with_invalid_email() {
    	
    	
    	
    	
	}
    
    
    //@Test
    public void TC_03_Register_with_incorrect_confirm_email() {
    	
    	
    	
    	
	}
    
    
    //@Test
    public void TC_04_Register_with_lessthan_6characters() {
    	
    	
    	
    	
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
