package evprimefrontendtests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ContactPage;

import static junit.framework.TestCase.assertEquals;

public class ContactPageTests {

    WebDriver driver;
    ContactPage contactPage;

    @Before
    public void setUp(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/contact");

        contactPage = new ContactPage(driver);
    }

    @Test
    public void testContactForm(){
        contactPage.openContactForm();
        contactPage.enterFields("Test name", "testtest@mail.com", "Test message description");
        contactPage.submitForm();
        assertEquals("http://localhost:3000/contact", driver.getCurrentUrl());

    }

    @Test
    public void onlyNameFieldSubmitted(){
        ContactPage contactPage = new ContactPage(driver);

        contactPage.openContactForm();
        contactPage.enterFields("test name", "", "");
        contactPage.submitForm();

        String actualMessage = contactPage.getEmailValidationMessage();
        String expectedMessage = "Please fill in this field.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void onlyEmailSubmitted(){
        contactPage.enterFields("","testmail@mail.com", "");
        contactPage.submitForm();
        String actualMessage = contactPage.getEmailValidationMessage();
        String expectedMessage = "";

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void onlyMessageIsSubmitted(){
        contactPage.enterFields("","","Test description 123");
        contactPage.submitForm();
        String actualMessage = contactPage.getEmailValidationMessage();
        String expectedMessage = "Please fill in this field.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void submitEmptyForm(){
        contactPage.enterFields("","","");
        contactPage.submitForm();
        String actualMessage = contactPage.getEmailValidationMessage();
        String expectedMessage = "Please fill in this field.";
        assertEquals(expectedMessage, actualMessage);
    }

}
