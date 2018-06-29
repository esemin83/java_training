package ru.stqa.train.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
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

  @Test
  public void getIssueStatus() throws RemoteException, ServiceException, MalformedURLException {
    boolean isIssueOpen = app.api().isIssueOpen(9);
    System.out.println("isIssueOpen " + isIssueOpen);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
