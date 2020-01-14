package ru.lanit.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import ru.lanit.util.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hooks {

    protected static WebDriver driver;
    protected static String baseURL;
    public static Properties properties = TestProperties.getInstance().getProperties();

    private static String oldWindowHandle;

    public static WebDriver getDriver() {
        return driver;
    }

    public Hooks() {
        PageFactory.initElements(getDriver(), this);
    }

    @Before
    public static void startScenario() throws Exception {
        switch (properties.getProperty("browser")) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
        }

        baseURL = properties.getProperty("app.url");
        System.out.println(baseURL);

        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("implicityWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("pageLoad")), TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get(baseURL + "/");

        oldWindowHandle = driver.getWindowHandle();
    }

    @After
    public static void tearDown() throws Exception {
        driver.quit();
    }

    public static String getOldWindowHandle() {
        return oldWindowHandle;
    }

    public static void setOldWindowHandle(String currentWindowHandle) {
        oldWindowHandle = currentWindowHandle;
    }
}
