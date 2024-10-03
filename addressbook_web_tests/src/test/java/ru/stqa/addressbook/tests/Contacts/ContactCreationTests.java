package ru.stqa.addressbook.tests.Contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.List;


public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>(List.of());
        for (var firstname : (List.of("", "first name"))) {
            for (var middlename : List.of("middle name", "")) {
                for (var lastname : List.of("", "last name")) {
                    for (var company : List.of("Google", "")) {
                        for (var address : List.of("", "street, house")) {
                            for (var mobile : List.of("89558886622", "")) {
                                for (var email : List.of("", "thgf@hfd.ru")) {
                                    result.add(new ContactData(firstname, middlename, lastname, company, address, mobile, email));
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i < 6; i++) {
            result.add(new ContactData(randomString(i * 3), randomString(i * 3), randomString(i * 3),
                    randomString(i * 3), randomString(i * 3), randomString(i * 3), randomString(i * 3)));
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("first name'", "", "", "", "", "", "")));
        return result;
    }

    /*@Test
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
    }*/

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactCount = app.contact().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactCount = app.contact().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }
}
