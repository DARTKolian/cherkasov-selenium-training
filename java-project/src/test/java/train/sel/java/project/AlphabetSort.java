package train.sel.java.project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolay on 13.05.2017.
 */
public class AlphabetSort {
    private WebDriver driver;
    private WebDriver wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test //1я часть задания
    public void AlphabetSortTest() {
        //определяем локаторы
        String countryPage = "http://localhost/litecart/admin/?app=countries&doc=countries";
        By countries_locator = By.xpath("//form[@name='countries_form']//td[5]/a");
        By geozoneCount_locator = By.xpath("//form[@name='countries_form']//td[6]");
        By zoneSelect_locator = By.xpath("//*[@id='table-zones']//tr/td[3]");

        //логинемся
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get(countryPage);

        //получаем количество стран
        List<WebElement> countryRows = driver.findElements(countries_locator);
        int countryRowsCount = countryRows.size();
        System.out.println("Country Rows Count: " + countryRowsCount);

        // списки для несортированных и отсортированных стран
        List<String> countryList = new ArrayList<>();
        List<String> sortCountryList = new ArrayList<>();

        //заполняет список названиями стран
        //переходим от WebElement к String, заполняем списки
        for (int el = 0; el < countryRowsCount; el++) {
            countryList.add(countryRows.get(el).getText());
            sortCountryList.add(countryRows.get(el).getText());
            //  System.out.println("Add to List "+"'"+ countryList.get(el).toString() +"'");
        }

        //сортируем список
        Collections.sort(sortCountryList);
        //проверяем отсортированы ли уже зоны, если несовпадает хоть одна, то роняем тест
        Assert.assertEquals(true, equalsList(countryList, sortCountryList));

         /*-*/
        //переходим на страницу страны, у которой геозон > 0 и проверяем сортировку геозон
        List<WebElement> geozone = driver.findElements(geozoneCount_locator);

        //проходим в цикле по таблице со странами
        int geozoneCount = geozone.size();
        for (int i=0;i<geozoneCount;i++)
            if (Integer.parseInt(geozone.get(i).getText()) != 0) {
                System.out.println("Country: " + countryRows.get(i).getText());

                //кликаем на страну, переходим на страницу
                countryRows.get(i).click();

                //получаем список временных зон в стране
                List<WebElement> zoneRows = driver.findElements(zoneSelect_locator);
                //удаляем последний элемент таблицы - это edit
                zoneRows.remove(zoneRows.size()-1);
                int zoneRowsCount = zoneRows.size();

                // списки для несортированных и отсортированных зон
                List<String> zoneList = new ArrayList<>();
                List<String> sortZoneList = new ArrayList<>();

                //заполняет список названиями геозон
                //переходим от WebElement к String, заполняем списки
                for (int el = 0; el < zoneRowsCount; el++) {
                    zoneList.add(zoneRows.get(el).getText());
                    sortZoneList.add(zoneRows.get(el).getText());
                    //System.out.println("Add to List "+"'"+ zoneList.get(el).toString() +"'");
                }

                //вывод на экран количества зон
                System.out.println("Zone Rows Count: " + zoneRowsCount);

                //сортируем список
                Collections.sort(sortZoneList);
                //проверяем отсортированы ли уже зоны, если несовпадает хоть одна, то роняем тест
                Assert.assertEquals(true, equalsList(zoneList, sortZoneList));

                //возвращаемся на страницу стран, обновляем список стран и геозон
                driver.get(countryPage);
                countryRows = driver.findElements(countries_locator);
                geozone = driver.findElements(geozoneCount_locator);
            }

    }
    @Test //2я часть задания
    public void geoZonesTest(){
        //определяем локаторы
        String geoZonesPage = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
        By countries_locator = By.xpath("//form[@name='geo_zones_form']//td[3]/a");
        By zoneSelect_locator = By.xpath("//*[@id='table-zones']//td[3]/select/option[@selected='selected']");

        //логинимся
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get(geoZonesPage);
        //получаем количество стран
        List<WebElement> countryRows = driver.findElements(countries_locator);
        int countryRowsCount = countryRows.size();
        System.out.println("Country Rows Count: "+countryRowsCount);

        // проходим в цикле по таблице со странами
        for (int i=0;i<countryRowsCount;i++) {
            System.out.println("Country: "+countryRows.get(i).getText());
            //кликаем на страну, переходим на страницу
            countryRows.get(i).click();

            //получаем список временных зон в стране
            List<WebElement> zoneRows = driver.findElements(zoneSelect_locator);
            int zoneRowsCount = zoneRows.size();

            // списки для несортированных и отсортированных зон
            List<String> zoneList = new ArrayList<>();
            List<String> sortZoneList = new ArrayList<>();

            //заполняет список названиями геозон
            //переходим от WebElement к String, заполняем списки
            for(int el=0;el<zoneRowsCount;el++){
                zoneList.add(zoneRows.get(el).getText());
                sortZoneList.add(zoneRows.get(el).getText());
                //System.out.println("Add to List "+"'"+ zoneList.get(el).toString() +"'");
            }

            //вывод на экран зон
            System.out.println("Zone Rows Count: "+ zoneRowsCount);
            /*
            for (int j=0;j<zoneRowsCount;j++) {
                System.out.println("---" + zoneRows.get(j).getText());
            }
            */

            //сортируем список
            Collections.sort(sortZoneList);
            //проверяем отсортированы ли уже зоны, если несовпадает хоть одна, то роняем тест
            Assert.assertEquals(true,equalsList(zoneList,sortZoneList));

            //возвращаемся на страницу геозон, обновляем список стран
            driver.get(geoZonesPage);
            countryRows = driver.findElements(countries_locator);
        }


    }
    //Функция сравнения списков зон
    public boolean equalsList(List<String> zoneList, List<String> sortZoneList){
        for(int el=0;el<zoneList.size();el++){
            System.out.println("*** "+zoneList.get(el).toString() + " = "+sortZoneList.get(el).toString());
            if(!zoneList.get(el).toString().equals(sortZoneList.get(el).toString())){
                return false;
            }
        }
        return true;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
    }
