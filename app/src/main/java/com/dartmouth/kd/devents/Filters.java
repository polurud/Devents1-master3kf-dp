package com.dartmouth.kd.devents;

/**
 * Created by kathrynflattum on 3/3/18.
 */

public class Filters {
    private Long mId;
    private int food;
    private int eventType;
    private int programType;
    private int year;
    private int major;
    private int greekSociety;
    private int gender;


    public Filters(){
        this.food = 0;
        this.eventType = 0;
        this.programType=0;
        this.year=0;
        this.major=0;
        this.gender=0;
        this.greekSociety=0;

    }


    public Long getfId() {
        return mId;
    }

    public void setfId(Long id) {
        this.mId = id;
    }

    public int getfFood(){
        return food;
    }

    public void setfFood(int food){
        this.food = food;
    }

    public int getfEventType() {
        return eventType;
    }

    public void setfEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getfProgramType() {
        return programType;
    }

    public void setfProgramType(int programType) {
        this.programType = programType;
    }

    public int getfYear() {
        return year;
    }

    public void setfYear(int year) {
        this.year = year;
    }

    public int getfMajor() {
        return major;
    }

    public void setfMajor(int major) {
        this.major = major;
    }

    public int getfGreekSociety() {
        return greekSociety;
    }

    public void setfGreekSociety(int greekSociety) {
        this.greekSociety = greekSociety;
    }

    public int getfGender() {
        return gender;
    }

    public void setfGender(int gender) {
        this.gender = gender;
    }
}
