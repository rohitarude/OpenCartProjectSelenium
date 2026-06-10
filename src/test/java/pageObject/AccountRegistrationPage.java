package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastName;
	
	@FindBy (xpath="//input[@id='input-email']")
	WebElement eMail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement contactNumber;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement privacyPol;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmMasg;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn;
	
	
	
	
	public void setFirstName(String firstname) {
		firstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		lastName.sendKeys(lastname);
	}
	public void setEMail(String email) {
		eMail.sendKeys(email);
	}
	
	public void setContact(String contact) {
		contactNumber.sendKeys(contact);
	}
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void setConfirmPassword(String compwd) {
		confPassword.sendKeys(compwd);
	}
	
	public void setPolicy() {
	    privacyPol.click();;
	}
	
	public void clickContinue() {
		btn.click();
		
	}
	public String getConfirmMasg() {
		try {
			return (confirmMasg.getText());
			
		}catch(Exception e) {
			return (e.getMessage());
			
		}
		
	}
	
	
	
	
}
