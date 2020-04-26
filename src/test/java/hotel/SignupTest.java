package hotel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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

@ExtendWith({ BrowserStrategyExtension.class, ScreenShooterExtension.class })
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("会員登録")
class SignupTest {

  @AfterEach
  void tearDown() {
    clearBrowserCookies();
  }

  @Test
  @Order(1)
  @DisplayName("ユーザの新規登録ができること")
  void testSignupSuccess() {
    var topPage = open("/", TopPage.class);

    var signupPage = topPage.goToSignupPage();
    signupPage.setEmail("new-user@example.com");
    signupPage.setPassword("password");
    signupPage.setPasswordConfirmation("password");
    signupPage.setUsername("新規ユーザ１");
    signupPage.setRank(Rank.一般会員);
    signupPage.setAddress("神奈川県横浜市港区");
    signupPage.setTel("01234567891");
    signupPage.setGender("女性");
    signupPage.setBirthday(LocalDate.parse("2000-01-01"));
    signupPage.setNotification(true);
    var myPage = signupPage.submit();

    myPage.getHeader().shouldHave(exactText("マイページ"));
  }

  @Test
  @Order(2)
  @DisplayName("必須項目を未入力にするとエラーとなること")
  void testSignupErrorBlank() {
    var topPage = open("/", TopPage.class);

    var signupPage = topPage.goToSignupPage();
    signupPage.setEmail("");
    signupPage.setPassword("");
    signupPage.setPasswordConfirmation("");
    signupPage.setUsername("");
    signupPage.setRank(Rank.プレミアム会員);
    signupPage.setAddress("");
    signupPage.setTel("");
    signupPage.setGender("回答しない");
    signupPage.setBirthday(null);
    signupPage.setNotification(false);
    signupPage.submit();

    signupPage.getEmailMessage().shouldHave(exactText("このフィールドを入力してください。"));
    signupPage.getPasswordMessage().shouldHave(exactText("このフィールドを入力してください。"));
    signupPage.getPasswordConfirmationMessage().shouldHave(exactText("このフィールドを入力してください。"));
    signupPage.getUsernameMessage().shouldHave(exactText("このフィールドを入力してください。"));
    signupPage.getAddressMessage().shouldBe(empty);
    signupPage.getTelMessage().shouldBe(empty);
    signupPage.getGenderMessage().shouldBe(empty);
    signupPage.getBirthdayMessage().shouldBe(empty);
  }

  @Test
  @Order(3)
  @DisplayName("指定のフォーマット外の入力でエラーとなること")
  void testSignupErrorInvalid() {
    var topPage = open("/", TopPage.class);

    var signupPage = topPage.goToSignupPage();
    signupPage.setEmail("a");
    signupPage.setPassword("1234567");
    signupPage.setPasswordConfirmation("1");
    signupPage.setUsername("テストテスト");
    signupPage.setRank(Rank.一般会員);
    signupPage.setAddress("千葉県千葉市");
    signupPage.setTel("1234567890");
    signupPage.setGender("その他");
    signupPage.setBirthday(LocalDate.parse("2000-01-01"));
    signupPage.setNotification(true);
    signupPage.submit();

    signupPage.getEmailMessage().shouldHave(exactText("メールアドレスを入力してください。"));
    signupPage.getPasswordMessage().shouldHave(exactText("8文字以上で入力してください。"));
    signupPage.getPasswordConfirmationMessage().shouldHave(exactText("8文字以上で入力してください。"));
    signupPage.getUsernameMessage().shouldBe(empty);
    signupPage.getAddressMessage().shouldBe(empty);
    signupPage.getTelMessage().shouldHave(exactText("指定されている形式で入力してください。"));
    signupPage.getGenderMessage().shouldBe(empty);
    signupPage.getBirthdayMessage().shouldBe(empty);
  }

  @Test
  @Order(4)
  @DisplayName("登録済みのメールアドレスはエラーとなること")
  void testSignupErrorDouble() {
    var topPage = open("/", TopPage.class);

    var signupPage = topPage.goToSignupPage();
    signupPage.setEmail("new-user@example.com");
    signupPage.setPassword("password");
    signupPage.setPasswordConfirmation("password");
    signupPage.setUsername("新規ユーザ１");
    signupPage.setRank(Rank.一般会員);
    signupPage.setAddress("神奈川県横浜市港区");
    signupPage.setTel("01234567891");
    signupPage.setGender("女性");
    signupPage.setBirthday(LocalDate.parse("2000-01-01"));
    signupPage.setNotification(true);
    signupPage.submit();

    signupPage.getEmailMessage().shouldHave(exactText("このメールアドレスはすでに登録済みです。"));
  }

  @Test
  @Order(5)
  @DisplayName("入力パスワードが一致しないとエラーとなること")
  void testSignupErrorUnMatchPassword() {
    var topPage = open("/", TopPage.class);

    var signupPage = topPage.goToSignupPage();
    signupPage.setEmail("new-user@example.com");
    signupPage.setPassword("password");
    signupPage.setPasswordConfirmation("123456789");
    signupPage.setUsername("新規ユーザ１");
    signupPage.setRank(Rank.一般会員);
    signupPage.setAddress("神奈川県横浜市港区");
    signupPage.setTel("01234567891");
    signupPage.setGender("女性");
    signupPage.setBirthday(LocalDate.parse("2000-01-01"));
    signupPage.setNotification(true);
    signupPage.submit();

    signupPage.getPasswordConfirmationMessage().shouldHave(exactText("入力されたパスワードと一致しません。"));
  }

}
