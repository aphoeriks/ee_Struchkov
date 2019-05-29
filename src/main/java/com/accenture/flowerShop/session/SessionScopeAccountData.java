package com.accenture.flowerShop.session;

import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.model.CartInfo;
import com.accenture.flowerShop.model.CartLine;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Scope (value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopeAccountData {
    private BigDecimal balance;
    private String login;
    private int discount;
    private CartInfo cartInfo;
    private boolean initialized;
    public SessionScopeAccountData(){
        this.initialized = false;
    }
    public void initialize(Account account){
        if(account.getRole().equals(Account.ROLE_CUSTOMER)){
            this.balance = account.getBalance();
            this.discount = account.getDiscount();
        }
        this.cartInfo = new CartInfo();
        this.login = account.getLogin();
        this.initialized = true;
    }
    public BigDecimal getCartPrice(){
        long out = 0;
        for (CartLine line: cartInfo.getCartLines()) {
            out += (line.getPrice().doubleValue()*100*line.getQuantity());
        }
        return new BigDecimal(out*(100-discount)/10000.0).setScale(2, RoundingMode.HALF_DOWN);
    }
    public void clearCart(){
        cartInfo = new CartInfo();
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    public CartInfo getCartInfo() {
        return cartInfo;
    }

    public void setCartInfo(CartInfo cartInfo) {
        this.cartInfo = cartInfo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
