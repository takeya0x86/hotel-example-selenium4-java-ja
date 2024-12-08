package hotel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

  private final WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    if (this.driver.getTitle() == null || !this.driver.getTitle().startsWith("ログイン")) {
      throw new IllegalStateException("現在のページが間違っています: " + this.driver.getTitle());
    }
  }

  public MyPage doLogin(String email, String password) {
    var emailInput = driver.findElement(By.id("email"));
    emailInput.clear();
    emailInput.sendKeys(email);
    var passwordInput = driver.findElement(By.id("password"));
    passwordInput.clear();
    passwordInput.sendKeys(password);
    var loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();
    return new MyPage(driver);
  }

  public void doLoginExpectingFailure(String email, String password) {
    var emailInput = driver.findElement(By.id("email"));
    emailInput.clear();
    emailInput.sendKeys(email);
    var passwordInput = driver.findElement(By.id("password"));
    passwordInput.clear();
    passwordInput.sendKeys(password);
    var loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();
  }

  public String getEmailMessage() {
    var emailMessage = driver.findElement(By.id("email-message"));
    return emailMessage.getText();
  }

  public String getPasswordMessage() {
    var passwordMessage = driver.findElement(By.id("password-message"));
    return passwordMessage.getText();
  }
}
