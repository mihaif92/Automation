package helpers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.Times;

public class Minion {

	public static void clickOnId(WebDriver driver, String id, int timeout) {

		WaitHelper.waitById(driver, id, timeout);
		WebElement element = driver.findElement(By.id(id));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", element);
		
	}
	
	public static void clickOnId(WebDriver driver, String id) {
		clickOnId(driver, id, (int)Times.TIMEOUT.getValue());
	}
	
	public static void clickOnClass(WebDriver driver, String clazz, int timeout) {
		
		WaitHelper.waitByClass(driver, clazz, timeout);
		WebElement element = driver.findElement(By.className(clazz));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", element);
	}
	
	public static void clickOnClass(WebDriver driver, String clazz) {
		clickOnClass(driver, clazz,(int)Times.TIMEOUT.getValue());		
	}

	public static void clickOnCssSelector(WebDriver driver, String cssSelector, int timeout) {
		
		WaitHelper.waitByCssSelector(driver, cssSelector, timeout);
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", element);
	}
	
	public static void clickOnCssSelector(WebDriver driver, String cssSelector) {
		clickOnCssSelector(driver, cssSelector,(int)Times.TIMEOUT.getValue());	
	}

	public static void clickOnXpath(WebDriver driver, String xPath, int timeout) {
		
		WaitHelper.waitByXpath(driver, xPath, timeout);
		WebElement element = driver.findElement(By.xpath(xPath));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", element);
	
	}
	
	public static void clickOnXpath(WebDriver driver, String xPath) {
		clickOnXpath(driver, xPath,(int)Times.TIMEOUT.getValue());
	}

	public static void sendKeysToId(WebDriver driver, String id, String msg) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds((int)Times.TIMEOUT.getValue()));

		clickOnId(driver, id);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		element.sendKeys(msg);
	}

	public static void sendKeysToCssSelector(WebDriver driver, String cssSelector, String msg) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds((int)Times.TIMEOUT.getValue()));

		clickOnId(driver, cssSelector);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
		element.sendKeys(msg);
	}

	public static void sendKeysToXpath(WebDriver driver, String xPath, String msg) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds((int)Times.TIMEOUT.getValue()));

		clickOnId(driver, xPath);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
		element.sendKeys(msg);
	}

	public static void sleep(Times time) {
		long msTime = (long) (time.getValue() * 1000);
		try {
			Thread.sleep(msTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * The function gets the parameter as seconds
	 */
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
