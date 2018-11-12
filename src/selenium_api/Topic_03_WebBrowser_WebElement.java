package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement {
	WebDriver driver;
	// enabled
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By education = By.xpath("//textarea[@id='edu']");
	By interestsDevelopment = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnabled = By.xpath("//button[@id='button-enabled']");
	// disable
	By Password = By.xpath("//input[@id='password']");
	By ageRadiobutton = By.xpath("//input[@id='radio-disabled']");
	By Biography = By.xpath("//textarea[@id='bio']");
	By JobRole02 = By.xpath("//select[@id='job2']");
	By interestsCheckboxDisabled = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttondisabled = By.xpath("//button[@id='button-disabled']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC01_Components_displayed() {
		if (isControlDisplayed(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation testing");
		}
		if (isControlDisplayed(ageUnder18Radio)) {
			driver.findElement(ageUnder18Radio).click();
		}
		if (isControlDisplayed(education)) {
			driver.findElement(education).sendKeys("Education Automation testing");
		}

	}

	@Test
	public void TC02_Components_Enabled() {
		Assert.assertTrue(isControlEnabled(emailTextbox));
		Assert.assertTrue(isControlEnabled(ageUnder18Radio));
		Assert.assertTrue(isControlEnabled(education));
		Assert.assertTrue(isControlEnabled(interestsDevelopment));
		Assert.assertTrue(isControlEnabled(slider01));
		Assert.assertTrue(isControlEnabled(buttonEnabled));

		Assert.assertFalse(isControlEnabled(Password));
		Assert.assertFalse(isControlEnabled(ageRadiobutton));
		Assert.assertFalse(isControlEnabled(Biography));
		Assert.assertFalse(isControlEnabled(JobRole02));
		Assert.assertFalse(isControlEnabled(interestsCheckboxDisabled));
		Assert.assertFalse(isControlEnabled(slider02));
		Assert.assertFalse(isControlEnabled(buttondisabled));

	}

	@Test
	public void TC03_Components_Selected() {
		// Step 01 - Truy cập vào trang:
		// https://daominhdam.github.io/basic-form/index.html
		// Step 02 - Click chọn Age (Under 18)/ Interests (Development)
		// Step 03 - Kiểm tra các phần tử tại Step 02 đã được chọn
		// Step 04 - Nếu chưa được chọn thì cho phép chọn lại 1 lần nữa
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(interestsDevelopment).click();
		if (isControlSelected(ageUnder18Radio)) {
			System.out.println("Element [" + ageUnder18Radio + "] is selected");
		} 
		else {
			driver.findElement(ageUnder18Radio).click();
			
		}
		
		if (isControlSelected(interestsDevelopment)) {
			System.out.println("Element [" + interestsDevelopment + "] is selected");
		} 
		else {
			driver.findElement(interestsDevelopment).click();
			
		}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isControlDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;

		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}

	}

	public boolean isControlSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}

	}

	public boolean isControlEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not disabled");
			return false;
		}

	}

}
