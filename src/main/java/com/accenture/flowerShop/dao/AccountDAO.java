package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.form.RegistrationForm;

public interface AccountDAO {


    public Account findAccount(String userName );




    void save(RegistrationForm registrationForm) throws Exception;
}