package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_19_Wait_PVIII_Page_Loading {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions action;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    
    @BeforeClass
    public void beforeClass() {
//    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	
    	action = new Actions(driver);
    }
       
    //@Test
    public void TC_01_OrangeHRM_Implicit() {   	
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
    	
    	driver.get("https://api.orangehrm.com/#api-_");
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#project>div>h1")).getText(), "OrangeHRM REST API Documentation");
    	
    }
    
    //@Test
    public void TC_02_OrangeHRM_Explicit() {   	
    	driver.navigate().refresh();
    	
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	explicitWait = new WebDriverWait(driver, 30); 
    	
    	//Wait cho spiner invisible
    	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#project>div>h1")).getText(), "OrangeHRM REST API Documentation");
    }
    
    //@Test
    public void TC_03_OrangeHRM_Page_Ready() {   	
    	driver.navigate().refresh();
    	
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	//Wait cho page load success / ready
    	Assert.assertTrue(isPageLoadedSuccess());
    		
    	Assert.assertEquals(driver.findElement(By.cssSelector("div#project>div>h1")).getText(), "OrangeHRM REST API Documentation");
    }
    
    //@Test
    public void TC_04_OrangeHRM_Page_Ready() {   	
    	driver.get("https://opensource-demo.orangehrmlive.com");
    	
    	driver.findElement(By.id("txtUsername")).sendKeys("Admin");
    	driver.findElement(By.id("txtPassword")).sendKeys("admin123");
    	driver.findElement(By.id("btnLogin")).click();
    	
    	//Wait cho page load success / ready
    	Assert.assertTrue(isPageLoadedSuccess());
    	
    	Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
    }
    
    @Test
    public void TC_05_Test_Project_Page_Ready() {   	
    	driver.get("https://blog.testproject.io/");
    	
    	//Handle popup
    	if (driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
    		driver.findElement(By.cssSelector("div#mailch-bg path")).click();
    	}
    	
    	//Hover chuột vào ô Search
    	action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
    	Assert.assertTrue(isPageLoadedSuccess());
    	
    	driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
    	driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
    	
    	//Wait cho page load success / ready
    	Assert.assertTrue(isPageLoadedSuccess());
    	
    	List<WebElement> allPostTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
    	for (WebElement postTitle : allPostTitle) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
			System.out.println(postTitle.getText());
		}
    }
    
    //jQuery + Javascript
    public boolean isPageLoadedSuccess() {
    	explicitWait = new WebDriverWait(driver, 30);
    	jsExecutor = (JavascriptExecutor) driver;
    	ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
    		@Override
    		public Boolean apply(WebDriver driver) {
    			return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
    		}
    	};

    	ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
    		@Override
    		public Boolean apply(WebDriver driver) {
    			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
    	};
    	return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
}

   
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}