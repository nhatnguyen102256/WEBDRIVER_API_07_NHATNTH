package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_button_radio_checkbox {
	WebDriver driver;
	JavascriptExecutor javascript;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Handlebutton() {
		driver.get("http://live.guru99.com/");
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		javascript.executeScript("arguments[0].click();", myAccountLink);

		String myAccountURL = driver.getCurrentUrl();
		Assert.assertEquals(myAccountURL, "http://live.guru99.com/index.php/customer/account/login/");

		WebElement createAnAccountBtn = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		javascript.executeScript("arguments[0].click();", createAnAccountBtn);

		String CreateAccountURL = driver.getCurrentUrl();
		Assert.assertEquals(CreateAccountURL, "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Checkbox() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");

		// the label click duoc nhung khong check isselected duoc
		// Phai khai bao 1 the label chi check click, 1 the input check isselected cho 1
		// element
		WebElement dualZoneLabel = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']"));
		WebElement dualZoneCheckbox = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		dualZoneLabel.click();
		Thread.sleep(3000);
		// the input co the kiem tra isselected duoc nhung khong click duoc
		Assert.assertTrue(dualZoneCheckbox.isSelected());
		// dung ca the label va input (2 bien cho 1 element)
		if (dualZoneCheckbox.isSelected()) {
			dualZoneLabel.click();
		}
		Assert.assertFalse(dualZoneCheckbox.isSelected());
	}

	@Test
	public void TC_03() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert alert = driver.switchTo().alert();

		String jsAlertMsg = alert.getText();
		Assert.assertEquals(jsAlertMsg, "I am a JS Alert");
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text() = 'You clicked an alert successfully ']")).isDisplayed());
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
