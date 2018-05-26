package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCountUI();
    app.getGroupHelper().createGroup(new GroupData("group_new", null, null));
    // Элемент не успевает отрисоваться в Chrome?
    //app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().goToGroupPage();
    int after = app.getGroupHelper().getGroupCountUI();
    Assert.assertEquals(after, before + 1);
  }
}
