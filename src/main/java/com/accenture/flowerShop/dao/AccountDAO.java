package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.account.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountDAO {


    Optional<Account> findAccount(String userName );



    void updateDiscount(String login, int discount);


    @Transactional
    Account createAccount(Account account) throws Exception;

    @Transactional
    Account updateAccount(Account account);
}