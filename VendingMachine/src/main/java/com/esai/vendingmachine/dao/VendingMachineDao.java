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

    ArrayList<VendingMachineProduct> loadProduct() throws ClassVendingMachineDaoException;

    VendingMachineProduct unmarshallProduct(String vendingMachineProduct);

    String marshallProduct(VendingMachineProduct product);

    void writeProduct() throws ClassVendingMachineDaoException;
}
