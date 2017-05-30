package com.lasanimas.simplyvaldo.mylogins.Model;

import java.util.HashMap;

public class CategoriesHelper
{
    private HashMap<String, EmailDB> email;
    private HashMap<String, SocialDB> social;
    private HashMap<String, MembershipDB> membership;
    private HashMap<String, MediaDB> media;
    private HashMap<String, BankDB> bank;
    private HashMap<String, DevicesDB> devices;
    private HashMap<String, OtherDB > other;

    public CategoriesHelper() {
    }

    public HashMap<String, EmailDB> getEmail() {
        return email;
    }

    public void setEmail(HashMap<String, EmailDB> email) {
        this.email = email;
    }

    public HashMap<String, SocialDB> getSocial() {
        return social;
    }

    public void setSocial(HashMap<String, SocialDB> social) {
        this.social = social;
    }

    public HashMap<String, MembershipDB> getMembership() {
        return membership;
    }

    public void setMembership(HashMap<String, MembershipDB> membership) {
        this.membership = membership;
    }

    public HashMap<String, MediaDB> getMedia() {
        return media;
    }

    public void setMedia(HashMap<String, MediaDB> media) {
        this.media = media;
    }

    public HashMap<String, BankDB> getBank() {
        return bank;
    }

    public void setBank(HashMap<String, BankDB> bank) {
        this.bank = bank;
    }

    public HashMap<String, DevicesDB> getDevices() {
        return devices;
    }

    public void setDevices(HashMap<String, DevicesDB> devices) {
        this.devices = devices;
    }

    public HashMap<String, OtherDB> getOther() {
        return other;
    }

    public void setOther(HashMap<String, OtherDB> other) {
        this.other = other;
    }
}
