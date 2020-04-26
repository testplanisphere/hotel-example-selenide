package hotel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.DayOfWeek.*;
import static org.junit.jupiter.api.Assertions.*;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.BrowserStrategyExtension;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import hotel.pages.ReservePage;
import hotel.pages.RoomPage;
import hotel.pages.TopPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ExtendWith({BrowserStrategyExtension.class, ScreenShooterExtension.class})
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("宿泊予約")
class ReserveTest {

  private static final DateTimeFormatter SHORT_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  private static final DateTimeFormatter LONG_FORMATTER = DateTimeFormatter.ofPattern("yyyy年M月d日");

  @AfterEach
  void tearDown() {
    if (WebDriverRunner.getWebDriver().getWindowHandles().size() > 1) {
      closeWindow();
    }
    switchTo().window("宿泊プラン一覧 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    clearBrowserCookies();
  }

  @Test
  @Order(1)
  @DisplayName("画面表示時の初期値が設定されていること_未ログイン")
  void testPageInitValue() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    var tomorrow = SHORT_FORMATTER.format(LocalDate.now().plusDays(1));

    reservePage.getPlanName().shouldHave(exactText("お得な特典付きプラン"));
    reservePage.getReserveDate().shouldHave(exactValue(tomorrow));
    reservePage.getReserveTerm().shouldHave(exactValue("1"));
    reservePage.getHeadCount().shouldHave(exactValue("1"));
    reservePage.getEmail().shouldBe(hidden);
    reservePage.getTel().shouldBe(hidden);

    reservePage.setContact("メールでのご連絡");
    reservePage.getEmail().shouldBe(visible);
    reservePage.getTel().shouldBe(hidden);
    reservePage.getEmail().shouldBe(empty);

    reservePage.setContact("電話でのご連絡");
    reservePage.getEmail().shouldBe(hidden);
    reservePage.getTel().shouldBe(visible);
    reservePage.getTel().shouldBe(empty);

    switchTo().frame("room");
    var roomPage = page(RoomPage.class);
    roomPage.getHeader().shouldHave(exactText("スタンダードツイン"));
    switchTo().defaultContent();
  }

  @Test
  @Order(2)
  @DisplayName("画面表示時の初期値が設定されていること_ログイン済み")
  void testPageInitValueLogin() {
    var topPage = open("/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("ichiro@example.com", "password");
    var plansPage = myPage.goToPlansPage();
    plansPage.openPlanByTitle("プレミアムプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    var tomorrow = SHORT_FORMATTER.format(LocalDate.now().plusDays(1));

    reservePage.getPlanName().shouldHave(exactText("プレミアムプラン"));
    reservePage.getReserveDate().shouldHave(exactValue(tomorrow));
    reservePage.getReserveTerm().shouldHave(exactValue("1"));
    reservePage.getHeadCount().shouldHave(exactValue("2"));
    reservePage.getEmail().shouldBe(hidden);
    reservePage.getTel().shouldBe(hidden);

    reservePage.setContact("メールでのご連絡");
    reservePage.getEmail().shouldBe(visible);
    reservePage.getTel().shouldBe(hidden);
    reservePage.getEmail().shouldHave(exactValue("ichiro@example.com"));

    reservePage.setContact("電話でのご連絡");
    reservePage.getEmail().shouldBe(hidden);
    reservePage.getTel().shouldBe(visible);
    reservePage.getTel().shouldHave(exactValue("01234567891"));

    switchTo().frame("room");
    var roomPage = page(RoomPage.class);
    roomPage.getHeader().shouldHave(exactText("プレミアムツイン"));
    switchTo().defaultContent();
  }

  @Test
  @Order(3)
  @DisplayName("入力値が空白でエラーとなること")
  void testBlankInputOne() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    reservePage.setReserveDate("");
    reservePage.setReserveTerm("");
    reservePage.setHeadCount("");
    reservePage.setUsername("");  // move focus

    reservePage.getReserveDateMessage().shouldHave(exactText("このフィールドを入力してください。"));
    reservePage.getReserveTermMessage().shouldHave(exactText("このフィールドを入力してください。"));
    reservePage.getHeadCountMessage().shouldHave(exactText("このフィールドを入力してください。"));
  }

  @Test
  @Order(4)
  @DisplayName("不正な入力値でエラーとなること_小")
  void testInvalidInputSmall() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    var today = SHORT_FORMATTER.format(LocalDate.now());

    reservePage.setReserveDate(today);
    reservePage.setReserveTerm("0");
    reservePage.setHeadCount("0");
    reservePage.setUsername("テスト太郎");  // move focus

    reservePage.getReserveDateMessage().shouldHave(exactText("翌日以降の日付を入力してください。"));
    reservePage.getReserveTermMessage().shouldHave(exactText("1以上の値を入力してください。"));
    reservePage.getHeadCountMessage().shouldHave(exactText("1以上の値を入力してください。"));
  }

