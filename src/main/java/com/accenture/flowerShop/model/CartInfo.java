package com.accenture.flowerShop.model;

import java.util.ArrayList;
import java.util.List;
public class CartInfo {
    private List<CartLine> cartLines;
    public CartInfo(){
        this.cartLines = new ArrayList<>();
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public void addCartLine(CartLine inLine){
        for (CartLine cartLine : this.cartLines) {
            if (cartLine.getName().equals(inLine.getName())) {
                cartLine.setQuantity(cartLine.getQuantity()+inLine.getQuantity());
                return;
            }
        }
        this.cartLines.add(inLine);
    }
}
