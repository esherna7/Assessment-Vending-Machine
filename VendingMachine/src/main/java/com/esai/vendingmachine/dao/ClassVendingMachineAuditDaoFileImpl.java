package com.esai.vendingmachine.dao;

import java.io.*;
import java.time.LocalDateTime;

/**
 *
 * @author Esai
 */
public class ClassVendingMachineAuditDaoFileImpl implements ClassVendingMachineAuditDao {

    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch(IOException e){
            throw new ClassVendingMachinePersistenceException("Could not persist audit file", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
