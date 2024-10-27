package ru.stqa.addressbook.model;

public record ContactData (
        String id,
        String firstName,
        String lastName,
        String photo,
        String address,
        String email,
        String home,
        String mobile,
        String work,
        String secondary) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, photo, this.address, this.email, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, address, this.email, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.address, email, this.home, this.mobile, this.work, this.secondary);
    }

    public ContactData withHomePhone(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.address, this.email, home, this.mobile, this.work, this.secondary);
    }
    public ContactData withMobilePhone(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.address, this.email, this.home, mobile, this.work, this.secondary);
    }
    public ContactData withWorkPhone(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, work, this.secondary);
    }
    public ContactData withSecondaryPhone(String secondary) {
        return new ContactData(this.id, this.firstName, this.lastName, this.photo, this.address, this.email, this.home, this.mobile, this.work, secondary);
    }
}
