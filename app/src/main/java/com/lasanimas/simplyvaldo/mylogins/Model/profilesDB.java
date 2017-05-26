package com.example.simplyvaldo.mylogins.Model;

import com.google.firebase.database.PropertyName;

public class profilesDB
{
    private String name;
    private String lastName;
    private String dateCreation;
    private String relationship;

    private loginsTypeDB types;

    public profilesDB(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public loginsTypeDB getTypes() {
        return types;
    }

    public void setTypes(loginsTypeDB types) {
        this.types = types;
    }
}

