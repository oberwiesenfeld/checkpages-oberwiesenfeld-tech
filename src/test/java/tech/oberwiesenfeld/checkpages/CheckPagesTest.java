package tech.oberwiesenfeld.checkpages;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;
import static java.util.Locale.GERMAN;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckPagesTest {

  private static final boolean DOCKER_COMPOSE = true;
  private static final String HTTP_SELENIUM_4444_WD_HUB = "http://selenium:4444/wd/hub";
  private static final String HTTP_LOCALHOST_4444_WD_HUB = "http://localhost:4444/wd/hub";
  private static final String IN_MUENCHEN_AM_OBERWIESENFELD = "https://oberwiesenfeld.tech/2018/03/09/in-muenchen-am-oberwiesenfeld/";
  private static WebDriver remoteWebDriver;

  @BeforeAll
  static void setup() throws MalformedURLException, InterruptedException {
    String urlToSeleniumServer;
    if (DOCKER_COMPOSE) {
      urlToSeleniumServer = HTTP_SELENIUM_4444_WD_HUB;
    }
    else {
      urlToSeleniumServer = HTTP_LOCALHOST_4444_WD_HUB;
    }
    System.out.println("Connecting to Selenium Server: " + urlToSeleniumServer);
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--ignore-certificate-errors");
    remoteWebDriver = new RemoteWebDriver(new URL(urlToSeleniumServer), chromeOptions);
    remoteWebDriver.get(IN_MUENCHEN_AM_OBERWIESENFELD);
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

  private void erstelleScreenShotUndSpeichereAlsDatei(String prefix, TakesScreenshot driver) throws IOException {
    // Take a screenshot of the current page
    File screenshot = driver.getScreenshotAs(OutputType.FILE);
    String pathname = createFileNameForScreenShot(prefix);
    FileUtils.copyFile(screenshot, new File(pathname));
    System.out.println("screenshot has been saved to " + pathname);
  }

  private String createCurrentTimeStamp() {
    String dateTimePattern = "yyyy_MM_dd_HH_mm_ss";
    DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    LocalDateTime now = LocalDateTime.now();
    return europeanDateFormatter.format(now);
  }

  private String createFileNameForScreenShot(String prefix) {
    return String.format(GERMAN, "/tmp/screen_%s_%s.png", prefix, createCurrentTimeStamp());
  }

  private static void acceptCookieOnPage(WebDriver driver) throws InterruptedException {
    WebElement w3 = driver.findElement(By.className("accept"));
    Thread.sleep(100);
    if (w3 != null && w3.isDisplayed() && w3.getSize().getHeight() > 1 && w3.getSize().getWidth() > 1) {
      w3.submit();
    }
    Thread.sleep(100);
  }
}