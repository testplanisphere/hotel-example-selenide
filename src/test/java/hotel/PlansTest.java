package hotel;

import static com.codeborne.selenide.CollectionCondition.*;
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
@DisplayName("プラン一覧")
class PlansTest {

  @AfterEach
  void tearDown() {
    clearBrowserCookies();
  }

  @Test
  @Order(1)
  @DisplayName("未ログイン状態でプラン一覧が表示されること")
  void testPlanListNotLogin() {
    var topPage = open("/ja/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    var plans = plansPage.getPlans();

    plans.shouldHave(size(7));
    plans.get(0).shouldHave(exactText("お得な特典付きプラン"));
    plans.get(1).shouldHave(exactText("素泊まり"));
    plans.get(2).shouldHave(exactText("出張ビジネスプラン"));
    plans.get(3).shouldHave(exactText("エステ・マッサージプラン"));
    plans.get(4).shouldHave(exactText("貸し切り露天風呂プラン"));
    plans.get(5).shouldHave(exactText("カップル限定プラン"));
    plans.get(6).shouldHave(exactText("テーマパーク優待プラン"));
  }

  @Test
  @Order(2)
  @DisplayName("一般会員でログイン状態でプラン一覧が表示されること")
  void testPlanListLoginNormal() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("sakura@example.com", "pass1234");
    var plansPage = myPage.goToPlansPage();
    var plans = plansPage.getPlans();

    plans.shouldHave(size(9));
    plans.get(0).shouldHave(exactText("お得な特典付きプラン"));
    plans.get(1).shouldHave(exactText("ディナー付きプラン"));
    plans.get(2).shouldHave(exactText("お得なプラン"));
    plans.get(3).shouldHave(exactText("素泊まり"));
    plans.get(4).shouldHave(exactText("出張ビジネスプラン"));
    plans.get(5).shouldHave(exactText("エステ・マッサージプラン"));
    plans.get(6).shouldHave(exactText("貸し切り露天風呂プラン"));
    plans.get(7).shouldHave(exactText("カップル限定プラン"));
    plans.get(8).shouldHave(exactText("テーマパーク優待プラン"));
  }

  @Test
  @Order(3)
  @DisplayName("プレミアム会員でログイン状態でプラン一覧が表示されること")
  void testPlanListLoginPremium() {
    var topPage = open("/ja/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("ichiro@example.com", "password");
    var plansPage = myPage.goToPlansPage();
    var plans = plansPage.getPlans();

    plans.shouldHave(size(10));
    plans.get(0).shouldHave(exactText("お得な特典付きプラン"));
    plans.get(1).shouldHave(exactText("プレミアムプラン"));
    plans.get(2).shouldHave(exactText("ディナー付きプラン"));
    plans.get(3).shouldHave(exactText("お得なプラン"));
    plans.get(4).shouldHave(exactText("素泊まり"));
    plans.get(5).shouldHave(exactText("出張ビジネスプラン"));
    plans.get(6).shouldHave(exactText("エステ・マッサージプラン"));
    plans.get(7).shouldHave(exactText("貸し切り露天風呂プラン"));
    plans.get(8).shouldHave(exactText("カップル限定プラン"));
    plans.get(9).shouldHave(exactText("テーマパーク優待プラン"));
  }

}
