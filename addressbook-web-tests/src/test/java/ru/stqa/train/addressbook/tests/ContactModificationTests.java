package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("First name", "Middle",
              "Last name", "Address new", "84953864656",
              "example@mail.com", null), false);
    }
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCountUI();
    app.getContactHelper().initContactModification(0);
    app.getContactHelper().fillContactForm(new ContactData("First name modified", "Middle modified",
            "Last name modified", "Address modified", "84951111111",
            "example_modified@mail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCountUI();
    Assert.assertEquals(after, before);
  }
}
