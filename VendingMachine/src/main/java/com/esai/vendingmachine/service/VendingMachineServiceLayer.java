package com.esai.vendingmachine.service;

import com.esai.vendingmachine.dao.ClassVendingMachineDaoException;
import com.esai.vendingmachine.dao.ClassVendingMachinePersistenceException;
import com.esai.vendingmachine.dto.Change;
import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Esai
 */
public interface VendingMachineServiceLayer {
    
    ArrayList<VendingMachineProduct> getProductList() throws ClassVendingMachineDaoException;
    
    Change calculatePurchase(BigDecimal userMoney, int productSelection) throws InsufficientFundsException, NoItemInventoryException, ClassVendingMachineDaoException, ClassVendingMachinePersistenceException;
}
