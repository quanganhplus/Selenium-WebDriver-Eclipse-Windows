package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_10_Custom_Dropdown {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");    
    JavascriptExecutor jsExecutor;
    WebDriverWait expliciWait;
    

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Wait cho các trạng thái của element : visible/ presence/ invisible/ staleness
		expliciWait = new WebDriverWait(driver, 30);
		
		//Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    @Test
    public void TC_01_jQuery() {
    	
    	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
    	
    	//Step 1 : Click 1 element cho nó xổ hết ra các item
    	driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-icon")).click();
    	sleepInSecond(3);
    	
    	//Step 2: Chờ cho các item load ra hết thành công 
    	//Lưu ý 1: Locator chứa hết tất cả các item
    	//Lưu ý 2: Locator đến node cuối cùng chứa text
    	expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
    	
    	//Step 3: Tìm item cần chọn
    	// +B1: Nếu các item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll xuống tìm tiếp
    	// +B2: Nếu các item cần chọn nằm ở dưới thì scroll xuống đến item đó
    	
    	// Lấy hết tất cả các element (item) ra sau đó duyệt từng item 
    	List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
    	
    	//Duyệt qua từng item getText của item ra
    	for (WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual Text = " + actualText);
			
			//Nếu text = text mong muốn (item cần click vào)
			if (actualText.equals("5")) {
				//Step4 : Click vào item đó
				item.click();
				
				//Thoát khỏi vòng lặp ko có kiểm tra element tiếp theo nữa
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
    
    public int getRandomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
