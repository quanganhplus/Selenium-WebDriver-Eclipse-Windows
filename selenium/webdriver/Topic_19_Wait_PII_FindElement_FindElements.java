package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PII_FindElement_FindElements {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);        
              
        driver.get("https://www.facebook.com/");
        
    	}
       
    //@Test
    public void TC_01_Find_Element() {  	
    	// - Có duy nhất 1 element
    	// Nếu như element xuất hiện ngay -> thì trả về element đó
    	// Nếu như element chưa xuất hiện -> thì sau 0.5s sẽ tìm lại cho đến khi nào hết time thì thôi
    	// Trả về duy nhất 1 element
//    	System.out.println("Start time = " + getCurrentTime());
//    	driver.findElement(By.xpath("//input[@id='email']"));
//    	System.out.println("End time = " + getCurrentTime());
    	
    	
    	
    	//- Không có element nào hết
    	// Nó sẽ tìm đi tìm lại cho đến hết timeout 10s
    	// Sau khi hết timeout thì đánh fail các test case này
    	// Ko có chạy các step còn lại
    	// Throw / Log ra 1 exception (ngoại lệ): No such Element - ko tìm thấy Element
//    	try {
//			System.out.println("Start time = " + getCurrentTime());
//			driver.findElement(By.xpath("//input[@name='quang anh']"));			
//		} catch (Exception e) {
//			System.out.println("End time = " + getCurrentTime());
//		}
    	
    	
    	
    	// - Có nhiều hơn 1 element
    	// Lấy ra element đầu tiên để thao tác
    	
    	driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
    	
    	
    }
    
    @Test
    public void TC_02_Find_Elements(){ 
    	int elementNumber = 0;
    	// - Có duy nhất 1 element 	
    	// - Có nhiều hơn 1 element
    	// Nếu như element xuất hiện ngay -> thì trả về element đó
    	// Nếu như element chưa xuất hiện -> thì sau 0.5s sẽ tìm lại cho đến khi nào hết time thì thôi
    	elementNumber = driver.findElements(By.xpath("//input[@id='email']")).size();
    	System.out.println("1 Element = " + elementNumber);
    	
    	
    	elementNumber = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
    	System.out.println("N Element = " + elementNumber);
    	
    	
    	// - Không có element nào hết
    	// Nó sẽ tìm đi tìm lại cho đến hết timeout 10s
    	// Sau khi hết timeout thì đánh fail Step này
    	// Vẫn chạy tiếp các step tiếp theo
    	System.out.println("Start time = " + getCurrentTime());
    	elementNumber = driver.findElements(By.xpath("//input[@name='quang anh']")).size();
    	System.out.println("0 Element = " + elementNumber);
    	System.out.println("End time = " + getCurrentTime());
    	
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
    
    public String getCurrentTime() {
    	Date date = new Date();
    	return date.toString();
    }
}