package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.GetElementDisplayed;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WaitP1 {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By startButton = By.xpath("//div[@id='start']/button");
	By loadingIcon = By.xpath("//div[@id='loading']/img");
	By helloWorldText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 15);

	}

	@Test
	public void TC01_01_ImplicitWait() {

		// step 1: Open app
		// Step 2: Wait Start button display
		// Step 3: Click on Start button
		// Step 4: Loading icon (Visible/invisible)
		// Step 5: Hello World displayed
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		// Sau khi click mat 5s de loading ra text
		Assert.assertTrue(driver.findElement(helloWorldText).isDisplayed());
	}

	@Test
	public void TC02_Invisibe() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(helloWorldText).isDisplayed());

	}
	
	@Test
	public void TC03_CheckHelloWorldText_visible() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloWorldText));
		Assert.assertTrue(driver.findElement(helloWorldText).isDisplayed());
	}
	
	@Test
	public void TC_04_HelloWorldText_LoadingIcon_NolongerInDom() {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//Check helloworld text and Loading icon invisible va ko co trong DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloWorldText));
		System.out.println("End time = " + getDateTimeSecond());
		
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		System.out.println("End time = " + getDateTimeSecond());

		driver.findElement(startButton).click();
		
		//Check helloworld text and Loading icon invisible va co trong DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloWorldText));
		System.out.println("End time = " + getDateTimeSecond());
		
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startButton));
		System.out.println("End time = " + getDateTimeSecond());


	}

	private String getDateTimeSecond() {
		// TODO Auto-generated method stub
		return null;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
