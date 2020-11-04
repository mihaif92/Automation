package tests.fxp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;

import enums.Times;
import helpers.ArgumentsHelper;
import helpers.JsonHelper;
import helpers.Logger;
import helpers.Minion;
import helpers.fxp.ConnectivityHelper;
import tests.base.FxpTestBase;

public class TwoUsersMessageTest extends FxpTestBase {
//************************************************************

//************************************************************	
	InputStream jsonFileStream = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream("AutomationJson.json");
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private long ts = timestamp.getTime();
	private String msg = "This is a test message\n" + ts;
	private String response = "  Message received!";
	private String secondUserName = "Boti2";
	private String password = "12345";
	public String hh = "lol";

	String jsonString = null;

	@Override
	protected void test() {

//		try (InputStream jsonFileStream = TwoUsersMessageTest.class.getResourceAsStream("AutomationJson.json")) {
		// pass InputStream to JSON-Library, e.g. using Jackson
//			ObjectMapper mapper = new ObjectMapper();
//			JsonNode jsonNode = mapper.readValue(jsonFileStream, JsonNode.class);
//			jsonString = mapper.writeValueAsString(jsonNode);
//			System.out.println(jsonString);

//		try (InputStream inputStream = TwoUsersMessageTest.class.getResourceAsStream("AutomationJson.json");
//				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//			jsonString = reader.lines().collect(Collectors.joining(System.lineSeparator()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//			try {
//				jsonString = readFromJARFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		jsonData();

		try {
			jsonFileStream = getClass().getClassLoader().getResourceAsStream("AutomationJson.json");
			System.out.println(jsonFileStream);
			URL jsonFileStreamURL = getClass().getClassLoader().getResource("AutomationJson.json");
			System.out.println(jsonFileStreamURL);
			jsonString = readFromJARFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		jsonData();

		sendMessageTo(driver);
		ConnectivityHelper.signOut(driver);
		ConnectivityHelper.signIn(driver, secondUserName, password);
		checkMessage();

	}

	@Override
	protected String getDescription() {
		return "This test sign-in to one user and sends a message to an other user, then switches to the second user and sends a feedback if the message was received";
	}

	private void clickOnMessagesBtn() {

		Minion.clickOnXpath(driver, "//a[@id='yui-gen7']");
		Minion.clickOnXpath(driver, "//a[contains(@href, 'chat.php')]");

	}

	private void sendMessageTo(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver,
				Duration.ofSeconds(1000 * ArgumentsHelper.getInstance().getConfig().timeout));

		clickOnMessagesBtn();
		WebElement iframe = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".divhed>iframe")));
		driver.switchTo().frame(iframe);

		Minion.clickOnClass(driver, "new-message-btn");

		Minion.sendKeysToId(driver, "recipients", secondUserName);

		Minion.sleep(Times.SHORT);
		Minion.sendKeysToId(driver, "recipients", "" + Keys.ARROW_DOWN + Keys.ENTER);

//		Fill the title and the message
		Minion.sendKeysToId(driver, "title", "Test Message " + timestamp.getTime());
		Minion.sleep(Times.SHORT);

//		Fill the message area
		Minion.sendKeysToId(driver, "title", "" + Keys.TAB + msg);

		Minion.clickOnXpath(driver, "//div[@class='send']");
		Logger.log("  Message was sent");

		driver.switchTo().defaultContent();
	}

	private void checkMessage() {

		clickOnMessagesBtn();
		WebDriverWait wait = new WebDriverWait(driver,
				Duration.ofSeconds(1000 * ArgumentsHelper.getInstance().getConfig().timeout));

		WebElement iframe = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".divhed>iframe")));
		driver.switchTo().frame(iframe);

		Minion.sleep(Times.SHORT);

		try {
			Minion.clickOnXpath(driver, "//div[contains(@title,'" + ts + "')]");
			Logger.log(response);
		} catch (Exception e) {
			Logger.log("Did NOT receive any message\n");
		}

		driver.switchTo().defaultContent();
		iframe = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#pm_holder")));
		driver.switchTo().frame("pm_holder");
		Minion.sendKeysToId(driver, "input-textarea", response + Keys.ENTER);

		driver.switchTo().defaultContent();
	}

	private void jsonData() {

		JsonNode node = null;
		try {
			node = JsonHelper.parse(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		secondUserName = node.get("secondUserName").asText();
		password = node.get("password").asText();
		msg = node.get("msg").asText() + "\n" + ts;
		response = node.get("response").asText();
	}

	public String readFromJARFile() throws IOException {
		InputStreamReader isr = new InputStreamReader(jsonFileStream);
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		isr.close();
		return sb.toString();
	}

//************************************************************	

	public String newTry() {

		InputStream inputStream = getClass().getResourceAsStream("AutomationJson.json");

		InputStreamReader isReader = new InputStreamReader(inputStream);

		BufferedReader reader = new BufferedReader(isReader);
		StringBuffer sb = new StringBuffer();
		String str;
		try {
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

//************************************************************	

}
