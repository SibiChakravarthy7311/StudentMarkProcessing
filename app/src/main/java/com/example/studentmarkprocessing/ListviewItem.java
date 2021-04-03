package com.example.studentmarkprocessing;


import android.content.Context;

public class ListviewItem{
    String roll,name,ccet1,ccet2,ccet3,sem;

    public ListviewItem(String roll, String name, String ccet1, String ccet2, String ccet3, String sem) {
        this.roll = roll;
        this.name = name;
        this.ccet1 = ccet1;
        this.ccet2 = ccet2;
        this.ccet3 = ccet3;
        this.sem = sem;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCcet1() {
        return ccet1;
    }

    public void setCcet1(String ccet1) {
        this.ccet1 = ccet1;
    }

    public String getCcet2() {
        return ccet2;
    }

    public void setCcet2(String ccet2) {
        this.ccet2 = ccet2;
    }

    public String getCcet3() {
        return ccet3;
    }

    public void setCcet3(String ccet3) {
        this.ccet3 = ccet3;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
}
