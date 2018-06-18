package ru.stqa.train.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Transient
  private String middlename;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String phoneHome;

  @Column(name = "mobile")
  @Type(type = "text")
  private String phoneMobile;

  @Column(name = "work")
  @Type(type = "text")
  private String phoneWork;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String emailFirst;

  @Column(name = "email2")
  @Type(type = "text")
  private String emailSecond;

  @Column(name = "email3")
  @Type(type = "text")
  private String emailThird;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Transient
  private File photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(phoneHome, that.phoneHome) &&
            Objects.equals(emailFirst, that.emailFirst);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstname, middlename, lastname, address, phoneHome, phoneMobile, phoneWork, emailFirst, emailSecond, emailThird);
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
            ", phoneMobile='" + phoneMobile + '\'' +
            ", phoneWork='" + phoneWork + '\'' +
            ", emailFirst='" + emailFirst + '\'' +
            ", emailSecond='" + emailSecond + '\'' +
            ", emailThird='" + emailThird + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", photo=" + photo +
            ", groups=" + groups +
            '}';
  }

  public int getId() {
    return id;
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

  public String getPhoneMobile() {
    return phoneMobile;
  }

  public String getPhoneWork() {
    return phoneWork;
  }

  public String getEmailFirst() {
    return emailFirst;
  }

  public String getEmailSecond() {
    return emailSecond;
  }

  public String getEmailThird() {
    return emailThird;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    return photo;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddleName(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhoneHome(String phoneHome) {
    this.phoneHome = phoneHome;
    return this;
  }

  public ContactData withPhoneMobile(String phoneMobile) {
    this.phoneMobile = phoneMobile;
    return this;
  }

  public ContactData withPhoneWork(String phoneWork) {
    this.phoneWork = phoneWork;
    return this;
  }

  public ContactData withEmailFirst(String emailFirst) {
    this.emailFirst = emailFirst;
    return this;
  }

  public ContactData withEmailSecond(String emailSecond) {
    this.emailSecond = emailSecond;
    return this;
  }

  public ContactData withEmailThird(String emailThird) {
    this.emailThird = emailThird;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public void withGroups(Set<GroupData> groups) {
    this.groups = groups;
  }

  public ContactData inGroups(GroupData group) {
    groups.add(group);
    return this;
  }
}
