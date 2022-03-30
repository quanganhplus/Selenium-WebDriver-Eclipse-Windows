package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic_10_Custom_Dropdown {

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
    public void TC_01_jQuery() {
    	
    	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
    	
    	selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
    	Assert.assertEquals("5", driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText());
    	
    	selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
    	Assert.assertEquals("15", driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText());
    	
    	selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
    	Assert.assertEquals("19", driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText());
    	
    	selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "3");
    	Assert.assertEquals("3", driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText());
    	
    	//span#number-button>span.ui-selectmenu-icon
    	//ul#number-menu div
    	//5
    	
	}
    
    
    //@Test
    public void TC_02_ReactJS() {
    	
    	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
    	
    	selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Stevie Feliciano");
    	Assert.assertEquals("Stevie Feliciano", driver.findElement(By.cssSelector("div.divider.text")).getText());
    	
    	selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Jenny Hess");
    	Assert.assertEquals("Jenny Hess", driver.findElement(By.cssSelector("div.divider.text")).getText());
    	
    	selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Justen Kitsune");
    	Assert.assertEquals("Justen Kitsune", driver.findElement(By.cssSelector("div.divider.text")).getText());
    }
    
    
    //@Test
    public void TC_03_VueJS() {
    	
    	driver.get("https://mikerodham.github.io/vue-dropdowns/");
    	selectItemInCustomDropdownList("div.btn-group", "ul.dropdown-menu a", "Second Option");
    	Assert.assertEquals("Second Option", driver.findElement(By.cssSelector("div.btn-group>li")).getText());
    	
    	selectItemInCustomDropdownList("div.btn-group", "ul.dropdown-menu a", "First Option");
    	Assert.assertEquals("First Option", driver.findElement(By.cssSelector("div.btn-group>li")).getText());
    	
    	selectItemInCustomDropdownList("div.btn-group", "ul.dropdown-menu a", "Third Option");
    	Assert.assertEquals("Third Option", driver.findElement(By.cssSelector("div.btn-group>li")).getText());
    	
    }
    
    //@Test
    public void TC_04_Angular_Select() {
    	
    	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
    	sleepInSecond(3);

    	//jsExecutor.executeScript("$(window).scrollTop(500)");
    	//jsExecutor.executeScript("document.querySelector(\"ng-select[bindvalue='provinceCode']\").scrollIntoView(true)");
    	//sleepInSecond(5);
    	
    	selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "span.ng-option-label", "Tỉnh Cao Bằng");
    	//1: Text nó ko có nằm ở bên HTML
    	String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
    	Assert.assertEquals("Tỉnh Cao Bằng", actualText);
    	
    	//2 
    	Assert.assertEquals("Tỉnh Cao Bằng", driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText());
    	
    	//3
    	Assert.assertEquals("Tỉnh Cao Bằng", driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"));
    	
    	
    	selectItemInCustomDropdownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "span.ng-option-label", "Huyện Trùng Khánh");
    	
    	
//    	jsExecutor.executeScript("$(window).scrollTop(500)");
//    	jsExecutor.executeScript("document.querySelector(\"ng-select[bindvalue='wardCode']\").scrollIntoView(true)");
//    	sleepInSecond(5);
    	
    	selectItemInCustomDropdownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "span.ng-option-label", "Xã Quang Vinh");
    	
    }
    
    
    @Test
    public void TC_05_Angular_Enter() {
    	
    	driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
    	sleepInSecond(3);
    	
    	enterToCustomDropdownList("ng-select[bindvalue='provinceCode'] input[role='combobox']", "span.ng-option-label", "Tỉnh Cao Bằng");
    	
    	enterToCustomDropdownList("ng-select[bindvalue='districtCode'] input[role='combobox']", "span.ng-option-label", "Huyện Trùng Khánh");
    	
    	enterToCustomDropdownList("ng-select[bindvalue='wardCode'] input[role='combobox']", "span.ng-option-label", "Xã Quang Vinh");
    	
    }
    
    public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
    	
    	//Step 1 : Click 1 element cho nó xổ hết ra các item
    	driver.findElement(By.cssSelector(parentLocator)).click();
    	sleepInSecond(2);
    	
    	//Step 2: Chờ cho các item load ra hết thành công 
    	//Lưu ý 1: Locator chứa hết tất cả các item
    	//Lưu ý 2: Locator đến node cuối cùng chứa text
    	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
    	
    	//Step 3: Tìm item cần chọn
    	
    	
    	// Lấy hết tất cả các element (item) ra sau đó duyệt từng item 
    	List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
    	
    	//Duyệt qua từng item getText của item ra
    	for (WebElement item : allItems) {
    		String actualText = item.getText();
    		System.out.println("Actual Text = " + actualText);
    		
    		//Nếu text = text mong muốn (item cần click vào)
    		if (actualText.equals(expectedTextItem)) {
    			// +B1: Nếu các item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới element tìm tiếp
    			// +B2: Nếu các item cần chọn nằm ở dưới thì scroll tới element
    			
    			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
    			sleepInSecond(2);
    			
    			//Step4 : Click vào item đó
    			item.click();
    			sleepInSecond(2);
    			
    			//Thoát khỏi vòng lặp, ko có kiểm tra element tiếp theo nữa
    			break;
    		}
    	}
    	
    }
    
    
    public void enterToCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
    	
    	//Step 1 : Phải lấy đến thẻ input (textbox) để sendkey vào
    	driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
    	sleepInSecond(2);
    	
    	//Step 2: Chờ cho các item load ra hết thành công 
    	//Lưu ý 1: Locator chứa hết tất cả các item
    	//Lưu ý 2: Locator đến node cuối cùng chứa text
    	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
    	
    	//Step 3: Tìm item cần chọn
    	
    	
    	// Lấy hết tất cả các element (item) ra sau đó duyệt từng item 
    	List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
    	
    	//Duyệt qua từng item getText của item ra
    	for (WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual Text = " + actualText);
			
			//Nếu text = text mong muốn (item cần click vào)
			if (actualText.equals(expectedTextItem)) {
				// +B1: Nếu các item cần chọn nằm trong vùng nhìn thấy thì ko cần scroll tới element tìm tiếp
		    	// +B2: Nếu các item cần chọn nằm ở dưới thì scroll tới element
				
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				
				//Step4 : Click vào item đó
				item.click();
				sleepInSecond(2);
				
				//Thoát khỏi vòng lặp, ko có kiểm tra element tiếp theo nữa
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
