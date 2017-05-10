package train.sel.java.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolay on 10.05.2017.
 */
public class AdminList {
    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void Test() {
        //заходим как администратор
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // собираем список меню, выводим на экран количество меню
        List<WebElement> menuItems = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        int menuItemSize = menuItems.size();
        System.out.println("Items Count: "+menuItems.size());

        // проходим в цикле по каждому элементу меню
        for (int i=1;i<=menuItemSize;i++) {
            // получаем i-тый элемент меню
            WebElement item = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li["+i+"]"));
            System.out.println("Item: "+item.getText());
            item.click();
            // проверяем наличие заголовка на странице после клика по меню
            WebElement h1 = driver.findElement(By.xpath("//*[@id='content']/h1"));
            System.out.println(" H1 = "+h1.getText());

            // вновь получаем i-тый элемент меню, т.к. старый "протух" после клика
            item = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li["+i+"]"));

            // собираем список подменю, узнаем количество, если > 0
            List<WebElement> subItems = item.findElements(By.xpath(".//ul/li"));
            int menuSubItemSize = subItems.size();
            if(subItems.size()>0) {
                for (int j=1;j<=menuSubItemSize;j++) {
                    // получаем i-тый элемент подменюменю, выводим на экран
                    WebElement subitem = item.findElement(By.xpath(".//ul/li["+j+"]"));
                    System.out.println(" --- SubItem: "+subitem.getText());
                    subitem.click();
                    // проверяем наличие заголовка на странице после клика по подменю
                    h1 = driver.findElement(By.xpath("//*[@id='content']/h1"));
                    System.out.println(" ---- H1 = "+h1.getText());
                    // вновь получаем i-тый элемент меню, т.к. старый "протух" после клика
                    item = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li["+i+"]"));
                }
            }

        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}