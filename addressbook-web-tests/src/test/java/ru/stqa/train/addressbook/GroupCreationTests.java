package ru.stqa.train.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("group_1", "header_1", "footer_1"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
