package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Handle_Dropdownlist {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor JavaExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		JavaExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


	}

	@Test
	public void TC01_Handle_HTML_Dropdownlist() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(3000);

		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Thread.sleep(3000);

		// Kiểm tra dropdownlist có bn options
		Assert.assertEquals(select.getOptions().size(), 5);
	}

	@Test
	public void TC_02_Jquery_Dropdown() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(3000);

		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

		selectItemInCustomDropdown("//span[@aria-owns ='color_listbox']", "//ul[@id='color_listbox']//li", "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns ='color_listbox']//span[@class='k-input' and text() ='Orange']")).isDisplayed());
		Thread.sleep(3000);

	}

	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItem) {
		// Click vao Dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		JavaExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		// Cho tat ca phan tu vao list
		List<WebElement> childlist = driver.findElements(By.xpath(childXpath));

		// Wait de tat ca cac phan tu trong list hien thi
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childlist));

		// Tao vong lap for de kiem tra list cac phan tu sau do get text
		/*
		 * Cach 1: for(int i = 0 ; i <childlist.size(); i ++) { }
		 */

		for (WebElement CheckChild : childlist) {
			String TextItem = CheckChild.getText();
			System.out.println("Text in dropdown =" + TextItem);
			if (TextItem.equals(expectedItem)) {
				JavaExecutor.executeScript("arguments[0].scrollIntoView(true);", CheckChild);
				CheckChild.click();

			}
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
