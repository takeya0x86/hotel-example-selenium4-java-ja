package hotel;

import static hotel.Utils.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import hotel.pages.TopPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("プラン一覧")
class PlansTest {

  private static WebDriver driver;

  @BeforeAll
  static void initAll() {
    driver = Utils.createWebDriver();
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
  @DisplayName("未ログイン状態でプラン一覧が表示されること")
  void testPlanListNotLogin() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var plansPage = topPage.goToPlansPage();
    var planTitles = plansPage.getPlanTitles();

    assertAll("プラン一覧",
        () -> assertEquals(7, planTitles.size()),
        () -> assertEquals("お得な特典付きプラン", planTitles.getFirst()),
        () -> assertEquals("素泊まり", planTitles.get(1)),
        () -> assertEquals("出張ビジネスプラン", planTitles.get(2)),
        () -> assertEquals("エステ・マッサージプラン", planTitles.get(3)),
        () -> assertEquals("貸し切り露天風呂プラン", planTitles.get(4)),
        () -> assertEquals("カップル限定プラン", planTitles.get(5)),
        () -> assertEquals("テーマパーク優待プラン", planTitles.get(6))
    );
  }

  @Test
  @Order(2)
  @DisplayName("一般会員でログイン状態でプラン一覧が表示されること")
  void testPlanListLoginNormal() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("sakura@example.com", "pass1234");

    var plansPage = myPage.goToPlansPage();
    var planTitles = plansPage.getPlanTitles();

    assertAll("プラン一覧",
        () -> assertEquals(9, planTitles.size()),
        () -> assertEquals("お得な特典付きプラン", planTitles.getFirst()),
        () -> assertEquals("ディナー付きプラン", planTitles.get(1)),
        () -> assertEquals("お得なプラン", planTitles.get(2)),
        () -> assertEquals("素泊まり", planTitles.get(3)),
        () -> assertEquals("出張ビジネスプラン", planTitles.get(4)),
        () -> assertEquals("エステ・マッサージプラン", planTitles.get(5)),
        () -> assertEquals("貸し切り露天風呂プラン", planTitles.get(6)),
        () -> assertEquals("カップル限定プラン", planTitles.get(7)),
        () -> assertEquals("テーマパーク優待プラン", planTitles.get(8))
    );
  }

  @Test
  @Order(3)
  @DisplayName("プレミアム会員でログイン状態でプラン一覧が表示されること")
  void testPlanListLoginPremium() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("ichiro@example.com", "password");

    var plansPage = myPage.goToPlansPage();
    var planTitles = plansPage.getPlanTitles();

    assertAll("プラン一覧",
        () -> assertEquals(10, planTitles.size()),
        () -> assertEquals("お得な特典付きプラン", planTitles.getFirst()),
        () -> assertEquals("プレミアムプラン", planTitles.get(1)),
        () -> assertEquals("ディナー付きプラン", planTitles.get(2)),
        () -> assertEquals("お得なプラン", planTitles.get(3)),
        () -> assertEquals("素泊まり", planTitles.get(4)),
        () -> assertEquals("出張ビジネスプラン", planTitles.get(5)),
        () -> assertEquals("エステ・マッサージプラン", planTitles.get(6)),
        () -> assertEquals("貸し切り露天風呂プラン", planTitles.get(7)),
        () -> assertEquals("カップル限定プラン", planTitles.get(8)),
        () -> assertEquals("テーマパーク優待プラン", planTitles.get(9))
    );
  }

}
