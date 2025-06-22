package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    WebDriver driver;

    private By contactButton = By.cssSelector("button.MuiButtonBase-root");
    private By nameFiled = By.cssSelector("input.MuiInputBase-input");
    private By emailField = By.cssSelector("input[name='email']");
    private By messageField = By.cssSelector("textarea[name='description']");
    private By sendButton = By.cssSelector("button[type='submit']");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void enterFields(String name, String email, String message) {
        driver.findElement(nameFiled).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(messageField).sendKeys(message);

    }

    public void submitForm(){
        driver.findElement(sendButton).click();
    }

    public void openContactForm() {
        clickElement(contactButton);
    }

    public String getEmailValidationMessage() {
        return driver.findElement(emailField).getAttribute("validationMessage");
    }


}
