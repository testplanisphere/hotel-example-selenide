package hotel.pages;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TopPage {

  public LoginPage goToLoginPage() {
    $(byLinkText("ログイン")).click();
    return page(LoginPage.class);
  }

  public SignupPage goToSignupPage() {
    $(byLinkText("会員登録")).click();
    return page(SignupPage.class);
  }
}
