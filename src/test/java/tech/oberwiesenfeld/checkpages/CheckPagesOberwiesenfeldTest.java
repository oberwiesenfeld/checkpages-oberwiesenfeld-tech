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
public class CheckPagesOberwiesenfeldTest extends CheckPagesTestHelper {

  private static final String IN_MUENCHEN_AM_OBERWIESENFELD = "https://oberwiesenfeld.tech/2018/03/09/in-muenchen-am-oberwiesenfeld/";

  @BeforeAll
  static void setup() throws MalformedURLException, InterruptedException {
    setupRemoteDriver(IN_MUENCHEN_AM_OBERWIESENFELD);
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
  void searchForEngineName() throws InterruptedException, IOException {
    remoteWebDriver.get(IN_MUENCHEN_AM_OBERWIESENFELD);
    sleep(1000);
    WebElement searchBox = remoteWebDriver.findElement(By.name("s"));
    searchBox.sendKeys("m2b15");
    searchBox.submit();
    sleep(5000);  // Let the user actually see something!
    erstelleScreenShotUndSpeichereAlsDatei("suche_m2b15", (TakesScreenshot) remoteWebDriver);
  }

  @Test
  void searchAndViewArticle() throws InterruptedException, IOException {
    remoteWebDriver.get(IN_MUENCHEN_AM_OBERWIESENFELD);
    sleep(1000);  // Let the user actually see something!
    WebElement searchBox = remoteWebDriver.findElement(By.name("s"));
    searchBox.sendKeys("m2b15");
    searchBox.submit();
    sleep(1000);  // Let the user actually see something!
    WebElement artikel = remoteWebDriver.findElement(By.linkText("In München am Oberwiesenfeld"));
    if (!artikel.isDisplayed()) {
      fail("Link nicht gefunden");
    }
    System.out.println("" + artikel.toString());
    sleep(1000);
    artikel.click();
    erstelleScreenShotUndSpeichereAlsDatei("click_artikel", (TakesScreenshot) remoteWebDriver);
    sleep(2000);  // Let the user actually see something!
  }
}