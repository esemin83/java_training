package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTestsCreationTrue extends TestBase {

  @Test(enabled = false)
  public void testContactCreationTrue() {
    Contacts before = app.db().contacts();
    GroupData groupData = new GroupData().withName("some_group");
    ContactData contact = new ContactData().withFirstName("First name")
            .withLastName("Last name").withAddress("Address new").withPhoneHome("84953864656")
            .withEmailFirst("example@mail.com").withGroup(groupData.getName());
    app.goTo().GroupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(groupData);
    } else {
      contact.withGroup(app.group().getGroupNameFromUI());
      //contact.withGroup(app.db().groups().iterator().next().getName());
    }
    app.contact().create(contact, true);
    app.goTo().HomePage();
    //assertThat(app.contact().countUI(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
