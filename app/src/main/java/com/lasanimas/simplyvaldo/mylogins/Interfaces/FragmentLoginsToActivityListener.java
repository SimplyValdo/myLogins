package com.lasanimas.simplyvaldo.mylogins.Interfaces;

public interface FragmentLoginsToActivityListener
{
    public void SendProfileName(String id, String name);
    public void SendLoginName(String id, String currentLoginName, String type, int tab);
}
