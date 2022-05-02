package webdriver;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_19_Wait_PVII_Fluent {

	//Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    WebDriverWait waitEx;
    FluentWait<WebDriver> fluentDriver;
    long sumTime = 30;
    long pollTime = 1;
    
    FluentWait<WebElement> fluentElement;
    String projectPath = System.getProperty("user.dir");

    
    @BeforeClass
    public void beforeClass() {
//    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();	
    	
    	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    	driver = new FirefoxDriver();
    	
    }
       
    @Test
    public void TC_01() {   	
    	waitEx = new WebDriverWait(driver, 30);
    	driver.get("https://automationfc.github.io/fluent-wait/");
    	
    	WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
    	waitEx.until(ExpectedConditions.visibilityOf(countdown));
    	
    	//Khởi tạo Fluent wait
    	new FluentWait<WebElement>(countdown)
    					// Tổng time wait là 15s
    					.withTimeout(Duration.ofSeconds(15))
    	                // Tần số mỗi 1s check 1 lần
    					.pollingEvery(Duration.ofSeconds(1))
    	                // Nếu gặp exception là find ko thấy element sẽ bỏ qua
    	                .ignoring(NoSuchElementException.class)
    	                // Kiểm tra điều kiện
    	                .until(new Function<WebElement, Boolean>() {
    	                        public Boolean apply(WebElement element) {
    	                                // Kiểm tra điều kiện countdount = 00
    	                                boolean flag = element.getText().endsWith("00");
    	                                System.out.println("Time = " + element.getText());
    	                                // return giá trị cho function apply
    	                                return flag;
    	                        }
    	                });

    }
    
    //@Test
    public void TC_02() {   	
    	
    	driver.get("https://automationfc.github.io/dynamic-loading/");
    	
    	fluentDriver = new FluentWait<WebDriver>(driver);
    	
    	clickToElement(By.cssSelector("div#start>button"));
    	
    	//Sau hki bấm Loading xuất hiện và mất đi sau 5s
    	//Icon loading biến mất , text = Helloworld xuất hiện
    	
    	waitElementVisible(By.cssSelector("div#finish>h4"));
    	
    	isElementDisplayed(By.cssSelector("div#finish>h4"));
    }
    
   
    
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    
    public WebElement findElement(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        		.withTimeout(Duration.ofSeconds(sumTime))
        		.pollingEvery(Duration.ofSeconds(pollTime))
        		.ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                }
        });
        return element;
    }

    public void clickToElement(By locator) {
        WebElement element = findElement(locator);
        element.click();
    }

    public WebElement waitElementVisible(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        		.withTimeout(Duration.ofSeconds(sumTime))
        		.pollingEvery(Duration.ofSeconds(pollTime))
        		.ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementDisplayed(By locator) {
        WebElement element = waitElementVisible(locator);
        FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
        		.withTimeout(Duration.ofSeconds(sumTime))
        		.pollingEvery(Duration.ofSeconds(pollTime))
        		.ignoring(NoSuchElementException.class);

        boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
                public Boolean apply(WebElement element) {
                        boolean flag = element.isDisplayed();
                        return flag;
                }
        });
        return isDisplayed;
    }
}