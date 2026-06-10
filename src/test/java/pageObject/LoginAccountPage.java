package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginAccountPage extends BasePage {

	public LoginAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath= "//input[@id='input-email']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	


	
	public void enterUsername(String userName) {
		//username.clear();
		
		username.sendKeys(userName);
	}
	
	public void enterPassword(String Password) {
		//password.clear();
		password.sendKeys(Password);
	}
	
	public void clickLoginButton() {
		loginBtn.click();
	}
	
	
	
	}
	
	
	
	
	
	
	


