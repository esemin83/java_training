package ru.stqa.train.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.GroupData;
import ru.stqa.train.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation1() {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("group_new1");
    app.group().create(group);
    app.goTo().GroupPage();
    Groups after = app.group().all();

    assertThat(after, equalTo(before.withAdded(
            group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    assertThat(after.size(), equalTo(before.size() + 1));
  }
}
