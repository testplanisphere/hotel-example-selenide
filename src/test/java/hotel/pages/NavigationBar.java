package hotel.pages;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public interface NavigationBar {

  default LoginPage goToLoginPage() {
    $(byLinkText("ログイン")).click();
    return page(LoginPage.class);
  }

  default SignupPage goToSignupPage() {
    $(byLinkText("会員登録")).click();
    return page(SignupPage.class);
  }

  default PlansPage goToPlansPage() {
    $(byLinkText("宿泊予約")).click();
    return page(PlansPage.class);
  }

}
