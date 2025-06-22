package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SidePanel extends BasePage {

    private By menuIcon = By.xpath("//*[@id=\"root\"]/div/div/header/div/button");
    private By homeButton = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/ul/li[1]/div/div[2]/span");
    private By eventsButton = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/ul/li[2]/div/div[2]/span");
    private By contactButton = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/ul/li[3]/div/div[2]/span");
    private By loginButton = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/ul/li[4]/div/div[2]/span");
    private By plusButton = By.xpath("/html/body/div/div/div[2]/button");
    private By addEventButton = By.xpath("//*[@id=\"SpeedDial-actions\"]/button");
    private By logoutButton = By.xpath("//div[@role='button' and .//span[text()='Log out']]");

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    public void clickMenuIcon() {
        clickElement(menuIcon);
    }

    public void clickEventsButton() {
        clickElement(eventsButton);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public String getTextFromHomeButton() {
        return getTextFromElement(homeButton);
    }

    public String getTextFromEventsButton() {
        return getTextFromElement(eventsButton);
    }

    public String getTextFromContactButton() {
        return getTextFromElement(contactButton);
    }

    public String getTextFromLoginButton() {
        return getTextFromElement(loginButton);
    }

    public void clickAddEventButton(){
        clickElement(addEventButton);
    }

    public void clickPlusButton(){
        clickElement(plusButton);
    }

    public void clickContactButton(){
        clickElement(contactButton);
    }

    public void clickLogoutButton(){
        clickElement(logoutButton);
    }

}
