package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_17_Window_Tab {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_Naukri_Tab_ID() {  	
    	//trang A
    	driver.get("https://www.naukri.com/"); 
    	String homePageWindowID = driver.getWindowHandle();
    	System.out.println("Tab A: "+ homePageWindowID);
    	
    	//Click vào link Jobs link (trang B)
    	driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
    	sleepInSecond(3);
    	System.out.println("Tab A: " + driver.getCurrentUrl());
    	
    	switchToWindowByID(homePageWindowID);
    	
    	//Sau khi Switch qua
    	System.out.println("Tab B: " + driver.getCurrentUrl());
    	
    	String jobWindowID = driver.getWindowHandle();
    	switchToWindowByID(jobWindowID);
    	
    	//Sau khi Switch qua
    	System.out.println("Tab A: " + driver.getCurrentUrl());
    	sleepInSecond(10);
    }
    
    
    @Test
    public void TC_02_Cambride_Dictionary_Title() {  	
    	//trang A
    	driver.get("https://dictionary.cambridge.org/vi/"); 
    	
    	
    }
    
    
    //Có thể viết thành 1 hàm khi nào muốn dùng thì gọi hàm đó ra
    //Chỉ dùng duy nhất 2 tab/ window
    public void switchToWindowByID(String currentWindowID) {
    	// Lấy hết tất cả các ID đang có ra
    	Set<String> allWindowsIDs = driver.getWindowHandles();
    	
    	//Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
    	for (String id : allWindowsIDs) {
			//Nếu như cái id nào mà khác vs id của page hiện tại
    		if (!id.equals(currentWindowID)) {
    			//Switch qua id của tab đó
    			driver.switchTo().window(id);
    		}
		}
    }
    
    //Cách 2: Dùng cho cả 2 Tab/ window hoặc nhiều hơn 2 đều được
    public void switchToWindowByTitle(String expectedTitle) {
    	// Lấy hết tất cả các ID đang có ra
    	Set<String> allWindowsIDs = driver.getWindowHandles();
    	
    	//Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
    	for (String id : allWindowsIDs) {
    		//Switch vào trước rồi mới kiểm tra điều kiện sau
    		driver.switchTo().window(id);
    		
    		//Lấy cái title của page đó ra
    		String actualTitle = driver.getTitle();
    		if (actualTitle.equals(expectedTitle)) {
				//Thỏa mãn điều kiện là đúng Tab/Window mình cần
    			break;
			}
    	}
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
