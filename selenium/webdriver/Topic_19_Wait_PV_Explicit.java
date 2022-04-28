package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PV_Explicit {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    
    //Wait rõ ràng: vs các điều kiện / status cụ thể
    WebDriverWait explicitWait;
    
    String projectPath = System.getProperty("user.dir");
    By loadingIcon = By.cssSelector("div#loading");
    By helloworldText = By.cssSelector("div#finish>h4");
    
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();     
    	
    	}
       
    
   //@Test
    public void TC_01_Invisible() { 
    	explicitWait = new WebDriverWait(driver, 30);
    	
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	//Loading icon biến mất sau 30s
    	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    
    //@Test
    public void TC_02_Visible() { 
    	explicitWait = new WebDriverWait(driver, 30);
    	
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	//Mong đợi Hello World hiển thị sau 30s
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
    	
    	Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
    }
    
    //@Test
    public void TC_03_Number() { 
    	explicitWait = new WebDriverWait(driver, 30);
    	
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	driver.findElement(By.cssSelector("div#start>button")).click();
    	
    	//Hello World text element = 1
    	explicitWait.until(ExpectedConditions.numberOfElementsToBe(helloworldText, 1));
    	
    	Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
    }
    
    @Test
    public void TC_04_Ajax_Loading() { 
    	explicitWait = new WebDriverWait(driver, 30);
    	
    	driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
    	
    	//Wait cho Date Picker xuất hiện
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
    	
    	//Element này đc tìm tại thời điểm mà chưa click lên ngày 11
    	WebElement selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
    	Assert.assertEquals(selectedDateText.getText(), "No Selected Dates to display.");
    	
    	//Wait cho ngày 11 có thể Click vào và sau đó click lên nó
    	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();
    	
    	//Wait cho Loading icon biến mất
    	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
    	
    	//Sau khi click vào ngày 11 thì element có text nó đc cập nhập lại
    	//Nếu dùng lại element ở trên rồi getText là sai
    	selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
    	
    	//Verify ngày được update
    	Assert.assertEquals(selectedDateText.getText(), "Monday, April 11, 2022");
    	
    	//Wait cho ngày được chọn selected thành công (visible)
    	WebElement todaySelected = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='11']")));
    	
    	//Verify ngày được chọn
    	Assert.assertTrue(todaySelected.isDisplayed());
    }
    
    
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}