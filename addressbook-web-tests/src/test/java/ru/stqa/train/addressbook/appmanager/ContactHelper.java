package ru.stqa.train.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.train.addressbook.model.ContactData;
import ru.stqa.train.addressbook.model.Contacts;
import ru.stqa.train.addressbook.model.GroupData;

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
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
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

  public void openContactDetailsById(ContactData contact) {
    wd.findElement(By.xpath(String.format("//input[@id='%s']/../..//img[@title='Details']", contact.getId()))).click();
  }

  public Boolean checkPicIsAttached(ContactData contact) {
    openContactDetailsById(contact);
    return isElementPresent(By.xpath("//img[@alt='Embedded Image']"));
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

  public void deleteAll(int size) {
    for (int i = 0; i < size; i++) {
      selectContact(i);
    }
    deleteSelectedContact();
    contactCache = null;
  }

  public void addContactToGroup(ContactData contact, GroupData group) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    selectContactById(contact);
    click(By.cssSelector("input[name='add']"));
    isElementPresent(By.xpath("//div[@class='msgbox'][contains(.,'Users added.')]"));
  }

  public void removeContactFromGroup(ContactData contactToDelete, GroupData groupToDelete) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupToDelete.getName());
    selectContactById(contactToDelete);
    click(By.cssSelector("input[name='remove']"));
    isElementPresent(By.xpath("//div[@class='msgbox'][contains(.,'Users removed.')]"));
  }

  public Boolean uiCheckContactAddedToGroup(ContactData cntToAdd, GroupData grpToAdd) {
    if (!wd.findElement(By.name("to_group")).getText().equals(grpToAdd.getName())) {
      new Select(wd.findElement(By.name("group"))).selectByVisibleText(grpToAdd.getName());
    }
    return isElementPresent(By.id(String.format("%s", cntToAdd.getId())));
  }

  public Boolean uiCheckContactRemovedFromGroup(ContactData contactToRemove, GroupData groupToRemove) {
    if (!wd.findElement(By.name("to_group")).getText().equals(groupToRemove.getName())) {
      new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupToRemove.getName());
    }
    return (!isElementPresent(By.id(String.format("%s", contactToRemove.getId()))));
  }
}

