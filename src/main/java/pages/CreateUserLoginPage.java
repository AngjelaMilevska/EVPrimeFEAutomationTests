package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateUserLoginPage extends BasePage {

    private By formTitle = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div/div/form/div/div[1]");
    private By emailTextBox = By.name("email");
    private By passwordTextBox = By.name("password");
    private By goButton = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div/div/form/div/div[4]/div[1]/button");
    private By changeStateButton = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div/div/form/div/div[4]/div[2]/button");

    public CreateUserLoginPage(WebDriver driver){
        super(driver);
    }

    public String getTextFromTitle(){
        return getTextFromElement(formTitle);
    }

    public void registerUser(String email, String password) throws InterruptedException {
        Thread.sleep(3000);
        clickElement(changeStateButton);
        insertText(emailTextBox, email);
        insertText(passwordTextBox, password);
        clickElement(goButton);
    }

    public void loginUser(String email, String password) throws InterruptedException {
        Thread.sleep(3000);
        insertText(emailTextBox, email);
        insertText(passwordTextBox, password);
        clickElement(goButton);

    }

    public void insertEmail(String value){
        insertText(emailTextBox, value);
    }

    public void insertPassword(String value){
        insertText(passwordTextBox, value);
    }

    public void clickGoButton(){
        clickElement(goButton);
    }

    public void clickChangeStateButton(){
        clickElement(changeStateButton);
    }

    public void registerAndLogin(String email, String password) throws InterruptedException {
        clickChangeStateButton();
        Thread.sleep(1000);
        registerUser(email, password);
        Thread.sleep(3000);
        insertText(emailTextBox, email);
        insertText(passwordTextBox, password);
        clickGoButton();
    }



}
