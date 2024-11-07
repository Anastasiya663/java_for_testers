package ru.stqa.addressbook.tests.Contacts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.TestBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>(List.of());
       /* for (var firstname : (List.of("", "first name"))) {
            for (var lastname : List.of("", "last name")) {
                result.add(new ContactData().withFirstName(firstname).withLastName(lastname));
            }
        }*/
        var json = Files.readString(Paths.get("contacts.json")); //читает файл целиком
        var mapper = new ObjectMapper(); // или YAMLMapper(); или XmlMapper(); соответственно
        var value = mapper.readValue(json,  new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "first name'", "lastName", "", "", "", "", "", "", "", "", "")));
        return result;
    }

    public static List<ContactData> singleRandomContact() throws IOException {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(6))
                .withAddress(CommonFunctions.randomString(7))
                .withEmail(CommonFunctions.randomString(8)));
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
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);

        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(6))
                .withAddress(CommonFunctions.randomString(7))
                .withEmail(CommonFunctions.randomString(8));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        var maxId = newRelated.get(newRelated.size() - 1).id();

        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }

    @Test
    public void canAddContactInGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().getFirst();

        if (app.hbm().getContactCount() == 0 || app.contacts().contactsOutOfGroups() == 0) {
            app.contacts().createContact(new ContactData()
                            .withFirstName(CommonFunctions.randomString(5))
                            .withLastName(CommonFunctions.randomString(6))
                            .withAddress(CommonFunctions.randomString(7))
                            .withEmail(CommonFunctions.randomString(8)));
        }

        var contact = app.hbm().getContactList().getFirst();
        var oldRelated = app.hbm().getContactsInGroup(group);

        if (oldRelated.contains(contact)) {
            contact = app.hbm().getContactList().getLast();
            app.contacts().addContactInGroup(group, contact);
        } else {
            app.contacts().addContactInGroup(group, contact);
        }

        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        var maxId = newRelated.get(newRelated.size() - 1).id();

        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Assertions.assertEquals(newContacts, oldContacts);
    }
}
