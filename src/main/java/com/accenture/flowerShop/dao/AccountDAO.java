package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.account.AccountCommerce;
import com.accenture.flowerShop.form.RegistrationForm;

public interface AccountDAO {


    Account findAccount(String userName );


    AccountCommerce findAccountCommerce(String login);

    Account save(RegistrationForm registrationForm) throws Exception;
}