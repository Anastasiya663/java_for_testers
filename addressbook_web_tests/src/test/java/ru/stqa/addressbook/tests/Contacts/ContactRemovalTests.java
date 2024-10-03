package ru.stqa.addressbook.tests.Contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.tests.TestBase;


public class ContactRemovalTests extends TestBase {

  @Test
  public void canRemoveContact() {
    if (app.contact().getCount() == 0) {
      app.contact().createContact(new ContactData());
    }
    int contactCount = app.contact().getCount();
    app.contact().removeContact();
    int newContactCount = app.contact().getCount();
    Assertions.assertEquals(contactCount - 1, newContactCount);
  }

  @Test
  public void canRemoveAllContacts() {
    if (app.contact().getCount() == 0) {
      app.contact().createContact(new ContactData());
    }
    app.contact().removeAllContact();
    Assertions.assertEquals(0, app.contact().getCount());
  }
}
