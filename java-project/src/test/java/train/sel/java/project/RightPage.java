package train.sel.java.project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolay on 13.05.2017.
 */
public class RightPage {
    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void RightPageTest() {
        By firstProduct = By.xpath("//div[@id='box-campaigns']//li/a[@class='link']");
        //By firstProductPagePrices = By.xpath("//div[@class='price-wrapper']");
        By firstProductPagePrices = By.xpath("//div[@id='box-product']");

        //логинемся
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //заходим на главную страницу с товарами
        driver.get("http://localhost/litecart/");

        //получаем 1 элемент и сохраняем в переменные необходимые значения
        WebElement currentProduct = driver.findElement(firstProduct);
        String hrefAttr = currentProduct.getAttribute("href");
        String titleAttr = currentProduct.getAttribute("title");

        String regularPrice = currentProduct.findElement(By.xpath(".//div/s")).getText();
        String regularPriceColor = currentProduct.findElement(By.xpath(".//div/s")).getCssValue("color");
        String regularPriceFontSize = currentProduct.findElement(By.xpath(".//div/s")).getCssValue("font-size");
        System.out.println("--- Main page ---");
        System.out.println("regularPriceColor = "+regularPriceColor);
        System.out.println("regularPriceFontSize = "+regularPriceFontSize);

        String campaignPrice = currentProduct.findElement(By.xpath(".//div/strong")).getText();
        String campaignPriceColor = currentProduct.findElement(By.xpath(".//div/strong")).getCssValue("color");
        String campaignPriceFontSize = currentProduct.findElement(By.xpath(".//div/strong")).getCssValue("font-size");
        System.out.println("campaignPriceColor = "+campaignPriceColor);
        System.out.println("campaignPriceFontSize = "+campaignPriceFontSize);

        currentProduct.click();

        //переходим на страницу товара и сохраняем в переменные необходимые значения
        WebElement prices = driver.findElement(firstProductPagePrices);
        String title = prices.findElement(By.xpath(".//h1")).getText();

        String regularPrice1 = prices.findElement(By.xpath(".//div/s")).getText();
        String regularPriceColor1 = prices.findElement(By.xpath(".//div/s")).getCssValue("color");
        String regularPriceFontSize1 = prices.findElement(By.xpath(".//div/s")).getCssValue("font-size");
        System.out.println("--- Product page ---");
        System.out.println("regularPriceColor1 = "+regularPriceColor1);
        System.out.println("regularPriceFontSize1 = "+regularPriceFontSize1);

        String campaignPrice1 = prices.findElement(By.xpath(".//div/strong")).getText();
        String campaignPriceColor1 = prices.findElement(By.xpath(".//div/strong")).getCssValue("color");
        String campaignPriceFontSize1 = prices.findElement(By.xpath(".//div/strong")).getCssValue("font-size");
        System.out.println("campaignPriceColor1 = "+campaignPriceColor1);
        System.out.println("campaignPriceFontSize1 = "+campaignPriceFontSize1);

        //проверяем, что открывается страница правильного товара
        //с правильным названием и ценами
        Assert.assertEquals(hrefAttr, driver.getCurrentUrl());
        Assert.assertEquals(titleAttr, title);
        //помимо проверки значений цен не явно проверяется зачеркнутость цены
        //т.к. сразу берем цены из тегов "s" и "strong"
        Assert.assertEquals(regularPrice, regularPrice1);
        Assert.assertEquals(campaignPrice, campaignPrice1);

        //проверяем цвета и размер шрифта цен
        Assert.assertEquals("rgba(119, 119, 119, 1)",regularPriceColor);
        Assert.assertEquals("14.4px",regularPriceFontSize);
        Assert.assertEquals("rgba(204, 0, 0, 1)",campaignPriceColor);
        Assert.assertEquals("18px",campaignPriceFontSize);

        Assert.assertEquals("rgba(102, 102, 102, 1)",regularPriceColor1);
        Assert.assertEquals("16px",regularPriceFontSize1);
        Assert.assertEquals("rgba(204, 0, 0, 1)",campaignPriceColor1);
        Assert.assertEquals("22px",campaignPriceFontSize1);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
