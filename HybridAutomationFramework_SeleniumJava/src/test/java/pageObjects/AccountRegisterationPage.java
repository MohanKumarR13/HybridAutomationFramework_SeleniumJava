package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegisterationPage extends BasePage {

	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelePhone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfrimPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setTxtFirstName(String fname) {
		txtFirstName.sendKeys(fname);
		;
	}

	public void setTxtLastName(String lname) {
		txtLastName.sendKeys(lname);
		;
	}

	public void setTxtEmail(String email) {
		txtEmail.sendKeys(email);
		;
	}

	public void setTxtTelePhone(String telePhone) {
		txtTelePhone.sendKeys(telePhone);
		;
	}

	public void setTxtPassword(String pwd) {
		txtPassword.sendKeys(pwd);
		;
	}

	public void setTxtConfrimPassword(String confirmPwd) {
		txtConfrimPassword.sendKeys(confirmPwd);
		;
	}

	public void setCheckPolicy() {
		checkPolicy.click();
		;
	}

	public void setBtnContinue() {
		btnContinue.click();

		/*
		 * btnContinue.submit();
		 * 
		 * Actions actions = new Actions(driver);
		 * actions.moveToElement(btnContinue).click().perform();
		 * 
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click()", btnContinue);
		 * 
		 * btnContinue.sendKeys(Keys.RETURN);
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		 * wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		 */
	}

	public String getConfirmationMessage() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
			// TODO: handle exception
		}
	}

}
