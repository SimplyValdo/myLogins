package com.example.simplyvaldo.mylogins;

public class ProfilesDB
{
    private String Name;
    private String lastName;
    private String dateCreation;
    private String relationship;

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

    ProfilesDB(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}

