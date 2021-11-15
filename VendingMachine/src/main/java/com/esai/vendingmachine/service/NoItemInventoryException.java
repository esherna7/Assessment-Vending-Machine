package com.esai.vendingmachine.service;

/**
 *
 * @author Esai
 */
public class NoItemInventoryException extends Exception{
    
    public NoItemInventoryException(String message){
        super(message);
    }
    
    public NoItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
