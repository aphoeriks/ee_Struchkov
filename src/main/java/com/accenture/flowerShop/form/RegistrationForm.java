package com.accenture.flowerShop.form;


import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
public class RegistrationForm {
    @Size(min=4, max=20)
    private String login;
    @Size(min=4, max=20)
    private String password;
    private String matchingPassword;
    @AssertTrue(message = "пароли должны быть одинаковыми")
    private boolean equalPasswords;
    @Size(min=4, max=20)
    private String address;
    @Size(min=4, max=20)
    private String phone;
    @Size(min=2, max=20)
    private String name;
    @Size(min=2, max=20)
    private String surname;
    @Size(min=2, max=20)
    private String patronymic;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.isEqualPasswords();
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
        this.isEqualPasswords();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isEqualPasswords() {
        try{this.equalPasswords = this.password.equals(this.matchingPassword);}
        catch(Exception e){}
        return this.equalPasswords;
    }

    public void setEqualPasswords(boolean equalPasswords) {
        this.equalPasswords = equalPasswords;
    }
}
