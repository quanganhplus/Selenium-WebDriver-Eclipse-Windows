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
    String osName = System.getProperty("os.name");
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
    	if (osName.startsWith("Windows")) {
    		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
    	} else if (osName.startsWith("Mac")) {
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_mac");
    	} else {
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_linux");
    	}
    	
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
    
    //@Test
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
    
    //@Test
    public void TC_05_Custom_Radio() {
    	driver.get("https://material.angular.io/components/radio/examples");
    	By winterCheckboxInput = By.cssSelector("input[value='Winter']");
    	
    	
    	//Case 1: Dùng thẻ Input
    	//Selenium click(); -> ElementNotInteractableException
    	//iSelected() -> work
    	
    	
    	//Case 2: Dùng thẻ Span
    	//Selenium click(); -> work
    	//iSelected() -> Not work
    	
    	
    	//Case 3: Dùng thẻ Span -> click
    	//Thẻ Input để - isSelected()
    	
    	
    	//Case 4: dùng thẻ Input
    	//Javascript - click (không quan tâm Element ẩn hay hiện)
    	//isSelected để verify
    	
    	clickByJavascript(winterCheckboxInput);
    	sleepInSecond(2);
    	
    	Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
    	
    }
    
    //@Test
    public void TC_06_Custom_Checkbox() {
    	driver.get("https://material.angular.io/components/checkbox/examples");
    	By checkedCheckbox = By.xpath("//span[text()='Checked']//preceding-sibling::span//input");
    	By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']//preceding-sibling::span//input");
    	
    	clickByJavascript(checkedCheckbox);
    	sleepInSecond(2);
    	clickByJavascript(indeterminateCheckbox);
    	sleepInSecond(2);
    	
    	Assert.assertTrue(isElementSelected(checkedCheckbox));
    	Assert.assertTrue(isElementSelected(indeterminateCheckbox));
    	
    	clickByJavascript(checkedCheckbox);
    	sleepInSecond(2);
    	clickByJavascript(indeterminateCheckbox);
    	sleepInSecond(2);
    	
    	Assert.assertFalse(isElementSelected(checkedCheckbox));
    	Assert.assertFalse(isElementSelected(indeterminateCheckbox));
    	
    }
    
    //@Test
    public void TC_07_Custom_Radio() {
    	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
 
    	By myfamilyRadio  = By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div//div[@class='mat-radio-inner-circle']");
    	By myselfRadio = By.xpath("//div[text()='Đăng ký bản thân']//preceding-sibling::div//div[@class='mat-radio-outer-circle']");
    	
    	clickByJavascript(myfamilyRadio);
    	sleepInSecond(2);
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).isDisplayed());
    	
    	clickByJavascript(myselfRadio);
    	sleepInSecond(2);
    	
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(), 0);
    	Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).size(), 0);
    	
    }
    
    @Test
    public void TC_08_Custom_Radio_Google() {
    	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
    	By haNoiCityRadio = By.xpath("//div[@aria-label='Hà Nội']");
    	Assert.assertEquals(driver.findElement(haNoiCityRadio).getAttribute("aria-checked"), "false");
    	
    	driver.findElement(haNoiCityRadio).click();
    	sleepInSecond(2);
    	
    	Assert.assertEquals(driver.findElement(haNoiCityRadio).getAttribute("aria-checked"), "true");
    }
    public void clickByJavascript(By by) {
    	
    	//arguments[0] chính là element đầu tiên đc tìm thấy bởi driver.findElement, ráp vào  arguments[0]
    	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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