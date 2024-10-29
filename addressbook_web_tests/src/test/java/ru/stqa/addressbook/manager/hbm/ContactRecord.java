package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String middlename;
    public String address;
    public String email;
    public String email2;
    public String email3;
    public String home;
    public String mobile;
    public String work;
    public String phone2;


    public ContactRecord() {
    }
    public ContactRecord(int id,
                         String firstname,
                         String lastname,
                         String address,
                         String email
//                         String home,
//                         String mobile,
//                         String work,
//                         String phone2
                        ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
//        this.home = home;
//        this.mobile = mobile;
//        this.work = work;
//        this.phone2 = phone2;
    }
}
