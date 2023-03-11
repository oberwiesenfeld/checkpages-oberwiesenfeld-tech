package tech.oberwiesenfeld.checkpages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.MalformedURLException;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckPagesBmwKraftradTypeR32Test extends CheckPagesTestHelper {

  private static final String BMW_KRAFTRAD_TYPE_R_32 = "https://oberwiesenfeld.tech/2022/11/28/das-500-ccm-b-m-w-kraftrad-type-r-32/";

  @BeforeAll
  static void setup() throws MalformedURLException, InterruptedException {
    setupRemoteDriver(BMW_KRAFTRAD_TYPE_R_32);
    sleep(1000);
    acceptCookieOnPage(remoteWebDriver);
  }

  @AfterAll
  static void cleanup() {
    if (remoteWebDriver != null) {
      remoteWebDriver.quit();
    }
  }

  @Test
  void searchForReitwagen() throws InterruptedException, IOException {
    remoteWebDriver.get(BMW_KRAFTRAD_TYPE_R_32);
    sleep(1000);
    WebElement searchBox = remoteWebDriver.findElement(By.name("s"));
    searchBox.sendKeys("bmw r32");
    searchBox.submit();
    sleep(5000);
    erstelleScreenShotUndSpeichereAlsDatei("suche_bmw_r32", (TakesScreenshot) remoteWebDriver);
  }

  @Test
  void searchAndViewArticle() throws InterruptedException, IOException {
    remoteWebDriver.get(BMW_KRAFTRAD_TYPE_R_32);
    sleep(1000);  // Let the user actually see something!
    WebElement searchBox = remoteWebDriver.findElement(By.name("s"));
    searchBox.sendKeys("bmw r32");
    searchBox.submit();
    sleep(1000);
    WebElement artikel = remoteWebDriver.findElement(By.linkText("Das 500 ccm B.M.W. – Kraftrad Type R 32"));
    if (!artikel.isDisplayed()) {
      fail("Link nicht gefunden");
    }
    System.out.println(artikel);
    sleep(1000);
    artikel.click();
    erstelleScreenShotUndSpeichereAlsDatei("click_artikel", (TakesScreenshot) remoteWebDriver);
    sleep(2000);
  }
}