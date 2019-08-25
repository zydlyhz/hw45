package core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Function {
	
	
	static WebDriver driver;
	static String url;
	static Writer report;
	
	public static void open(String url) {
		Logger.getLogger("").setLevel(Level.OFF);
		driver = new SafariDriver();
		driver.manage().timeouts().implicitlyWait(2,  TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}

static boolean isElementPresent(By by) {
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty()) return true; else return false;
	}
	

static String getSize(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			// return driver.findElement(by).getRect().getDimension()
			return driver.findElement(by).getSize().toString().replace(", ", "x"); else return "null";
	}
	
	static String getLocation(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equals("Safari")) return "0x0";
		
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed()) 
			return driver.findElement(by).getRect().getPoint().toString().replace(", ",	"x");
		else return "null";
	}
	
	static void setValue(By by, String value) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed()) driver.findElement(by).sendKeys(value);;
		
	}
	
	static String getValue(By by) {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed()	&&
		driver.findElement(by).getTagName().equalsIgnoreCase("input")) 
			return driver.findElement(by).getAttribute("value").toString().trim();
		
		else if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed() &&
				driver.findElement(by).getTagName().equalsIgnoreCase("span"))
			return driver.findElement(by).getText().trim();
			else return "null";
	}
	
	static void submit(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			driver.findElement(by).submit();
		
	}
	
	
	
	

	static String getBrowser() {
		
		String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString().trim();
		return browser.replaceFirst(String.valueOf(browser.charAt(0)), String.valueOf(browser.charAt(0)).toUpperCase());
		// return browser.substring(0,1).toUpperCase() + browser.substring(1).toLowerCase();
			}

	static String getOS() {
		return ((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().trim();
			}
	
	static String getFileName() {
		String file = driver.getCurrentUrl().toString().trim();
		return file.substring(file.lastIndexOf('/') + 1);
			}
	
	static void waitTitlePage(String title){
		WebDriverWait wait = new WebDriverWait(driver, 15); 
		wait.until(ExpectedConditions.titleIs(title));
		}
	
	static void writeReportHeader(Writer report) throws IOException {
		report.write("#,Browser,Page,Field,isPresent,Value,Size,Location" + "\n");
		System.out.print("#,Browser,Page,Field,isPresent,Value,Size,Location" + "\n");
		}
	
static void writeReportLine(String index, String fieldName, By by, Writer report) throws IOException {
		
		report.write(
				index + "," + 
				getBrowser() + "," + 
				getFileName() + "," + 
				fieldName + "," + 
				isElementPresent(by) + "," +
				getValue(by) + "," +
				getSize(by) + "," +
				getLocation(by) + "\n");
		
	System.out.print(
				index + "," + 
				getBrowser() + "," + 
				getFileName() + "," + 
				fieldName + "," + 
				isElementPresent(by) + "," +
				getValue(by) + "," +
				getSize(by) + "," +
				getLocation(by) + "\n");
		}
	
	static void quit(){
		driver.quit();
		}
	static By e1 = By.id("id_fname");
	static By e2 = By.id("id_lname");
	static By e3 = By.id("id_email");
	static By e4 = By.id("id_phone");
	static By e5 = By.id("id_submit_button");
	
	
	
	public static void main(String[] args) throws IOException {

		open("http://alex.academy/exe/signup/v1/index.php");
report = new FileWriter ("./report_" + getBrowser().toString().toLowerCase() + ".csv", false);  
		
		// open(url);
		writeReportHeader(report);
		
		// First Name
		setValue(e1, "Jeeka");
		writeReportLine("01", "First Name", e1, report);
		
		// Last Name
				setValue(e2, "Tursunbai");
				writeReportLine("02", "Last Name", e2, report);
				
				// Email
				setValue(e3, "email@test.com");
				writeReportLine("03", "Email", e3, report);
				
				// Phone 
				setValue(e4, "4155555555");
				writeReportLine("04", "Phone", e4, report);
				
				// Submit
				
				submit(e5);
				waitTitlePage("Confirmation");
				
				
				// First Name
				writeReportLine("05", "First Name", e1, report);
				
				// Last Name
				writeReportLine("06", "Last Name", e2, report);
				
				// Email
				writeReportLine("07", "Email", e3, report);
				
				// Phone	
				writeReportLine("08", "Phone", e4, report);
				
		
				report.flush();
				report.close();
				
		quit();
		
		
		
	
		
		
	}
	
}
