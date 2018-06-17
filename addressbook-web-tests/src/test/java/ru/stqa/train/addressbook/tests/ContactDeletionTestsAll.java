package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTestsAll extends TestBase {

  @Test
  public void testDeletionContactAll() {
    Contacts before = app.db().contacts();
    if (before.size() > 0) {
      app.goTo().HomePage();
      app.contact().deleteAll(before.size());
      app.goTo().HomePage();
      Contacts after = app.db().contacts();
      assertThat(after.size(), equalTo(0));
    }
  }
}