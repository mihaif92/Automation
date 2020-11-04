package tests.base;

import org.openqa.selenium.WebDriver;
import org.springframework.util.StopWatch;

import enums.ArgumentsEnums;
import helpers.ArgumentsHelper;
import helpers.Logger;

public abstract class TestBase implements ImplementationRunner {

	protected static String testName;
	StopWatch watch = new StopWatch();
	public boolean testResult = true;
	protected double testDuration;
	protected WebDriver driver = null;
	protected int lives = ArgumentsHelper.getInstance().getConfig().testTries;

	public void run(WebDriver driver) {

		this.driver = driver;
		watch.start();

		printTestInfo();

		while (lives > 0) {
			try {
				initTestVariables();
				beforeTest();
				test();
				afterTest();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				lives--;
				testResult = false;
				onFail();
			}
			onSuccess();

		}

		watch.stop();
		testDuration = watch.getTotalTimeSeconds();

		printReport(testResult);
	}

	protected void printTestInfo() {
		Logger.log("\n   ---------------------------   ");
		Logger.log("       " + this.getClass().getSimpleName() + "   ");
		Logger.log("   ---------------------------   ");
		Logger.log("   " + getDescription() + "\n");
	}
	
	protected void printTestInfo(String str) {
		Logger.log("\n   -------------------------------------------------   ");
		Logger.log("       " + this.getClass().getSimpleName() + " : " + str + "   ");
		Logger.log("   -------------------------------------------------   ");
		Logger.log("   " + getDescription() + "\n");
	}

	protected abstract String getDescription();

	/*
	 * This function is the test that you want to implement
	 */
	protected abstract void test() throws Exception;

	private void initTestVariables() {
		testResult = true;

		driver.manage().deleteAllCookies();
		driver.get(ArgumentsHelper.getInstance().getConfig().Url2Load);
	}

	protected void beforeTest() {
	}

	protected void afterTest() {
	}

	private void printReport(boolean status) {
		if (status) {
//			Logger.log("\n\n  ******************************************************************************************");
			printTestInfo("Test report");
			Logger.log("\n   [Test finished SUCCESSFULLY] in " + testDuration + " seconds with " + lives + " of "
					+ ArgumentsEnums.TEST_TRIES.value() + " lives");
			testSuccessPrint();
			Logger.log(
					"\n\n  ******************************************************************************************");
		} else {
//			Logger.log("\n\n  ******************************************************************************************");
			printTestInfo("Test report");
			Logger.log("\n   [Test FAILED] in " + testDuration + " seconds with " + lives + " of " + ArgumentsEnums.TEST_TRIES.value()
					+ " lives");
			testFailedPrint();
			Logger.log(
					"\n\n  ******************************************************************************************\n");
		}
	}

	private static void testFailedPrint() {
		String failed = " _____     _ _          _ \n" + "|  ___|_ _(_) | ___  __| |\n"
				+ "| |_ / _` | | |/ _ \\/ _` |\n" + "|  _| (_| | | |  __/ (_| |\n" + "|_|  \\__,_|_|_|\\___|\\__,_|\n"
				+ "\n";
		Logger.log(failed);
	}

	private static void testSuccessPrint() {
		Logger.log(" ___ _   _  ___ ___ ___  ___ ___ \n" + "/ __| | | |/ __/ __/ _ \\/ __/ __|\n"
				+ "\\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" + "|___/\\__,_|\\___\\___\\___||___/___/" + "\n");
	}

	protected void onSuccess() {
	}

	protected void onFail() {

	}

}
