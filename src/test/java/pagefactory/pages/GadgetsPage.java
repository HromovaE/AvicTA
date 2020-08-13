package pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GadgetsPage extends BasePage {
    @FindBy(xpath = "//div[@class='brand-box__title']/a[contains(@href,'umnyij-dom')]")
    private WebElement smartHome;

    public GadgetsPage(WebDriver driver) {
        super(driver);
    }

    public void clickSmartHome() {
        smartHome.click();
    }
}
