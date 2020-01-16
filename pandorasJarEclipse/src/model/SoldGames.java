package model;

import utility.Acquisto;

import java.util.ArrayList;
import java.util.TreeMap;

public class SoldGames
{
    TreeMap<Integer,Integer> soldGPerYear;
    TreeMap<Integer,Double> earnedMoneyPerYear;

    public TreeMap<Integer, Integer> getSoldGPerYear() {
        return soldGPerYear;
    }

    public void setSoldGPerYear(TreeMap<Integer, Integer> soldGPerYear) {
        this.soldGPerYear = soldGPerYear;
    }

    public TreeMap<Integer, Double> getEarnedMoneyPerYear() {
        return earnedMoneyPerYear;
    }

    public void setEarnedMoneyPerYear(TreeMap<Integer, Double> earnedMoneyPerYear) {
        this.earnedMoneyPerYear = earnedMoneyPerYear;
    }
}
