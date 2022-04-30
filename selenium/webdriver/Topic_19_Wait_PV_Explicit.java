package webdriver;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_Wait_PV_Explicit {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String separatorChar = File.separator;
    String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
    
    //Image Name: Verify
    String seleniumImage = "01.jpg";
    String appiumImage = "02.jpg";
    String apiImage = "03.jpg";
    
  //Image location: senkey
    String seleniumImageLocation = uploadFolderLocation + seleniumImage;
    String appiumImageLocation = uploadFolderLocation + appiumImage;
    String apiImageLocation = uploadFolderLocation + apiImage;
    
    //Wait rõ ràng: vs các điều kiện / status cụ thể
    WebDriverWait explicitWait;

    By loadingIcon = By.cssSelector("div#loading");
    By helloworldText = By.cssSelector("div#finish>h4");
    
    @BeforeClass
    public void beforeClass() {
//		//Both : Windows + MAC
    	if (osName.toUpperCase().startsWith("MAC")) {
    		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
    		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver_mac");    		
    	} else {
    		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe"); 		
    	}
    	
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
    
    //@Test
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
    

    @Test
    public void TC_05_Upload_Files() { 
    	explicitWait = new WebDriverWait(driver, 60);
    	
    	driver.get("https://gofile.io/uploadFiles");
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-sm-auto>button.uploadButton")));
    	
    	String homePageWindowID = driver.getWindowHandle();
    	System.out.println("Tab A: " + driver.getCurrentUrl());
    	
    	
    	By uploadFileBy = By.cssSelector("input[type='file']");
    	
    	//Load file + Uploading
    	driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation + "\n" + appiumImageLocation + "\n" + apiImageLocation);
    	
    	//Wait cho file đc upload thành công trong vòng 60s
    	explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
    	
    	//Wait cho text đc visible
    	WebElement upaloadedText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
    	Assert.assertTrue(upaloadedText.isDisplayed());
    	
    	//Click vao Download link
    	driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();
    	
    	//Switch qua new Tab của Download link
    	switchToWindowByID(homePageWindowID);  	
    	
    	WebElement buttonDownload_1 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='01.jpg']//parent::a//parent::div//following-sibling::div//span[text()='Download']")));
    	Assert.assertTrue(buttonDownload_1.isDisplayed());
    	
    	WebElement buttonDownload_2 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='02.jpg']//parent::a//parent::div//following-sibling::div//span[text()='Download']")));
    	Assert.assertTrue(buttonDownload_2.isDisplayed());
    	
    	WebElement buttonDownload_3 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='03.jpg']//parent::a//parent::div//following-sibling::div//span[text()='Download']")));
    	Assert.assertTrue(buttonDownload_3.isDisplayed());
    	
    	//Sau khi Switch qua
    	System.out.println("Tab B: " + driver.getCurrentUrl());   	
    }
    
    @AfterClass
    public void afterClass() {
        driver.quit();
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
}