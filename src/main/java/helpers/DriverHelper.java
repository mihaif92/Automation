package helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHelper {

	private static WebDriver driver = null;

	private DriverHelper() {
	};

	public static WebDriver getDriver() {

		if (driver == null) {
			driver = initiateDriver(driver);
		}
		return driver;
	}

	private static WebDriver initiateDriver(WebDriver driver) {
		System.setProperty("webdriver.chrome.driver", ArgumentsHelper.getInstance().getConfig().driverPath);
		driver = new ChromeDriver();
		Point p = new Point(0, 0);
		driver.manage().window().setPosition(p);
		int width = 1080;
		int height = 800;
		driver.manage().window().setSize(new Dimension(width, height));
//		driver.get(Config.Url2Load);
		return driver;
	}
}
