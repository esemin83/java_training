package ru.stqa.train.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;
import ru.stqa.train.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTestsCreationAddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    GroupData newGroup = new GroupData().withName("some_group");

    ContactData newContact = new ContactData().withFirstName("First name")
            .withLastName("Last name").withAddress("Address new").withPhoneHome("84953864656")
            .withEmailFirst("example@mail.com");

    if (app.db().contacts().size() == 0) {
      app.goTo().GroupPage();
      app.contact().create(newContact, false);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(newGroup);
    }
    app.goTo().GroupPage();
  }

  @Test
  public void ContactCreationTestsCreationAddContactToGroup() {
    System.out.println("test");

    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();






  }
}

