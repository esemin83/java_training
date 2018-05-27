package ru.stqa.train.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String phoneHome;
  private final String emailFirst;
  private String group;


  public ContactData(int id, String firstname, String middlename, String lastname, String address, String phoneHome,
                     String emailFirst, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.phoneHome = phoneHome;
    this.emailFirst = emailFirst;
    this.group = group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", phoneHome='" + phoneHome + '\'' +
            ", emailFirst='" + emailFirst + '\'' +
            ", group='" + group + '\'' +
            '}';
  }

  public ContactData(String firstname, String middlename, String lastname, String address, String phoneHome,
                     String emailFirst, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.phoneHome = phoneHome;
    this.emailFirst = emailFirst;
    this.group = group;
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

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstname, lastname);
  }
}
