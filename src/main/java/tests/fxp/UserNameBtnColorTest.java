package tests.fxp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import enums.Times;
import helpers.Logger;
import helpers.Minion;
import tests.base.FxpTestBase;

public class UserNameBtnColorTest extends FxpTestBase {

	public final String leftBtnHoverColor = "#cddc39";

	public void checkBtnColor(WebDriver driver, WebElement element, String color) throws InterruptedException {

		String hexColor = null;
		String rgbColor = null;

		Actions hover = new Actions(driver);
		hover.moveToElement(element).build().perform();
		Minion.sleep(Times.SHORT);

		rgbColor = element.getCssValue("color");
		hexColor = Color.fromString(rgbColor).asHex();

		if (!hexColor.equals(color)) {
			throw new RuntimeException("Button " + hexColor + " color is NOT " + color);
		}
	}

	public void test() {

		try {
			WebElement element = driver.findElement(By.xpath("//b[contains(text(),'Boti')]"));
			checkBtnColor(driver, element, leftBtnHoverColor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Logger.log("  Header buttons hover Successfully ");
	}

	protected String getDescription() {
		return "The test checks if the user name button change colore if mouse hover over it on the header of the page";
	}
}
