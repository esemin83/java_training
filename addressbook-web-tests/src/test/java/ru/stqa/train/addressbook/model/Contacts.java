package ru.stqa.train.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts(Collection<ContactData> contacts) {
    this.delegate = new HashSet<ContactData>(contacts);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

  public Contacts contactsNotInGroup() {
    Contacts cntNotInGroup = new Contacts();
    for (ContactData contact : this) {
      if (contact.getGroups().size() == 0) {
        cntNotInGroup.add(contact);
      }
    }
    return cntNotInGroup;
  }

  public Contacts contactsInGroup() {
    Contacts cntInGroup = new Contacts();
    for (ContactData contact : this) {
      if (contact.getGroups().size() != 0) {
        cntInGroup.add(contact);
      }
    }
    return cntInGroup;
  }
}
