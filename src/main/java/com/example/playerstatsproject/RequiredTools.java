package com.example.playerstatsproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequiredTools {
    List<Player> PlayerList;

    public RequiredTools(List<Player> playerList) {
        PlayerList = playerList;
    }

    public Player ByName(String name) {
        for (Player player : PlayerList) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    public List<Player> ByCluborCountry(String country, String club) {
        List<Player> srch = new ArrayList<>();
        for (Player pl : PlayerList) {
            if (pl.getCountry().equalsIgnoreCase(country)) {
                if (club.equalsIgnoreCase("ANY") || pl.getClub().equalsIgnoreCase(club)) {
                    srch.add(pl);
                }
            }
        }
        return srch;
    }
    public List<Player> ByClub(String club) {
        List<Player> srch = new ArrayList<>();
        for (Player pl : PlayerList) {
            if (pl.getClub().equalsIgnoreCase(club)) {
                    srch.add(pl);
            }
        }
        return srch;
    }

    public List<Player> ByPosition(String position) {
        List<Player> srch = new ArrayList<>();
        for (Player pl : PlayerList) {
            if (pl.getPosition().equalsIgnoreCase(position)) {
                srch.add(pl);
            }
        }
        return srch;
    }

    public List<Player> BySalaryRange(long range1, long range2) {
        List<Player> srch = new ArrayList<>();
        if (range1 > range2) {
            long t = range1;
            range1 = range2;
            range2 = t;
        }
        for (Player pl : PlayerList) {

            if (pl.getWeeklySalary() >= range1 && pl.getWeeklySalary() <= range2) {
                srch.add(pl);
            }
        }
        return srch;
    }

    public HashMap<String, Integer> ByCountrywisePlayerCount() {
        HashMap<String, Integer> map = new HashMap<>();
        for (Player pl : PlayerList) {
            if (map.containsKey(pl.getCountry().toUpperCase())) {
                map.put(pl.getCountry().toUpperCase(), map.get(pl.getCountry().toUpperCase()) + 1);
            } else {
                map.put(pl.getCountry().toUpperCase(), 1);
            }
        }
        return map;
    }

    public List<Player> PlayerwiththeMaximumSalary(String club) {
        List<Player> srch = new ArrayList<>();
        long wksalary = -1;
        for (Player player : PlayerList) {
            if (player.getClub().equalsIgnoreCase(club) && player.getWeeklySalary() > wksalary) {
                wksalary = player.getWeeklySalary();
            }
        }
        for (Player player : PlayerList) {
            if (player.getClub().equalsIgnoreCase(club) && player.getWeeklySalary() == wksalary) {
                srch.add(player);
            }
        }
        return srch;
    }

    public List<Player> PlayerwiththeMaximumAge(String club) {
        List<Player> srch = new ArrayList<>();
        int age = -1;
        for (Player player : PlayerList) {
            if (player.getClub().equalsIgnoreCase(club) && player.getAge() > age) {
                age = player.getAge();
            }
        }
        for (Player player : PlayerList) {
            if (player.getClub().equalsIgnoreCase(club) && player.getAge() == age) {
                srch.add(player);
            }
        }
        return srch;
    }

    public List<Player> PlayerwiththeMaximumHeight(String club) {
        List<Player> srch = new ArrayList<>();
        double height = -1;
        for (Player player : PlayerList) {
            if (player.getClub().equalsIgnoreCase(club) && player.getHeight() > height) {
                height = player.getHeight();
            }
        }
        for (Player player : PlayerList) {
            if (player.getClub().equalsIgnoreCase(club) && player.getHeight() == height) {
                srch.add(player);
            }
        }
        return srch;
    }
    public long TotalYearlySalary(String club) {
        long salary = 0;
        for (Player player : PlayerList) {
            if(club.equalsIgnoreCase(player.getClub()))
            salary += player.getWeeklySalary();
        }
        return salary*52;
    }
    public Player addPlayer(String name,String country,int age,double height,String club,String position,int jerseynumber,long weeklysalary) {
        Player add=new Player();

        for (Player player : PlayerList) {
            if (player.getName().equalsIgnoreCase(name)) {
                return null;
            }
        }
        try {
            add.setName(name);
            add.setCountry(country);
            add.setAge(age);
            add.setHeight(height);
            add.setClub(club);
            add.setPosition(position);
            add.setJerseyNumber(jerseynumber);
            add.setWeeklySalary(weeklysalary);
        }catch (Exception e)
        {
            System.out.println(e);
        }

        return add;
    }

    public boolean ConvertToBillion(long weeklySalary) {
        long ans = weeklySalary / 1000000000;
        if (ans > 0) {
            return true;
        }
        else
        {
            return false;
        }
    }public boolean ConvertToMillion(long weeklySalary) {
        long ans = weeklySalary / 1000000;
        if (ans > 0) {
            return true;
        }
        else
        {
            return false;
        }
    }public boolean ConvertToThousand(long weeklySalary) {
        long ans = weeklySalary / 1000;
        if (ans > 0) {
            return true;
        }
        else
        {
            return false;
        }
    }
}
