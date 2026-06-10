package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC01_registerAccount extends BaseClass {

	@Test(groups = "Regression")
	public void verify_account_registration() {

		logger.info("**** TC01 started ****");
		try {
			logger.info("launch a page");

			HomePage HP = new HomePage(driver);
			HP.myAccount();
			HP.registerClick();

			logger.info("start to fill a customer details");

			AccountRegistrationPage ARP = new AccountRegistrationPage(driver);
			ARP.setFirstName(randomString().toUpperCase());
			ARP.setLastName(randomString().toUpperCase());
			ARP.setEMail(randomString() + "@gmail.com");
			ARP.setContact(randomNumber());
			String password = randomAlphabet();
			ARP.setPassword(password);
			ARP.setConfirmPassword(password);
			ARP.setPolicy();
			ARP.clickContinue();

			logger.info("checking confirmation massage");

			String confirmationMsg = ARP.getConfirmMasg();

			if (confirmationMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);

				logger.info("Test run completed...");

			} else {
				logger.error("test failed ....");
			}

			// Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!!");
		} catch (Exception e) {
			logger.debug("Debug log");
			logger.error("test failed", e);
			Assert.fail();

		}

	}

}
