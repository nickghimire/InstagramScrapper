/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.instascrapper;

/**
 *
 * @author apple
 */
public class Endpoint {
    public static final String BASE_URL ="https://www.instagram.com";
    public static final String ACCOUNT_JSON_INFO = "https://www.instagram.com/{{username}}/?__a=1";
   
    public static final String INSTAGRAM_QUERY_URL = "https://www.instagram.com/query/";
    
    public static String getAccountJsonInfoLinkByUsername(String username){
        return ACCOUNT_JSON_INFO.replace("{{username}}", username);
    }
}
