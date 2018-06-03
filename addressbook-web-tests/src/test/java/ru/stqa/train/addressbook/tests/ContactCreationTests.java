package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    int before = app.contact().getContactCountUI();
    app.contact().create(new ContactData("First name", "Middle", "Last name",
            "Address new", "84953864656",
            "example@mail.com", "group_new"), false);
    app.goTo().HomePage();
    int after = app.contact().getContactCountUI();
    Assert.assertEquals(after, before + 1);
  }

  @Test(enabled = false)
  public void testContactCreation0() {
    int before = app.contact().getContactCountUI();
    String groupName = "some_group";
    app.goTo().GroupPage();
    if (!app.group().isThereAGroup()) {
      app.group().create(new GroupData(groupName, null, null));
    } else {
      groupName = app.group().getGroupNameFromUI();
    }
    app.contact().create(new ContactData("First name", "Middle", "Last name",
            "Address new", "84953864656",
            "example@mail.com", groupName), true);
    app.goTo().HomePage();
    int after = app.contact().getContactCountUI();
    Assert.assertEquals(after, before + 1);
  }

  @Test
  public void testContactCreation1() {
    List<ContactData> before = app.contact().List();
    GroupData groupData = new GroupData("some_group", null, null);
    ContactData contact = new ContactData("First name", "Middle", "Last name",
            "Address new", "84953864656",
            "example@mail.com", groupData.getName());
    app.goTo().GroupPage();

    if (app.group().List().size() == 0) {
      app.group().create(groupData);
    } else {
      contact.setGroup(app.group().getGroupNameFromUI());
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
