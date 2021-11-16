package com.esai.vendingmachine.ui;

import com.esai.vendingmachine.dto.Change;
import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.util.ArrayList;

/**
 *
 * @author Esai
 */
public class VendingMachineView {

    // handles print messages
    private UserIO io;
    // handles product list index
    private static int productIndex;

    // constructor assign io
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    // prints vending machine banner
    public void printMenuBanner() {
        io.print("Vending Machine");
        io.print("No.\t\tPrice\tInventory");
    }

    // prints list of products from vending machine
    public void printProductList(ArrayList<VendingMachineProduct> productList) {
        productIndex = 1;
        // lambda printing product info
        productList.forEach((product) -> {
            io.print(productIndex++ + ". " + product.getProductName() + "\t" +
                    product.getPrice() + "\t" + product.getInventoryCount());
        });
        // prints Exit option
        io.print(productIndex + ". Exit");
    }

    // display menu options and return user selection
    public int getSelection() {
        // return user selected option
        return io.readInt("Please enter the product number you want to purchase.");
    }

    // ask user for how much money they enter into vending machine
    // return user input as string
    public String getUserMoney() {
        return io.readString("How much money would you like to enter?");
    }
    
    //display users change from purchase
    public void displayUserChange(Change userChange){
        io.print("\nChange");
        io.print("Quarters: " + userChange.getQuarterCount());
        io.print("Dimes: " + userChange.getDimeCount());
        io.print("Nickels: " + userChange.getNickelCount());
        io.print("Penny: " + userChange.getPennyCount());
    }
}
