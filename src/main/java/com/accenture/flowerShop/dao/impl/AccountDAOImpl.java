package com.accenture.flowerShop.dao.impl;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.entity.account.AccountCommerce;
import com.accenture.flowerShop.entity.account.AccountContact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

// Transactional for Hibernate
@Transactional
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account findAccount(String login ) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("login", login));
        return (Account) crit.uniqueResult();
    }


    @Override
    public void save(RegistrationForm data) throws Exception{
        String login = data.getLogin();


        Account account = null;

        if (login != null) {
            account = this.findAccount(login);
        }
        if (account != null) {
            throw new Exception("Account already registered");
        }

        account = new Account();
        account.setLogin(login);
        account.setPassword(data.getPassword());
        account.setRole(Account.ROLE_CUSTOMER);
        account.setActive(true);
        AccountCommerce commerce = new AccountCommerce();
        commerce.setBalance(new BigDecimal(2000));
        commerce.setDiscount(5);
        commerce.setAccount(account);
        account.setCommerce(commerce);
        AccountContact contact = new AccountContact();
        contact.setAddress(data.getAddress());
        contact.setName(data.getName());
        contact.setSurname(data.getSurname());
        contact.setPatronymic(data.getPatronymic());
        contact.setPhone(data.getPhone());
        contact.setAccount(account);
        account.setContact(contact);
        this.sessionFactory.getCurrentSession().persist(account);
    }

}