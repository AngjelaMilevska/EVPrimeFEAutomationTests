package evprimefrontendtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CreateUserLoginPage;
import pages.SidePanel;
import util.DateBuilder;

import static junit.framework.TestCase.assertEquals;

public class SidePanelTests {

    private WebDriver driver;
    private SidePanel sidePanel;
    private CreateUserLoginPage createUserLoginPage;
    private String testEmail;
    private String testPassword;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("http://localhost:3000/");

        sidePanel = new SidePanel(driver);
        createUserLoginPage = new CreateUserLoginPage(driver);
        testEmail = "mail" + DateBuilder.currentTime() + "@mail.com";
        testPassword = "pass123";

        sidePanel.navigateTo("http://localhost:3000/");
    }


    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void sidePanelItemsValidationWithoutLogin() throws InterruptedException {
        sidePanel.clickMenuIcon();
        Thread.sleep(3000);
        assertEquals("Home", sidePanel.getTextFromHomeButton());
        assertEquals("Events", sidePanel.getTextFromEventsButton());
        assertEquals("Contact", sidePanel.getTextFromContactButton());
        assertEquals("Login", sidePanel.getTextFromLoginButton());
    }

    private void registerAndLoginUser() throws InterruptedException {
        sidePanel.clickMenuIcon();
        Thread.sleep(2000);
        sidePanel.clickLoginButton();
        Thread.sleep(2000);
        createUserLoginPage.clickChangeStateButton();
        Thread.sleep(1000);
        createUserLoginPage.insertEmail(testEmail);
        createUserLoginPage.insertPassword(testPassword);
        createUserLoginPage.clickGoButton();
        Thread.sleep(3000);
        sidePanel.clickLoginButton();
        Thread.sleep(2000);
        createUserLoginPage.insertEmail(testEmail);
        createUserLoginPage.insertPassword(testPassword);
        createUserLoginPage.clickGoButton();
        Thread.sleep(5000);
    }

    @Test
    public void clickEventsButton() throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(3000);
        sidePanel.clickEventsButton();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/events", currentUrl);
    }

    @Test
    public void clickContactButton() throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(3000);
        sidePanel.clickContactButton();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/contact", currentUrl);
    }

    @Test
    public void clickLogoutButton() throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(3000);
        sidePanel.clickLogoutButton();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/", currentUrl);
    }

}
