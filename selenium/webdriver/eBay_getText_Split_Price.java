package webdriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class eBay_getText_Split_Price {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;   
    String projectPath = System.getProperty("user.dir");

    
    @BeforeClass
    public void beforeClass() {
//    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);    	
    	driver.get("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313&_nkw=iphone+11&_sacat=0");
    	
    }
       
    @Test
    public void TC_01() throws InterruptedException {   	
    	
    	String a = driver.findElement(By.xpath("//div[@class='s-item__details clearfix']//span[@class='s-item__price']//span[contains(text(),to)]/parent::span")).getText();
    	
    	ArrayList <String> arrayListString = new ArrayList<>();
    	for (String s: a.split(" ")) {
    		arrayListString.add(s);
    	}
    	System.out.println("Full gía trị form ... to: " + a);
    	System.out.println("Dấu khoảng trắng vị trí đầu tiên : " + arrayListString.get(0));
    	
    	sleepInSecond(5);
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