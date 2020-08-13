package pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SmartHomePage extends BasePage {
    @FindBy(xpath = "//a[contains(@class,'prod-cart__buy')]")
    private List<WebElement> productList;

    @FindBy(xpath = "//span[contains(@class,'js_plus btn-count btn-count--plus')]")
    private WebElement increaseCountButton;

    @FindBy(xpath = "//input[contains(@class,'js-changeAmount count')]")
    private WebElement countOfProductInCart;

    public SmartHomePage(WebDriver driver) {
        super(driver);
    }

    public void addToCartFirstProduct() {
        productList.get(0).click();
    }

    public void clickIncreaseCountButton() {
        increaseCountButton.click();
    }

    public String getCountOfProductInCart() {
        return countOfProductInCart.getAttribute("value");
    }
}
