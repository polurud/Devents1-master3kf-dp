package com.dartmouth.kd.devents;

import java.util.Calendar;

/**
 * Created by kathrynflattum on 2/25/18.
 */

public class CampusEvent {

    private Long mId;
    private String Title;
    private String Location;
    private String Description;
    private String Date;
    private String Start;
    private String End;
    private String URL;
    private Double Latitude;
    private Double Longitude;
    private int Food;
    private int EventType;
    private int ProgramType;
    private int Year;
    private int Major;
    private int GreekSociety;
    private int Gender;

    public CampusEvent(){
        this.Title = "";
        this.Location = "";
        this.Description = "";
        this.Date = "";
        this.Start = "";
        this.End = "";
        this.URL = "";
        this.Latitude = null;
        this.Longitude = null;
        this.Food = 0;
        this.EventType = 0;
        this.ProgramType = 0;
        this.Year = 0;
        this.Major = 0;
        this.Gender = 0;
        this.GreekSociety=0;


    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long id) {
        this.mId = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    //@Exclude
    public String getDate() {
        return Date;
    }

    public void setDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar date = Calendar.getInstance();
        date.set(year, monthOfYear, dayOfMonth);
        Date = date.toString();
    }


    public long getDateTimeInMillis() {
        long mDate = 0;
        return mDate;
    }


    public void setStart(int hourOfDay, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(0,0,0,hourOfDay,minute);
        Start = date.toString();
    }

    public void setStart(String start){
        this.Start = start;
    }

    public void setDateTime(String date){
        this.Date = date;
    }

    public String getmStart() {
        return Start;
    }


    public void setEnd(int hourOfDay, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(0,0,0,hourOfDay,minute);
        End = date.toString();
    }

    public void setEnd(String end){
        this.End = end;
    }

    public String getmEnd() {
        return End;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getFood(){
        return Food;
    }

    public void setFood(int Food){
        this.Food = Food;
    }

    public int getEventType() {
        return EventType;
    }

    public void setEventType(int EventType) {
        this.EventType = EventType;
    }

    public int getProgramType() {
        return ProgramType;
    }

    public void setProgramType(int ProgramType) {
        this.ProgramType = ProgramType;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public int getMajor() {
        return Major;
    }

    public void setMajor(int Major) {
        this.Major = Major;
    }

    public int getGreekSociety() {
        return GreekSociety;
    }

    public void setGreekSociety(int GreekSociety) {
        this.GreekSociety = GreekSociety;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

}