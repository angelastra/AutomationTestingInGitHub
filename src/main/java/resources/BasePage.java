package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	String URL;
	
	public WebDriver initializeDriver() throws IOException 
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\initialData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		//String browser = System.getProperty("browser"); //Using maven command -Dbrowser=chrome
		String browser = prop.getProperty("browser");
		URL = prop.getProperty("url");
		if(browser.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless")) {
				options.addArguments("--headless");
			}
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
			driver = new ChromeDriver(options);
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
			
		else if(browser.equals("ie")) 
		{
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
	
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public void clickElement(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
		//driver.findElement(locator).click();
	}
	
	public void writeOnElement(By locator, String text) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(text);
		//driver.findElement(locator).sendKeys(text);
	}
}
