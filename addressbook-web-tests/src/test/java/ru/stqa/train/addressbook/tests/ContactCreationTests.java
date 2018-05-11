package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.initContactCreation();
    app.fillContactForm(new ContactData("First name", "Midle", "Last name", "Address new", "84953864656", "example@mail.com"));
    app.submitContactCreation();
    app.goToHomePage();
  }
}
