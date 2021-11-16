/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esai.vendingmachine.dao;

import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.io.FileWriter;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author herna
 */
public class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImplTest() {
    }

    @Test
    public void testGetProductList() throws Exception {
        // Assign testDao to testFile
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);

        // Create test clones
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);
        VendingMachineProduct testClone2 = new VendingMachineProduct();
        testClone2.setProductName("Snickers");
        testClone2.setProductPrice("2.00");
        testClone2.setInventoryCount(4);

        // Assert
        assertEquals(2, testDao.getProductList().size(), "Should have 2 products");
        assertTrue(testDao.getProductList().get(0).getProductName().equals(testClone.getProductName()), "Should have Almonds");
        assertTrue(testDao.getProductList().get(1).getProductName().equals(testClone2.getProductName()), "Should have Snickers");
    }

    @Test
    public void testGetSelectedProduct() throws Exception {
        // Assign testDao to testFile
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);
        testDao.loadProduct();

        // Create test Clones
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);

        // Assert
        VendingMachineProduct shouldBeAlmonds = testDao.getSelectedProduct(1);
        assertNotNull(shouldBeAlmonds, "Getting index 0 should not be null");
        assertEquals(shouldBeAlmonds.getProductName(), testDao.getSelectedProduct(1).getProductName(), "shouldBeAlmonds should be Almonds");
        assertNotEquals(testClone.getProductName(), testDao.getSelectedProduct(2).getProductName(), "Product in index 1 should not be Almonds but snickers");
    }

    @Test
    public void testSubtractProduct() throws Exception {
        // Assign testDao to testFile
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);
        testDao.loadProduct();

        // subtract inventory from snickers
        testDao.subtractProduct(testDao.getSelectedProduct(1));

        // Assert
        assertNotEquals(4, testDao.getSelectedProduct(1).getInventoryCount(), "Inventory count should not be 4 after purchase");
    }

    @Test
    public void testUnmarshallProduct() throws Exception {
        // Assign testDao to testFile
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);

        // create sample text file to split
        String testText = "Almonds::2.00::4";

        // create new vending machine item with info from productText
        VendingMachineProduct productTest = testDao.unmarshallProduct(testText);

        // Assert
        assertEquals(productTest.getProductName(), productTest.getProductName(), "The split text should be Almonds");
        assertEquals(productTest.getInventoryCount(), productTest.getInventoryCount(), "The split text inventory count should be 4");
        assertEquals(productTest.getPrice(), productTest.getPrice(), "The split text price should be 2.00");
    }

    @Test
    public void testLoadProduct() throws Exception {
        // Assign testDao to testFile
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);

        // create testClones
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Almonds");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);
        VendingMachineProduct testClone2 = new VendingMachineProduct();
        testClone2.setProductName("Snickers");
        testClone2.setProductPrice("2.00");
        testClone2.setInventoryCount(4);

        // Result
        ArrayList<VendingMachineProduct> resultList = testDao.loadProduct();
        // Expected Results
        ArrayList<VendingMachineProduct> expResultList = new ArrayList<VendingMachineProduct>();
        expResultList.add(testClone);
        expResultList.add(testClone2);

        // Assert
        assertEquals(expResultList.size(), resultList.size(), "Test Products loaded from File");
    }

    @Test
    public void testMarshallProduct() throws Exception {
        // Assign testDao to testFile
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);

        // create testClones
        VendingMachineProduct testClone = new VendingMachineProduct();
        testClone.setProductName("Lollipop");
        testClone.setProductPrice("2.00");
        testClone.setInventoryCount(4);

        String newMarshallText = testDao.marshallProduct(testClone);

        // Assert
        assertEquals(newMarshallText, "Lollipop::2.00::4", "New marshalled text should be reset to file variation");
    }

    @Test
    public void testWriteProduct() throws Exception{
        // Assign testDao to testFile
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoFileImpl(testFile);
        testDao.loadProduct();
        testDao.writeProduct();
        
        assertEquals(testDao.getProductList().size(), 2, "Should have 2 products in list file");
    }

}
