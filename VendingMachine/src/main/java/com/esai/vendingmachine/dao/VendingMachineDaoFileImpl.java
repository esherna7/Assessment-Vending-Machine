package com.esai.vendingmachine.dao;

import com.esai.vendingmachine.dto.VendingMachineProduct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;

/**
 *
 * @author Esai
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    // holds vendingmachine.txt
    public static final String VENDING_MACHINE = "vendingmachine.txt";
    // Delimiter
    public static final String DELIMITER = "::";
    // list of vending machine products
    ArrayList<VendingMachineProduct> productList = new ArrayList<VendingMachineProduct>();
    // allows access to write audit
    private ClassVendingMachineAuditDao auditDao = new ClassVendingMachineAuditDaoFileImpl();
    
    // return productList inventory in arraylist
    @Override
    public ArrayList<VendingMachineProduct> getProductList() throws ClassVendingMachineDaoException {
        productList = new ArrayList<VendingMachineProduct>();
        loadProduct();
        return productList;
    }

    // return selected vending machine item
    @Override
    public VendingMachineProduct getSelectedProduct(int selection) {
        return productList.get(--selection);
    }

    // subtract product inventory
    @Override
    public void subtractProduct(VendingMachineProduct productToSubtract) throws ClassVendingMachineDaoException, ClassVendingMachinePersistenceException {
        // reset product List
        productList = new ArrayList<VendingMachineProduct>();
        loadProduct();
        // subtract product inventory
        for (int x = 0; x < productList.size(); x++) {
            if (productList.get(x).getProductName().equals(productToSubtract.getProductName())) {
                productList.get(x).setInventoryCount(productList.get(x).getInventoryCount() - 1);
                writeProduct();
                auditDao.writeAuditEntry(productList.get(x).getProductName());
                break;
            }
        }
    }

    // translate object in file to a vending machine item object
    // splits on delimiter ::
    public VendingMachineProduct unmarshallProduct(String vendingMachineProduct) {
        // split vending machine items info from file to array via :: delimiter
        String[] vendingMachineItemTokens = vendingMachineProduct.split(DELIMITER);

        // create new vending machine item with info from vendingMachineItemTokens
        VendingMachineProduct productFromFile = new VendingMachineProduct();
        productFromFile.setProductName(vendingMachineItemTokens[0]);
        productFromFile.setProductPrice(vendingMachineItemTokens[1]);
        productFromFile.setInventoryCount(Integer.parseInt(vendingMachineItemTokens[2]));

        // return created product
        return productFromFile;
    }

    // reads VendingMachineProducts into memory
    private void loadProduct() throws ClassVendingMachineDaoException {
        Scanner scanner;

        // try catch block to throw error if file not found
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(VENDING_MACHINE)));
        } catch (FileNotFoundException e) {
            throw new ClassVendingMachineDaoException("Could not load Vending Machine Products into memory.", e);
        }

        String currentLine;
        VendingMachineProduct currentProduct;

        // Go line by line in VendingMachine.txt and unmarshall line into VendingMachineProduct
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            productList.add(currentProduct);
        }
        //close scanner
        scanner.close();
    }

    // transform Product information in memory to line of text to be written
    private String marshallProduct(VendingMachineProduct product) {
        String productText = product.getProductName() + DELIMITER;
        productText += product.getPrice() + DELIMITER;
        productText += product.getInventoryCount();

        return productText;
    }

    // write current arraylist of products to VENDING_MACHINE file
    private void writeProduct() throws ClassVendingMachineDaoException {
        PrintWriter out;

        // try catch block throws error if file isnt found
        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE));
        } catch (IOException e) {
            throw new ClassVendingMachineDaoException("Could not save product data.", e);
        }

        String productText;
        // transform product info to text
        for (VendingMachineProduct currentProduct : productList) {
            productText = marshallProduct(currentProduct);
            // write text to file
            out.println(productText);
            out.flush();
        }
        // close PrintWriter
        out.close();
    }

}
