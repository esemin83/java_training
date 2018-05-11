package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("group_1", "header_1", "footer_1"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }
}
