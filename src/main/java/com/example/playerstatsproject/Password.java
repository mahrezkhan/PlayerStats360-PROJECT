package com.example.playerstatsproject;

public class Password {
    String Clubname;
    String password;

    public Password(String clubname, String password) {
        Clubname = clubname;
        this.password = password;
    }

    public String getClubname() {
        return Clubname;
    }

    public void setClubname(String clubname) {
        Clubname = clubname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString()
    {
        return Clubname+" "+password;
    }

}
