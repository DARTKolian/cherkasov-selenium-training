package train.sel.java.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //IE
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        //WebDriver driver = new InternetExplorerDriver(caps);
        //System.out.println(((HasCapabilities) driver).getCapabilities());

        //Google Chrome
        driver = new ChromeDriver();

        //Mozilla Firefox
        //Mozilla FIrefox new cheme
        // driver = new FirefoxDriver();

        //Mozilla FIrefox old scheme
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        //WebDriver driver = new FirefoxDriver(caps);
        //System.out.println(((HasCapabilities) driver).getCapabilities());

        //Mozilla FIrefox oldscheme ESR45
        //DesiredCapabilities caps = new DesiredCapabilities();
        //driver = new FirefoxDriver (
                //new FirefoxBinary(new File("c:\\Program Files (x86)\\Mozilla Firefox\\ESR45\\firefox.exe")),
                //new FirefoxProfile(), caps);
        //System.out.println(((HasCapabilities) driver).getCapabilities());

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void LoginTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
