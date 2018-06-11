package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTestsAll extends TestBase {

  @Test
  public void testGroupDeletionAll() {
    Groups before = app.group().all();
    if (before.size() > 0) {
      app.group().deleteAllGroups(before.size());
      Groups after = app.group().all();
      assertThat(after.size(), equalTo(0));
    }
  }
}
