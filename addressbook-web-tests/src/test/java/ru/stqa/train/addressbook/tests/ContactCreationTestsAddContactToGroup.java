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

public class ContactCreationTestsAddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();
    Contacts cntNotInGroup = contacts.contactsNotInGroup();
    GroupData newGroup = new GroupData().withName("some_group");
    ContactData newContact = new ContactData().withFirstName("First name")
            .withLastName("Last name").withAddress("Address new").withPhoneHome("84953864656")
            .withEmailFirst("example@mail.com");

    if (app.db().contacts().size() == 0 || cntNotInGroup.size() == 0) {
      app.goTo().HomePage();
      app.contact().create(newContact, false);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(newGroup);
    }
    app.goTo().HomePage();
  }

  @Test
  public void ContactCreationTestsAddContactToGroup() {
    // find contacts not in group
    Groups groups = app.db().groups();
    Contacts contacts_before = app.db().contacts();
    Contacts cntNotInGroup = contacts_before.contactsNotInGroup();
    // set contact and group
    ContactData cntToAdd = cntNotInGroup.iterator().next();
    GroupData grpToAdd = groups.iterator().next();
    // add contact to group
    app.goTo().HomePage();
    app.contact().addContactToGroup(cntToAdd, grpToAdd);
    // refresh added contact
    Contacts contacts_after = app.db().contacts();
    Contacts addedContact =
            contacts_after.stream()
                    .filter((ContactData q) -> q.getId() == cntToAdd.getId()).collect(Collectors.toCollection(Contacts::new));
    assertThat((addedContact.iterator().next().getGroups()), equalTo(new Groups().withAdded(grpToAdd)));
  }
}

