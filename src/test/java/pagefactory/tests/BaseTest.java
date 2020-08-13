package pagefactory.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pagefactory.pages.*;

public class BaseTest {

    private WebDriver driver;
    public static final String AVIC_URL = "https://avic.ua/"; //constanta
    protected static final int AMOUNT_SECONDS_TO_WAIT = 30;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //браузер на весь экран
        driver.get(AVIC_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public BasePage getBasePage() {
        return new BasePage(driver);
    }

    public SearchResultPage getSearchResultPage() {
        return new SearchResultPage(driver);
    }

    public AppleStorePage getAppleStorePage(){
        return new AppleStorePage(driver);
    }

    public IphonePage getIphonePage(){
        return new IphonePage(driver);
    }

    public ContactsPage getContactsPage() {
        return new ContactsPage(driver);
    }

    public GadgetsPage getGadgetsPage() {
        return new GadgetsPage(driver);
    }

    public SmartHomePage getSmartHomePage() {
        return new SmartHomePage(driver);
    }
}
