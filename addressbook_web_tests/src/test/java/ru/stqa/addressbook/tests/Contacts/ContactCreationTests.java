package ru.stqa.addressbook.tests.Contacts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;
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
                new ContactData("", "first name'", "lastName", "")));
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
        var oldContacts = app.contact().getList();
        app.contact().createContact(contact);

        var newContacts = app.contact().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withFirstName(contact.firstName()).withLastName(contact.lastName()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contact().getList();
        app.contact().createContact(contact);
        var newContacts = app.contact().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }
}
