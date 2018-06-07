package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;

import java.io.File;
import java.util.List;

public class testMethods extends TestBase{
  /*
  @Test(enabled = false)
  public void testGetGroupName(){

    app.goTo().GroupPage();
    String groupName;
    groupName = app.group().getGroupNameFromUI();
    //System.out.println("groupName = " + groupName);
  }

  @Test
  public void testGetContactList1() {
    app.goTo().HomePage();
    List<ContactData> list = app.contact().List();
    System.out.println("contactList 2 = " + list.toString());
    //System.out.println(contactList);
  }
  */
  @Test
  public void testGetInfoFromEditForm() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData list = app.contact().infoFromEditForm(contact);
    System.out.println("FromEditForm " + list.toString());
  }

  @Test
  public void testGetContactAll() {
    app.goTo().HomePage();
    Contacts list = app.contact().all();
    System.out.println("FromUI = " + list.toString());
    //System.out.println(contactList);
  }
  @Test
  public void getAbsPath() {
    File someFile = new File(".");
    System.out.println(someFile.getAbsolutePath());
    File file = new File("src\\test\\resources\\profile-512.jpg");
    System.out.println(file.getAbsolutePath());
    System.out.println(file.exists());
  }
}
