package testBases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;

public class AccountRegister extends BaseClass {

@Test
public void verify_register() {
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickRegister();
	
	AccountRegisterationPage accountRegisterationPage=new AccountRegisterationPage(driver);
	
	accountRegisterationPage.setTxtFirstName(randomString().toUpperCase());
	accountRegisterationPage.setTxtLastName(randomString().toUpperCase());
	accountRegisterationPage.setTxtEmail(randomString()+"@gmail.com");
	accountRegisterationPage.setTxtTelePhone(randomNumber());
	
	String passwd=randomAlphaNumeric();
	accountRegisterationPage.setTxtPassword(passwd);
	accountRegisterationPage.setTxtConfrimPassword(passwd);
	
	accountRegisterationPage.setCheckPolicy();
	accountRegisterationPage.setBtnContinue();
	
	String confirmMsg=accountRegisterationPage.getConfirmationMessage();
	Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");






}

}
