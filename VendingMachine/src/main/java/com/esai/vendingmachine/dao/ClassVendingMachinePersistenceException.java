package com.esai.vendingmachine.dao;

/**
 *
 * @author Esai
 */
public class ClassVendingMachinePersistenceException extends Exception{
    public ClassVendingMachinePersistenceException(String message){
        super(message);
    }
    
    public ClassVendingMachinePersistenceException(String message, Throwable cause){
        super(message, cause);
    }
}
