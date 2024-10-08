package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper (ApplicationManager manager) {
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

    public void removeContact(ContactData contact) {
        openContactsPage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        selectContact(contact);
        initContactModification();
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification() {
        click(By.cssSelector("[title='Edit']"));
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
}
