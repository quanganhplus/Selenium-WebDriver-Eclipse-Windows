package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_Button_DefaultRadio_Checkbox {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");    
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
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
    public void TC_01_Button() {
    	
    	driver.get("https://www.fahasa.com/customer/account/create");
    	driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
    	
    	//Verify login button is disable
    	By loginButton = By.cssSelector("button.fhs-btn-login");
    	Assert.assertFalse(driver.findElement(loginButton).isEnabled());
    	
    	driver.findElement(By.cssSelector("input#login_username")).sendKeys("quanganh.plus@gmail.com");
    	driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
    	sleepInSecond(1);
    	
    	//Verify login button is enabled
    	Assert.assertTrue(driver.findElement(loginButton).isEnabled());
    	
    	//Verify login button background = Red
    	String loginButtonBackgroundColorRGB = driver.findElement(loginButton).getCssValue("background-color");
    	System.out.println("RGB color = " + loginButtonBackgroundColorRGB);
    	
    	//Verify = RGB
    	Assert.assertEquals(loginButtonBackgroundColorRGB, "rgba(201, 33, 39, 1)");
    	
    	//Convert qua Hexa
    	String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRGB).asHex();
    	System.out.println("Hexa color = " + loginButtonBackgroundColorHexa.toUpperCase());
    	Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(), "#C92127");
    	
    	driver.navigate().refresh();
    	driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
    	
    	//Remove disabled attribute
    	jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButton));
    	sleepInSecond(2);
    	
    	driver.findElement(loginButton).click();
    	
    	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
    	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box fhs-input-display checked-error']//following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
    	sleepInSecond(2);
    }
    
    //@Test
    public void TC_02_Default_Radio() {
    	
    	driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
    	
    	By oneDotEight = By.xpath("//label[text()='1.8 Petrol, 118kW']//preceding-sibling::input");
    	By twoDotZero = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input");
    	By threeDotSix = By.xpath("//label[text()='3.6 Petrol, 191kW']//preceding-sibling::input");
    	
    	Assert.assertFalse(driver.findElement(twoDotZero).isSelected());
    	
    	driver.findElement(twoDotZero).click();
    	sleepInSecond(3);  	
    	Assert.assertTrue(driver.findElement(twoDotZero).isSelected());
    	
    	driver.findElement(oneDotEight).click(); 
    	sleepInSecond(3);
    	Assert.assertTrue(driver.findElement(oneDotEight).isSelected());
    	
    	Assert.assertFalse(driver.findElement(twoDotZero).isSelected());
    	
    	Assert.assertFalse(driver.findElement(threeDotSix).isEnabled());
    }
    
    //@Test
    public void TC_03_Default_Checkbox() {
    	
    	driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
    	
    	By LuggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']//preceding-sibling::input");
    	By HeatedCheckbox = By.xpath("//label[text()='Heated front and rear seats']//preceding-sibling::input");
    	By TowbarCheckbox = By.xpath("//label[text()='Towbar preparation']//preceding-sibling::input");
    	By LeatherCheckbox = By.xpath("//label[text()='Leather trim']//preceding-sibling::input");
    	
    	//Select
    	checkToCheckbox(LuggageCheckbox);
    	checkToCheckbox(HeatedCheckbox);
    	
    	//Verify Selected
    	Assert.assertTrue(isElementSelected(LuggageCheckbox));
    	Assert.assertTrue(isElementSelected(HeatedCheckbox));
    	Assert.assertTrue(isElementSelected(LeatherCheckbox));
    	
    	//Verify Disabled
    	Assert.assertFalse(isElementEnabled(TowbarCheckbox));
    	Assert.assertFalse(isElementEnabled(LeatherCheckbox));
    	
    	//De-Select
    	uncheckToCheckbox(LuggageCheckbox);  	
    	uncheckToCheckbox(HeatedCheckbox);
    	
    	//Verify De-Selected
    	Assert.assertFalse(isElementSelected(LuggageCheckbox));
    	Assert.assertFalse(isElementSelected(HeatedCheckbox));
    	Assert.assertFalse(isElementSelected(TowbarCheckbox));
    	
	}
    
    @Test
    public void TC_04_Multiple_Checkbox() {
    	
    	driver.get("https://automationfc.github.io/multiple-fields/");
    	
    	List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
    	System.out.println("Checkbox size = " + checkboxes.size());
    	
    	//Action - Select
    	for (WebElement checkbox : checkboxes) {
    		if (!checkbox.isSelected()) {
    		checkbox.click();
		}
    }
    	//Verify Selected
    	for (WebElement checkbox : checkboxes) {
    		Assert.assertTrue(checkbox.isSelected());
		}
    	
    	//Action De-Select
    	for (WebElement checkbox : checkboxes) {
    		if (checkbox.isSelected()) {
    		checkbox.click();
		}
    }
    	//Verify - DeSelected
    	for (WebElement checkbox : checkboxes) {
    		Assert.assertFalse(checkbox.isSelected());
		}
    }
    
    public void checkToCheckbox(By by) {
    	if (!driver.findElement(by).isSelected()) {
    		driver.findElement(by).click();
    	}
    }
    
    public void uncheckToCheckbox(By by) {
    	if (driver.findElement(by).isSelected()) {
    		driver.findElement(by).click();
    	}
    }
    
    public boolean isElementSelected(By by) {
    	if (driver.findElement(by).isSelected()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean isElementEnabled(By by) {
    	if (driver.findElement(by).isEnabled()) {
    		return true;
    	} else {
    		return false;
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
