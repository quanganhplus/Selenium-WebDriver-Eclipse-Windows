package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


	public class Topic_08_Exerciese_Textbox_TextArea_I {

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
	    
	    //input edit Customer
	    String editAddress = "17 duy tân";
	    String editCity = "ho chi minh";
	    String editState = "quan tan binh";
	    String editPin = "888888";
	    String editPhone = "02439740088";
	    String editEmail = "quanganh" + getRandomNumber() + "@hotmail.com";
	    
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
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
    	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    	driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
    	
    	String homePageWelcomeMsg = driver.findElement(By.xpath("//marquee[contains(text(),'Welcome To Manager')]")).getText();
    	Assert.assertEquals(homePageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
    	
    	//Verify màn hình hiển thị userID thành công
    	Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : "+ userID +"']")).isDisplayed());
    	
    	}
    
    @Test
	public void TC_01_New_Customer() {
    	
    	
    	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
    	
    	// input data New Customer form
    	driver.findElement(nameTextbox).sendKeys(customerName);
    	driver.findElement(genderRadio).click();
        driver.findElement(dobTextbox).sendKeys(dateOfBirth);
        driver.findElement(addressTextArea).sendKeys(address);
        driver.findElement(cityTextbox).sendKeys(city);
        driver.findElement(sateTextbox).sendKeys(state);
        driver.findElement(pinTextbox).sendKeys(pin);
        driver.findElement(phoneTextbox).sendKeys(phone);
        driver.findElement(emailTextbox).sendKeys(email);
        driver.findElement(passwordTextbox).sendKeys(password);
        sleepInSecond(3);
        
        driver.findElement(By.xpath("//input[@name='sub']")).click();
        sleepInSecond(3);
    	
        //Verify màn hình hiển thị New Customer form thành công
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
    	
    	//Verify output data = input data
    	customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
    	System.out.println("Customer ID at New Customer form = " + customerID);
    	
    	Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText());
    	Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText());
    	//Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText());
    	Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText());
    	Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText());
    	Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText());
    	Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText());
    	Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText());
    	Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText());
    	
	}
    
    
    @Test
    public void TC_02_Edit_Customer() {
    	
    	driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
    	driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
    	driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
    	
    	//Verify output at Edit Customer form = input at New Customer form
    	Assert.assertEquals(customerName, driver.findElement(nameTextbox).getAttribute("value"));
    	Assert.assertEquals(address, driver.findElement(addressTextArea).getText());
    	
    	driver.findElement(addressTextArea).clear();
    	driver.findElement(addressTextArea).sendKeys(editAddress);
    	driver.findElement(cityTextbox).clear();
    	driver.findElement(cityTextbox).sendKeys(editCity);
    	driver.findElement(sateTextbox).clear();
    	driver.findElement(sateTextbox).sendKeys(editState);
    	driver.findElement(pinTextbox).clear();
    	driver.findElement(pinTextbox).sendKeys(editPin);
    	driver.findElement(phoneTextbox).clear();
    	driver.findElement(phoneTextbox).sendKeys(editPhone);
    	driver.findElement(emailTextbox).clear();
    	driver.findElement(emailTextbox).sendKeys(editEmail);
    	driver.findElement(By.xpath("//input[@name='sub']")).click();
    	sleepInSecond(3);
    	
    	//Verify màn hình hiển thị Edit Customer form thành công
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
    	
    	
    	//Verify output data = input data
    	Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText());
    	Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText());
    	Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText());
    	//Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText());
    	Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText());
    	Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText());
    	Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText());
    	Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText());
    	Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText());
    	Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText());
    	
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
