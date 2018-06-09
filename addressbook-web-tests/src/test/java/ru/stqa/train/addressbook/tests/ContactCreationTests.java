package ru.stqa.train.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> contactsFromFile() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split("[;]");
      list.add(new Object[]{new ContactData().withFirstName(split[0]).withLastName(split[1]).withAddress(split[2])
              .withEmailFirst(split[3]).withPhoneHome(split[4])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "contactsFromFile")
  public void testContactCreation0(ContactData contact) {
    Contacts before = app.contact().all();
    GroupData groupData = new GroupData().withName("some_group");
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(groupData);
    } else {
      contact.withGroup(app.group().getGroupNameFromUI());
    }
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().countUI(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testContactCreationFileAttachedCheck() {
    Contacts before = app.contact().all();
    GroupData groupData = new GroupData().withName("some_group");
    File file = new File("src\\test\\resources\\profile-512.jpg");
    ContactData contact = new ContactData().withFirstName("First name").withMiddleName("Middle")
            .withLastName("Last name").withAddress("Address new").withPhoneHome("84953864656")
            .withEmailFirst("example@mail.com").withGroup(groupData.getName()).withPhoto(file);
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(groupData);
    } else {
      contact.withGroup(app.group().getGroupNameFromUI());
    }
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().countUI(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    //Assert.assertTrue(app.contact().checkPicIsAttached(contact));
    assertThat(app.contact().checkPicIsAttached(contact), equalTo(true));
  }
}
