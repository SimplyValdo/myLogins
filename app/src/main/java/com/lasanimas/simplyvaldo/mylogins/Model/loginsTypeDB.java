package com.lasanimas.simplyvaldo.mylogins.Model;


import java.util.HashMap;

public class loginsTypeDB
{
    private HashMap<String, emailDB> email;

    public loginsTypeDB()
    {

    }

    public HashMap<String, emailDB> getEmail() {
        return email;
    }

    public void setEmail(HashMap<String, emailDB> email) {
        this.email = email;
    }
}
