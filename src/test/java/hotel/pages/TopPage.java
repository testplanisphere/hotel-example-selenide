package hotel.pages;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TopPage {

  public LoginPage goToLoginPage() {
    $(byLinkText("ログイン")).click();
    return page(LoginPage.class);
  }
}
