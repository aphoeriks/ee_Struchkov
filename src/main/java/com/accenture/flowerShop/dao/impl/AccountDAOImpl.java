package com.accenture.flowerShop.dao.impl;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.account.AccountCommerce;
import com.accenture.flowerShop.entity.account.AccountContact;
import com.accenture.flowerShop.form.RegistrationForm;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
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
        Session session = sessionFactory.getCurrentSession();
        Account a= session.find(Account.class, login);
        return Optional.of(a);
    }

    @Override
    public void updateDiscount(String login, int discount) {
        Account account = findAccount( login).get();
        account.getCommerce().setDiscount(discount);
        sessionFactory.getCurrentSession().update(account);
    }
    @Override
    public Account save(RegistrationForm data) throws Exception{
        String login = data.getLogin();
        if (login != null) {
            if (this.findAccount(login).isPresent()) {
                throw new Exception("Аккаунт уже зарегестрирован");
            }
        }

//todo constructor
        AccountContact contact = new AccountContact(data.getContact());
        AccountCommerce commerce = new AccountCommerce();
        Account account = new Account(login, data.getPassword(), Account.ROLE_CUSTOMER, contact,commerce);
        this.sessionFactory.getCurrentSession().persist(account);
        return  account;
    }


}