package helpers;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import enums.ArgumentsEnums;

public class ArgumentsHelper {

	public class Config {

		public final int timeout;
		public final String driverPath; // "D:\\Automation\\selenium_drivers\\chromedriver.exe";
		public final int testTries;
		public final String Url2Load;
		public final String singleTestName;

		public Config(int timeout, String driverPath, int testTries, String Url2Load, String singleTestName) {

			this.timeout = timeout;
			this.driverPath = driverPath;
			this.testTries = testTries;
			this.Url2Load = Url2Load;
			this.singleTestName = singleTestName;

		}

//		[Test - 1] = SigninAndSignoutTest
//		[Test - 2] = UserProfileFillTest
//		[Test - 3] = UserNameBtnColorTest
//		[Test - 4] = TwoUsersMessageTest
	}

	private CommandLine commandLine;
	private static ArgumentsHelper instance;
	private Config config = null;

	private ArgumentsHelper() {
	}

	public static synchronized ArgumentsHelper getInstance() {
		if (instance == null) {
			instance = new ArgumentsHelper();
		}
		return instance;
	}

	public void populateArguments(String[] params) throws ParseException {

		Option optionDriverPath = Option.builder("d").required(true).desc("The path to the browser driver")
				.longOpt("driver").hasArg(true).valueSeparator('=').build();

		Option optionTimeOut = Option.builder("t").required(false)
				.desc("The timeout that the test will wait to an element to apear in the DOM").longOpt("timeout")
				.hasArg(true).valueSeparator('=').build();

		Option optionTestTries = Option.builder("n").required(false)
				.desc("The number of time a test can fail until continu to the next test").longOpt("testTries")
				.hasArg(true).valueSeparator('=').build();

		Option optionUrlToLoad = Option.builder("u").required(false)
				.desc("The URL that you want to load for the driver").longOpt("urlToLoad").hasArg(true)
				.valueSeparator('=').build();

		Option optionSinglTestToRun = Option.builder("r").required(false)
				.desc("The name of the single test that you want to run").longOpt("singleTestToRun").hasArg(true)
				.valueSeparator('=').build();

		Options options = new Options();

		CommandLineParser parser = new DefaultParser();

		options.addOption(optionDriverPath);
		options.addOption(optionTimeOut);
		options.addOption(optionTestTries);
		options.addOption(optionUrlToLoad);
		options.addOption(optionSinglTestToRun);

		try

		{
			commandLine = parser.parse(options, params);
			initConfig();

		} catch (ParseException exception) {
			Logger.log("Parse error: ");
			Logger.log(exception.getMessage());
			throw exception;
		}
	}

	private void initConfig() {
		String driverPath = commandLine.getOptionValue(ArgumentsEnums.DRIVER_PATH.value());
		
		String temp = commandLine.getOptionValue(ArgumentsEnums.TIME_OUT.value());
		if( temp == null) {
			temp = "5";
		}
		int timeOut = Integer.parseInt(temp);
		
		temp = commandLine.getOptionValue(ArgumentsEnums.TEST_TRIES.value());
		if( temp == null) {
			temp = "3";
		}
		int testTries = Integer.parseInt(temp);
		
		temp = commandLine.getOptionValue(ArgumentsEnums.URL_TO_LOAD.value());
		if( temp == null) {
			temp = "https://www.fxp.co.il";
		}
		String urlToLoad = temp;
		
		temp = commandLine.getOptionValue(ArgumentsEnums.SINGL_TEST_TO_RUN.value());
		if( temp == null) {
//			[Test - 1] = SigninAndSignoutTest
//			[Test - 2] = UserProfileFillTest
//			[Test - 3] = UserNameBtnColorTest
//			[Test - 4] = TwoUsersMessageTest
			temp = "TwoUsersMessageTest";
		}
		String singleTestToRun = temp;
		
		config = new Config(timeOut, driverPath, testTries, urlToLoad, singleTestToRun);
	}
	
	public Config getConfig() {
		return config;
	}

}
