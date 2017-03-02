package com.test.demo.myapp.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.test.demo.myapp.data.TokenObject;

import java.util.HashMap;

public class TokenService extends Service{

    private HashMap<Integer, TokenObject> tokenMap;

    public TokenService(@Nullable HashMap<Integer, TokenObject> tokenMap){
        this.tokenMap = tokenMap;
        if(tokenMap == null){
            tokenMap = new HashMap<>();
        }
    }

    public TokenObject getTokenForId(int id){
        if(tokenMap.containsKey(id) && tokenMap.get(id).isUsed()){
            return getNewTokenFromNetwork(id);
        }

        if(tokenMap.containsKey(id) && tokenMap.get(id).isUsable()){
            return tokenMap.get(id);
        }

        return null;
    }

    public TokenObject getNewTokenFromNetwork(int id){
        //This is from "the network"
        TokenObject tokenObject = new TokenObject(id, "randomTokenString");
        tokenMap.put(tokenObject.getId(), tokenObject);
        return tokenObject;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
