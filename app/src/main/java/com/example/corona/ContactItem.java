package com.example.corona;

public class ContactItem {
    private String state;
    private String number;

    public ContactItem(String mstate, String mnumber){
        state=mstate;
        number=mnumber;
    }

    public String getState(){
        return state;
    }
    public String getNumber(){
        return number;
    }
}
