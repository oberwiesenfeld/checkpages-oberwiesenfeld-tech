package tech.oberwiesenfeld.checkpages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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

import static java.lang.String.format;
import static java.util.Locale.GERMAN;
import static org.openqa.selenium.OutputType.FILE;

class CheckPagesTestHelper {

  private static final boolean DOCKER_COMPOSE = true;
  private static final String HTTP_SELENIUM_4444_WD_HUB = "http://selenium:4444/wd/hub";
  //docker run -d -p 4444:4444 --shm-size=2g selenium/standalone-chrome
  private static final String HTTP_LOCALHOST_4444_WD_HUB = "http://localhost:4444/wd/hub";
  static WebDriver remoteWebDriver;

  static void setupRemoteDriver(String url) throws MalformedURLException {
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
    remoteWebDriver.get(url);
  }

  static void acceptCookieOnPage(WebDriver driver) throws InterruptedException {
    WebElement w3 = driver.findElement(By.className("accept"));
    Thread.sleep(100);
    if (w3 != null && w3.isDisplayed() && w3.getSize().getHeight() > 1 && w3.getSize().getWidth() > 1) {
      w3.submit();
    }
    Thread.sleep(100);
  }

  void erstelleScreenShotUndSpeichereAlsDatei(String prefix, TakesScreenshot driver) throws IOException {
    // Take a screenshot of the current page
    File screenshot = driver.getScreenshotAs(FILE);
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
    return format(GERMAN, "/tmp/screen_%s_%s.png", prefix, createCurrentTimeStamp());
  }
}
