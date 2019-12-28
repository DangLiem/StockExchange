/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchange;

import java.util.Comparator;

/**
 *
 * @author dangliem
 */
public class StockOrder {
    private Integer userId;
    private Integer total;
    private Integer price;

    public StockOrder(Integer userId, Integer total, Integer price) {
        this.userId = userId;
        this.total = total;
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UserID: " + this.userId + ", total: " + this.total + ", price: " + this.price; 
    }
    
    
}
