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
        driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
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
    	
    	// Mở trang đăng ký alada lên
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	
        //nhập dữ liệu hợp lệ vào các field ngoại trừ Email và Confirm email
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("quanganh trinh");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@123@456");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@123@456");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("09123456789");
        
        
    	// Click button Đăng ký
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        //Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    	
    	
	}
    
    
    @Test
    public void TC_03_Register_with_incorrect_confirm_email() {
    	
    	// Mở trang đăng ký alada lên
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	
        //nhập dữ liệu hợp lệ vào các field ngoại trừ Confirm email
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("quanganh trinh");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("quanganh.plus@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@123");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("09123456789");
        
        
    	// Click button Đăng ký
        driver.findElement(By.xpath("//button[@type='submit']")).click();
      
        
        //Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    	
    	
	}
    
    
    @Test
    public void TC_04_Register_with_lessthan_6characters() {
    	
    	// Mở trang đăng ký alada lên
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	
        //nhập dữ liệu hợp lệ vào các field ngoại trừ Password và Confirm Password
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("quanganh trinh");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("quanganh.plus@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("quanganh.plus@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("09123456789");
        
        
    	// Click button Đăng ký
        driver.findElement(By.xpath("//button[@type='submit']")).click();
      
        
        //Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    	
    	
    }
    
    
    @Test
    public void TC_05_Register_with_incorrect_confirm_password() {
    	
    	// Mở trang đăng ký alada lên
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	
        //nhập dữ liệu hợp lệ vào các field ngoại trừ Confirm Password
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("quanganh trinh");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("quanganh.plus@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("quanganh.plus@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("1235678");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("09123456789");
        
        
    	// Click button Đăng ký
        driver.findElement(By.xpath("//button[@type='submit']")).click();
      
        
        //Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
    	
    	
    }
    
    
    @Test
    public void TC_06_Register_with_invalid_phone_number() {
    	
    	// Mở trang đăng ký alada lên
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    	
        //nhập dữ liệu hợp lệ vào các field ngoại trừ Phone number
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("quanganh trinh");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("quanganh.plus@gmail.com");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("quanganh.plus@gmail.com");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345678");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("097782556288");
        sleepInSecond(3);
        
    	// Click button Đăng ký
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        
        //Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
        
        //nhập dữ liệu hợp lệ vào các field ngoại trừ Phone number
        //clear sendKeys
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).clear();
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("trinh quang anh");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtEmail']")).clear();
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("quanganh.plus@icloud.com");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).clear();
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("quanganh.plus@icloud.com");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345678");
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123456");
        sleepInSecond(3);
        
        // Click button Đăng ký
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        //Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
    	
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
