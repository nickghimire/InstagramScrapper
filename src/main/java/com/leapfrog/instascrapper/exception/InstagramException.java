/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.instascrapper.exception;

/**
 *
 * @author apple
 */
public class InstagramException  extends Exception{
    
    public InstagramException(){
        
    }
    public InstagramException(String message){
        super(message);
    }
    
}
