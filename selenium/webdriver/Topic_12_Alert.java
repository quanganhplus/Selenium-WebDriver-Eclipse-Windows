package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_Alert {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    Alert alert;
    String projectPath = System.getProperty("user.dir");
    String authenChrome = projectPath + "\\autoITScripts\\authen_chrome.exe";
    String authenFirefox = projectPath + "\\autoITScripts\\authen_firefox.exe";
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    
   

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
    	if (osName.startsWith("Windows")) {
    		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
    	} else if (osName.startsWith("Mac")) {
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_mac");
    	} else {
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_linux");
    	}
    	
		driver = new ChromeDriver();
		
		//Wait cho các trạng thái của element : visible/ presence/ invisible/ staleness
		explicitWait = new WebDriverWait(driver, 30);
		
		//Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_Accept_Alert() {
    	
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
    	sleepInSecond(2);
    	
    	//Switch qua Alert
    	alert= driver.switchTo().alert();    	
    	Assert.assertEquals(alert.getText(), "I am a JS Alert");
    	
    	//Accept Alert
    	alert.accept();
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    	
    }
    
  //@Test
    public void TC_02_Confirm_Alert() {
    	
    	driver.get("https://automationfc.github.io/basic-form/index.html");
    	driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
    	sleepInSecond(2);
    	
    	//Switch qua Alert
    	alert= driver.switchTo().alert();    	
    	Assert.assertEquals(alert.getText(), "I am a JS Confirm");
    	
    	//Cancel Alert
    	alert.dismiss();
    	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    	
    }
    
    
  //@Test
    public void TC_03_Prompt_Alert() {
    	
	  	driver.get("https://automationfc.github.io/basic-form/index.html");
	  	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  	sleepInSecond(2);
	  	
	  	//Switch qua Alert
	  	alert= driver.switchTo().alert();	  	
	  	Assert.assertEquals(alert.getText(), "I am a JS prompt");
	  	
	  	alert.sendKeys("Hello quanganh");
	  	
	  	//Accept Alert
    	alert.accept();
	  	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: Hello quanganh");  	    	
    }
    
    
    //@Test
    public void TC_04_Authentication_Alert_I() {
    	String username = "admin";
    	String password = "admin";
    	driver.get("http://"+ username +":"+ password +"@" + "the-internet.herokuapp.com/basic_auth");
    	sleepInSecond(2);
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
  
    //@Test
    public void TC_04_Authentication_Alert_II_pageA_To_pageB() {
    	String username = "admin";
    	String password = "admin";
	  	
    	driver.get("https://the-internet.herokuapp.com/");
	  	sleepInSecond(2);
	  	
	  	String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	  	System.out.println(basicAuthenLink);
	  	
	  	String[] basicAuthen = basicAuthenLink.split("//");
	  	
	  	basicAuthenLink = basicAuthen[0] + "//" + username + ":" + password + "@" + basicAuthen[1];
	  	System.out.println(basicAuthenLink);
	  	
	  	driver.get(basicAuthenLink);
	  	sleepInSecond(2);
	  	
	  	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
  }
    
    @Test
    public void TC_04_Authentication_Alert_III_AutoIT() throws IOException {
    	String username = "admin";
    	String password = "admin";
    	
    	driver.get("https://the-internet.herokuapp.com/");
    	
    	//Script sẽ chạy trc để chờ Authen Alert bật lên sau
    	if(driver.toString().contains("FireFox")) {
    		Runtime.getRuntime().exec(new String[] {authenFirefox, username, password});
    	} else {
    		Runtime.getRuntime().exec(new String[] {authenChrome, username, password});
    	}
    		    	
    	driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
    	sleepInSecond(2);
	  	
	  	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    	
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
