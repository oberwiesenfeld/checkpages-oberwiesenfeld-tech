# CheckPages [![Build Status](https://travis-ci.org/oberwiesenfeld/checkpages-oberwiesenfeld-tech.png)](https://travis-ci.org/oberwiesenfeld/checkpages-oberwiesenfeld-tech)

DockerCompose project which runs two containers.

* a headless chrome in a selenium server.
* a maven java junit project which runs selenium tests

## CheckPageTest 

The java unit test file `CheckPagesTest` holds the selenium tests against the
webpage https://oberwiesenfeld.tech

## Log of a sample run

```
docker-compose build && docker-compose up
selenium uses an image, skipping
Building checkpages
Step 1/4 : FROM maven:3.6.0-jdk-8-slim
 ---> c203892a8124
Step 2/4 : COPY src /usr/src/app/src
 ---> Using cache
 ---> c78da375c551
Step 3/4 : COPY pom.xml /usr/src/app
 ---> 9195bcf3865d
Step 4/4 : CMD mvn -f /usr/src/app/pom.xml clean test
 ---> Running in 14eb90ce12d9
Removing intermediate container 14eb90ce12d9
 ---> d15431a08a7c
Successfully built d15431a08a7c
Successfully tagged checkpages:0.0.1
Starting checkpages_selenium_1 ... done
Recreating checkpages_checkpages_1 ... done
Attaching to checkpages_selenium_1, checkpages_checkpages_1
selenium_1    | 2019-03-30 22:12:22,133 INFO Included extra file "/etc/supervisor/conf.d/selenium.conf" during parsing
selenium_1    | 2019-03-30 22:12:22,134 INFO supervisord started with pid 8
selenium_1    | 2019-03-30 22:12:23,137 INFO spawned: 'xvfb' with pid 11
selenium_1    | 2019-03-30 22:12:23,139 INFO spawned: 'selenium-standalone' with pid 12
selenium_1    | 22:12:23.327 INFO [GridLauncherV3.parse] - Selenium server version: 3.141.59, revision: e82be7d358
selenium_1    | 2019-03-30 22:12:23,328 INFO success: xvfb entered RUNNING state, process has stayed up for > than 0 seconds (startsecs)
selenium_1    | 2019-03-30 22:12:23,328 INFO success: selenium-standalone entered RUNNING state, process has stayed up for > than 0 seconds (startsecs)
selenium_1    | 22:12:23.397 INFO [GridLauncherV3.lambda$buildLaunchers$3] - Launching a standalone Selenium Server on port 4444
selenium_1    | 2019-03-30 22:12:23.446:INFO::main: Logging initialized @298ms to org.seleniumhq.jetty9.util.log.StdErrLog
selenium_1    | 22:12:23.642 INFO [WebDriverServlet.<init>] - Initialising WebDriverServlet
selenium_1    | 22:12:23.716 INFO [SeleniumServer.boot] - Selenium Server is up and running on port 4444
checkpages_1  | [INFO] Scanning for projects...
checkpages_1  | [INFO] 
checkpages_1  | [INFO] -------------< tech.oberwiesenfeld.checkpages:checkpages >--------------
checkpages_1  | [INFO] Building checkpages 1.0-SNAPSHOT
checkpages_1  | [INFO] --------------------------------[ jar ]---------------------------------
checkpages_1  | [INFO] 
checkpages_1  | [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ checkpages ---
checkpages_1  | [INFO] 
checkpages_1  | [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ checkpages ---
checkpages_1  | [INFO] Using 'UTF-8' encoding to copy filtered resources.
checkpages_1  | [INFO] skip non existing resourceDirectory /usr/src/app/src/main/resources
checkpages_1  | [INFO] 
checkpages_1  | [INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ checkpages ---
checkpages_1  | [INFO] Changes detected - recompiling the module!
checkpages_1  | [INFO] Compiling 1 source file to /usr/src/app/target/classes
checkpages_1  | [INFO] 
checkpages_1  | [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ checkpages ---
checkpages_1  | [INFO] Using 'UTF-8' encoding to copy filtered resources.
checkpages_1  | [INFO] skip non existing resourceDirectory /usr/src/app/src/test/resources
checkpages_1  | [INFO] 
checkpages_1  | [INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ checkpages ---
checkpages_1  | [INFO] Changes detected - recompiling the module!
checkpages_1  | [INFO] Compiling 1 source file to /usr/src/app/target/test-classes
checkpages_1  | [INFO] 
checkpages_1  | [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ checkpages ---
checkpages_1  | [INFO] Surefire report directory: /usr/src/app/target/surefire-reports
checkpages_1  | 
checkpages_1  | -------------------------------------------------------
checkpages_1  |  T E S T S
checkpages_1  | -------------------------------------------------------
checkpages_1  | Running tech.oberwiesenfeld.checkpages.CheckPagesTest
checkpages_1  | Connecting to Selenium Server: http://selenium:4444/wd/hub
selenium_1    | 22:12:30.861 INFO [ActiveSessionFactory.apply] - Capabilities are: {
selenium_1    |   "browserName": "chrome",
selenium_1    |   "goog:chromeOptions": {
selenium_1    |     "args": [
selenium_1    |       "--ignore-certificate-errors"
selenium_1    |     ],
selenium_1    |     "extensions": [
selenium_1    |     ]
selenium_1    |   }
selenium_1    | }
selenium_1    | 22:12:30.862 INFO [ActiveSessionFactory.lambda$apply$11] - Matched factory org.openqa.selenium.grid.session.remote.ServicedSession$Factory (provider: org.openqa.selenium.chrome.ChromeDriverService)
selenium_1    | Starting ChromeDriver 73.0.3683.68 (47787ec04b6e38e22703e856e101e840b65afe72) on port 27836
selenium_1    | Only local connections are allowed.
selenium_1    | Please protect ports used by ChromeDriver and related test frameworks to prevent access by malicious code.
selenium_1    | [1553983950.877][SEVERE]: bind() failed: Cannot assign requested address (99)
selenium_1    | 22:12:31.469 INFO [ProtocolHandshake.createSession] - Detected dialect: OSS
selenium_1    | 22:12:31.677 INFO [RemoteSession$Factory.lambda$performHandshake$0] - Started new session 2a5a951a328e288f4416e9f13265362d (org.openqa.selenium.chrome.ChromeDriverService)
checkpages_1  | Mar 30, 2019 10:12:31 PM org.openqa.selenium.remote.ProtocolHandshake createSession
checkpages_1  | INFO: Detected dialect: OSS
checkpages_1  | screenshot has been saved to /tmp/screen_suche_m2b15_2019_03_30_22_12_44.png
checkpages_1  | [[RemoteWebDriver: chrome on LINUX (2a5a951a328e288f4416e9f13265362d)] -> link text: In München am Oberwiesenfeld]
checkpages_1  | screenshot has been saved to /tmp/screen_click_artikel_2019_03_30_22_12_49.png
checkpages_1  | Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 24.803 sec
selenium_1    | 22:12:55.023 INFO [ActiveSessions$1.onStop] - Removing session 2a5a951a328e288f4416e9f13265362d (org.openqa.selenium.chrome.ChromeDriverService)
checkpages_1  | 
checkpages_1  | Results :
checkpages_1  | 
checkpages_1  | Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
checkpages_1  | 
checkpages_1  | [INFO] ------------------------------------------------------------------------
checkpages_1  | [INFO] BUILD SUCCESS
checkpages_1  | [INFO] ------------------------------------------------------------------------
checkpages_1  | [INFO] Total time:  31.291 s
checkpages_1  | [INFO] Finished at: 2019-03-30T22:12:55Z
checkpages_1  | [INFO] ------------------------------------------------------------------------
checkpages_checkpages_1 exited with code 0 
``` 
