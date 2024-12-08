package hotel;

import static hotel.Utils.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hotel.pages.SignupPage.Gender;
import hotel.pages.SignupPage.Rank;
import hotel.pages.TopPage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Colors;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("マイページ")
class MyPageTest {

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
  @DisplayName("定義済みユーザの情報が表示されること_ichiro")
  void testMyPageExistUserOne() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("ichiro@example.com", "password");

    assertAll("マイページ表示項目",
        () -> assertEquals("ichiro@example.com", myPage.getEmail()),
        () -> assertEquals("山田一郎", myPage.getUsername()),
        () -> assertEquals("プレミアム会員", myPage.getRank()),
        () -> assertEquals("東京都豊島区池袋", myPage.getAddress()),
        () -> assertEquals("01234567891", myPage.getTel()),
        () -> assertEquals("男性", myPage.getGender()),
        () -> assertEquals("未登録", myPage.getBirthday()),
        () -> assertEquals("受け取る", myPage.getNotification())
    );
  }

  @Test
  @Order(2)
  @DisplayName("定義済みユーザの情報が表示されること_sakura")
  void testMyPageExistUserTwo() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("sakura@example.com", "pass1234");

    assertAll("マイページ表示項目",
        () -> assertEquals("sakura@example.com", myPage.getEmail()),
        () -> assertEquals("松本さくら", myPage.getUsername()),
        () -> assertEquals("一般会員", myPage.getRank()),
        () -> assertEquals("神奈川県横浜市鶴見区大黒ふ頭", myPage.getAddress()),
        () -> assertEquals("未登録", myPage.getTel()),
        () -> assertEquals("女性", myPage.getGender()),
        () -> assertEquals("2000年4月1日", myPage.getBirthday()),
        () -> assertEquals("受け取らない", myPage.getNotification())
    );
  }

  @Test
  @Order(3)
  @DisplayName("定義済みユーザの情報が表示されること_jun")
  void testMyPageExistUserThree() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("jun@example.com", "pa55w0rd!");

    assertAll("マイページ表示項目",
        () -> assertEquals("jun@example.com", myPage.getEmail()),
        () -> assertEquals("林潤", myPage.getUsername()),
        () -> assertEquals("プレミアム会員", myPage.getRank()),
        () -> assertEquals("大阪府大阪市北区梅田", myPage.getAddress()),
        () -> assertEquals("01212341234", myPage.getTel()),
        () -> assertEquals("その他", myPage.getGender()),
        () -> assertEquals("1988年12月17日", myPage.getBirthday()),
        () -> assertEquals("受け取らない", myPage.getNotification())
    );
  }

  @Test
  @Order(4)
  @DisplayName("定義済みユーザの情報が表示されること_yoshiki")
  void testMyPageExistUserFour() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("yoshiki@example.com", "pass-pass");

    assertAll("マイページ表示項目",
        () -> assertEquals("yoshiki@example.com", myPage.getEmail()),
        () -> assertEquals("木村良樹", myPage.getUsername()),
        () -> assertEquals("一般会員", myPage.getRank()),
        () -> assertEquals("未登録", myPage.getAddress()),
        () -> assertEquals("01298765432", myPage.getTel()),
        () -> assertEquals("未登録", myPage.getGender()),
        () -> assertEquals("1992年8月31日", myPage.getBirthday()),
        () -> assertEquals("受け取る", myPage.getNotification())
    );
  }

  @Test
  @Order(5)
  @DisplayName("新規登録したユーザの情報が表示されること")
  void testMyPageNewUser() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var signupPage = topPage.goToSignupPage();
    signupPage.setEmail("new-user@example.com");
    signupPage.setPassword("11111111");
    signupPage.setPasswordConfirmation("11111111");
    signupPage.setUsername("田中花子");
    signupPage.setRank(Rank.一般会員);
    signupPage.setAddress("神奈川県横浜市港区");
    signupPage.setTel("09876543211");
    signupPage.setGender(Gender.女性);
    signupPage.setBirthday(LocalDate.parse("2000-01-01"));
    signupPage.setNotification(false);
    var myPage = signupPage.goToMyPage();

    assertAll("マイページ表示項目",
        () -> assertEquals("new-user@example.com", myPage.getEmail()),
        () -> assertEquals("田中花子", myPage.getUsername()),
        () -> assertEquals("一般会員", myPage.getRank()),
        () -> assertEquals("神奈川県横浜市港区", myPage.getAddress()),
        () -> assertEquals("09876543211", myPage.getTel()),
        () -> assertEquals("女性", myPage.getGender()),
        () -> assertEquals("2000年1月1日", myPage.getBirthday()),
        () -> assertEquals("受け取らない", myPage.getNotification())
    );
  }

  @Test
  @Order(6)
  @DisplayName("アイコン設定で画像以外のファイルはエラーとなること")
  void testIconNotImage() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");
    var iconPage = myPage.goToIconPage();

    Path file = Paths.get("src", "test", "resources", "dummy.txt");
    iconPage.setIcon(file);

    assertEquals("画像ファイルを選択してください。", iconPage.getIconMessage());
  }

  @Test
  @Order(7)
  @DisplayName("アイコン設定で10KBを越えるファイルはエラーとなること")
  void testIconOverSize() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");
    var iconPage = myPage.goToIconPage();

    Path file = Paths.get("src", "test", "resources", "240x240_12.png");
    iconPage.setIcon(file);

    assertEquals("ファイルサイズは10KB以下にしてください。", iconPage.getIconMessage());
  }

  @Test
  @Order(8)
  @DisplayName("設定したアイコンがマイページに表示されること")
  void testIconSuccess() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");
    var iconPage = myPage.goToIconPage();

    Path file = Paths.get("src", "test", "resources", "240x240_01.png");
    iconPage.setIcon(file);
    iconPage.setZoom(80);
    iconPage.setColor(Colors.BLACK.getColorValue());
    iconPage.goToMyPage();

    assertAll("アイコン画像",
        () -> assertTrue(myPage.existsIconImage()),
        () -> assertEquals(80 - 10, myPage.getIconImageWidth()), // -(padding + border)
        () -> assertEquals(Colors.BLACK.getColorValue(), myPage.getIconImageBorder())
    );
  }

  @Test
  @Order(9)
  @DisplayName("新規登録したユーザが削除できること")
  void testDeleteUser() {
    driver.get(BASE_URL);
    var topPage = new TopPage(driver);

    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");
    myPage.deleteUser();

    Alert confirm = wait.until(ExpectedConditions.alertIsPresent());
    assertEquals("退会すると全ての情報が削除されます。\nよろしいですか？", confirm.getText());
    confirm.accept();
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    assertEquals("退会処理を完了しました。ご利用ありがとうございました。", alert.getText());
    alert.accept();
    wait.until(ExpectedConditions.urlContains("index.html"));
    assertNotNull(driver.getCurrentUrl());
    assertTrue(driver.getCurrentUrl().contains("index.html"));
  }

}
