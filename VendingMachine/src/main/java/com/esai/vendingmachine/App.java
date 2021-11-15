package com.esai.vendingmachine;

import com.esai.vendingmachine.controller.VendingMachineController;
import com.esai.vendingmachine.dao.ClassVendingMachineDaoException;
import com.esai.vendingmachine.dao.ClassVendingMachinePersistenceException;
import com.esai.vendingmachine.service.InsufficientFundsException;
import com.esai.vendingmachine.service.NoItemInventoryException;

/**
 *
 * @author Esai
 */
public class App {
    
    public static void main(String[] args) throws InsufficientFundsException, NoItemInventoryException, ClassVendingMachineDaoException, ClassVendingMachinePersistenceException{
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}
