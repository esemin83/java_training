package ru.stqa.train.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.train.mantis.model.MailMessage;
import ru.stqa.train.mantis.model.UserData;
import ru.stqa.train.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException, InterruptedException {
    Users userListFromDB = app.db().getUserListFromDB();
    String newPassword = "e" + randomInt(0, 10000) + "y";
    UserData userToReset = userListFromDB.iterator().next();
    System.out.println("\n" + "userToReset" + userToReset + "\n");
    app.registration().resetPwdByAdmin(userToReset);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
    System.out.println("\n" + "mailMessages" + mailMessages + "\n");
    String confirmationLink = findConfirmationLink(mailMessages, userToReset.getMail());
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(userToReset.getUserName(), newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://localhost/mantisbt-1.2.19/verify.php").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  private int randomInt(int min, int max) {
    return min + (int) (Math.random() * ((max - min) + 1));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
