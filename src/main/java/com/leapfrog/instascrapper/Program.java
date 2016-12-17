/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.instascrapper;

import com.leapfrog.instascrapper.exception.InstagramException;
import com.leapfrog.instascrapper.exception.InstagramNotFoundException;
import com.leapfrog.instascrapper.model.Account;
import java.io.IOException;

/**
 *
 * @author apple
 */
public class Program {

    public static void main(String[] args) throws IOException, InstagramException, InstagramNotFoundException {
        Instagram instagram = new Instagram();
        Account account = instagram.getAccountByUsername("nickghimire");
                System.out.println("username:" + account.username);

        System.out.println("Full Name:" + account.fullName);
        System.out.println("biography:" + account.biography);
        System.out.println("followsCount:" + account.followsCount);
        System.out.println("followedByCount:" + account.followedByCount);
        System.out.println("mediaCount:" + account.mediaCount);
        System.out.println("isPrivate:"+account.isPrivate);
        

    }

}
