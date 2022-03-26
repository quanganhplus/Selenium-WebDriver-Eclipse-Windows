package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_07_demo_eBox_vne {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    

    @BeforeClass
    public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

//    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		
        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
 
    	}
    
    @Test
	public void TC_01_Login_iframe() {
    	
    	driver.get("https://ebox.vnexpress.net/");
    	driver.findElement(By.xpath("//p[@class='group-btn-inline']//following-sibling::a")).click();
    	sleepInSecond(2);
    	driver.findElement(By.xpath("//button[@class='btn btn-add-to-cart add-to-cart pc btn_buy_21']")).click();
    	sleepInSecond(2);
    	
    	//Switch vào iframe login vne này trước
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='mfp-iframe iframe_guest']")));    	
    	driver.findElement(By.xpath("//input[@name='myvne_email']")).sendKeys("duy.taddy@gmail.com");
    	driver.findElement(By.xpath("//input[@name='myvne_password']")).sendKeys("Duy123456");
    	driver.findElement(By.xpath("//button[@id='myvne_button_login']")).click();
    	
    	
//    	//Verify password textbox
//    	Assert.assertTrue(driver.findElement(By.xpath("//input[@name='myvne_password']")).isDisplayed());
  	
    	//Switch to parent - thoát iFrame login vne 
    	driver.switchTo().defaultContent();
    	sleepInSecond(3);
    	
    	driver.findElement(By.xpath("//button[@class='btn btn-add-to-cart add-to-cart pc btn_buy_21']")).click();
    	sleepInSecond(2);
    	
    	//chuyển đến trang Mua vé cá nhân
    	driver.findElement(By.xpath("//button[@id='btnOrderPersonal']")).click();
    	sleepInSecond(2);
    	
    	//chuyển đến trang thanh toán & chọn thanh toán chuyển khoản
    	driver.findElement(By.xpath("//span[text()='Chuyển khoản']")).click();
    	sleepInSecond(2);
    	
    	//Switch vào iframe chứa element reCAPTCHA
    	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
    	driver.findElement(By.xpath("//label[@id='recaptcha-anchor-label']")).click();
    	sleepInSecond(3);
    	//thoát iFrame
    	driver.switchTo().defaultContent();
    	sleepInSecond(3);
    	
    	driver.findElement(By.xpath("//button[@id='btn-payment']")).click();
    	sleepInSecond(2);
    	
    	//xác nhận chuyển khoản
    	driver.findElement(By.xpath("//button[@class='btn-ebox ebox-bg-primary']")).click();
    	sleepInSecond(2);
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
    
    public int getRandomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
