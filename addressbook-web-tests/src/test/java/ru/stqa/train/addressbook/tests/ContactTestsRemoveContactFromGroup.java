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

public class ContactTestsRemoveContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts_before = app.db().contacts();
    GroupData newGroup = new GroupData().withName("some_group");
    ContactData newContact = new ContactData().withFirstName("First name")
            .withLastName("Last name").withAddress("Address new").withPhoneHome("84953864656")
            .withEmailFirst("example@mail.com");

    if (contacts_before.contactsInGroup().size() == 0 || contacts_before.contactsNotInGroup().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(newContact, false);
    }

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(newGroup);
    }
    Contacts contacts_after = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData cntToAdd = contacts_after.contactsNotInGroup().iterator().next();
    GroupData grpToAdd = groups.iterator().next();
    app.goTo().HomePage();
    app.contact().addContactToGroup(cntToAdd, grpToAdd);
    app.goTo().HomePage();
  }

  @Test
  public void ContactCreationTestsRemoveContactFromGroup() {
    // find contacts in group
    Contacts contacts_before = app.db().contacts();
    // select group to remove
    GroupData groupToRemove = contacts_before.contactsInGroup().iterator().next().getGroups().iterator().next();
    // select contact to remove
    ContactData contactToRemove = contacts_before.contactsInGroup().iterator().next();
    //remove
    app.contact().removeContactFromGroup(contactToRemove, groupToRemove);
    //refresh data
    Contacts contacts_after = app.db().contacts();
    Contacts removedContact =
            contacts_after.stream()
                    .filter((ContactData q) -> q.getId() == contactToRemove.getId())
                    .collect(Collectors.toCollection(Contacts::new));
    assertThat((removedContact.iterator().next().getGroups().size()), equalTo(0));
    app.goTo().HomePage();
    // UI check
    assertThat(app.contact().uiCheckContactRemovedFromGroup(contactToRemove, groupToRemove), equalTo(true));
  }
}

