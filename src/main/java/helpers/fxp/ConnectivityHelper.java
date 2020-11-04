package helpers.fxp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.Times;
import helpers.Logger;
import helpers.Minion;

public class ConnectivityHelper {

	public static void signIn(WebDriver driver, String userName, String password) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000 * (long)Times.TIMEOUT.getValue()));

//		Click on sign-in button
		Minion.clickOnXpath(driver, "//a[@href='loginpage.php\']");

//		User name and password 
		Minion.sendKeysToId(driver, "navbar_username", userName + Keys.TAB + password);

//		Click on login button
		Minion.clickOnXpath(driver, "//input[@name='loginbtn']");
		
//		WaitHelper.wait(driver, id, timeout);
//		WebElement element = driver.findElement(By.id(id));

		WebElement userNameLogedin = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text()," + userName + ")]")));

		if (userNameLogedin.isDisplayed()) {
			Logger.log("  Loged-in Successfuly");
		} else {
			throw new RuntimeException("Login FAILED");
		}

	}

	public static void signOut(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000 * (long)Times.TIMEOUT.getValue()));

//		Click on settings button
		Minion.clickOnXpath(driver, "//a[@id='yui-gen3']");

//		Click on logout button
		Minion.clickOnXpath(driver, "//a[contains(@href,'login.php?do=logout&logouthash=')]");

//		Click on home page button at the nav-bar
		Minion.clickOnId(driver, "fxp_logo_img");

		WebElement signInBtn = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='loginpage.php\']")));

		if (signInBtn.isDisplayed()) {
			Logger.log("  Loged-out Successfuly");
		} else {
			throw new RuntimeException("Logout FAILED");
		}
	}

}
