package ru.stqa.addressbook.model;

public record ContactData (String id, String firstName, String lastName, String photo) {

    public ContactData() {
        this("", "", "", "");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.photo);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.photo);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, photo);
    }
}
