package jenkins;

import java.util.Random;
import java.util.concurrent.TimeUnit;  
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//bai tap link
//https://docs.google.com/document/d/1UaYIlYZMJib3ThkE2KaEhn9J2saOc5kSIhRYrGyNZwo/edit#heading=h.fzmzozwecou3

import io.github.bonigarcia.wdm.WebDriverManager;

public class Topic_03_Xpath_Part_III_login {
	WebDriver driver;
	//String projectPath = System.getProperty("user.dir");
    String firstName, lastName,fullName, emailAddress, password;
	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Automationfc1";
		lastName = "FC1";
		fullName = firstName+ " " +lastName;
		emailAddress = "afc1"+getRandomNumber()+"@gmail.com";
		password = "12345678";
	}

	@Test
	public void Login_01_Empty_Data() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//phải sendkey trống vì lỡ 1 số site nó sẽ để mặc định dữ liệu, 
		//tốt hơn là nên clear()
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		
		//click login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
		
	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@456.7895");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		//click login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	@Test
	public void Login_03_Password_Less_Than_6_Chars_Invalid_Email() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		//click login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	@Test
	public void Login_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationfc1@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345678");
		//click login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		
	}
	@Test
	public void Login_05_Create_New_Account() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//nhap du lieu
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		//driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(""); //cái này ko yêu cầu nên bỏ qua
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
		//click register
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		//verify tuyệt đối
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");
		
		//verify tương đối
		String ContactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(ContactInformationText.contains(fullName));
		Assert.assertTrue(ContactInformationText.contains(emailAddress));
		
		//logout ra
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	@Test
	public void Login_06_Valid_Email_Password() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		//click login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		//verify tương đối
		String ContactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(ContactInformationText.contains(fullName));
		Assert.assertTrue(ContactInformationText.contains(emailAddress));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}