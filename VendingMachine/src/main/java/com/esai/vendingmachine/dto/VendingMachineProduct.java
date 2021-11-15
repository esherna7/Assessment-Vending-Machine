package com.esai.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Esai
 */
public class VendingMachineProduct {

    // properties of Vending Machine items
    String productName;
    BigDecimal price;
    int inventoryCount;

    // return item Name
    public String getProductName() {
        return productName;
    }

    // set item Name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // return item price
    public BigDecimal getPrice() {
        return price;
    }

    // set item price
    public void setProductPrice(String price) {
        this.price = new BigDecimal(price);
    }

    // return inventory count
    public int getInventoryCount() {
        return inventoryCount;
    }

    // set inventory count
    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
