package com.esai.vendingmachine.service;

import com.esai.vendingmachine.dao.ClassVendingMachineAuditDao;
import com.esai.vendingmachine.dao.ClassVendingMachineDaoException;
import com.esai.vendingmachine.dao.ClassVendingMachinePersistenceException;
import com.esai.vendingmachine.dao.VendingMachineDao;
import com.esai.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.esai.vendingmachine.dto.Change;
import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Esai
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    // allow access to doa
    private VendingMachineDao dao;
    // allow access to auditDao
    private ClassVendingMachineAuditDao auditDao;

    // constructor assigning dao and auditdao
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, ClassVendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    // access dao to return arraylist of products in vending machine
    @Override
    public ArrayList<VendingMachineProduct> getProductList() throws ClassVendingMachineDaoException {
        return dao.getProductList();
    }

    // calculate change via Change and return Change object to view
    @Override
    public Change calculatePurchase(BigDecimal userMoney, int productSelection) throws InsufficientFundsException, NoItemInventoryException, ClassVendingMachineDaoException, ClassVendingMachinePersistenceException {
        // call dao to get selected product info from txt file
        VendingMachineProduct selectedProduct;
        selectedProduct = dao.getSelectedProduct(productSelection);
        // throw exception if not enough inventory
        if (selectedProduct.getInventoryCount() == 0) {
            throw new NoItemInventoryException("Product not available in inventory");
        }
        // throw exception if not enough funds to purchase product
        if (userMoney.compareTo(selectedProduct.getPrice()) == -1) {
            throw new InsufficientFundsException("Insufficient Funds\n User Funds: " + userMoney);
        } // create Change and calculate change and assign amount of coins based on change
        // return Change object to view
        else {
            BigDecimal change = userMoney.subtract(selectedProduct.getPrice());
            Change userChange = new Change();
            userChange.calculateChange(change);
            dao.subtractProduct(selectedProduct);
            return userChange;
        }

    }
}
