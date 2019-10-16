package com.example.monopolymoneytracker;

public class Accounter {

    private static final float percent = (float) 0.10;

    public Accounter(){
    }

    public int updatePayment(int total, int payValue){


        if(total > payValue) {
            total -= payValue;
        }else{
            total = 0;
        }

        return total;
    }

    public int updateIncome(int total, int payday){

        total +=payday;

        return total;
    }

    public int getTenPercent(int total){

        int amount = (int) (total * percent);
        return amount;
    }
}
