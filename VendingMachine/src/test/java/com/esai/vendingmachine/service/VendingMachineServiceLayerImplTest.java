/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esai.vendingmachine.service;

import com.esai.vendingmachine.dao.ClassVendingMachineAuditDao;
import com.esai.vendingmachine.dao.ClassVendingMachineAuditDaoFileImpl;
import com.esai.vendingmachine.dao.VendingMachineDao;
import com.esai.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.io.FileWriter;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author herna
 */
public class VendingMachineServiceLayerImplTest {

    VendingMachineServiceLayer service;
    VendingMachineDao testDao;
    ClassVendingMachineAuditDao testAuditDao;

    public VendingMachineServiceLayerImplTest() {
    }

    @Test
    public void testGetProductList() throws Exception {
        // Assign service
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);
        testAuditDao = new ClassVendingMachineAuditDaoFileImpl();
        service = new VendingMachineServiceLayerImpl(testDao, testAuditDao);

        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);

        assertEquals(2, service.getProductList().size(), "Should have 2 product");
    }

    @Test
    public void testCalculatePurchase() throws Exception {
        // Assign service
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);
        testAuditDao = new ClassVendingMachineAuditDaoFileImpl();
        service = new VendingMachineServiceLayerImpl(testDao, testAuditDao);

        // Create sample test
        int productSelection = 1;
        BigDecimal userMoney = new BigDecimal(".50");
        
        // Create test clone
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);

        service.getProductList();

        try {
            service.calculatePurchase(userMoney, productSelection); //user money is not enough funds
        } catch (NoItemInventoryException e) {
            fail("Incorrect exception was thrown");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

}
