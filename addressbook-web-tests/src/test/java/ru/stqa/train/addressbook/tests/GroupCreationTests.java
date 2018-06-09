package ru.stqa.train.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.GroupData;
import ru.stqa.train.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{new GroupData().withName("group name 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[]{new GroupData().withName("group name 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[]{new GroupData().withName("group name 3").withHeader("header 33").withFooter("footer 3")});
    list.add(new Object[]{new GroupData().withName("").withHeader("").withFooter("")});
    return list.iterator();
  }


  @Test(dataProvider = "validGroups")
  public void testGroupCreation0(GroupData group) {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    app.group().create(group);
    app.goTo().GroupPage();
    assertThat(app.group().countUI(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(
            group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromFile() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split("[;]");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }


  @Test(dataProvider = "validGroupsFromFile")
  public void testGroupCreation1(GroupData group) {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    app.group().create(group);
    app.goTo().GroupPage();
    assertThat(app.group().countUI(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(
            group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation2Bad() {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Name'").withHeader("Header").withFooter("Footer");
    app.group().create(group);
    app.goTo().GroupPage();
    assertThat(app.group().countUI(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
