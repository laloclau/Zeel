package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UITest {
	
	static WebDriver driver;
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://automationintesting.online/");
		
		enterText(By.id("name"), "Ada Lovelace");
		enterText(By.id("email"), "ada.lovelace@zeel.com");
		enterText(By.id("phone"), "347-555-1212");
		enterText(By.id("subject"), "interview test for Zeel");
		enterText(By.id("description"), "hello Worl, this is simple input!");
		
		clickElement(By.id("submitContact"));

		customWait(3);
		
		String successMessage = driver.findElement(By.xpath("//div[@class='row contact']//h2")).getText();
		
		String secondLine = getText(By.xpath("(//div[@class='row contact']//p)[1]"));
		String thirdLine = getText(By.xpath("(//div[@class='row contact']//p)[2]"));

		String fourthLine = getText(By.xpath("(//div[@class='row contact']//p)[3]"));

		
		if(successMessage.contains("Thanks for getting in touch Ada Lovelace!")) {
			System.out.println("The successful message is displayed");
		}else {
			System.out.println("Issue Found: successful message is NOT display in the UI");
		}
		
		if(secondLine.equals("We'll get back to you about")) {
			System.out.println("The successful message 'We'll get back to you about' is displayed");
		}else {
			System.out.println("Issue Found: wrong message display: "+secondLine);
		}
		
		if(thirdLine.equals("interview test for Zeel")) {
			System.out.println("The successful message 'interview test for Zeel' is displayed");
		}else {
			System.out.println("Issue Found: wrong message display: "+thirdLine);
		}
		
		if(fourthLine.equals("as soon as possible.")) {
			System.out.println("The successful message 'as soon as possible.' is displayed");
		}else {
			System.out.println("Issue Found: wrong message display: "+fourthLine);
		}
		
		
		driver.quit();
	}
	
	
	
	
	public static void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	
	public static void enterText(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}
	
	public static String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public static void customWait(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
