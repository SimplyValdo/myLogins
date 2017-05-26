package com.lasanimas.simplyvaldo.mylogins.Model;


public class currentProfileLogins
{
    private emailDB email;
    private socialDB social;

    public currentProfileLogins()
    {

    }

    public emailDB getEmail() {
        return email;
    }

    public void setEmail(emailDB email) {
        this.email = email;
    }

    public socialDB getSocial() {
        return social;
    }

    public void setSocial(socialDB social) {
        this.social = social;
    }
}
