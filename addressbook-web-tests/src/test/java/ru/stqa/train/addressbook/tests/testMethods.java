package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;

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
  public void testGetContactList0() {
    app.goTo().HomePage();
    Contacts list = app.contact().all();
    System.out.println("contactList 1= " + list.toString());
    //System.out.println(contactList);
  }

  @Test
  public void testGetContactList1() {
    app.goTo().HomePage();
    List<ContactData> list = app.contact().List();
    System.out.println("contactList 2= " + list.toString());
    //System.out.println(contactList);
  }
}
