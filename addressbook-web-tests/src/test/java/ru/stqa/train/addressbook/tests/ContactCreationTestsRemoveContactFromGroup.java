package ru.stqa.train.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;
import ru.stqa.train.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTestsRemoveContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    Contacts cntInGroup = contacts.contactsInGroup();
    Contacts cntNotInGroup = contacts.contactsNotInGroup();

    GroupData newGroup = new GroupData().withName("some_group");
    ContactData newContact = new ContactData().withFirstName("First name")
            .withLastName("Last name").withAddress("Address new").withPhoneHome("84953864656")
            .withEmailFirst("example@mail.com");

    if (contacts.contactsInGroup().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(newContact, false);
    }

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(newGroup);
    }

    ContactData cntToAdd = cntNotInGroup.iterator().next();
    GroupData grpToAdd = groups.iterator().next();
    // add contact to group
    app.goTo().HomePage();
    app.contact().addContactToGroup(cntToAdd, grpToAdd);
    app.goTo().HomePage();
  }

  @Test
  public void ContactCreationTestsCreationAddContactToGroup() {

  }
}

