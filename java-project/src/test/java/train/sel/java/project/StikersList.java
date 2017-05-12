package train.sel.java.project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolay on 11.05.2017.
 */
public class StikersList {
    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Test(){
        driver.get("http://localhost/litecart/");
        // собираем список уточек из всех категорий на главной
        List<WebElement> productsCard = driver.
                findElements(By.xpath(".//*[@id='box-most-popular']//li |" +
                        " .//*[@id='box-campaigns']//li |" +
                        " .//*[@id='box-latest-products']//li"));

        // выводим количество собранных уточек
        int cardsCount = productsCard.size();
        System.out.println("Cards Count: "+cardsCount);

        // пробегаем в цикле по уточкам и собираем список стикеров с каждой уточки
        for (int i=0;i<cardsCount;i++) {
            // собираем список стикеров с каждой уточки
            List<WebElement> stickers = productsCard.get(i).findElements(By.xpath("./a/div/div"));
            // выводим количество собранных стикеров
            int stikcersCount = stickers.size();
            System.out.println("Stickers Count: "+stickers.size());

            // выводим на экран текст каждого стикера
            for (int j=0;j<stikcersCount;j++) {
                System.out.println("-- " + (j+1) + " sticker name: " + stickers.get(j).getText());
            }
            // роняем тест, если стикеров > 2
            //Assert.assertEquals(1,stikcersCount);
            Assert.assertTrue(stikcersCount < 2);
        }

    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
