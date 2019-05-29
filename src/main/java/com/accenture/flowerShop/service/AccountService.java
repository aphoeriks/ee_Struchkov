package com.accenture.flowerShop.service;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.form.RegistrationForm;
import com.accenture.flowerShop.session.SessionScopeAccountData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    SessionScopeAccountData accountData;
    public Account save(RegistrationForm form) throws Exception{
        String login = form.getLogin();
        if (login != null) {
            if (accountDAO.findAccount(login).isPresent()) {
                throw new Exception("Аккаунт уже зарегестрирован");
            }
        }
        Account account = new Account(login, form.getPassword(), Account.ROLE_CUSTOMER,
                form.getAddress(),form.getPhone(),
                form.getName(),form.getSurname(),form.getPatronymic());
        accountDAO.createAccount(account);
        return  account;
    }
    public void initialiseSession(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountData.initialize(accountDAO.findAccount(userDetails.getUsername()).get());
    }
    public boolean loginIsFree(String login){
        return !accountDAO.findAccount(login).isPresent();
    }
}
