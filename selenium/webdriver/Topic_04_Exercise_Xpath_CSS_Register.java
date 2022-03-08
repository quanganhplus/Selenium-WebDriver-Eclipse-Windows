package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_04_Exercise_Xpath_CSS_Register {

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
	public void TC_01_Register_with_emty_data() {
    	
    	 // Mở trang đăng ký alada lên
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	
    	// Click button Đăng ký
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        //Kiểm tra các erorr message hiển thị tại form đăng ký ở các field bắt buộc
        driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText();
        driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
        driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
        driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
        driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
        
        
        //Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
        
	}
    
    
    @Test
    public void TC_02_Register_with_invalid_email() {
    	
    	
    	
    	
	}
    
    
    @Test
    public void TC_03_Register_with_incorrect_confirm_email() {
    	
    	
    	
    	
	}
    
    
    @Test
    public void TC_04_Register_with_lessthan_6characters() {
    	
    	
    	
    	
    }
    
    
    @Test
    public void TC_05_Register_with_incorrect_confirm_password() {
    	
    	
    	
    	
    }
    
    
    @Test
    public void TC_06_Register_with_invalid_phone_number() {
    	
    	
    	
    	
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
