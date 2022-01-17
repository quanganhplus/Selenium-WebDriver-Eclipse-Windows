package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css {
    //Khai báo 1 biến đại diện cho Selenium WebDriver
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        // Mở trang tạo account techpanda lên
        driver.get("http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_01() {
        //Selenium locator (By class)

        //ID
        driver.findElement(By.id("firstname")).sendKeys("quang anh");
        sleepInSecond(1);

        //Name
        driver.findElement(By.name("lastname")).sendKeys("trinh");
        sleepInSecond(1);

        //Class - kiểm tra hiển thị
        driver.findElement(By.className("customer-name-middlename")).isDisplayed();
        driver.findElement(By.className("name-middlename")).isDisplayed();

        //TagName - kiểm tra xem có bao nhiêu thẻ input
        int inputNumber = driver.findElements(By.tagName("input")).size();
        System.out.println(inputNumber);

        //LinkText - click chuyển đến link trang SEARCH TERMS
        driver.findElement(By.linkText("SEARCH TERMS")).click();
        sleepInSecond(3);

        //Partial LinkText
        driver.findElement(By.partialLinkText("ADVANCED")).click();
        sleepInSecond(3);

        //Css
        driver.findElement(By.cssSelector("input[id='name']")).sendKeys("iPhone");
        sleepInSecond(1);
        //clear sendKeys
        driver.findElement(By.cssSelector("input[name='name']")).clear();

        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("Fold3");
        sleepInSecond(1);
        driver.findElement(By.cssSelector("input[name='name']")).clear();

        driver.findElement(By.cssSelector("#name")).sendKeys("Xiaomi");
        sleepInSecond(1);

        //Xpath
        driver.findElement(By.xpath("//input[@id='description']")).sendKeys("iPhone 13 Pro Max");
        sleepInSecond(1);
        driver.findElement(By.xpath("//input[@id='description']")).clear();

        driver.findElement(By.xpath("//input[@name='description']")).sendKeys("Apple Watch 7");
        sleepInSecond(1);
        driver.findElement(By.xpath("//input[@name='description']")).clear();

        driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div/input")).sendKeys("AirPod Pro");
        sleepInSecond(1);

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