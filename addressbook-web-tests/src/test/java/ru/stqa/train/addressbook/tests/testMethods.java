package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

import java.util.List;

public class testMethods extends TestBase{

  @Test(enabled = false)
  public void testGetGroupName(){
    app.getNavigationHelper().goToGroupPage();
    String groupName;
    groupName = app.getGroupHelper().getGroupNameFromUI();
    System.out.println("groupName = " + groupName);
  }


  @Test
  public void testGetContactList() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> contactList = app.getContactHelper().getContactList();
    System.out.println("contactList = " + contactList.toString());
    //System.out.println(contactList);
  }
}
