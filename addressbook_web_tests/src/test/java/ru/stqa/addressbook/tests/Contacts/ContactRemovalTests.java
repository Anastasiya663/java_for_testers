package ru.stqa.addressbook.tests.Contacts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        List<ContactData> oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));

        List<ContactData> newContacts = app.hbm().getContactList();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData()
                            .withFirstName(CommonFunctions.randomString(5))
                            .withLastName(CommonFunctions.randomString(6))
                            .withAddress(CommonFunctions.randomString(7))
                            .withEmail(CommonFunctions.randomString(8)),
                    group);
        }

        var contact = app.hbm().getContactList().get(0);
        app.contacts().addContactInGroup(group, contact);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = app.contacts().getGroupWithContacts(group);
        expectedList.sort(compareById);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
        Assertions.assertEquals(newRelated, expectedList);
    }

    @Test
    public void canRemoveAllContacts() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        app.contacts().removeAllContact();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }
}
