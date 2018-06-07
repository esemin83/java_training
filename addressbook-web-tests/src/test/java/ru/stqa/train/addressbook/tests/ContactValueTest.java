package ru.stqa.train.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.train.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactValueTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("First name").withMiddleName("Middle name")
              .withLastName("Last name")
              .withAddress("Address ## modified 345453 $$ + - 4")
              .withPhoneHome("+8(495)1111111")
              .withPhoneMobile("4-35-4543")
              .withPhoneWork("1 2123 334 54")
              .withEmailFirst("example!45@mail.com")
              //.withEmailSecond("exads11sa=_e!45@mail.com")
              .withEmailThird("741##8900@mail.com"), false);
    }
    app.goTo().HomePage();
  }

  @Test
  public void contactValuesTest() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {

    return Arrays.asList(contact.getEmailFirst(), contact.getEmailSecond(), contact.getEmailThird())
    .stream().filter((s) -> ! s.equals(""))
    .collect(Collectors.joining("\n"));
  }


  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
    .stream().filter((s) -> ! s.equals(""))
            .map(ContactValueTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
