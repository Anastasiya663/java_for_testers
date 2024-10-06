package ru.stqa.addressbook.model;

public record ContactData (String id, String firstName, String lastName) {

    public ContactData() {
        this("", "", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName);
    }

    public ContactData withNameAndLastName(String firstName, String lastName) {
        return new ContactData(this.id, firstName, lastName);
    }
}
