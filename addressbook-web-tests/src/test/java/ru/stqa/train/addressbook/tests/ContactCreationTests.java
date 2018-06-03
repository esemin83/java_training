package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation1() {
    Set<ContactData> before = app.contact().all();
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
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}
