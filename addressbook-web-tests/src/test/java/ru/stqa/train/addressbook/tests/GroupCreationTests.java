package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("group_1", null, null));
    //app.getGroupHelper().initGroupCreation();
    //app.getGroupHelper().fillGroupForm(new GroupData("group_1", null, null));
    //app.getGroupHelper().submitGroupCreation();
    //app.getGroupHelper().returnToGroupPage();
  }
}