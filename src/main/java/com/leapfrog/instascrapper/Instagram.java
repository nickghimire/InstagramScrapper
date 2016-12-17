/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.instascrapper;

import okhttp3.*;
import com.google.gson.Gson;
import com.leapfrog.instascrapper.exception.InstagramException;
import com.leapfrog.instascrapper.exception.InstagramNotFoundException;
import com.leapfrog.instascrapper.model.Account;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

public class Instagram {

    private OkHttpClient httpClient;
    private Gson gson;

    public Instagram() {
        this(new OkHttpClient());
    }

    public Instagram(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        this.gson = new Gson();
    }

    public Instagram(String proxyHost, int proxyPort) {
        this(new OkHttpClient.Builder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                .build());
    }

    public Instagram(String proxyHost, int proxyPort, final String username, final String password) {
        Authenticator proxyAuthenticator = new Authenticator() {
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }
        };
        this.httpClient = new OkHttpClient.Builder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                .proxyAuthenticator(proxyAuthenticator).build();
        this.gson = new Gson();
    }

    public Account getAccountByUsername(String username) throws IOException, InstagramException, InstagramNotFoundException {

        Request request = new Request.Builder()
                .url(Endpoint.getAccountJsonInfoLinkByUsername(username)).build();

        Response response = this.httpClient.newCall(request).execute();

        if (response.code() == 404) {
            throw new InstagramNotFoundException("Account with given username doesnot exist");
        }
        if (response.code() != 200) {
            throw new InstagramException("Response Code is not equal 200");
        }
        String jsonString = response.body().string();
        Map userJson = gson.fromJson(jsonString, Map.class);
        return Account.fromAccountPage((Map) userJson.get("user"));

    }

    private String generateRandomString(int length) {
        char[] characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random random = new SecureRandom();
        int characterslength = characters.length;
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            randomString.append(characters[random.nextInt(characterslength)]);
        }
        return randomString.toString();
    }
    
    private Request getApiRequest(String params){
        String random=generateRandomString(10);
        RequestBody formBody = new FormBody.Builder()
                .add("q", params)
                .build();
        
                return new Request.Builder()
                        .url(Endpoint.INSTAGRAM_QUERY_URL)
                        .post(formBody)
                        .header("Cookie", String.format("csrftoken=%s;", random))
                        .header("Referer", "https://www.instagram.com/")
                        .build();
    }
}
