package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.account.AccountCommerce;
import com.accenture.flowerShop.form.RegistrationForm;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AccountDAO {


    Optional<Account> findAccount(String userName );



    void updateDiscount(String login, int discount);

    Account save(RegistrationForm registrationForm) throws Exception;
}