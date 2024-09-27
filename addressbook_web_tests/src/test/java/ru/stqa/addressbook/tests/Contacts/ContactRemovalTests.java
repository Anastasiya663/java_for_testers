package ru.stqa.addressbook.tests.Contacts;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.tests.TestBase;


public class ContactRemovalTests extends TestBase {

  @Test
  public void canRemoveContact() {
    if (!app.contact().isContactPresent()) {
      app.contact().createContact(new ContactData());
    }
    app.contact().removeContact();
  }
}
