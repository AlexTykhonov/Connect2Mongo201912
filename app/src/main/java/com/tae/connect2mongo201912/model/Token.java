
package com.tae.connect2mongo201912.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

//package com.tae.androidclientspring_boot_webflux_security.model;
//
//public class Token {
//
//   private  String token;
//
//    public Token(String token) {
//        this.token = token;
//    }
//
//    public Token() {
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//}
