package pagefactory.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AddToCartTest extends BaseTest {
    private static final String PRODUCT_ADDED_TO_CART = "1";
    private static final String PRODUCTS_AFTER_COUNT_INCREASING = "2";

    @Test(priority = 1)
    public void checkAddToCart() {
        getHomePage().clickCatalogueButton();
        getHomePage().clickAppleStoreButton();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        getAppleStorePage().clickIphoneButton();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        getIphonePage().clickAddToCardButton();
        getBasePage().waitForElementVisibility(AMOUNT_SECONDS_TO_WAIT, getIphonePage().getAddToCartPopup());
        getIphonePage().clickContinueShoppingButton();

        assertEquals(getSmartHomePage().getCountOfProductInCart(), PRODUCT_ADDED_TO_CART);
    }

    @Test(priority = 2)
    public void checkContactsPlacesOfTakeOut() {
        getHomePage().clickContactsLink();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        Assert.assertTrue(getContactsPage().getPlacesOfTakeOut().size()>0);

    }

    @Test(priority = 3)
    public void checkCorrectQuantityItemsInBasket() {
        getHomePage().clickCatalogueButton();
        getHomePage().clickGadgetsGroup();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        getGadgetsPage().clickSmartHome();
        getBasePage().waitForPageReadyState(AMOUNT_SECONDS_TO_WAIT);
        getSmartHomePage().addToCartFirstProduct();
        getBasePage().waitForElementVisibility(AMOUNT_SECONDS_TO_WAIT, getIphonePage().getAddToCartPopup());
        getSmartHomePage().clickIncreaseCountButton();
        assertEquals(getSmartHomePage().getCountOfProductInCart(), PRODUCTS_AFTER_COUNT_INCREASING);
    }
}
