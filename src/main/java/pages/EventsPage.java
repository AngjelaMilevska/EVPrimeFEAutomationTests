package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class EventsPage extends BasePage {

    private By eventCards = By.cssSelector("div.MuiButtonBase-root.MuiListItemButton-root.MuiListItemButton-gutters[role='button']");
    private By plusButton = By.xpath("//*[@id=\"root\"]/div/div[2]/button");
    private By addEventButton = By.xpath("//*[@id=\"SpeedDial-actions\"]/button");
    private By eventImage = By.xpath(("(//img[@alt='New York City of lights'])[1]"));
    private By updateButton = By.xpath("/html/body/div/div/div[1]/main/div[2]/div[1]/div[2]/button[1]");
    private By deleteButton = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/div[1]/div[1]/div[1]/button");
    private By deleteConfirmationModal = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/div[1]/div[1]/div[1]/button");
    private By confirmDeleteButton = By.xpath("/html/body/div[2]/div[3]/div/div/button[1]");
    private By backButton = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/div[1]/div[2]/button[2]");


    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfEvents() {
        return findElements(eventCards).size();
    }


    public void clickFirstEvent() {
        List<WebElement> events = findElements(eventCards);
        if (!events.isEmpty()) {
            events.get(0).click();
        } else {
            throw new NoSuchElementException("No event cards found to click.");
        }
    }

    public void clickPlusButton() {
        clickElement(plusButton);
    }

    public void clickAddEventButton() {
        clickElement(addEventButton);
    }

    public void clickEventImage() {
        clickElement(eventImage);
    }

    public boolean isImageVisible() {
        return isElementDisplayed(eventImage);
    }

    public void clickUpdateButton() {
        clickElement(updateButton);
    }

    public void clickDeleteButton() {
        clickElement(deleteButton);
    }

    public boolean isDeleteConfirmationVisible() {
        return isElementDisplayed(deleteConfirmationModal);
    }

    public void confirmDelete() {
        clickElement(confirmDeleteButton);
    }

    public void clickBackButton() {
        clickElement(backButton);
    }

}
