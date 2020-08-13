package pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class ContactsPage extends BasePage {

    @FindBy(xpath = "//div[@class='map-holder']/div[@class='general-col border--bottom']")
    private List<WebElement> placesOfTakeOut;

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getPlacesOfTakeOut() {
        return placesOfTakeOut;
    }

}
