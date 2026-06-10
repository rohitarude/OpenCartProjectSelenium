package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginAccountPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void verify_loginDDT(String email, String password, String exp) {
		logger.info("TC03_Started..........");

		try {
			HomePage HP = new HomePage(driver);
			HP.myAccount();
			HP.loginClick();
			;

			LoginAccountPage LP = new LoginAccountPage(driver);
			logger.info("entered a username details");
			LP.enterUsername(email);
			logger.info("password entered ");
			LP.enterPassword(password);
			logger.info("click on login button .........");
			LP.clickLoginButton();

			MyAccountPage AP = new MyAccountPage(driver);
			Boolean targetPage = AP.accountPageExist();

			logger.info("....Validation Started....");

			if (exp.equalsIgnoreCase("Valid")) {

				if (targetPage) {
					AP.logoutClick();
					Assert.assertTrue(true);
				} else {
					Assert.fail();
				}

			} else if (exp.equalsIgnoreCase("Invalid")) {

				if (!targetPage) {
					Assert.assertTrue(true);
				} else {
					AP.logoutClick();
					Assert.fail();
				}

			}

		} catch (Exception e) {
			logger.debug("Test Failed", e);
			e.getMessage();
			Assert.fail();

		}

		logger.info("TC03_completed.............");
	}

}
