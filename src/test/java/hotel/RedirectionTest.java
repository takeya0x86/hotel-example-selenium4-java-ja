package hotel;

import static hotel.Utils.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hotel.pages.TopPage;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("リダイレクト")
class RedirectionTest {

  private static WebDriver driver;

  private static WebDriverWait wait;

  @BeforeAll
  static void initAll() {
    driver = Utils.createWebDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @AfterEach
  void tearDown() {
    driver.manage().deleteAllCookies();
  }

  @AfterAll
  static void tearDownAll() {
    driver.quit();
  }

  @Test
  @Order(1)
  @DisplayName("未ログインでマイページからトップへリダイレクトすること")
  void testMyPageToTop() {
    driver.get(BASE_URL + "/mypage.html");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(2)
  @DisplayName("ログイン済みでログイン画面からトップへリダイレクトすること")
  void testLoginPageToTop() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    loginPage.doLogin("ichiro@example.com", "password");

    driver.get(BASE_URL + "/login.html");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(3)
  @DisplayName("ログイン済みで登録画面からトップへリダイレクトすること")
  void testSignupPageToTop() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    loginPage.doLogin("ichiro@example.com", "password");

    driver.get(BASE_URL + "/signup.html");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(4)
  @DisplayName("存在しないプランIDでトップへリダイレクトすること")
  void testNoPlanPageToTop() {
    driver.get(BASE_URL + "/reserve.html?plan-id=100");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(5)
  @DisplayName("不正なプランIDでトップへリダイレクトすること1")
  void testInvalidPlanPageToTop() {
    driver.get(BASE_URL + "/reserve.html?plan-id=abc");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(6)
  @DisplayName("不正なプランIDでトップへリダイレクトすること2")
  void testInvalidParamPlanPageToTop() {
    driver.get(BASE_URL + "/reserve.html");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(7)
  @DisplayName("未ログインで会員専用プランでトップへリダイレクトすること")
  void testMemberOnlyPlanPageToTop() {
    driver.get(BASE_URL + "/reserve.html?plan-id=3");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(8)
  @DisplayName("未ログインでプレミアム専用プランでトップへリダイレクトすること")
  void testPremiumOnlyPlanPageToTop() {
    driver.get(BASE_URL + "/reserve.html?plan-id=1");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(9)
  @DisplayName("一般会員でプレミアム専用プランでトップへリダイレクトすること")
  void testPremiumOnlyPlanNormalMemberPageToTop() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    loginPage.doLogin("sakura@example.com", "pass1234");

    driver.get(BASE_URL + "/reserve.html?plan-id=1");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }

  @Test
  @Order(10)
  @DisplayName("予約画面を経ずに確認画面でトップへリダイレクトすること")
  void testInvalidParamConfirmPageToTop() {
    driver.get(BASE_URL + "/confirm.html");
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().endsWith("index.html"));
  }
}
