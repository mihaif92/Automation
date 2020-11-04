package auto;

import java.lang.reflect.Modifier;

import org.openqa.selenium.WebDriver;

import com.google.common.reflect.ClassPath;

import helpers.ArgumentsHelper;
import helpers.DriverHelper;
import helpers.Logger;
import tests.base.ImplementationRunner;

public class App {

	public static void main(String[] args) throws Throwable {

		ArgumentsHelper.getInstance().populateArguments(args);
		String singleTestName = ArgumentsHelper.getInstance().getConfig().singleTestName;
		
		WebDriver driver = DriverHelper.getDriver();

		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		String testSuite = Cmdtool.getInstance().getValueOf(signinAndSignoutTest);
//		ArrayList<ImplementationRunner> tests2Run = new ArrayList<ImplementationRunner>();
		for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
			if (info.getName().startsWith("tests")) {
				final Class<?> clazz = info.load();
//				Run one test by name
				if (!Modifier.isAbstract(clazz.getModifiers()) && !singleTestName.isEmpty()
						&& clazz.getSimpleName().equals(singleTestName)) {
					((ImplementationRunner) clazz.getDeclaredConstructor().newInstance()).run(driver);
				}
//				Run all tests:
				else if (!Modifier.isAbstract(clazz.getModifiers()) && singleTestName.isEmpty()) {
					((ImplementationRunner) clazz.getDeclaredConstructor().newInstance()).run(driver);
//					tests2Run.add(((ImplementationRunner) clazz.getDeclaredConstructor().newInstance()));
				}
			}
		}

		theEnd();
		driver.quit();
		System.exit(0);
	}

	private static void theEnd() {
		String logo = " ____                                         __ _       _     _              _                                    __       _ _       \r\n"
				+ "|  _ \\ _ __ ___   __ _ _ __ __ _ _ __ ___    / _(_)_ __ (_)___| |__   ___  __| |  ___ _   _  ___ ___ ___  ___ ___ / _|_   _| | |_   _ \r\n"
				+ "| |_) | '__/ _ \\ / _` | '__/ _` | '_ ` _ \\  | |_| | '_ \\| / __| '_ \\ / _ \\/ _` | / __| | | |/ __/ __/ _ \\/ __/ __| |_| | | | | | | | |\r\n"
				+ "|  __/| | | (_) | (_| | | | (_| | | | | | | |  _| | | | | \\__ \\ | | |  __/ (_| | \\__ \\ |_| | (_| (_|  __/\\__ \\__ \\  _| |_| | | | |_| |\r\n"
				+ "|_|   |_|  \\___/ \\__, |_|  \\__,_|_| |_| |_| |_| |_|_| |_|_|___/_| |_|\\___|\\__,_| |___/\\__,_|\\___\\___\\___||___/___/_|  \\__,_|_|_|\\__, |\r\n"
				+ "                 |___/                                                                                                          |___/ \r\n"
				+ "\n";
		Logger.log(logo);
	}

}
