package loop;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Loop_Register {
  WebDriver driver;
  String loginPageUrl, userID, userPassword, email;
  String projectPath = System.getProperty("user.dir");

  @BeforeMethod
  public void beforeMethod() {
	    System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
	    loginPageUrl = "https://demo.guru99.com/v4/";
	    
	    
	    email = "briantracy" + getRandomNumber() + "@mail.net";
  }

  @Test(invocationCount = 3)
  public void Register() {
	  	driver.get(loginPageUrl);
	  	
	    driver.findElement(By.xpath("//a[text()='here']")).click();
	
	    driver.findElement(By.name("emailid")).sendKeys(email);
	    driver.findElement(By.name("btnLogin")).click();
	
	    userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	    userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	    
	    System.out.println(userID);
	    System.out.println(userPassword);
  }

  @AfterMethod
  public void afterMethod() {
    driver.quit();
  	}
  
  public int getRandomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(9999);
	}
  
}