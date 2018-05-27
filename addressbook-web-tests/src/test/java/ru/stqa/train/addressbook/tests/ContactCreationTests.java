package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    int before = app.getContactHelper().getContactCountUI();
    app.getContactHelper().createContact(new ContactData("First name", "Middle", "Last name",
            "Address new", "84953864656",
            "example@mail.com", "group_new"), false);
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCountUI();
    Assert.assertEquals(after, before + 1);
  }

  @Test
  public void testContactCreation0() {
    int before = app.getContactHelper().getContactCountUI();
    String groupName = "some_group";
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
    } else {
      groupName = app.getGroupHelper().getGroupNameFromUI();
    }
    app.getContactHelper().createContact(new ContactData("First name", "Middle", "Last name",
            "Address new", "84953864656",
            "example@mail.com", groupName), true);
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCountUI();
    Assert.assertEquals(after, before + 1);
  }

  @Test
  public void testContactCreation1() {
    //int before = app.getContactHelper().getContactCountUI();
    List<ContactData> before = app.getContactHelper().getContactList();
    GroupData groupData = new GroupData("some_group", null, null);
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData(groupData.getName(), null, null));
    } else {
      groupData.setName(app.getGroupHelper().getGroupNameFromUI());
    }
    app.getContactHelper().createContact(new ContactData("First name", "Middle", "Last name",
            "Address new", "84953864656",
            "example@mail.com", groupData.getName()), true);
    app.getNavigationHelper().goToHomePage();
    //int after = app.getContactHelper().getContactCountUI();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
