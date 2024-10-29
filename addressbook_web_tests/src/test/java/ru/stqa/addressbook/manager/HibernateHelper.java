package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=ConvertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
//        List<GroupData> result = new ArrayList<>();
//        for(var record : records) {
//            result.add(convert(record));
//        }
//        return result;
    }

    private static GroupData convert(GroupRecord record) { //конвертирует одну запись в объект типа GroupData (из GroupRecord)
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) { //конвертирует одну запись в объект типа GroupRecord (из GroupData)
        var id = data.id();
        if("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }
//для контактов
    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(c -> convert(c)).collect(Collectors.toList());
//        List<ContactData> result = new ArrayList<>();
//        for(var record : records) {
//            result.add(convert(record));
//        }
//        return result;
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData().withId("" + record.id)
                .withFirstName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withHomePhone(record.home)
                .withMobilePhone(record.mobile)
                .withWorkPhone(record.work)
                .withSecondaryPhone(record.phone2);
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.address(), data.email());
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) { //по идентификатору группы ищем все контакты, включенные в эту группу
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
