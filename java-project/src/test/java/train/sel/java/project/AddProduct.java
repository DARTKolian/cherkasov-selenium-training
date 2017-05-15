package train.sel.java.project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolay on 15.05.2017.
 */
public class AddProduct {
    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void AddProductTest() {
        //локаторы
        String imagePath = (new File(".").getAbsolutePath()).replace(".","")
                + "src\\test\\java\\train\\sel\\java\\project\\Gravel.jpg";
        By status = By.xpath("//input[@value='1']");
        By name = By.xpath("//input[@name='name[en]']");
        By image = By.xpath("//input[@name='new_images[]']");

        By tabInformation = By.xpath("//a[@href='#tab-information']");
        By manufacturer = By.xpath("//select[@name='manufacturer_id']/option[contains(text(),'ACME')]");
        By keywords = By.xpath("//input[@name='keywords']");
        By shortDescription= By.xpath("//input[@name='short_description[en]']");
        By textarea = By.xpath("//textarea[@name='description[en]']");
        By headTitle = By.xpath("//input[@name='head_title[en]']");
        By metaDescription = By.xpath("//input[@name='meta_description[en]']");

        By tabPrices = By.xpath("//a[@href='#tab-prices']");
        By purchasePrice = By.xpath("//input[@name='purchase_price']");
        By purchasePriceCurrencyCode = By.xpath("//select[@name='purchase_price_currency_code']/option[contains(text(),'Dollars')]");

        By save = By.xpath("//button[@name='save']");

        //логинемся
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //переходим в каталог товаров и тыкаем Add new product
        driver.get("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product");
        driver.findElement(status).click();

        //заполняем вкладку General
        String nameProduct = "Gravel" + System.currentTimeMillis();
        driver.findElement(name).sendKeys(nameProduct);
        driver.findElement(image).sendKeys(imagePath);

        //заполняем вкладку Information
        driver.findElement(tabInformation).click();
        driver.findElement(manufacturer).click();
        driver.findElement(keywords).sendKeys("Gravel, Boat");
        driver.findElement(shortDescription).sendKeys("Boat of gravel");
        driver.findElement(textarea).sendKeys("Boat of gravel. Buy it");
        driver.findElement(headTitle).sendKeys("Boat of gravel");
        driver.findElement(metaDescription).sendKeys("Gravel, Boat");

        //заполняем вкладку Prices
        driver.findElement(tabPrices).click();
        driver.findElement(purchasePrice).clear();
        driver.findElement(purchasePrice).sendKeys("100");
        driver.findElement(purchasePriceCurrencyCode).click();

        //сохраняем новый товар
        driver.findElement(save).click();

        //переходим в каталог
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");

        //объявляем локатор только что добавленного товара
        String splitAddedProductLocator = "//form[@name='catalog_form']//tr/td[3]/a[contains(text(),'"+ nameProduct +"')]";
        By addedProduct = By.xpath(splitAddedProductLocator);

        //проверяем, что имя созданного товара соответствует имени товара в каталоге
        Assert.assertEquals(nameProduct,driver.findElement(addedProduct).getText());
    }
    
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
