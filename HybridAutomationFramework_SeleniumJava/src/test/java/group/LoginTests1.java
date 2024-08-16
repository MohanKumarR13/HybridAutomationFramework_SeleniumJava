package group;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginTests1 extends BaseClass {
	@Test(dataProvider = "testdata", dataProviderClass = DataProviders.class,groups = "datadriven")
	public void verifyLogin(String mail, String pwrd, String exp) {
		logger.info("===Login Test===");
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(mail);
			loginPage.setPwd(pwrd);
			loginPage.clickBtnLogin();

			MyAccountPage accountPage = new MyAccountPage(driver);
			boolean targetPage = accountPage.isMyAccountPageExists();

			if (exp.equals("Valid")) {
				if (targetPage == true) {
					Assert.assertTrue(targetPage);
					accountPage.clickLogOut();
					Assert.assertTrue(true);
				}else {
					Assert.assertTrue(false);

				}

			}
			
			if (exp.equals("Invalid")) {
				if (targetPage == true) {
					Assert.assertTrue(targetPage);
					accountPage.clickLogOut();
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);

				}

			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("Login Test Completed");
	}
}
