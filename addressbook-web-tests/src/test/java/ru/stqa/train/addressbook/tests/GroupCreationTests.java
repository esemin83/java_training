package ru.stqa.train.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().GroupPage();
    List<GroupData> before = app.group().List();
    GroupData group = new GroupData("group_new1", null, null);
    app.group().create(group);
    app.goTo().GroupPage();
    List<GroupData> after = app.group().List();
    Assert.assertEquals(after.size(), before.size() + 1);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.add(group);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
