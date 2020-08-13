package pagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IphonePage extends BasePage {

    @FindBy(xpath = "//a[@class='prod-cart__buy'][contains(@data-ecomm-cart,'White (MWL82)')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]")
    private WebElement continueButton;

    @FindBy(id = "js_cart")
    private WebElement addToCartPopup;

    @FindBy(xpath = "//p[contains(text(), 'Цвет')]")
    private WebElement openColorDropdownMenu;

    @FindBy(xpath = "//input[@id='fltr-cvet-korallovyj']/following-sibling::label")
    private WebElement checkKoralovyiColor;

    @FindBy(xpath = "//input[@id='fltr-cvet-korallovyj']/following-sibling::a")
    private WebElement applyKoralovyiColor;

    //@FindBy(xpath = "//div[@class='prod-cart height']"))
    //private List<WebElement> ;

    public IphonePage (WebDriver driver) {
        super(driver);
    }

    public void clickAddToCardButton() {
        addToCartButton.click();//add to cart iphone
    }

    public void clickContinueShoppingButton() {
        continueButton.click();
    }

    public WebElement getAddToCartPopup(){
        return addToCartPopup;
    }

    public void openColorDropdownMenu() {
        openColorDropdownMenu.click();
    }

    public WebElement getCheckKoralovyiColor() {
        return checkKoralovyiColor;
    }
    public void clickKoralovyiColorCheckBox(){
        checkKoralovyiColor.click();
    }

    public WebElement getApplyKoralovyiColorButton() {
        return applyKoralovyiColor;
    }

    public void clickApplyKoralovyiColorButton(){
        applyKoralovyiColor.click();
    }
}
