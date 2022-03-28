package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_09_Default_Dropdown {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    JavascriptExecutor jsExecutor;
    WebDriverWait expliciwait;
    Actions action;
    Select select;
    

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Khởi tạo sau khi driver này đc sinh ra
		//JavascriptExecutor /WebdriverWait /Actions /...
		jsExecutor = (JavascriptExecutor) driver;
		expliciwait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		
        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

       
    	}
    
    @Test
	public void TC_01_Rode() {
    	//web lỗi
    	driver.get("https://rode.com/en/support/where-to-buy");
    	
    	//Khởi tạo khi sử dụng (element xuất hiện)
    	//Khởi tạo select để thao tác vs element (country dropdown)
    	select = new Select(driver.findElement(By.xpath("//select[@id='country']")));
    	
    	//Ko support Multiple select
    	Assert.assertFalse(select.isMultiple());
    	
    	//Select giá trị Vietnam
    	select.selectByVisibleText("Vietnam");
    	sleepInSecond(5);
    	
    	//Verify Vietnam selected success
    	Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
    	
    	driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
    	sleepInSecond(3);
    	
    	//results 32
    	Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(),"32");
    	
    	//Java Generic
    	List<WebElement> storeName = driver.findElements(By.cssSelector("div#search_results div.store_name"));
    	
    	//Verify tổng số lượng store name = 32
    	Assert.assertEquals(storeName.size(), 32);
    	
    	for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
    	
	}
    
    
    @Test
    public void TC_02_NopeCommerce() {
    	
    	
    	
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
