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
    type(By.name("mobile"), contactData.getPhoneMobile());
    type(By.name("work"), contactData.getPhoneWork());
    type(By.name("email"), contactData.getEmailFirst());
    type(By.name("email2"), contactData.getEmailSecond());
    type(By.name("email3"), contactData.getEmailThird());

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
    wd.findElement(By.cssSelector(String.format("a[href$='%s'] img[title='Edit']", contact.getId()))).click();
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
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
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
      String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
              .withAllPhones(allPhones).withAllEmails(allEmails));
    }

    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact);
    String firstname = wd.findElement(By.cssSelector("input[name='firstname']")).getAttribute("value");
    String lastname = wd.findElement(By.cssSelector("input[name='lastname']")).getAttribute("value");
    String address = wd.findElement(By.cssSelector("textarea[name='address']")).getAttribute("value");
    String phoneHome = wd.findElement(By.cssSelector("input[name='home']")).getAttribute("value");
    String phoneMobile = wd.findElement(By.cssSelector("input[name='mobile']")).getAttribute("value");
    String phoneWork = wd.findElement(By.cssSelector("input[name='work']")).getAttribute("value");

    String emailFirst = wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
    String emailSecond = wd.findElement(By.cssSelector("input[name='email2']")).getAttribute("value");
    String emailThird = wd.findElement(By.cssSelector("input[name='email3']")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withFirstName(firstname).withLastName(lastname).withAddress(address).withPhoneHome(phoneHome)
            .withPhoneMobile(phoneMobile).withPhoneWork(phoneWork).withEmailFirst(emailFirst).withEmailSecond(emailSecond)
            .withEmailThird(emailThird);


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
