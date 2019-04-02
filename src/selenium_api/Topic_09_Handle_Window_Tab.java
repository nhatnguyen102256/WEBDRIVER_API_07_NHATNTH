package selenium_api;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Window_Tab {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC01() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		//get ra cua so hoac tab parrent dang active
		String parrentwindowid = driver.getWindowHandle();
		System.out.println(parrentwindowid);
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		switchToChildWindow(parrentwindowid);
		Assert.assertEquals(driver.getTitle(), "Google");
		closeAllWithoutParentWindows(parrentwindowid);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	//handle khi co 2 cua so 
	public void switchToChildWindow(String parent) {
		//get ra tat ca cac tab hoac cua so dang co -->ID duy nhat. Neu dung List thi no se lay ca ID trung
        Set<String> allWindows = driver.getWindowHandles();
        //for each
        for (String ChildWindow : allWindows) {
        	//duyet qua trung
                    if (!ChildWindow.equals(parent)) {
                                driver.switchTo().window(ChildWindow);
                                break;
                    }
        }
        
        
}
	public void switchToChildWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
                    driver.switchTo().window(runWindows);
                    String currentWin = driver.getTitle();
                    if (currentWin.equals(title)) {
                                break;
                    }
        }
}
	public boolean closeAllWithoutParentWindows(String parentID) {
		//Get t
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
                    if (!runWindows.equals(parentID)) {
                                driver.switchTo().window(runWindows);
                                driver.close();
                    }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
                   return true;
        else
                   return false;
}

}
