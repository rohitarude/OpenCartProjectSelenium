package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy (xpath="//span[normalize-space()='My Account']") 
	WebElement myAccountClick;
	
	@FindBy (xpath = "//a[normalize-space()='Register']")
	WebElement registerClick;
	
	@FindBy (xpath ="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement login;
	
	
	
	
	public void myAccount() {
		myAccountClick.click();
		
	}
	
	public void registerClick() {
		
		registerClick.click();
		
	}
	
	public void loginClick() {
		login.click();
	}
	

}