  @Test
  @Order(5)
  @DisplayName("不正な入力値でエラーとなること_大")
  void testInvalidInputBig() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    var after90 = SHORT_FORMATTER.format(LocalDate.now().plusDays(91));

    reservePage.setReserveDate(after90);
    reservePage.setReserveTerm("10");
    reservePage.setHeadCount("10");
    reservePage.setUsername("テスト太郎");  // move focus

    reservePage.getReserveDateMessage().shouldHave(exactText("3ヶ月以内の日付を入力してください。"));
    reservePage.getReserveTermMessage().shouldHave(exactText("9以下の値を入力してください。"));
    reservePage.getHeadCountMessage().shouldHave(exactText("9以下の値を入力してください。"));
  }

  @Test
  @Order(6)
  @DisplayName("不正な入力値でエラーとなること_文字列")
  void testInvalidInputOther() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    reservePage.setReserveDate("12/3//345");
    reservePage.setReserveTerm("a");
    reservePage.setHeadCount("a");
    reservePage.setUsername("テスト太郎");  // move focus

    reservePage.getReserveDateMessage().shouldHave(exactText("有効な値を入力してください。"));
    reservePage.getReserveTermMessage().shouldHave(exactText("このフィールドを入力してください。"));
    reservePage.getHeadCountMessage().shouldHave(exactText("このフィールドを入力してください。"));
  }

  @Test
  @Order(7)
  @DisplayName("不正な入力値でエラーとなること_確定時_メール選択")
  void testInvalidInputOnSubmitEmail() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    reservePage.setUsername("");
    reservePage.setContact("メールでのご連絡");
    reservePage.setEmail("");
    reservePage.submit();

    reservePage.getUsernameMessage().shouldHave(exactText("このフィールドを入力してください。"));
    reservePage.getEmailMessage().shouldHave(exactText("このフィールドを入力してください。"));
  }

  @Test
  @Order(8)
  @DisplayName("不正な入力値でエラーとなること_確定時_電話選択")
  void testInvalidInputOnSubmitTel() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    reservePage.setUsername("");
    reservePage.setContact("電話でのご連絡");
    reservePage.setTel("");
    reservePage.submit();

    reservePage.getUsernameMessage().shouldHave(exactText("このフィールドを入力してください。"));
    reservePage.getTelMessage().shouldHave(exactText("このフィールドを入力してください。"));
  }

  @Test
  @Order(9)
  @DisplayName("宿泊予約が完了すること_未ログイン_初期値")
  void testReserveSuccess() {
    var topPage = open("/", TopPage.class);
    var plansPage = topPage.goToPlansPage();
    plansPage.openPlanByTitle("お得な特典付きプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    var expectedStart = LocalDate.now().plusDays(1);
    var expectedEnd = LocalDate.now().plusDays(2);
    String expectedTotalBill;
    if (expectedStart.getDayOfWeek() == SUNDAY || expectedStart.getDayOfWeek() == SATURDAY) {
      expectedTotalBill = "合計 8,750円（税込み）";
    } else {
      expectedTotalBill = "合計 7,000円（税込み）";
    }
    final var expectedTerm = LONG_FORMATTER.format(expectedStart) + " 〜 " + LONG_FORMATTER.format(expectedEnd) + " 1泊";

    reservePage.setUsername("テスト太郎");
    reservePage.setContact("希望しない");
    var confirmPage = reservePage.submit();

    confirmPage.getTotalBill().shouldHave(exactText(expectedTotalBill));
    confirmPage.getPlanName().shouldHave(exactText("お得な特典付きプラン"));
    confirmPage.getTerm().shouldHave(exactText(expectedTerm));
    confirmPage.getHeadCount().shouldHave(exactText("1名様"));
    confirmPage.getPlans().shouldHave(exactText("なし"));
    confirmPage.getUsername().shouldHave(exactText("テスト太郎様"));
    confirmPage.getContact().shouldHave(exactText("希望しない"));
    confirmPage.getComment().shouldHave(exactText("なし"));

    confirmPage.confirm();
    confirmPage.getModalMessage().shouldHave(exactText("ご来館、心よりお待ちしております。"));
    confirmPage.close();
    assertTrue(Wait().until(ExpectedConditions.numberOfWindowsToBe(1)));
  }

  @Test
  @Order(10)
  @DisplayName("宿泊予約が完了すること_ログイン")
  void testReserveSuccess2() {
    var topPage = open("/", TopPage.class);
    var loginPage = topPage.goToLoginPage();
    var myPage = loginPage.doLogin("ichiro@example.com", "password");
    var plansPage = myPage.goToPlansPage();
    plansPage.openPlanByTitle("プレミアムプラン");
    switchTo().window("宿泊予約 | HOTEL PLANISPHERE - テスト自動化デモサイト");
    var reservePage = page(ReservePage.class);

    var expectedStart = LocalDate.now().plusDays(90);
    var expectedEnd = LocalDate.now().plusDays(92);
    String expectedTotalBill;
    if (expectedStart.getDayOfWeek() == SATURDAY) {
      expectedTotalBill = "合計 112,000円（税込み）";
    } else if (expectedStart.getDayOfWeek() == SUNDAY || expectedStart.getDayOfWeek() == FRIDAY) {
      expectedTotalBill = "合計 102,000円（税込み）";
    } else {
      expectedTotalBill = "合計 92,000円（税込み）";
    }
    final var expectedTerm = LONG_FORMATTER.format(expectedStart) + " 〜 " + LONG_FORMATTER.format(expectedEnd) + " 2泊";

    reservePage.setReserveDate(SHORT_FORMATTER.format(expectedStart));
    reservePage.setReserveTerm("2");
    reservePage.setHeadCount("4");
    reservePage.setBreakfastPlan(true);
    reservePage.setEarlyCheckInPlan(true);
    reservePage.setSightseeingPlan(false);
    reservePage.setContact("メールでのご連絡");
    reservePage.setComment("あああ\n\nいいいいいいい\nうう");
    var confirmPage = reservePage.submit();

    confirmPage.getTotalBill().shouldHave(exactText(expectedTotalBill));
    confirmPage.getPlanName().shouldHave(exactText("プレミアムプラン"));
    confirmPage.getTerm().shouldHave(exactText(expectedTerm));
    confirmPage.getHeadCount().shouldHave(exactText("4名様"));
    confirmPage.getPlans().shouldHave(text("朝食バイキング"));
    confirmPage.getPlans().shouldHave(text("昼からチェックインプラン"));
    confirmPage.getPlans().shouldNotHave(text("お得な観光プラン"));
    confirmPage.getUsername().shouldHave(exactText("山田一郎様"));
    confirmPage.getContact().shouldHave(exactText("メール：ichiro@example.com"));
    confirmPage.getComment().shouldHave(exactText("あああ\n\nいいいいいいい\nうう"));

    confirmPage.confirm();
    confirmPage.getModalMessage().shouldHave(exactText("ご来館、心よりお待ちしております。"));
    confirmPage.close();
    assertTrue(Wait().until(ExpectedConditions.numberOfWindowsToBe(1)));
  }

}
