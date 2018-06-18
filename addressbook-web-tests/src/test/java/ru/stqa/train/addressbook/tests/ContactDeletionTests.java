package ru.stqa.train.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("First name").withLastName("Last name")
              .withAddress("Address new").withPhoneHome("84953864656").withEmailFirst("example@mail.com"), false);
    }
    app.goTo().HomePage();
  }

  @Test
  public void testDeletionContact() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().HomePage();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    //assertThat(app.contact().countUI(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListUI();
  }
}
