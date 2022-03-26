package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


	public class Topic_08_Exerciese_Textbox_TextArea_II {

		//Khai báo 1 biến đại diện cho Selenium WebDriver
	    WebDriver driver;
	    String projectPath = System.getProperty("user.dir");
	    
	    String customerID;
	    String userID = "mngr394649";
	    String password = "gehYdaj";
	    
	    //input New Customer / Output data
	    String customerName = "quang anh";
	    String gender = "male";
	    String dateOfBirth = "03252022";
	    String address = "121 Nguyen Dinh Chieu";
	    String city = "Ha Noi";
	    String state = "hbt";
	    String pin = "123456";
	    String phone = "0977825562";
	    String email = "quanganh" + getRandomNumber() + "@gmail.com";
	    
	    
	    //Locator for New Customer / Edit Customer form
	    By nameTextbox = By.name("name");
	    By genderRadio = By.xpath("//input[@value=\"m\"]");
	    By genderTextbox = By.name("gender");
	 	By dobTextbox = By.name("dob");
	    By addressTextArea = By.name("addr");
	    By cityTextbox = By.name("city");
	    By sateTextbox = By.name("state");
	    By pinTextbox = By.name("pinno");
	    By phoneTextbox = By.name("telephoneno");
	    By emailTextbox = By.name("emailid");
	    By passwordTextbox = By.name("password");
	    
	    																		
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        
        driver.get("https://demo.guru99.com/v4/");
        
    	
    	}
    
    @Test
	public void TC_01_New_Customer() {
    	
    	
    	
    	
	}
    
    
    @Test
    public void TC_02_Edit_Customer() {
    	
    	
    	
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
}
