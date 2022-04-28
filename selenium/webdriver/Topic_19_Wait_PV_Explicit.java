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
    	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
    	
    	WebElement selectedDateText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Panel1"));
    	Assert.assertEquals(selectedDateText.getText(), "No Selected Dates to display");
    	
    	//Wait cho ngày 11 có thể Click vào và sau đó click lên nó
    	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();
    	
    	//Wait cho Loading icon biến mất
    	
    	
    	//Verify ngày được update
    	
    	
    	//Verify ngày được chọn
    	
    }
    
    
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}