package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css {
	// Khai bao bien cho selenium WebDriver

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		// Khoi tao Firefox Browser
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_VerifyURLandtitle() {
		String homepagetitle = driver.getTitle();
		Assert.assertEquals(homepagetitle, "Home page");
		// Hoac dung Assert.assertTrue(homepagetitle.equals("Home page"));
		// hoac Assert.assertTrue(homepagetitle.contains("Home"));
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.navigate().back();
		String loginURL = driver.getCurrentUrl();
		Assert.assertEquals(loginURL, "http://live.guru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		String CreateAccountURL = driver.getCurrentUrl();
		Assert.assertEquals(CreateAccountURL, "http://live.guru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Login_Empty() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		String ErrorMessageEmail = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(ErrorMessageEmail, "This is a required field.");
		String ErrorMessagePassword = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(ErrorMessagePassword, "This is a required field.");
	}

	@Test
	public void TC_03_Login_with_Email_invalid() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("send2")).click();
		String ErrorMessageEmailForm = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(ErrorMessageEmailForm,
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_04_Login_with_Password_Lessthan_6_characters() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		String ErrorMessagePassLess6 = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(ErrorMessagePassLess6,
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_Login_with_Password_incorrect() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		String ErrorMessageEmailorPass = driver.findElement(By.xpath("//span[text()=\"Invalid login or password.\"]"))
				.getText();
		Assert.assertTrue(ErrorMessageEmailorPass.equals("Invalid login or password."));
	}

	@Test
	public void TC_06_Create_an_account() {
		String firstname= "Nhat", lastname = "Nguyen", email_address = "testing" + randomEmail()+ "@gmail.com", password = "123456a@";
				
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);	
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email_address);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title=\"Register\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span [text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title=\"Log Out\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("Random number = " + number);
		return number;
	}
}
