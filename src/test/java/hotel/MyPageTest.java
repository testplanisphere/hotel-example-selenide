package hotel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.jupiter.api.Assertions.*;

import com.codeborne.selenide.junit5.BrowserStrategyExtension;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import hotel.pages.SignupPage.Rank;
import hotel.pages.TopPage;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserStrategyExtension.class, ScreenShooterExtension.class})
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("マイページ")
class MyPageTest {

  @AfterEach
  void tearDown() {
    clearBrowserCookies();
  }

  @Test
  @Order(1)
  @DisplayName("定義済みユーザの情報が表示されること_ichiro")
  void testMyPageExistUserOne() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("ichiro@example.com", "password");

    myPage.getEmail().shouldHave(exactText("ichiro@example.com"));
    myPage.getUsername().shouldHave(exactText("山田一郎"));
    myPage.getRank().shouldHave(exactText("プレミアム会員"));
    myPage.getAddress().shouldHave(exactText("東京都豊島区池袋"));
    myPage.getTel().shouldHave(exactText("01234567891"));
    myPage.getGender().shouldHave(exactText("男性"));
    myPage.getBirthday().shouldHave(exactText("未登録"));
    myPage.getNotification().shouldHave(exactText("受け取る"));
  }

  @Test
  @Order(2)
  @DisplayName("定義済みユーザの情報が表示されること_sakura")
  void testMyPageExistUserTwo() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("sakura@example.com", "pass1234");

    myPage.getEmail().shouldHave(exactText("sakura@example.com"));
    myPage.getUsername().shouldHave(exactText("松本さくら"));
    myPage.getRank().shouldHave(exactText("一般会員"));
    myPage.getAddress().shouldHave(exactText("神奈川県横浜市鶴見区大黒ふ頭"));
    myPage.getTel().shouldHave(exactText("未登録"));
    myPage.getGender().shouldHave(exactText("女性"));
    myPage.getBirthday().shouldHave(exactText("2000年4月1日"));
    myPage.getNotification().shouldHave(exactText("受け取らない"));
  }

  @Test
  @Order(3)
  @DisplayName("定義済みユーザの情報が表示されること_jun")
  void testMyPageExistUserThree() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("jun@example.com", "pa55w0rd!");

    myPage.getEmail().shouldHave(exactText("jun@example.com"));
    myPage.getUsername().shouldHave(exactText("林潤"));
    myPage.getRank().shouldHave(exactText("プレミアム会員"));
    myPage.getAddress().shouldHave(exactText("大阪府大阪市北区梅田"));
    myPage.getTel().shouldHave(exactText("01212341234"));
    myPage.getGender().shouldHave(exactText("その他"));
    myPage.getBirthday().shouldHave(exactText("1988年12月17日"));
    myPage.getNotification().shouldHave(exactText("受け取らない"));
  }

  @Test
  @Order(4)
  @DisplayName("定義済みユーザの情報が表示されること_yoshiki")
  void testMyPageExistUserFour() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("yoshiki@example.com", "pass-pass");

    myPage.getEmail().shouldHave(exactText("yoshiki@example.com"));
    myPage.getUsername().shouldHave(exactText("木村良樹"));
    myPage.getRank().shouldHave(exactText("一般会員"));
    myPage.getAddress().shouldHave(exactText("未登録"));
    myPage.getTel().shouldHave(exactText("01298765432"));
    myPage.getGender().shouldHave(exactText("未登録"));
    myPage.getBirthday().shouldHave(exactText("1992年8月31日"));
    myPage.getNotification().shouldHave(exactText("受け取る"));
  }

  @Test
  @Order(5)
  @DisplayName("新規登録したユーザの情報が表示されること")
  void testMyPageNewUser() {
    var topPage = open("/ja/", TopPage.class);

    var signupPage = topPage.goToSignupPage();
    signupPage.setEmail("new-user@example.com");
    signupPage.setPassword("11111111");
    signupPage.setPasswordConfirmation("11111111");
    signupPage.setUsername("田中花子");
    signupPage.setRank(Rank.一般会員);
    signupPage.setAddress("神奈川県横浜市港区");
    signupPage.setTel("09876543211");
    signupPage.setGender("女性");
    signupPage.setBirthday(LocalDate.parse("2000-01-01"));
    signupPage.setNotification(false);
    var myPage = signupPage.submit();

    myPage.getEmail().shouldHave(exactText("new-user@example.com"));
    myPage.getUsername().shouldHave(exactText("田中花子"));
    myPage.getRank().shouldHave(exactText("一般会員"));
    myPage.getAddress().shouldHave(exactText("神奈川県横浜市港区"));
    myPage.getTel().shouldHave(exactText("09876543211"));
    myPage.getGender().shouldHave(exactText("女性"));
    myPage.getBirthday().shouldHave(exactText("2000年1月1日"));
    myPage.getNotification().shouldHave(exactText("受け取らない"));
  }

  @Test
  @Order(6)
  @DisplayName("アイコン設定で画像以外のファイルはエラーとなること")
  void testIconNotImage() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");

    var iconPage = myPage.goToIconPage();
    iconPage.setIcon("dummy.txt");

    iconPage.getIconMessage().shouldHave(exactText("画像ファイルを選択してください。"));
  }

  @Test
  @Order(7)
  @DisplayName("アイコン設定で10KBを越えるファイルはエラーとなること")
  void testIconOverSize() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");

    var iconPage = myPage.goToIconPage();
    iconPage.setIcon("240x240_12.png");

    iconPage.getIconMessage().shouldHave(exactText("ファイルサイズは10KB以下にしてください。"));
  }

  @Test
  @Order(8)
  @DisplayName("設定したアイコンがマイページに表示されること")
  void testIconSuccess() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");

    var iconPage = myPage.goToIconPage();
    iconPage.setIcon("240x240_01.png");
    iconPage.setZoom(80);
    iconPage.setColor("#000000");
    iconPage.submit();

    myPage.getIconImage().should(exist);
    myPage.getIconImage().shouldHave(attribute("width", "70"));
    myPage.getIconImage().shouldHave(cssValue("backgroundColor", "rgba(0, 0, 0, 1)"));
  }

  @Test
  @Order(9)
  @DisplayName("新規登録したユーザが削除できること")
  void testDeleteUser() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("new-user@example.com", "11111111");

    myPage.deleteUser();
    confirm("退会すると全ての情報が削除されます。\nよろしいですか？");
    confirm("退会処理を完了しました。ご利用ありがとうございました。");
    assertTrue(url().contains("index.html"));
  }

}
