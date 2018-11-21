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

public class Topic_04_Textbox_TextArea {
	WebDriver driver;
	private String newName, newDob, newAddress, newCity, newState, newPinn, newPhone, newEmail, password;
	private String editAddress, editCity, editState, editPinn, editPhone, editEmail, customerID;

	By nameByTextbox = By.xpath("//input[@name='name']");
	By dobByTextbox = By.xpath("//input[@id='dob']");
	By addressByTextarea = By.xpath("//textarea[@name='addr']");
	By cityByTextbox = By.xpath("//input[@name='city']");
	By stateByTextbox = By.xpath("//input[@name='state']");
	By pinnByTextbox = By.xpath("//input[@name='pinno']");
	By phoneByTextbox = By.xpath("//input[@name='telephoneno']");
	By emailByTextbox = By.xpath("//input[@name='emailid']");
	By passwordByTextbox = By.xpath("//input[@name='password']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// khoi tao
		newName = "Nhat Nguyen Hong";
		newDob = "1993-10-02";
		newAddress = "234 Pham Van Dong";
		newCity = "Hanoi";
		newState = "TuLiem";
		newPinn = "242323";
		newPhone = "0372576727";
		newEmail = "testing" + randomEmail() + "@gmail.com";
		password = "12345678";
		editAddress = "233 Pham Van Dong";
		editCity = "Danang";
		editState = "TuLiem2";
		editPinn = "354566";
		editPhone = "0547547445";
		editEmail = "Edittesting" + randomEmail() + "@gmail.com";

	}

	@Test
	public void TC01_NewCustomer() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr161493");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("harErAh");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		driver.findElement(By.xpath("//a[text() ='New Customer']")).click();
		//
		driver.findElement(nameByTextbox).sendKeys(newName);
		driver.findElement(By.xpath("//input[@type='radio']")).click();
		driver.findElement(dobByTextbox).sendKeys(newDob);
		driver.findElement(addressByTextarea).sendKeys(newAddress);
		driver.findElement(cityByTextbox).sendKeys(newCity);
		driver.findElement(stateByTextbox).sendKeys(newState);
		driver.findElement(pinnByTextbox).sendKeys(newPinn);
		driver.findElement(phoneByTextbox).sendKeys(newPhone);
		driver.findElement(emailByTextbox).sendKeys(newEmail);
		driver.findElement(passwordByTextbox).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		// get customerID
		customerID = driver.findElement(By.xpath("//td[text() = 'Customer ID']/following-sibling::td")).getText();
		System.out.println("CustomerID = "+ customerID);

		// Verify output = input

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), newName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), newDob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPinn);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmail);
		// In ra man hinh
		System.out.println(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());

	}

	@Test
	public void TC02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		driver.findElement(addressByTextarea).clear();
		driver.findElement(addressByTextarea).sendKeys(editAddress);
		driver.findElement(cityByTextbox).clear();
		driver.findElement(cityByTextbox).sendKeys(editCity);
		driver.findElement(stateByTextbox).clear();
		driver.findElement(stateByTextbox).sendKeys(editAddress);
		driver.findElement(pinnByTextbox).clear();
		driver.findElement(pinnByTextbox).sendKeys(editPinn);
		driver.findElement(phoneByTextbox).clear();
		driver.findElement(phoneByTextbox).sendKeys(editPhone);
		driver.findElement(emailByTextbox).clear();
		driver.findElement(emailByTextbox).sendKeys(editEmail);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
				
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
