package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("group_modified", null, null));
    // Элемент не успевает отрисоваться в Chrome?
    //app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().goToGroupPage();
  }
}
