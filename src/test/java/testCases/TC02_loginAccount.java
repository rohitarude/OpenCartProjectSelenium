package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginAccountPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC02_loginAccount extends BaseClass {
	
		@Test(groups={"Regression","Sanity"})
		public void loginAccount() {
			
			
			
			try {
			HomePage HP = new HomePage(driver);
			HP.myAccount();
			HP.loginClick();

			LoginAccountPage LP = new LoginAccountPage(driver);

			logger.debug("enter the account username.....");
			LP.enterUsername(p.getProperty("email"));

			logger.info("enter the account password");
			LP.enterPassword(p.getProperty("password"));
			LP.clickLoginButton();
			
			MyAccountPage AP = new MyAccountPage(driver);
			
			logger.info("checking headed masg");
			Boolean msg =AP.accountPageExist();
			
			Assert.assertEquals(msg, true);
			
			logger.info("Account logout");
			AP.logoutClick();
			
			}
			catch(Exception e) {
				e.getMessage();
				logger.error("test failed", e);
				Assert.fail();
			}
			
			logger.info("TC02 finished .....");

		}
	
	
	

}
