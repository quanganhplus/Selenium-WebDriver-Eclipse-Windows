package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


	public class Topic_08_Exerciese_Textbox_TextArea_II {

		//Khai báo 1 biến đại diện cho Selenium WebDriver
	    WebDriver driver;
	    String projectPath = System.getProperty("user.dir");
	    
	    String employeeID;
	    String userName = "Admin";
	    String password = "admin123";
	    
	    //input New Employee
	    String firstName = "quang anh";
	    String lastName = "trinh";
	   		    
	    //input New Edit Employee
	    String newFirstName = "thuy linh";
	    String newLastName = "bui";
	    
	    //Locator at Personal Details
	    By EmpFirstName = By.xpath("//input[@id='personal_txtEmpFirstName']");
	    By EmpLastName = By.xpath("//input[@id='personal_txtEmpLastName']");
	    By EmployeeId = By.xpath("//input[@id='personal_txtEmployeeId']");
	    
	    
	    																		
    @BeforeClass
    public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

        //Set thời gian chờ để tìm đc element
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
    	driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
    	sleepInSecond(3);
    	}
    
    @Test
	public void TC_01_New_Employee() {
    	
    	driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
    	driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
    	driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
    	employeeID = driver.findElement(By.xpath("//input[@id='employeeId']")).getAttribute("value");
    	System.out.println("employeeID là :" + employeeID);
    	driver.findElement(By.xpath("//input[@id='btnSave']")).click();
    	
    	//Verify input at Add Employee = output at Personal Detail
    	Assert.assertEquals(firstName , driver.findElement(EmpFirstName).getAttribute("value"));
    	Assert.assertEquals(lastName , driver.findElement(EmpLastName).getAttribute("value"));
    	Assert.assertEquals(employeeID , driver.findElement(EmployeeId).getAttribute("value"));
    	
    	//Verify các textbox: FistName/LastName/EmployeeID bị disable
    	Assert.assertFalse(driver.findElement(EmpFirstName).isEnabled());
    	Assert.assertFalse(driver.findElement(EmpLastName).isEnabled());
    	Assert.assertFalse(driver.findElement(EmployeeId).isEnabled());
    	
    	driver.findElement(By.xpath("//input[@id='btnSave']")).click();
    }
    	
    @Test
    public void TC_02_Edit_Employee() {
    	
    	//clear và nhập giá trị FullName , LastName sau khi click Edit
    	driver.findElement(EmpFirstName).clear();
    	driver.findElement(EmpFirstName).sendKeys(newFirstName);
    	driver.findElement(EmpLastName).clear();
    	driver.findElement(EmpLastName).sendKeys(newLastName);
    	
    	//Verify các textbox: FistName/LastName enabled
    	Assert.assertTrue(driver.findElement(EmpFirstName).isEnabled());
    	Assert.assertTrue(driver.findElement(EmpLastName).isEnabled());
    	
    	driver.findElement(By.xpath("//input[@id='btnSave']")).click();
    	
    	//Verify FisrtName/LastName update thành công
    	Assert.assertEquals(newFirstName , driver.findElement(EmpFirstName).getAttribute("value"));
    	Assert.assertEquals(newLastName , driver.findElement(EmpLastName).getAttribute("value"));
    	
    	//Verify các textbox: FistName/LastName/EmployeeID bị disable
    	Assert.assertFalse(driver.findElement(EmpFirstName).isEnabled());
    	Assert.assertFalse(driver.findElement(EmpLastName).isEnabled());
    	Assert.assertFalse(driver.findElement(EmployeeId).isEnabled());
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
