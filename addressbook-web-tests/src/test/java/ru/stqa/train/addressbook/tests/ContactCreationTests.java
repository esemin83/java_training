package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation1() {
    List<ContactData> before = app.contact().List();
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

    List<ContactData> after = app.contact().List();
    Assert.assertEquals(after.size(), before.size() + 1);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.add(contact);
    after.sort(byId);
    before.sort(byId);
    Assert.assertEquals(after, before);


  }
}
