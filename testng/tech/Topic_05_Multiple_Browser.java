package tech;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_Multiple_Browser {
	WebDriver driver;
    By emailTextbox = By.xpath("//*[@id='email']");
    By passwordTextbox = By.xpath("//*[@id='pass']");
    By loginButton = By.xpath("//*[@id='send2']");
    String projectPath = System.getProperty("user.dir");
    
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
    	switch (browserName) {
    	case "chrome":
    		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
    		driver = new ChromeDriver();
    		break;
    	case "firefox":
    		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    		driver = new FirefoxDriver();
    		break;
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
	    	driver = new EdgeDriver();
			break;

		default:
			System.out.println("Browser name is not valid.");
			break;
		}
    	
    	
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Login() {
            driver.get("http://live.techpanda.org/index.php/customer/account/login/");

            driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
            driver.findElement(passwordTextbox).sendKeys("111111");
            driver.findElement(loginButton).click();
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
            driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
            driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @DataProvider(name = "user_pass")
    public Object[][] UserAndPasswordData() {
            return new Object[][] { 
                    { "selenium_11_01@gmail.com", "111111" }, 
                    { "selenium_11_02@gmail.com", "111111" }, 
                    { "selenium_11_03@gmail.com", "111111" } };
    }

    @AfterClass
    public void afterClass() {
            driver.quit();
    }

}
