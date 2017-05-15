package train.sel.java.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolay on 15.05.2017.
 */
public class UserReg {
    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void UseerRegTest() {
        //локаторы
        By firstname = By.xpath("//input[@name='firstname']");
        By lastname = By.xpath("//input[@name='lastname']");
        By address = By.xpath("//input[@name='address1']");
        By postcode = By.xpath("//input[@name='postcode']");
        By city = By.xpath("//input[@name='city']");
        By countryList = By.xpath("//span[@class='select2-selection__arrow']");
        By country = By.xpath("//li[contains(text(),'United States')]");
        By email = By.xpath("//input[@name='email']");
        By phone = By.xpath("//input[@name='phone']");
        By password = By.xpath("//input[@name='password']");
        By confirmed_password = By.xpath("//input[@name='confirmed_password']");
        By create_account = By.xpath("//button[@name='create_account']");
        By login = By.xpath("//button[@name='login']");
        String pass = "password12";
        String logoutPage = "http://localhost/litecart/en/logout";

        //заполнение полей для регистрации
        driver.get("http://localhost/litecart/en/create_account");
        driver.findElement(firstname).sendKeys("Ivan");
        driver.findElement(lastname).sendKeys("Ivanov");
        driver.findElement(address).sendKeys("Lizukova");
        driver.findElement(postcode).sendKeys("35005");
        driver.findElement(city).sendKeys("Voronezh");
        driver.findElement(countryList).click();
        driver.findElement(country).click();
        //int rand = Math.abs(new Random().nextInt());
        //String mail = "test"+ rand +"@test.com";
        String mail = "mail"+ System.currentTimeMillis() +"@test.com";
        driver.findElement(email).sendKeys(mail);
        driver.findElement(phone).sendKeys("+79123456789");
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmed_password).sendKeys(pass);
        driver.findElement(create_account).click();

        //логаут
        driver.get(logoutPage);

        //логин
        driver.findElement(email).sendKeys(mail);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(login).click();

        //логаут
        driver.get(logoutPage);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
