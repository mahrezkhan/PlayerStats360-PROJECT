package com.example.playerstatsproject;

import java.io.Serializable;

public class Player implements Serializable{
    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String Club;
    private String Position;
    private int JerseyNumber;
    private long WeeklySalary;
    private String Status;
    private String Buyer;

    Player() {
        Name = Country = Club = Position =Status= null;
        Age = JerseyNumber =  -1;
        Height = -1;
        WeeklySalary=-1;
    }
    Player(String name,String country,int age,double height,String club,String position,int jerseynumber,long weeklysalary)
    {
        Name=name;
        Country=country;
        Age=age;
        Height=height;
        Club=club;
        Position=position;
        JerseyNumber=jerseynumber;
        WeeklySalary=weeklysalary;
    }
    String getName() {
        return Name;
    }

    String getCountry() {
        return Country;
    }

    int getAge() {
        return Age;
    }

    double getHeight() {
        return Height;
    }

    String getClub() {
        return Club;
    }

    String getPosition() {
        return Position;
    }

    int getJerseyNumber() {
        return JerseyNumber;
    }

    long getWeeklySalary() {
        return WeeklySalary;
    }
    public String getBuyer() {
        return Buyer;
    }

    void setName(String name) {
        Name = name;
    }

    void setCountry(String country) {
        Country = country;
    }

    void setAge(int age) {
        Age = age;
    }

    void setHeight(double height) {
        Height = height;
    }

    void setClub(String club) {
        Club = club;
    }

    void setPosition(String position) {
        Position = position;
    }

    void setJerseyNumber(int jerseynumber) {
        JerseyNumber = jerseynumber;
    }

    void setWeeklySalary(long weeklysalary) {
        WeeklySalary = weeklysalary;
    }
    public void setStatus(String sell) {
        Status=sell;
    }
    public String getStatus()
    {
        return Status;
    }

    public void setBuyer(String buyer) {
        this.Buyer = buyer;
    }
    @Override
    public String toString()
    {
        if(JerseyNumber==-1)
        {
            return Name+" "+Country+" "+Age+" "+Height+" "+Club+" "+Position+" "+"Unavailable"+" "+WeeklySalary;

        }
        return Name+" "+Country+" "+Age+" "+Height+" "+Club+" "+Position+" "+JerseyNumber+" "+WeeklySalary;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Player)) return false;
        Player player = (Player) obj;
        return Name.equals(player.Name) && Country.equals(player.Country) && Age==player.Age && Height==player.Height && Club.equals(player.Club) && Position.equals(player.Position) && JerseyNumber==player.JerseyNumber && WeeklySalary==player.WeeklySalary;
    }


}