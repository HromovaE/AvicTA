package pagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

public class AppleStorePage extends BasePage {

    @FindBy(xpath = "//div[@class='brand-box__title']/a[contains(@href,'iphone')]")
    private WebElement iphoneButton;

    public AppleStorePage (WebDriver driver) {
        super(driver);
    }


    public void clickIphoneButton() {
        iphoneButton.click();
    }
}
