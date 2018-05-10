package ru.stqa.train.addressbook;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String phoneHome;
  private final String emailFirst;

  public ContactData(String firstname, String middlename, String lastname, String address, String phoneHome, String emailFirst) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.phoneHome = phoneHome;
    this.emailFirst = emailFirst;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneHome() {
    return phoneHome;
  }

  public String getEmailFirst() {
    return emailFirst;
  }
}
