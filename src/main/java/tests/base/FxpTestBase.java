package tests.base;

import enums.Times;
import helpers.Minion;
import helpers.fxp.ConnectivityHelper;

public abstract class FxpTestBase extends TestBase {

	@Override
	protected void beforeTest() {

		ConnectivityHelper.signIn(driver, "Boti", "123456");
		Minion.sleep(Times.MEDIUM);
		try {
			Minion.clickOnId(driver, "onesignal-slidedown-cancel-button");
		} catch (Exception e) {

		}
	}

	@Override
	protected abstract void test() throws Exception;

	@Override
	protected void afterTest() {

		ConnectivityHelper.signOut(driver);
	}
}
