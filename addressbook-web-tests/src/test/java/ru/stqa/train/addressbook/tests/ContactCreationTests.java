package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().createContact(new ContactData("First name","Middle","Last name", "Address new", "84953864656",
                    "example@mail.com", "group_1"), true);
    //app.getContactHelper().initContactCreation();
    //app.getContactHelper().fillContactForm(new ContactData("First name", "Midle",
    //        "Last name", "Address new", "84953864656",
    //        "example@mail.com", "group_1"), true);
    //app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
  }
}
