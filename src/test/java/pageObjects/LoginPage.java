package pageObjects;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "username")
	WebElement inputEmail;

	@FindBy(id = "password")
	WebElement inputPassword;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement buttonLogin;

	@FindBy(xpath = "//button[text()='Reset User Session']")
	WebElement buttonResetSession;

	@FindBy(xpath = "//button[text()='Close']")
	WebElement buttonResetSessionClose;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setEmail(String email) {
		inputEmail.clear();
		inputEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		inputPassword.clear();
		inputPassword.sendKeys(password);
	}

	public void clickLoginButton() throws InterruptedException {
		buttonLogin.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(buttonResetSession)).click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonResetSessionClose)).click();
			buttonLogin.click();
			} catch (Exception e) {
			}
	}

	public Boolean controlElementsValidation() {
		WebElement[] elements = new WebElement[] { inputEmail, inputPassword };
		for (WebElement element : elements) {
			try {
				if (!element.isDisplayed()) {
					return false;
				}
			} catch (NoSuchElementException e) {
				return false;
			}
		}
		return true;
	}

}
