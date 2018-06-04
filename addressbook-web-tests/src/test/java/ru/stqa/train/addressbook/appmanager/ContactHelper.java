package ru.stqa.train.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhoneHome());
    type(By.name("email"), contactData.getEmailFirst());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(ContactData contact) {
    wd.findElement(By.cssSelector("input[value='" + contact.getId() + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[title='Edit']")).get(index).click();
  }

  public void initContactModificationById(ContactData contact) {
    wd.findElement(By.cssSelector("a[href$='" + contact.getId() + "'] img[title='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.cssSelector("input[name='selected[]']"));
  }

  public int countUI() {
    return wd.findElements(By.cssSelector("input[name='selected[]']")).size();
  }

  public List<ContactData> List() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
    }

    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
    }

    return new Contacts(contactCache);
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact);
    deleteSelectedContact();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact);
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }
}
