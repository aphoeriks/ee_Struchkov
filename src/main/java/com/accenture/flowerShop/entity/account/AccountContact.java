package com.accenture.flowerShop.entity.account;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT_CONTACT")
public class AccountContact implements Serializable {
    private static final long serialVersionUID = -4201267223547700334L;
    @Id
    @Column(name = "LOGIN",length = 20)
    private String login;
    @Column(name = "NAME",length = 20,nullable = false)
    private String name;
    @Column(name = "SURNAME",length = 20,nullable = false)
    private String surname;
    @Column(name = "PATRONYMIC",length = 20,nullable = false)
    private String patronymic;
    @Column(name = "ADDRESS",length = 40,nullable = false)
    private String address;
    @Column(name = "PHONE", nullable = false,length = 13)
    private String phone;

    @OneToOne
    @MapsId
    private Account account;

    public AccountContact(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
