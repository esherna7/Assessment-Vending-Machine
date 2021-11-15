package com.esai.vendingmachine.dao;

import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.util.ArrayList;

/**
 *
 * @author Esai
 */
public interface VendingMachineDao {

    ArrayList<VendingMachineProduct> getProductList() throws ClassVendingMachineDaoException;

    VendingMachineProduct getSelectedProduct(int selection);

    void subtractProduct(VendingMachineProduct productToSubtract) throws ClassVendingMachineDaoException, ClassVendingMachinePersistenceException;
            
}
