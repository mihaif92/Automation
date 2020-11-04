package tests.fxp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.Logger;
import helpers.Minion;
import tests.base.FxpTestBase;

public class UserProfileFillTest extends FxpTestBase {

	public void userProfileFill(WebDriver driver) {

//		Fill user name
		Minion.sendKeysToId(driver, "cfield_2", "Boti");

//		Fill fields of interests
		Minion.sendKeysToId(driver, "cfield_3", "Java and Automation");

// 		Click on submit form button
		Minion.clickOnXpath(driver, "//div[@class='blockfoot actionbuttons settings_form_border']//input[1]");

		Logger.log("  User Profile was updated");
	}

	public void userProfileClear(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cfield_2")));
		userName.clear();

		WebElement FiledsOfIntrest = driver.findElement(By.id("cfield_3"));
		FiledsOfIntrest.clear();

// 		Click on submit form button
		Minion.clickOnXpath(driver, "//div[@class='blockfoot actionbuttons settings_form_border']//input[1]");

		Logger.log("  User Profile was cleared");

	}

	public void userProfileCheck(WebDriver driver, String status) {

		int counterTest = 0;
		int numOfTests = 2;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cfield_2")));
		String userNameInputBox = userName.getAttribute("value");

		WebElement FiledsOfIntrest = driver.findElement(By.id("cfield_3"));
		String FiledsOfIntrestInputBox = FiledsOfIntrest.getAttribute("value");

		if (status.equals("emptyCheck")) {

			if (userNameInputBox.isEmpty()) {
				counterTest++;
			}

			if (FiledsOfIntrestInputBox.isEmpty()) {
				counterTest++;
			}
		} else if (status.equals("fillCheck")) {
			if (userNameInputBox.equals("Boti")) {
				counterTest++;
			}

			if (FiledsOfIntrestInputBox.equals("Java and Automation")) {
				counterTest++;
			}
		}

		if (counterTest == numOfTests) {
			Logger.log("  User profile was updated SUCCESSFULLY");
		} else {
			throw new RuntimeException("User profile FAILED to update");
		}
	}

	public void test() {

//		Click on settings button
		Minion.clickOnXpath(driver, "//img[@class='setopo initial loading']");

//		Click on user profile button
		Minion.clickOnXpath(driver, "//a[@href='profile.php?do=editprofile']");

		userProfileClear(driver);
		userProfileCheck(driver, "emptyCheck");

		userProfileFill(driver);
		userProfileCheck(driver, "fillCheck");

	}

	protected String getDescription() {
		return "Log-in and navigate to user profile, fill data and check the changes and then clean the form and check the changes and then log-out";
	}

}
