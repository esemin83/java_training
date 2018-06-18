package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTestsAll extends TestBase {

  @Test
  public void testGroupDeletionAll() {
    app.goTo().GroupPage();
    Groups before = app.db().groups();
    if (before.size() > 0) {
      app.group().deleteAllGroups(before.size());
      Groups after = app.db().groups();
      assertThat(after.size(), equalTo(0));
      verifyGroupListUI();
    }
  }
}
