package testBases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class LoginTest extends BaseClass {
	@Test
	public void verifyLogin() {
		logger.info("===Login Test===");
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickRegister();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(properties.getProperty("EMAIL"));
			loginPage.setPwd(properties.getProperty("PASSWORD"));
			loginPage.clickBtnLogin();

			MyAccountPage accountPage = new MyAccountPage(driver);
			boolean targetPage = accountPage.isMyAccountPageExists();

			Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("Login Test Completed");
	}
}
