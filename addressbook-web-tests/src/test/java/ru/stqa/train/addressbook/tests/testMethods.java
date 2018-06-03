package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

import java.util.List;

public class testMethods extends TestBase{

  @Test(enabled = false)
  public void testGetGroupName(){
    app.goTo().GroupPage();
    String groupName;
    groupName = app.group().getGroupNameFromUI();
    System.out.println("groupName = " + groupName);
  }


  @Test
  public void testGetContactList() {
    app.goTo().HomePage();
    List<ContactData> contactList = app.contact().List();
    System.out.println("contactList = " + contactList.toString());
    //System.out.println(contactList);
  }
}
