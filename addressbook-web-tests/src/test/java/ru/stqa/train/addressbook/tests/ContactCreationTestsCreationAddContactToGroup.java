package ru.stqa.train.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;
import ru.stqa.train.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
      app.goTo().HomePage();
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
    // find contacts not in group
    Groups groups = app.db().groups();
    Contacts contacts_before = app.db().contacts();
    Contacts cntNotInGroup = new Contacts();
    for(ContactData cnt: contacts_before ){
      if (cnt.getGroups().size() == 0) {
        cntNotInGroup.add(cnt);
      }
    }

    System.out.println("cntToAdd = " + cntNotInGroup);

    if (cntNotInGroup.size() == 0){
      ContactData newCnt = new ContactData().withFirstName("First name")
              .withLastName("Last name").withAddress("Address new").withPhoneHome("84953864656")
              .withEmailFirst("example@mail.com");
      app.goTo().HomePage();
      app.contact().create(newCnt, false);
      cntNotInGroup.add(newCnt);
    }
    // add contact to group
    ContactData cntToAdd = cntNotInGroup.iterator().next();
    GroupData grpToAdd = groups.iterator().next();
    app.goTo().HomePage();
    app.contact().addContactToGroup(cntToAdd, grpToAdd);

    Contacts contacts_after = app.db().contacts();
    //contacts_after.add(cntToAdd);
    Contacts addedContact =
            contacts_after.stream()
                    .filter((ContactData q) -> q.getId() == cntToAdd.getId()).collect(Collectors.toCollection(Contacts::new));
    System.out.println("addedContact = " + addedContact);
    assertThat(addedContact.iterator().next().getGroups(), equalTo(grpToAdd));



  }
}

