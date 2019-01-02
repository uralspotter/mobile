package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "IOS";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub/";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver = this.getAppiumDriverByPlatform(capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "C:\\Users\\ural\\Desktop\\ТУСУР\\software testing\\Автоматизация моб тест\\Листинги с лекции\\javaAppiumAutomation\\apks\\org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE 11.3");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "/Users/qa/Desktop/javaAppiumAutomation/apks/wikipedia.app");
        } else {
            throw new Exception("Cannot find get run from evn variable. Platform version " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getAppiumDriverByPlatform(DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");

        if(platform.equals(PLATFORM_ANDROID)) {
            return new AndroidDriver(new URL(AppiumUrl), capabilities);
        } else if(platform.equals(PLATFORM_IOS)) {
            return new IOSDriver(new URL(AppiumUrl), capabilities);
        } else {
            throw new Exception("Cannot find driver from evn variable. Platform version " + platform);
        }
    }
}