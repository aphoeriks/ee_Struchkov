package com.accenture.flowerShop.dao.impl;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.form.RegistrationForm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// Transactional for Hibernate

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Account> findAccount(String login ) {
        //todo
        Account a= sessionFactory.getCurrentSession().find(Account.class, login);
        return Optional.ofNullable(a);
    }
@Transactional
    @Override
    public void updateDiscount(String login, int discount) {
        Account account = findAccount( login).get();
        account.setDiscount(discount);
        sessionFactory.getCurrentSession().update(account);
    }
    @Transactional
    @Override
    public Account createAccount(Account account) throws Exception {
        sessionFactory.getCurrentSession().persist(account);
        return account;
    }
    @Transactional
    @Override
    public Account updateAccount(Account account){
        sessionFactory.getCurrentSession().update(account);
        return account;
    }


}