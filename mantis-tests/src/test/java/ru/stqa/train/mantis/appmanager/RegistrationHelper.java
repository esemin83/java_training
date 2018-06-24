package ru.stqa.train.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.train.mantis.model.UserData;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.xpath("//input[@value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//input[@value='Update User']"));
  }

  public void resetPwdByAdmin(UserData user) throws InterruptedException {
    loginAsAdmin();
    // go to users manage page //
    click(By.xpath("//td//a[contains(.,'Manage')]"));
    click(By.xpath("//a[contains(.,'Manage Users')]"));
    // select user by id //
    selectUserById(user.getId());
    // reset //
    click(By.xpath("//input[@value='Reset Password']"));
    // logout
    click(By.xpath("//a[contains(.,'Logout')]"));
  }

  private void selectUserById(int id) {
    click(By.cssSelector(String.format("a[href$='user_id=%s']", id)));
  }

  private void loginAsAdmin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.xpath("//input[@value='Login']"));
  }

}
