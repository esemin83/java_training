package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation1() {
    Contacts before = app.contact().all();
    GroupData groupData = new GroupData().withName("some_group");
    ContactData contact = new ContactData().withFirstname("First name").withMiddlename("Middle").withLastname("Last name")
            .withAddress("Address new").withPhoneHome("84953864656").withEmailFirst("example@mail.com").withGroup(groupData.getName());
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(groupData);
    } else {
      contact.withGroup(app.group().getGroupNameFromUI());
    }
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().countUI(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after , equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
