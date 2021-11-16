package com.esai.vendingmachine.controller;

import com.esai.vendingmachine.dao.ClassVendingMachineDaoException;
import com.esai.vendingmachine.dao.ClassVendingMachinePersistenceException;
import com.esai.vendingmachine.dto.Change;
import com.esai.vendingmachine.service.InsufficientFundsException;
import com.esai.vendingmachine.service.NoItemInventoryException;
import com.esai.vendingmachine.service.VendingMachineServiceLayer;
import com.esai.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.esai.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;

/**
 *
 * @author Esai
 */
public class VendingMachineController {

    // handles ui and user interaction
    private VendingMachineView view;
    // handles change and purchases and can access doa
    private VendingMachineServiceLayer service;

    // constructor assigning view and service
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }
    
    // holds user money input
    BigDecimal userMoney = new BigDecimal("0.00");

    public void run() throws InsufficientFundsException, NoItemInventoryException, ClassVendingMachineDaoException, ClassVendingMachinePersistenceException {
        int menuSelection = 0;

        // display menu banner
        displayMenuBanner();
        // display products in vending machine
        displayProductList();
        // gets users inputted money
        userMoney = getUserMoney();
        // get menu selection from user
        menuSelection = getMenuSelection();
        // do case based on selected option
        switch (menuSelection) {
            // Purchase Twix Bar
            case 1:
                purchaseProduct(menuSelection);
                break;
            // Purchase M&Ms
            case 2:
                purchaseProduct(menuSelection);
                break;
            // Purchase KitKat
            case 3:
                purchaseProduct(menuSelection);
                break;
            // Purchase Crunch
            case 4:
                purchaseProduct(menuSelection);
                break;
            // Purchase Five Gum
            case 5:
                purchaseProduct(menuSelection);
                break;
            // Purchase Nerds Candy
            case 6:
                purchaseProduct(menuSelection);
                break;
            // exit
            case 7:
                break;
        }

    }

    // Display Menu banner
    private void displayMenuBanner() {
        view.printMenuBanner();
    }

    // display products in vending machine
    private void displayProductList() throws ClassVendingMachineDaoException {
        view.printProductList(service.getProductList());
    }

    // return user money input from view
    private BigDecimal getUserMoney() {
        // convert string to BigDecimal
        return new BigDecimal(view.getUserMoney());
    }

    // calls View to print out Menu and read user input selection
    // returns the users input to determine menu selection
    private int getMenuSelection() {
        return view.getSelection();
    }

    // purchase product based on user selected option
    // calls service to calculate purchase and is returned Change object containing change
    // send Change to view to display change
    private void purchaseProduct(int menuSelection) throws InsufficientFundsException, NoItemInventoryException, ClassVendingMachineDaoException, ClassVendingMachinePersistenceException {
        Change userChange = service.calculatePurchase(userMoney, menuSelection);
        view.displayUserChange(userChange);
    }
}
