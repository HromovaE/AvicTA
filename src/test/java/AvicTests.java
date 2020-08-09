import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class AvicTests {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //браузер на весь экран
        driver.get("https://avic.ua/");
    }


    @Test(priority = 1)
    public void checkThatUrlContainsSearchWord() {
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("iPhone 11", Keys.ENTER); //вводим в поиск iPhone 11
        assertTrue(driver.getCurrentUrl().contains("query=iPhone+11")); //проверяем что урла содержит кверю
    }


    @Test(priority = 2)
    public void checkElementsAmountOnSearchPage() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("iPhone 11", ENTER);//вводим в поиск iPhone 11
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//неявное ожидание 30 сек
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));//собрали элементы поиска в лист
        int actualElementsSize = elementList.size();//узнали количество элементов в листе
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//неявное ожидание 30 сек
        assertEquals(actualElementsSize,12);//сравнили количество элементов актуальное с тем которое ожидаем
    }

    @Test(priority = 3)
    public void checkThatSearchResultsContainsSearchWord() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("iPhone 11", ENTER);//вводим в поиск iPhone 11
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));//собрали элементы поиска в лист
        for (WebElement webElement : elementList) { //прошлись циклом и проверили что каждый элемент листа содержит текс iPhone 11
            Assert.assertTrue(webElement.getText().contains("iPhone 11"));
        }

    }

    @Test(priority = 4)
    public void checkAddToCart() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")).click();//Apple Store
        driver.findElement(xpath("//div[@class='brand-box__title']/a[contains(@href,'iphone')]")).click();//iphone
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        driver.findElement(xpath("//a[@class='prod-cart__buy'][contains(@data-ecomm-cart,'Black (MWLT2)')]")).click();//add to cart iphone
        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится попап с товаром добавленным в корзину
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(xpath("//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]")).click();//продолжить покупки
        String actualProductsCountInCart =
                driver.findElement(xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")).getText();//получили один продукт
        assertEquals(actualProductsCountInCart, "1");
    }

    @Test(priority = 5)
    public void checkFilterByColor() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")).click();//Apple Store
        driver.findElement(xpath("//div[@class='brand-box__title']/a[contains(@href,'iphone')]")).click();//iphone
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        driver.findElement(xpath("//p[contains(text(), 'Цвет')]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(xpath("//input[@id='fltr-cvet-korallovyj']/following-sibling::label")).click();
        driver.findElement(xpath("//input[@id='fltr-cvet-korallovyj']/following-sibling::a")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//неявное ожидание 30 сек
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart height']"));
        int actualElementsSize = elementList.size();
        assertEquals(actualElementsSize,6);//сравнение
    }

    @Test(priority = 6)
    public void checkContactsPlacesofTakeOut() {
        driver.get("https://avic.ua/kontaktyi");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='map-holder']/div[@class='general-col border--bottom']"));
        Assert.assertTrue(elementList.size()>0);

    }

    @Test(priority = 7)
    public void checkCorrectQuantityItemsInBasket() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'gadzhetyi1')]")).click();//Блок "Гаджеты"
        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится попап с товаром добавленным в корзину
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait
        driver.findElement(xpath("//div[@class='brand-box__title']/a[contains(@href,'umnyij-dom')]")).click();//Умный дом
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait
        List<WebElement> elementList = driver.findElements(xpath("//a[contains(@class,'prod-cart__buy')]"));
        elementList.get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(xpath("//span[contains(@class,'js_plus btn-count btn-count--plus')]")).click();//каталог товаров
        String actualProductsCountInCart =
                driver.findElement(xpath("//input[contains(@class,'js-changeAmount count')]")).getAttribute("value");
        assertEquals(actualProductsCountInCart, "2");
    }

    @Test(priority = 8)
    public void checkCorrectAddressPage() {
        String href = driver.findElement(xpath("//ul[@class='footer-soc flex-wrap']/li[1]/a")).getAttribute("href");//футер Facebook

        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится попап с товаром добавленным в корзину
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait

        driver.navigate().to(href);
        assertEquals(driver.getCurrentUrl(), href);
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }


}
