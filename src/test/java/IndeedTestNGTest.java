import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.openqa.selenium.remote.DesiredCapabilities;

@SuppressWarnings("unused")
public class IndeedTestNGTest {
	
	public String baseUrl;
    public WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		// Change as per your geckodriver download location
		System.setProperty("webdriver.gecko.driver","C:/Users/Dolly S Nyirenda/Desktop/Selenium/Software/Step 4/geckodriver-v0.13.0-win64/geckodriver.exe"); 
		// Create Firefox Driver with Marionette capabilities 
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
    	//WebDriver driver = new FirefoxDriver(capabilities);
		driver = new FirefoxDriver(capabilities);
		// Set implicit wait for 30 seconds 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		//public WebDriver driver;
		baseUrl = "http://www.indeed.co.uk";
	}
	
	 @DataProvider
	  public Object[][] getIndeedJobSearchParams() 
	  {
	    return new Object[][] 
	    		{
			      new Object[] { "Selenium", "London" },
			      //new Object[] { "Cucumber", "Glasgow" },
			      //new Object[] { "Testing", "Manchester" },
	    		};
	  }
	
	@Test(dataProvider = "getIndeedJobSearchParams")
	public void testIndeedJobSearch(String keyword, String location)
	{
//		if(true)
//		{
//			throw new SkipException("Test is skipped in Excel file");
//		}
		driver.get(baseUrl);
		driver.findElement(By.id("what")).sendKeys(keyword);
		driver.findElement(By.id("where")).clear();
		driver.findElement(By.id("where")).sendKeys(location);
		driver.findElement(By.id("fj")).click();
		
		String expectedTitle = "Job Search | Indeed";
		String actualTitle=driver.getTitle();
		
		//assertEquals("ERROR: Job search title is wrong", expectedTitle, actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "ERROR: Job search title is wrong");
	}
	
	@Test
	public void testIndeedSiteHomePageLogo()
	{
		driver.get(baseUrl);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Indeed job search']")).isDisplayed(), 
				"ERROR: Home page logo is not displayed");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
