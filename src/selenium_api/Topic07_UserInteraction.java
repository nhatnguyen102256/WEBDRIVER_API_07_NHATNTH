package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_UserInteraction {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_MoveMouse() {
		driver.get("http://www.myntra.com/");
		WebElement avatarIcon = driver
				.findElement(By.xpath("//div[@class='desktop-user']/div[@class='desktop-userIconsContainer']"));
		action.moveToElement(avatarIcon).perform();
		driver.findElement(By.xpath("//a[text()='log in']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}

	// @Test
	public void TC_02_ClickandHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numberIcon = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		// 12 elements: element dau tien la 0: 0 1 2 3 4 5 6...11
		// Bam va giu chuot tu element 0 va move den element 3, sau do nha chuot ra
		action.clickAndHold(numberIcon.get(0)).moveToElement(numberIcon.get(3)).release().perform();
		// kiem tra 4 phan tu duoc chon
		List<WebElement> numberselected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Phan tu duoc chon la " + numberselected.size());
		Assert.assertEquals(4, numberselected.size());

		// Refresh the page
		driver.navigate().refresh();

		// chon tung element khong theo hang
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		elements.get(5).click();
		elements.get(7).click();
		elements.get(8).click();
		elements.get(10).click();
		action.keyUp(Keys.CONTROL).perform();
		List<WebElement> numberselectedrandom = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(4, numberselectedrandom.size());
	}

	// @Test
	public void TC_03_doubleclick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doublebutton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doublebutton).perform();

		Alert doubleAlert = driver.switchTo().alert();
		Assert.assertEquals(doubleAlert.getText(), "The Button was double-clicked.");
		doubleAlert.accept();

	}

	//@Test
	public void TC_04_Rightclick_context() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightclickbutton = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightclickbutton).perform();

		// hover mouse
		WebElement quitVisible = driver.findElement(By
				.xpath("//li[contains(@class, 'context-menu-icon-quit')]"));
		action.moveToElement(quitVisible).perform();
		
		//check quit hovered
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') and contains(@class,'context-menu-visible')]")).isDisplayed());
		quitVisible.click();
		Alert quitalert = driver.switchTo().alert();
		quitalert.accept();
	}
	
	@Test
	public void TC_05_Drag_and_drop() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targerElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(sourceElement, targerElement).build().perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
