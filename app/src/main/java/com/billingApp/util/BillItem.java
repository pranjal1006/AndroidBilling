package com.billingApp.util;

import android.widget.TextView;

public class BillItem {

    private Integer sn;
    private String itemName;
    private Integer quantity;
    private Double rate;
    private Double amount;

    public BillItem(Integer sn, String itemName, Integer quantity, Double rate) {
        this.sn = sn;
        this.itemName = itemName;
        this.quantity = quantity;
        this.rate = rate;
        amount = rate * quantity;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
