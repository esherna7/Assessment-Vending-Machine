/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esai.vendingmachine.dao;

import com.esai.vendingmachine.dto.VendingMachineProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author herna
 */
public class VendingMachineDaoFileImplTest {

    private VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineDaoFileImplTest() {
    }
    
    @BeforeAll
    public void setUp() throws Exception{
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(3);
        dao.getProductList().add(testClone);
    }

    @Test
    public void testGetProductList() throws Exception {
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);

        assertEquals(6, dao.getProductList().size(), "Should have 6 product");
        assertFalse(dao.getProductList().contains(testClone), "Should not have Almonds");
    }

    @Test
    public void testGetSelectedProduct() {
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);

        VendingMachineProduct shouldBeAlmonds = dao.getSelectedProduct(1);
        assertNotNull(1, "Getting index 0 should not be null");
        assertNotEquals(testClone, shouldBeAlmonds, "Product in index 0 should not be Almonds");
    }

    @Test
    public void testSubtractProduct() throws Exception {
        dao.subtractProduct(dao.getSelectedProduct(1));

        assertNotEquals(8, dao.getSelectedProduct(1).getInventoryCount(), "Inventory count should not be 8 after purchase");
    }

}
