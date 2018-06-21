package ru.stqa.train.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {


  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    type(By.xpath("//input[@name='username']"), username);
    type(By.xpath("//input[@name='password']"), password);
    click(By.xpath("//input[@class='button']"));
  }

  public void logout() {
    click(By.xpath("//a[contains(.,'Logout')]"));
  }
}


