package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("First name", "Middle", "Last name", "Address new", "84953864656",
              "example@mail.com", "group_1"), false);
    }
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testDeletionContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().deleteContact(index);
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(after, before);
  }
}
