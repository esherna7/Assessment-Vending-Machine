package com.esai.vendingmachine.dao;

/**
 *
 * @author Esai
 */
public class ClassVendingMachineDaoException extends Exception{
    
    public ClassVendingMachineDaoException(String message){
        super(message);
    }
    
    public ClassVendingMachineDaoException(String message, Throwable cause){
        super(message, cause);
    }
}
