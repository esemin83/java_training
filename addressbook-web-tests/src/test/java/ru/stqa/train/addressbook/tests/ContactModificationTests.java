package ru.stqa.train.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("First name modified")
            .withMiddlename("Middle name modified").withLastname("Last name modified")
            .withAddress("Address modified").withPhoneHome("84951111111").withEmailFirst("new_mail@mail.com");
    app.contact().modify(contact);
    app.goTo().HomePage();
    assertThat(app.contact().countUI(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
