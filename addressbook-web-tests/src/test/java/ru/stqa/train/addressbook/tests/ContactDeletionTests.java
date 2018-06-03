package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().List().size() == 0) {
      app.contact().create(new ContactData("First name", "Middle", "Last name", "Address new", "84953864656",
              "example@mail.com", "group_1"), false);
    }
    app.goTo().HomePage();
  }

  @Test
  public void testDeletionContact() {
    List<ContactData> before = app.contact().List();
    int index = before.size() - 1;
    app.goTo().HomePage();
    app.contact().delete(index);
    app.goTo().HomePage();
    List<ContactData> after = app.contact().List();
    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(after, before);
  }
}
