package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pageObjects.LoanManagerPage;
import pageObjects.LoginPage;

public class Steps {
	
	WebDriver driver;
	LoginPage lp;
	LoanManagerPage lmp;

	@Given("Launch Chrome browser")
	public void launch_chrome_browser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		lp = new LoginPage(driver);
	}

	@When("Go to page {string}")
	public void go_to_page(String url) {
		driver.get(url);
	}
	
	@Then("Assert validate controls exist")
	public void assert_validate_controls_exist() {
		Assert.assertTrue("Control elements are not present or visible", lp.controlElementsValidation());
	}

	@When("Enter user name as {string} and password as {string} and login to application")
	public void enter_user_name_as_and_password_as_and_login_to_application(String usrnm, String pswd) throws InterruptedException {
		lp.setEmail(usrnm);
		lp.setPassword(pswd);
		lp.clickLoginButton();
	}

	@Then("User land on page {string}")
	public void user_land_on_page(String url) {
		lmp = new LoanManagerPage(driver);
		Assert.assertTrue(lmp.urlValidation(url));
	}

	@Then("Assert validations of controls")
	public void assert_validations_of_controls() {
		lmp.controlElementsValidation();
	}

	@Then("Validate results that loans displayed are with status {string}, total number of results is {int}")
	public void validate_results_that_loans_displayed_are_with_status_total_number_of_results_is(String status, Integer results) throws InterruptedException { 
		lmp.selectLoanStatus(status);
		Assert.assertTrue("Number verification failed", lmp.accountNumberVerification(results));
		Assert.assertTrue("Loan status verification failed", lmp.loanSubStatusVerification(status));
	}

	@Then("Close browser")
	public void close_browser() {
		driver.close();
	}

}
