package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.webkit.ThemeClient;

public class Topic_10_Javascript_Executor {
	
	WebDriver driver;
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
  }
  @Test
  public void TC01() {
	  navigateToUrlByJS("http://live.guru99.com/");
	  String DomainName = executeForBrowser("return document.domain");
	  Assert.assertEquals(DomainName, "live.guru99.com");
	  
	  String URLName = executeForBrowser("return document.URL");
	  Assert.assertEquals(URLName, "http://live.guru99.com/");
	  
	  WebElement mobilelink = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  highlightElement(mobilelink);
	  clickToElementByJS(mobilelink);
	  
	  WebElement SonyExperiaAddtoCart = driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']"));
	  highlightElement(SonyExperiaAddtoCart);
	  clickToElementByJS(SonyExperiaAddtoCart);
	  
	  String GetInnerText = executeForBrowser("return document.documentElement.innerText");
	  Assert.assertTrue(GetInnerText.contains("Sony Xperia was added to your shopping cart."));
	  
	  WebElement PrivacyPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  highlightElement(PrivacyPolicy);
	  clickToElementByJS(PrivacyPolicy);
	  
	  String PrivacyPolicyTitle = executeForBrowser("return document.title");
	  Assert.assertEquals(PrivacyPolicyTitle, "Privacy Policy");
	  
	  scrollToBottomPage();
	  
	  WebElement VerifyScrolltoBottom = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
	  Assert.assertTrue(VerifyScrolltoBottom.isDisplayed());
	  
	  navigateToUrlByJS("http://demo.guru99.com/v4/ ");
	  
	  String DomainNewName = executeForBrowser("return document.domain");
	  Assert.assertEquals(DomainNewName, "demo.guru99.com");
	  
	  
	  
	  
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public void highlightElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.border='6px groove red'", element);
      try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  public String executeForBrowser(String javaSript) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return (String) js.executeScript(javaSript);
  }

  public Object clickToElementByJS(WebElement element) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].click();", element);
  }

  public Object sendkeyToElementByJS(WebElement element, String value) {
         JavascriptExecutor js = (JavascriptExecutor) driver;
         return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
  }

  public Object removeAttributeInDOM(WebElement element, String attribute) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
  }

  public Object scrollToBottomPage() {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
  }

  public Object navigateToUrlByJS(String url) {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.location = '" + url + "'");
  }
}
