package ru.stqa.train.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactForm(new ContactData("First name", "Midle", "Last name", "Address new", "84953864656", "example@mail.com"));
    submitContactCreation();
    goToHomePage();
  }
}
