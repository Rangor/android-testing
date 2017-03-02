package com.test.demo.myapp.data;


public class TokenObject {

    private boolean hasBeenSent;
    private boolean hasBeenRecieved;
    private String token;
    private int id;

    public TokenObject(int id, String token) {
        this.id = id;
        this.token = token;
        hasBeenSent = false;
        hasBeenRecieved = false;
    }

    public void setHasBeenSent(boolean hasBeenSent) {
        this.hasBeenSent = hasBeenSent;
    }

    public void setHasBeenRecieved(boolean hasBeenRecieved) {
        this.hasBeenRecieved = hasBeenRecieved;
    }

    public boolean isUsed() {
        return hasBeenSent && hasBeenRecieved;
    }

    public boolean isUsable(){
        return !hasBeenSent && !hasBeenRecieved;
    }

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }
}
