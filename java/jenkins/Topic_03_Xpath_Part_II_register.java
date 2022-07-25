package jenkins;

import java.util.concurrent.TimeUnit;  //phim tat xoa ctrl +D

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
//bai tap link
//https://docs.google.com/document/d/1UaYIlYZMJib3ThkE2KaEhn9J2saOc5kSIhRYrGyNZwo/edit#heading=h.fzmzozwecou3

public class Topic_03_Xpath_Part_II_register {
	WebDriver driver;
	//String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("");
	}

	@Test
	public void Register_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//click vào đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//kiểm tra lỗi hiển thị ở các field bắt buộc
		//driver.findElement(By.id("txtFirstname-error")).getText();
		//kiểm tra 1 điều kiện trả về là bằng với điều kiện mình mông muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("123@456@345"); //email sai
		driver.findElement(By.id("txtCEmail")).sendKeys("123@456@345");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0932523859");
		
		
		//click vào đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//kiểm tra 1 điều kiện trả về là bằng với điều kiện mình mông muốn
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

		
	
	}

	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("jonhwick@gmail.com"); 
		driver.findElement(By.id("txtCEmail")).sendKeys("jonhwick@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0932523859");
		
		
		//click vào đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//kiểm tra 1 điều kiện trả về là bằng với điều kiện mình mông muốn
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	
	}
	@Test
	public void Register_04_Password_Less_Than_6_Chars() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("jonhwick@gmail.com"); 
		driver.findElement(By.id("txtCEmail")).sendKeys("jonhwick@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0932523859");
		
		
		//click vào đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//kiểm tra 1 điều kiện trả về là bằng với điều kiện mình mông muốn
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	
	}
	@Test
	public void Register_05_Incorrect_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("jonhwick@gmail.com"); 
		driver.findElement(By.id("txtCEmail")).sendKeys("jonhwick@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456"); //hai mat khau khac nhau
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0932523859");
		
		
		//click vào đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//kiểm tra 1 điều kiện trả về là bằng với điều kiện mình mông muốn
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	
	}
	@Test
	public void Register_06_Incorrect_Phone() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("jonhwick@gmail.com"); 
		driver.findElement(By.id("txtCEmail")).sendKeys("jonhwick@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456"); 
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("09325238");//phone nho hon 10 so
		
		
		//click vào đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//kiểm tra 1 điều kiện trả về là bằng với điều kiện mình mông muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}