package ru.stqa.train.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class TestMethods extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testGenerateRandomInt() {
    int s = randomInt(0, 1000);
    System.out.println("s = " + s);

  }

  private int randomInt(int min, int max) {
    //Random random = null;
    return min + (int) (Math.random() * ((max - min) + 1));
  }

  @Test
  public void testGetMail() throws IOException, MessagingException {
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 100000);
    System.out.println("\n" + "mailMessages = " + mailMessages + "\n");
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
