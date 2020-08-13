package pagefactory.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SearchTest extends BaseTest {

    private static final String SEARCH_RESULT = "query=iPhone"; //constanta
    private static final String SEARCH_KEYWORD = "iPhone 11"; //constanta
    private static final int EXPECTED_PRODUCTS_AMOUNT = 12; //constanta
    private static final int EXPECTED_FILTERED_PRODUCTS_AMOUNT = 6; //constanta

    @Test(priority = 1)
    public void checkThatUrlContainsSearchWord() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        assertTrue(getDriver().getCurrentUrl().contains(SEARCH_RESULT)); //проверяем что урла содержит кверю
    }


    @Test(priority = 2)
    public void checkElementsAmountOnSearchPage() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        getBasePage().implicitlyWait(AMOUNT_SECONDS_TO_WAIT);
        int actualElementsSize = getSearchResultPage().getProductList().size();//узнали количество элементов в листе
        getBasePage().implicitlyWait(AMOUNT_SECONDS_TO_WAIT);
        assertEquals(actualElementsSize,EXPECTED_PRODUCTS_AMOUNT);//сравнили количество элементов актуальное с тем которое ожидаем
    }

    @Test(priority = 3)
    public void checkThatSearchResultsContainsSearchWord() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        for (WebElement webElement : getSearchResultPage().getProductList()) { //прошлись циклом и проверили что каждый элемент листа содержит текс iPhone 11
            assertTrue(webElement.getText().contains(SEARCH_KEYWORD));
        }
    }

    @Test(priority = 4)
    public void checkFilterByColor() {
        getHomePage().clickCatalogueButton();
        getHomePage().clickAppleStoreButton();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        getAppleStorePage().clickIphoneButton();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        getIphonePage().openColorDropdownMenu();
        getBasePage().waitForElementVisibility(AMOUNT_SECONDS_TO_WAIT, getIphonePage().getCheckKoralovyiColor());
        getIphonePage().clickKoralovyiColorCheckBox();
        getBasePage().waitForElementVisibility(AMOUNT_SECONDS_TO_WAIT, getIphonePage().getApplyKoralovyiColorButton());
        getIphonePage().clickApplyKoralovyiColorButton();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        int actualElementsSize = getSearchResultPage().getProductList().size();

        assertEquals(actualElementsSize,EXPECTED_FILTERED_PRODUCTS_AMOUNT);//сравнение
    }
}
