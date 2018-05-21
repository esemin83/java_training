package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;

public class GroupGetNameTest extends TestBase{

  @Test
  public void testGetGroupName(){
    app.getNavigationHelper().goToGroupPage();
    String groupName;
    groupName = app.getGroupHelper().getGroupName();
    System.out.println(groupName);
  }
}
