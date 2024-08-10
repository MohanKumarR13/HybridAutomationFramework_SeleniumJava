package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "email")
	WebElement email;

	@FindBy(name = "password")
	WebElement pwd;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;

	public void setEmail(String emailId) {
		email.sendKeys(emailId);
	}

	public void setPwd(String password) {
		pwd.sendKeys(password);
	}

	public void clickBtnLogin() {
		btnLogin.click();
	}

}
