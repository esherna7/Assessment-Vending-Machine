/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esai.vendingmachine.service;

import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author herna
 */
public class VendingMachineServiceLayerImplTest {

    VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
        service = new VendingMachineServiceLayerImpl();
    }

    @BeforeEach
    public void setUp() throws Exception {
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(3);
        service.getProductList().add(testClone);
    }

    @Test
    public void testCalculatePurchase() throws Exception {
        int productSelection = 1;
        BigDecimal userMoney = new BigDecimal(".50");
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);
        service.getProductList().add(testClone);
        try {
            service.calculatePurchase(userMoney, productSelection);
        } catch (NoItemInventoryException e) {
            fail("Incorrect exception was thrown");
        } catch (InsufficientFundsException e){
            return;
        }
    }

}
