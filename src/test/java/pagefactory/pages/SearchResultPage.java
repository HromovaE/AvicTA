package pagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//div[@class='prod-cart__descr']")
    private List<WebElement> productList;

    public SearchResultPage (WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getProductList() {
        return productList;
    }

}
