package core;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.*;



public class Function {

	static WebDriver driver;
	static String url;
	static long start;
	static long finish;
	
	public static void open(String url) {
		Logger.getLogger("").setLevel(Level.OFF);
		driver = new SafariDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(url);
		
		
	}
	
	public static boolean isPresent(By by) {
		if (driver.findElements(by).size() > 0) return true; else return false;
	}
	
	public static boolean isVisible(By by) {
		if ((driver.findElements(by).size() > 0) && driver.findElement(by).isDisplayed()) return true; else return false;
	}
	
	static By e1 = By.id("id_quotes");
	static By e2 = By.id("id_current_location");
	static By e3 = By.id("id_weather_icon");
	static By e4 = By.id("id_temperature");
	static By e5 = By.id("id_f_title");
	
	
	public static void pageSignUpValidation(String url) {
		open(url);
	
		System.out.println("Page URL: " + driver.getCurrentUrl());
		System.out.println("01.Element[Quotes]: " + (isPresent(e1) ? "Exists" : "Not exist"));  
		System.out.println("02.Element[Location]: " + (isPresent(e2) ? "Exists" : "Not exist"));  
		System.out.println("03.Element[Weather Icon]: " + (isPresent(e3) ? "Exists" : "Not exist"));  
		System.out.println("04.Element[Temperature]: " + (isPresent(e4) ? "Exists" : "Not exist"));  
		System.out.println("05.Element[Title]: " + (isPresent(e5) ? "Exists" : "Not exist"));  
		driver.findElement(By.id("id_fname")).sendKeys("Jeeka");
		driver.findElement(By.id("id_lname")).sendKeys("Tursunbai");
		driver.findElement(By.id("id_email")).sendKeys("email@email.com");
		driver.findElement(By.id("id_phone")).sendKeys("415-555-5556");
		driver.findElement(By.id("id_submit_button")).click();
		
	}
	
	static By e6 = By.id("id_f_label_fn");
	static By e7 = By.id("id_fname");
	static By e8 = By.id("id_f_label_ln");
	static By e9 = By.id("id_lname");
	static By e10 = By.id("id_f_label_ea");
	
	public static void pageConfirmationValidation() {
		// open(url);
		// driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);
		
		System.out.println("Page URL: " + driver.getCurrentUrl());
		
		System.out.println("06.Element[Fname Label]: " + (isPresent(e6) ? "Exists" : "Not exist"));  
		System.out.println("07.Element[First Name]: " + (isPresent(e7) ? "Exists" : "Not exist"));  
		System.out.println("08.Element[Lname Label]: " + (isPresent(e8) ? "Exists" : "Not exist"));  
		System.out.println("09.Element[Last Name]: " + (isPresent(e9) ? "Exists" : "Not exist")); 
		System.out.println("10.Element[Email label]: " + (isPresent(e10) ? "Exists" : "Not exists"));  //signup & confirm
	}
	
	public static void main(String[] args)	{
		System.out.println("Browser: Safari");
		start = System.currentTimeMillis();
		pageSignUpValidation("http://alex.academy/exe/signup/v1/index.php");
		pageConfirmationValidation();
		
		driver.quit();
		finish = System.currentTimeMillis();
		System.out.println("Response time: " + (finish - start) + " ms");
	}
	
}
