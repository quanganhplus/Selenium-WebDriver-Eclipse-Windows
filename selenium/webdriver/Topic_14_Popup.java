package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_Popup {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
    			
		jsExecutor = (JavascriptExecutor) driver;
		
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
    	}
    
    
    //@Test
    public void TC_01_Fixed_Popup() {  	
    	driver.get("https://ngoaingu24h.vn/"); 	
    	
    	By loginPopup = By.cssSelector("div#modal-login-v1");
    	Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    	
    	driver.findElement(By.cssSelector("button.login_")).click();
    	sleepInSecond(3);
    	Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
    	
    	driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
    	driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
    	
    	driver.findElement(By.cssSelector("button.btn-login-v1")).click();
    	
    	Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
    	
    	driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
    	sleepInSecond(3);
    	Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }
    
    
    //@Test
    public void TC_02_Random_Popup_In_Dom() {  	
    	//Step 1
    	driver.get("https://vnk.edu.vn/");
    	sleepInSecond(15);
    	
    	//Step 2 - luôn có element trong DOM dù có hiển thị hoặc ko
    	if (driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
    		System.out.println("Case 1 - Nếu như popup hiển thị thì có thể thao tác vs popup rồi close đi -> qua step tiếp theo ");
    		//Close popup trong này
    		driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
    		sleepInSecond(3);
    		
    		//Verify popup ko còn hiển thị nữa
    		Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
    	} else {
    		System.out.println("Case 2 - Nếu như popup ko hiển thị thì qua step tiếp theo luôn");
    	}
    	
    	 	
    	//Step 3 - Click vào trang Liên hệ
    	driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
    	
    	//Step 4 - Verify qua trang Liên hệ thành công
    	Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());
    }
    
    //@Test
    public void TC_03_Random_Popup_In_Dom_KMPlayer(){  	
    	//Step 1
    	driver.get("https://www.kmplayer.com/home");
    	
    	//Step 2 - luôn có element trong DOM dù có hiển thị hoặc ko
    	if (driver.findElement(By.cssSelector("div#layer2")).isDisplayed()) {
    		System.out.println("Case 1 - Nếu như popup hiển thị thì có thể thao tác vs popup rồi close đi -> qua step tiếp theo ");
    		//Close popup trong này
    		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
    		sleepInSecond(3);
    		
    		//Verify popup ko còn hiển thị nữa
    		Assert.assertFalse(driver.findElement(By.cssSelector("div#layer2")).isDisplayed());
    	} else {
    		System.out.println("Case 2 - Nếu như popup ko hiển thị thì qua step tiếp theo luôn");
    	}
    	
    	 	
    	//Step 3 - Click vào trang MOVIEBLOC 
    	driver.findElement(By.xpath("//a[contains(text(),'MOVIEBLOC')]")).click();
    	
    	//Step 4 - Verify qua trang Liên hệ thành công
    	Assert.assertTrue(driver.findElement(By.cssSelector("section.main_top_banner")).isDisplayed());
    }
    
    @Test
    public void TC_04_Random_Popup_Not_In_Dom(){  	
    	//Step 1
    	driver.get("https://dehieu.vn/");
    	sleepInSecond(5);
    	
    	System.out.println("Find popup - start : " + getCurrentDateTime());
    	
    	List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));  	
    	
    	System.out.println("Find popup - end : " + getCurrentDateTime());
    	
    	//Step 2 - luôn có element trong DOM dù có hiển thị hoặc ko
    	if (popupContent.size() > 0) {
    		System.out.println("Case 1 - Nếu như popup hiển thị thì có thể thao tác vs popup rồi close đi -> qua step tiếp theo ");
    		
    		//Thao tác với popup
    		driver.findElement(By.cssSelector("input#popup-name")).sendKeys("quang anh");
    		driver.findElement(By.cssSelector("input#popup-email")).sendKeys("quanganh@gmail.com");
    		driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0977825562");
    		sleepInSecond(3);
    		
    		//Close popup trong này
    		driver.findElement(By.cssSelector("button#close-popup")).click();
    		sleepInSecond(3);
    		
    		//Verify popup ko còn hiển thị nữa
    		System.out.println("Verify popup - start : " + getCurrentDateTime());
    		
    		popupContent = driver.findElements(By.cssSelector("div.popup-content"));
    		Assert.assertEquals(popupContent.size(), 0);
    		
    		System.out.println("Verify popup - end : " + getCurrentDateTime());

    	} else {
    		System.out.println("Case 2 - Nếu như popup ko hiển thị thì qua step tiếp theo luôn");
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
    
    public String getCurrentDateTime() {
    	return new Date().toString();
    }
}

