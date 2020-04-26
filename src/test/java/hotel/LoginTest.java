package hotel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.junit5.BrowserStrategyExtension;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import hotel.pages.TopPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BrowserStrategyExtension.class, ScreenShooterExtension.class})
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ログイン")
class LoginTest {

  @AfterEach
  void tearDown() {
    clearBrowserCookies();
  }

  @Test
  @Order(1)
  @DisplayName("定義済みユーザでログインができること")
  void testLoginSuccess() {
    var topPage = open("/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("ichiro@example.com", "password");

    myPage.getHeader().shouldHave(exactText("マイページ"));
  }

  @Test
  @Order(2)
  @DisplayName("未入力でエラーとなること")
  void testLoginFailBlank() {
    var topPage = open("/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    loginPage.doLogin("", "");

    loginPage.getEmailMessage().shouldHave(exactText("このフィールドを入力してください。"));
    loginPage.getPasswordMessage().shouldHave(exactText("このフィールドを入力してください。"));
  }

  @Test
  @Order(3)
  @DisplayName("未登録のユーザでエラーとなること")
  void testLoginFailUnregister() {
    var topPage = open("/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    loginPage.doLogin("error@example.com", "error");

    loginPage.getEmailMessage().shouldHave(exactText("メールアドレスまたはパスワードが違います。"));
    loginPage.getPasswordMessage().shouldHave(exactText("メールアドレスまたはパスワードが違います。"));
  }

}
