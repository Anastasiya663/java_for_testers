package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.linkText("add new"))) {
            click(By.linkText("home"));
        }
    }

    /*public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }*/

    public void createContact(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        openContactsPage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void initContactModification(ContactData contact) {
        click(By.xpath(".//td[8]/a[contains(@href,'" + contact.id() + "')]/img[@alt='Edit']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        //attach(By.name("photo"), contact.photo());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void removeAllContact() {
        openContactsPage();
        selectAllContact();
        removeSelectedContact();
    }

    private void selectAllContact() {
        click(By.xpath("//input[@id='MassCB']"));
    }

    public List<ContactData> getList() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var tr = manager.driver.findElements(By.name("entry"));
        for (var t : tr) {
            var cell = t.findElements(By.tagName("td"));
            var lastname = cell.get(1).getText();
            var firstname = cell.get(2).getText();
            var checkbox = t.findElement(By.cssSelector("td.center input"));
            var id = checkbox.getAttribute("id");
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    public void addContactInGroup(GroupData group, ContactData contact) {
        returnToHomePage();
        selectContact(contact);
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
        click(By.xpath("//input[@name='add']"));
        returnToHomePage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openContactsPage();
        selectGroupForRemoveContact(group);
        selectContact(contact);
        removeSelectedContactFromGroup();
        returnToHomePage();
    }

    private void removeSelectedContactFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    private void selectGroupForRemoveContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public List<ContactData> getGroupWithContacts(GroupData group) {
        returnToHomePage();
        selectGroupForRemoveContact(group);
        return getList();
    }

    public int contactsOutOfGroups() {
        returnToHomePage();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue("[none]");
        return getList().size();
    }

    public String getPhones(ContactData contact) { // для конкретного контакта
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() { //для всех контактов
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var addresses = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, addresses);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }


    public String getPhonesFromModification(ContactData contact) {
        var homeFromModification = manager.driver.findElement(By.name("home")).getAttribute("value");
        var mobileFromModification = manager.driver.findElement(By.name("mobile")).getAttribute("value");
        var workFromModification = manager.driver.findElement(By.name("work")).getAttribute("value");
        return Stream.of(homeFromModification, mobileFromModification, workFromModification)
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
    }

    public String getAddressFromModification(ContactData contact) {
        return manager.driver.findElement(By.name("address")).getAttribute("value");
    }

    public String getEmailsFromModification(ContactData contact) {
        var emailFromModification = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2FromModification = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3FromModification = manager.driver.findElement(By.name("email3")).getAttribute("value");
        return Stream.of(emailFromModification, email2FromModification, email3FromModification)
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
    }
}
