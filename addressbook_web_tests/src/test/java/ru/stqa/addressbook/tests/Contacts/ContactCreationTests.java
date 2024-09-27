package ru.stqa.addressbook.tests.Contacts;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.tests.TestBase;


public class ContactCreationTests extends TestBase {

    @Test
    public void createContact() {
        app.contact().createContact(new ContactData("first name", "middle name", "last name", "Google", "street, house", "89558886622", "thgf@hfd.ru"));
    }

    @Test
    public void canCreateEmptyContact() {
        app.contact().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithNameAndAddress() {
        app.contact().createContact(new ContactData().withNameAndAddress("first name", "last name", "street, house"));
    }
}
