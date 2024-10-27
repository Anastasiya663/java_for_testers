package ru.stqa.addressbook.tests.Contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(4))
                    .withLastName(CommonFunctions.randomString(5))
                    .withAddress(CommonFunctions.randomString(6))
                    .withEmail(CommonFunctions.randomString(7))
                    .withHomePhone(CommonFunctions.randomInt(6))  // ???
                    .withMobilePhone(CommonFunctions.randomInt(7))
            );
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);

//        for (var contact:contacts) {
//            var phones = app.contacts().getPhones(contact);
//            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
//                    .filter(s -> s != null && !"".equals(s))
//                    .collect(Collectors.joining("\n"));
//
//            Assertions.assertEquals(expected, phones.get(contact.id()));
//        }
//        var contact = contacts.get(0);
    }
}
