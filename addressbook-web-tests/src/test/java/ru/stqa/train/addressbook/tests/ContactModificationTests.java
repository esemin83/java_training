package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("First name").withMiddlename("Middle").withLastname("Last name")
              .withAddress("Address new").withPhoneHome("84953864656").withEmailFirst("example@mail.com"), false);
    }
    app.goTo().HomePage();

  }

  @Test
  public void testContactModification1() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("First name modified")
            .withMiddlename("Middle name modified").withLastname("Last name modified")
            .withAddress("Address modified").withPhoneHome("84951111111").withEmailFirst("new_mail@mail.com");
    app.contact().modify(contact);
    app.goTo().HomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
