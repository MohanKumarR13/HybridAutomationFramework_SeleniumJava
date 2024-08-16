package group;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class AccountRegister1 extends BaseClass {

	@Test(groups = {"regression","master"})
	public void verify_register() {
		logger.info("===Starting Test Case===");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("===Click My Account===");

			hp.clickRegister();
			logger.info("===Click Register Link===");

			AccountRegisterationPage accountRegisterationPage = new AccountRegisterationPage(driver);
			logger.info("===Provide Customer Details===");

			accountRegisterationPage.setTxtFirstName(randomString().toUpperCase());
			accountRegisterationPage.setTxtLastName(randomString().toUpperCase());
			accountRegisterationPage.setTxtEmail(randomString() + "@gmail.com");
			accountRegisterationPage.setTxtTelePhone(randomNumber());

			String passwd = randomAlphaNumeric();
			accountRegisterationPage.setTxtPassword(passwd);
			accountRegisterationPage.setTxtConfrimPassword(passwd);

			accountRegisterationPage.setCheckPolicy();
			accountRegisterationPage.setBtnContinue();
			logger.info("===Validate Expected Message===");

			String confirmMsg = accountRegisterationPage.getConfirmationMessage();
			if (confirmMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}else {
				logger.error("Test Failed");
				logger.debug("Debug logs");
				Assert.assertTrue(false);

			}
			//Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("===Completed Test Case===");

	}

}
