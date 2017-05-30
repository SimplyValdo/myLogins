package com.lasanimas.simplyvaldo.mylogins.Model;

public class ProfilesDB
{
    private String name;
    private String lastName;
    private String dateCreation;
    private String relationship;

    private CategoriesHelper categories;

    public ProfilesDB(){

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

    public CategoriesHelper getCategories() {
        return categories;
    }

    public void setCategories(CategoriesHelper categories) {
        this.categories = categories;
    }
}

