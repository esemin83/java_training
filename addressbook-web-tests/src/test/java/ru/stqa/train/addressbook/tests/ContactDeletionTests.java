package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testDeletionContact() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("First name", "Middle", "Last name", "Address new", "84953864656",
              "example@mail.com", "group_1"), false);
    }
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCountUI();
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCountUI();
    Assert.assertEquals(after, before - 1);
  }
}
