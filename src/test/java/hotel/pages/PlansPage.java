package hotel.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;

public class PlansPage {

  public ElementsCollection getPlans() {
    $("#plan-list > div[role=\"status\"]").waitUntil(disappears, 10000);
    return $$(".card-title");
  }

}
