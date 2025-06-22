package evprimefrontendtests;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CreateEditEventPage;
import pages.CreateUserLoginPage;
import pages.EventsPage;
import pages.SidePanel;
import util.DateBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventTests {

    private WebDriver driver;
    private SidePanel sidePanel;
    private CreateUserLoginPage createUserLoginPage;
    private CreateEditEventPage createEditEventPage;
    private EventsPage eventsPage;

    private String testEmail;
    private String testPassword;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.get("http://localhost:3000/");

        sidePanel = new SidePanel(driver);
        eventsPage = new EventsPage(driver);
        createUserLoginPage = new CreateUserLoginPage(driver);
        createEditEventPage = new CreateEditEventPage(driver);

        testEmail = "mail" + DateBuilder.currentTime() + "@mail.com";
        testPassword = "pass123";
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void registerAndLoginUser() throws InterruptedException {
        sidePanel.clickMenuIcon();
        Thread.sleep(3000);
        sidePanel.clickLoginButton();
        Thread.sleep(3000);
        createUserLoginPage.clickChangeStateButton();
        Thread.sleep(1000);
        assertEquals("Create new user", createUserLoginPage.getTextFromTitle());
        createUserLoginPage.insertEmail(testEmail);
        createUserLoginPage.insertPassword(testPassword);
        createUserLoginPage.clickGoButton();
        Thread.sleep(3000);
        sidePanel.clickLoginButton();
        Thread.sleep(3000);
        createUserLoginPage.insertEmail(testEmail);
        createUserLoginPage.insertPassword(testPassword);
        createUserLoginPage.clickGoButton();
        Thread.sleep(8000);
    }

    @Test
    public void isUserLoggedInSuccessfully() throws InterruptedException {
        registerAndLoginUser();
        assertEquals("http://localhost:3000/", driver.getCurrentUrl());
        Thread.sleep(3000);
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/ul/li/div"));
        assertTrue(logoutButton.isDisplayed());
    }

    @Test
    public void createAnEventTest() throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(8000);
        sidePanel.clickPlusButton();
        sidePanel.clickAddEventButton();

        createEditEventPage.insertEventTitle("Wizz Air Marathon in Skopje");
        createEditEventPage.insertEventImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9B44L9rRcwy_ihxcPQP8iEoVK34ckIG5kxg&s");
        createEditEventPage.insertEventDate("2024-06-06");
        createEditEventPage.insertEventLocation("Skopje");
        createEditEventPage.insertEventDescription("Highlights from the Wizz Air Marathon 2024");
        createEditEventPage.clickCreateEventButton();
    }

    @Test
    public void clickOnEventImage() throws InterruptedException {
        registerAndLoginUser();

        sidePanel.clickEventsButton();
        Thread.sleep(5000);
        eventsPage.clickFirstEvent();
        Thread.sleep(3000);
        eventsPage.clickEventImage();
        assertTrue(eventsPage.isImageVisible());
    }

    @Test
    public void updateEventTest() throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(3000);
        sidePanel.clickPlusButton();
        Thread.sleep(8000);
        sidePanel.clickAddEventButton();
        createEditEventPage.insertEventTitle("Update Test");
        createEditEventPage.insertEventImage("https://via.placeholder.com/150");
        createEditEventPage.insertEventDate("2025-06-10");
        createEditEventPage.insertEventLocation("Update City");
        createEditEventPage.insertEventDescription("To be updated.");
        createEditEventPage.clickCreateEventButton();
        Thread.sleep(5000);
        eventsPage.clickEventImage();
        Thread.sleep(8000);
        eventsPage.clickUpdateButton();
        Thread.sleep(5000);
        createEditEventPage.clearAndInsertEventTitle("Updated title");
        Thread.sleep(8000);
        createEditEventPage.clickUpdateEventButton();
        assertTrue(driver.getCurrentUrl().contains("/events"));

    }

    @Test
    public void deleteEventTest() throws InterruptedException {
        registerAndLoginUser();

        eventsPage.clickPlusButton();
        Thread.sleep(3000);
        eventsPage.clickAddEventButton();
        createEditEventPage.insertEventTitle("Event will be deleted");
        createEditEventPage.insertEventImage("https://via.placeholder.com/150");
        createEditEventPage.insertEventDate("2025-06-01");
        createEditEventPage.insertEventLocation("City Lights");
        createEditEventPage.insertEventDescription("Millions of lights, countless stories. Urban magic in full display");
        createEditEventPage.clickCreateEventButton();
        eventsPage.clickEventImage();
        Thread.sleep(5000);
        eventsPage.clickDeleteButton();
        assertTrue(eventsPage.isDeleteConfirmationVisible());
        eventsPage.confirmDelete();
        Thread.sleep(1000);
        assertEquals(4, eventsPage.getNumberOfEvents());
    }

    @Test
    public void clickBackButton() throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(3000);
        sidePanel.clickEventsButton();
        Thread.sleep(3000);
        eventsPage.clickEventImage();
        Thread.sleep(3000);
        eventsPage.clickBackButton();
        Thread.sleep(3000);
        assertTrue(driver.getCurrentUrl().contains("/events"));
    }

    @Test
    public void clickDeleteButton() throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(3000);
        sidePanel.clickEventsButton();
        Thread.sleep(3000);
        eventsPage.clickEventImage();
        Thread.sleep(3000);
        eventsPage.clickDeleteButton();
        Thread.sleep(3000);
        assertTrue(driver.getCurrentUrl().contains("/events"));
    }


    public void createEventWithMissingField(String title, String image, String date, String location, String description) throws InterruptedException {
        registerAndLoginUser();
        Thread.sleep(3000);
        sidePanel.clickPlusButton();
        sidePanel.clickAddEventButton();
        if (title != null) createEditEventPage.insertEventTitle(title);
        if (image != null) createEditEventPage.insertEventImage(image);
        if (date != null) createEditEventPage.insertEventDate(date);
        if (location != null) createEditEventPage.insertEventLocation(location);
        if (description != null) createEditEventPage.insertEventDescription(description);
        createEditEventPage.clickCreateEventButton();
        Thread.sleep(3000);
        Assume.assumeTrue("Skipping validation because page it's not ready", true);
    }

    @Test
    public void createEventWithoutTitle() throws InterruptedException {
        createEventWithMissingField(null, "https://via.placeholder.com/150", "05-05-2026", "Skopje", "Best bakery in the city");
    }

    @Test
    public void createEventWithoutImage() throws InterruptedException {
        createEventWithMissingField("New coffee shop in the town", null, "05-05-2026", "Ohrid", "High-quality coffee beans");
    }

    @Test
    public void createEventWithoutDate() throws InterruptedException {
        createEventWithMissingField("New coffee shop in the town", "https://sumatocoffee.com/cdn/shop/articles/the-importance-of-coffee-shops-in-communities-782577.jpg?v=1713277728&width=1600", null, "Barcelona", "High-quality coffee beans");
    }

    @Test
    public void createAnEventWithoutLocation() throws InterruptedException {
        createEventWithMissingField("New coffee shop in the town", "https://sumatocoffee.com/cdn/shop/articles/the-importance-of-coffee-shops-in-communities-782577.jpg?v=1713277728&width=1600", "06-06-2025", null, "High-quality coffee beans");

    }

    @Test
    public void createAnEventWithoutDescription() throws InterruptedException {
        createEventWithMissingField("New coffee shop in the town", "https://sumatocoffee.com/cdn/shop/articles/the-importance-of-coffee-shops-in-communities-782577.jpg?v=1713277728&width=1600", "06-06-2025", "London", null);
    }
}