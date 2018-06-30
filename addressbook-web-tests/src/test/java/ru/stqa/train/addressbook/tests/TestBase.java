package ru.stqa.train.addressbook.tests;

import org.hibernate.service.spi.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.train.addressbook.appmanager.ApplicationManager;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;
import ru.stqa.train.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + "with param " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m, Object[] p) {
    logger.info("Stop test " + m.getName() + "with param " + Arrays.asList(p));
  }


  public void verifyGroupListUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(
              dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet()),
              equalTo(uiGroups));
      System.out.println("Check UI verify");
    }

  }

  public void verifyContactListUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts.stream().map((b) -> new ContactData()
              .withId(b.getId()).withFirstName(b.getFirstname()).withLastName(b.getLastname()).
                              withAddress(b.getAddress())).collect(Collectors.toSet()),
              equalTo(dbContacts.stream().map((b) -> new ContactData()
                      .withId(b.getId()).withFirstName(b.getFirstname())
                      .withLastName(b.getLastname()).withAddress(b.getAddress())).collect(Collectors.toSet())));
      System.out.println("Check UI verify");
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (app.rst().isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
