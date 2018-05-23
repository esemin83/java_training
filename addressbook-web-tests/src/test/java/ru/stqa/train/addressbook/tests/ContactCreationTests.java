package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().createContact(new ContactData("First name", "Middle", "Last name",
            "Address new", "84953864656",
            "example@mail.com", "group_new"), false);
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testContactCreation0() {
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

  }

  @Test
  public void testContactCreation1() {
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

  }
}
