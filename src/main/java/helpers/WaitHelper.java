package helpers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	private static WebElement element;

	public static void waitById(WebDriver driver, String id, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		element = wait.until(ExpectedConditions.visibilityOf(element));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));

	}

	public static void waitByXpath(WebDriver driver, String xPath, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		element = wait.until(ExpectedConditions.visibilityOf(element));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
	}

	public static void waitByCssSelector(WebDriver driver, String cssSelector, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
		element = wait.until(ExpectedConditions.visibilityOf(element));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
	}

	public static void waitByClass(WebDriver driver, String className, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
		element = wait.until(ExpectedConditions.visibilityOf(element));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
	}

}
