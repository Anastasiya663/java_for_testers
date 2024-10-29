package ru.stqa.addressbook.tests.Contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.tests.TestBase;

import java.util.ArrayList;


public class ContactInfoTests extends TestBase {

//    @Test
//    void testPhones() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData()
//                    .withFirstName(CommonFunctions.randomString(4))
//                    .withLastName(CommonFunctions.randomString(5))
//                    .withAddress(CommonFunctions.randomString(6))
//                    .withEmail(CommonFunctions.randomString(7))
//                    .withHomePhone("757568585")  // ???
//                    .withMobilePhone("757568585545")
//            );
//        }
//        var contacts = app.hbm().getContactList();
//        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
//                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
//                        .filter(s -> s != null && !"".equals(s))
//                        .collect(Collectors.joining("\n"))
//        ));
//        var phones = app.contacts().getPhones();
//        Assertions.assertEquals(expected, phones);
//
////        for (var contact:contacts) {
////            var phones = app.contacts().getPhones(contact);
////            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
////                    .filter(s -> s != null && !"".equals(s))
////                    .collect(Collectors.joining("\n"));
////
////            Assertions.assertEquals(expected, phones.get(contact.id()));
////        }
////        var contact = contacts.get(0);
//    }
//
//    @Test
//    void testPostAddress() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData()
//                    .withFirstName(CommonFunctions.randomString(4))
//                    .withLastName(CommonFunctions.randomString(5))
//                    .withAddress(CommonFunctions.randomString(6))
//                    .withEmail(CommonFunctions.randomString(7))
//                    .withHomePhone("757568585")
//                    .withMobilePhone("75756851185")
//            );
//        }
//        var contacts = app.hbm().getContactList(); // сравнили с БД
//        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
//                Stream.of(contact.address())
//                        .filter(s -> s != null && !"".equals(s))
//                        .collect(Collectors.joining("\n"))
//        ));
//        var addresses = app.contacts().getAddress();
//        Assertions.assertEquals(expected, addresses);
//    }
//
//    @Test
//    void testEmails() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData()
//                    .withFirstName(CommonFunctions.randomString(4))
//                    .withLastName(CommonFunctions.randomString(5))
//                    .withAddress(CommonFunctions.randomString(6))
//                    .withEmail(CommonFunctions.randomString(7))
//                    .withHomePhone("757568585")
//                    .withMobilePhone("757568585")
//            );
//        }
//        var contacts = app.hbm().getContactList(); // сравнили с БД
//        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
//                Stream.of(contact.email(), contact.email2(), contact.email3())
//                        .filter(s -> s != null && !"".equals(s))
//                        .collect(Collectors.joining("\n"))
//        ));
//        var emails = app.contacts().getEmails();
//        Assertions.assertEquals(expected, emails);
//    }

    @Test
    void testAllAdditionalInfo() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(4))
                    .withLastName(CommonFunctions.randomString(5))
                    .withAddress(CommonFunctions.randomString(6))
                    .withEmail("bdbsb@45.ru")
                    .withEmail2("334@hfdh.com")
                    .withEmail3("fksdjnfk@ere.net")
                    .withHomePhone("4242611")
                    .withMobilePhone("757568585")
                    .withWorkPhone("750068580")
            );
        }
        var contacts = app.contacts().getList();
        var contact = contacts.get(0);

        ArrayList<String> addInfoFromUi = new ArrayList<>();
        addInfoFromUi.add(app.contacts().getPhones(contact));
        addInfoFromUi.add(app.contacts().getAddress(contact));
        addInfoFromUi.add(app.contacts().getEmails(contact));

        app.contacts().initContactModification(contact);

        ArrayList<String> addInfoFromModificationForm = new ArrayList<>();
        addInfoFromModificationForm.add(app.contacts().getPhonesFromModification(contact));
        addInfoFromModificationForm.add(app.contacts().getAddressFromModification(contact));
        addInfoFromModificationForm.add(app.contacts().getEmailsFromModification(contact));

        Assertions.assertEquals(addInfoFromUi, addInfoFromModificationForm);
    }
}
