package ru.stqa.train.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.train.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

  @Test
  public void firstLogin() throws IOException {
    HttpSession session = app.newSession();
    boolean login = session.login("administrator", "root");
    System.out.println(login);
    assertTrue(session.login("administrator", "root"));
    boolean loginAs = session.isLoginInAs("administrator");
    System.out.println(loginAs);
    assertTrue(session.isLoginInAs("administrator"));
  }
}
