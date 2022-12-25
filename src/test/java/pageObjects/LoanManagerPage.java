package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class LoanManagerPage {

	WebDriver driver;

	@FindBy(xpath = "//span[@class='name text-capitalize ng-binding'][text()='Loans']")
	WebElement buttonLoans;

	@FindBy(xpath = "//md-menu[contains(@title, 'Logged with')]/div/a/img[@class='circle-image']")
	WebElement avatar;

	@FindBy(xpath = "//button[@class='md-primary white-font default-buttons-blue loan-manager-button md-button md-lptheme-theme md-ink-ripple'][text()='New Loan']")
	WebElement buttonNewLoan;

	@FindBy(xpath = "//md-icon[@class='new-lp-icon new-lp-icon-excel-reports md-lptheme-theme'][@aria-label = 'reports']")
	WebElement buttonReports;

	@FindBy(xpath = "//span[text()='QA Testing Tenant']")
	WebElement titleQaTestingTenant;

	@FindBy(xpath = "//md-select[@id='loanStatus']/md-select-value")
	WebElement dropDownLoanStatus;

	@FindBy(xpath = "//div[@id='select_container_158']")
	WebElement dropDownSelectContainer;

	@FindBy(xpath = "//md-whiteframe[@title='Total number of accounts']//div[@class='md-display-1 ng-binding']")
	WebElement totalNumberOfAccaunts;

	@FindBy(id = "loanManagerTable")
	WebElement loanManagerTable;

	@FindBy(xpath = "//table[@id='loanManagerTable']/tbody/tr/td[25]")
	List<WebElement> tableSubStatus;

	@FindBy(xpath = "//button[text()='No']")
	WebElement buttonChangePassword;

	public LoanManagerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean urlValidation(String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.urlContains(url));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Boolean controlElementsValidation() {
		WebElement[] elements = new WebElement[] { buttonLoans, avatar, buttonNewLoan, buttonReports,
				titleQaTestingTenant };
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

	public void selectLoanStatus(String status) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(buttonChangePassword)).click();
		} catch (NoSuchElementException e) {
		}
		wait.until(ExpectedConditions.elementToBeClickable(dropDownLoanStatus)).click();
		WebElement statusToSelect = dropDownSelectContainer.findElement(By.xpath("//div[text()='" + status + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(statusToSelect)).click();
	}

	public Boolean accountNumberVerification(int i) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(totalNumberOfAccaunts, Integer.toString(i)));
			wait.until(ExpectedConditions.visibilityOfAllElements(tableSubStatus));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean loanSubStatusVerification(String status) throws InterruptedException {
		for (int i = 0; i < tableSubStatus.size(); i++) {
			if (!tableSubStatus.get(i).getText().equals(status)) {
				return false;
			}
		}
		return true;
	}

}
