package com.esai.vendingmachine.dao;

/**
 *
 * @author Esai
 */
public interface ClassVendingMachineAuditDao {
    
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException;
}
