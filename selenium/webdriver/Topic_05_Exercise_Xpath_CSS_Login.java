package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_05_Exercise_Xpath_CSS_Login {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    //Khai báo biến 
    String firstName, fullName, lastName, emailAddress, password;

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		firstName = "quang anh";
		lastName = "trinh";
		fullName = firstName +" "+ lastName;
		emailAddress = "quanganh.plus" + getRandomNumber() + "@gmail.com";
		password = "123456";
		
        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();

       
    	}
    
    @Test
	public void TC_01_Login_with_empty_Email_and_Password() {
    	
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
	}
    
    
    @Test
    public void TC_02_Login_with_invalid_Email() {
    	
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
        sleepInSecond(2);
	}
    
    
    @Test
    public void TC_03_Login_with_lessthan_6characters() {
    	
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("quanganh.plus@gmail.com");
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
        sleepInSecond(2);
    	
	}
    
    
    @Test
    public void TC_04_Login_with_incorrect_Email_Password() {
    	
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("quanganh.plus@gmail.com");
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']")).getText(), "Invalid login or password.");
        sleepInSecond(2);
    	
    	
    }
    
    
    @Test
    public void TC_05_Create_a_new_account() {
    	
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
    	driver.findElement(By.xpath("//a[@class='button']")).click();
    	driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//button[@class='button']")).click();
    	sleepInSecond(1);
    	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");
    	sleepInSecond(1);
    	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//p[@class='hello']")).getText(), "Hello, "+ fullName +"!");
    	sleepInSecond(1);

    	String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		sleepInSecond(1);
		
    	driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class=\"skip-link skip-account\"]")).click();
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//li[@class=' last']")).click();
    	sleepInSecond(1);
    	// Logout Page Url matching
     	String logoutPageUrl = driver.getCurrentUrl();
     	Assert.assertEquals(logoutPageUrl, "http://live.techpanda.org/index.php/customer/account/logoutSuccess/");
    	
    }
    
    
    @Test
    public void TC_06_Login_with_valid_Email_and_Password() {
    	
    	driver.get("http://live.techpanda.org/");
    	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
    	driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
    	sleepInSecond(1);
    	driver.findElement(By.xpath("//button[@id='send2']")).click();
    	
    	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//p[@class='hello']")).getText(), "Hello, "+ fullName +"!");
    	sleepInSecond(1);
    	String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		sleepInSecond(1);
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
