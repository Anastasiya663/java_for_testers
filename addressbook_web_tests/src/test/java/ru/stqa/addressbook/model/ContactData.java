package ru.stqa.addressbook.model;

public record ContactData (String firstName, String middleName, String lastName, String company, String address, String mobile, String email) {

    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withNameAndAddress(String firstName, String lastName, String address) {
        return new ContactData(firstName, this.middleName, lastName, this.company, address, this.mobile, this.email);
    }
}
