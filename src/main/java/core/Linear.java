package core;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.*;




public class Linear {
	
	static WebDriver driver;
	static String url;
	static long start;
	static long finish;
	
	public static void open(String url) {
		Logger.getLogger("").setLevel(Level.OFF);
		driver = new SafariDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Page URL: " + driver.getCurrentUrl());
	}

	
	public static boolean isPresent(By by) {
		
		if (driver.findElements(by).size() > 0) return true; else return false;
	}
	
	public static boolean isVisible(By by) {
		
		if((driver.findElements(by).size() > 0) && driver.findElement(by).isDisplayed()) return true; else return false;
	}
	
	static By e1 = By.id("id_fname");
	static By e2 = By.id("id_lname");
	static By e3 = By.id("id_email");
	static By e4 = By.id("id_phone");
	static By e5 = By.id("id_submit_button");
	
	public static void main(String[] args) {
		
		System.out.println("Browser: Safari");
	
	start = System.currentTimeMillis();
	url = "http://alex.academy/exe/signup/v1/index.php";
	
	open(url);
	
	System.out.println("01. Element [First Name] " + (isPresent(e1) ? "Exists" : "Not exists"));
	System.out.println("01. Element [First Name] " + (isPresent(e2) ? "Exists" : "Not exists"));
	System.out.println("01. Element [First Name] " + (isPresent(e3) ? "Exists" : "Not exists"));
	System.out.println("01. Element [First Name] " + (isPresent(e4) ? "Exists" : "Not exists"));
	System.out.println("01. Element [First Name] " + (isPresent(e5) ? "Exists" : "Not exists"));
	
	driver.quit();
	
	url = "http://alex.academy/exe/signup/v1/confirmation.php";
	open(url);
	
	System.out.println("01. Element [First Name] " + (isVisible(e1) ? "Visible" : "Not visible"));
	System.out.println("01. Element [First Name] " + (isVisible(e2) ? "Visible" : "Not visible"));
	System.out.println("01. Element [First Name] " + (isVisible(e3) ? "Visible" : "Not visible"));
	System.out.println("01. Element [First Name] " + (isVisible(e4) ? "Visible" : "Not visible"));
	
	driver.quit();
	
	finish = System.currentTimeMillis()	;
	
	System.out.println("Response time: " + (finish - start) + " ms");
	}
	
}
