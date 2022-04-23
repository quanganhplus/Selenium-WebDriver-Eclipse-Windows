package webdriver;

import java.util.Random;
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


public class Topic_18_Javascript_Executor {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Ép kiểu
		jsExecutor = (JavascriptExecutor) driver;
		
        //Set thời gian chờ để tìm đc element (findElement)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
        emailAddress = "quanganh.plus" + getRandomNumber() + "@gmail.com";
        
    	}
    
    
    @Test
    public void TC_01_live_guru() {  		
    	
    	navigateToUrlByJS("http://live.techpanda.org");
    	sleepInSecond(3);
    	
    	String homePageDomain = (String) executeForBrowser("return document.domain;");
    	Assert.assertEquals(homePageDomain, "live.techpanda.org");
    	
    	String homePageURL = (String) executeForBrowser("return document.URL;");
    	Assert.assertEquals(homePageURL, "http://live.techpanda.org/");
    	
    	clickToElementByJS("//a[text()='Mobile']");
    	sleepInSecond(3);
    	
    	clickToElementByJS("//a[text()='IPhone']//parent::h2//following-sibling::div//button");
    	sleepInSecond(3);
    	
    	String ShoppingCartText = getInnerText();
    	Assert.assertTrue(ShoppingCartText.contains("IPhone was added to your shopping cart."));
    	
    	clickToElementByJS("//a[text()='Customer Service']");
    	sleepInSecond(3);
    	
    	String titlePageCustomer = (String) executeForBrowser("return document.title;");
    	Assert.assertEquals(titlePageCustomer, "Customer Service");
    	
    	scrollToElementOnDown("//input[@id='newsletter']");
    	sleepInSecond(3);
    	
    	sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
    	sleepInSecond(3);
    	
    	clickToElementByJS("//button[@class='button']");
    	sleepInSecond(3);
    	
    	Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
    	
    	navigateToUrlByJS("http://demo.guru99.com/v4/");
    	sleepInSecond(3);
    	
    	Assert.assertTrue(areExpectedTextInInnerText("demo.guru99.com"));
    	sleepInSecond(3);
    }
    
    
    //@Test
    public void TC_02_HTML_Validate_Message() {  	
    	driver.get("https://www.pexels.com/vi-vn/join-contributor/");
    	
    	
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
    
    public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
